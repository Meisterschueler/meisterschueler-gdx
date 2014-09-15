package de.meisterschueler.gdx.screens;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import de.meisterschueler.basic.NoteOff;
import de.meisterschueler.basic.NoteOn;
import de.meisterschueler.gdx.Utils;

public class ScrollEffect extends MidiScreen {

	public class Bubble {
		public Bubble(NoteOn noteOn) {
			this.noteOn = noteOn;
		}

		private int fromX;
		private int toX;
		private int y;
		NoteOn noteOn;
		NoteOff noteOff;
	}

	List<Bubble> bubbles = new CopyOnWriteArrayList<Bubble>();
	private float bubbleSpeed = 100;
	
	private ShapeRenderer shapeRenderer;
	private SpriteBatch spriteBatch;
	private BitmapFont font;
	
	public ScrollEffect() {
		shapeRenderer = new ShapeRenderer();
		spriteBatch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.WHITE);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		int velocitySum = 0;
		for (Bubble bubble : bubbles) {
			velocitySum += bubble.noteOn.getVelocity();
		}
		int meanVelocity = (bubbles.size() > 0) ? velocitySum / bubbles.size() : 0;
		
		float rectHight = Gdx.graphics.getHeight()/128f;
		for (Bubble bubble : bubbles) {
			shapeRenderer.begin(ShapeType.Filled);
			
			Color color = Utils.getSpectralColor(bubble.noteOn.getVelocity(), meanVelocity-20, meanVelocity+20, 5, 1);			
			shapeRenderer.setColor(color);
			
			if (bubble.noteOff == null) {
				shapeRenderer.rect(bubble.fromX, bubble.y, Gdx.graphics.getWidth()-bubble.fromX, rectHight);
			} else {
				shapeRenderer.rect(bubble.fromX, bubble.y, bubble.toX-bubble.fromX, rectHight);
			}
			
			shapeRenderer.end();

			bubble.fromX -= bubbleSpeed*Gdx.graphics.getDeltaTime();
			bubble.toX -= bubbleSpeed*Gdx.graphics.getDeltaTime();
			
			if (bubble.noteOff != null && bubble.toX < 0) {
				bubbles.remove(bubble);
			}
		}
		
		spriteBatch.begin();
		font.draw(spriteBatch, "ScrollEffect", 20, 20);
		spriteBatch.end();
	}

	@Override
	public void onMidiNoteOn(NoteOn noteOn) {
		Bubble bubble = new Bubble(noteOn);
		bubble.fromX = Gdx.graphics.getWidth();
		bubble.y = (int) (Gdx.graphics.getHeight()*bubble.noteOn.getNote()/128f);
		bubbles.add(bubble);
	}

	@Override
	public void onMidiNoteOff(NoteOff noteOff) {
		for (int i=bubbles.size()-1; i>=0; i--) {
			Bubble bubble = bubbles.get(i);
			if (bubble.noteOn.getNote() == noteOff.getNote() && bubble.noteOff == null) {
				bubble.toX = Gdx.graphics.getWidth();
				bubble.noteOff = noteOff;
				return;
			}
		}
	}
}
