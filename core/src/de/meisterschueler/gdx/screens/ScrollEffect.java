package de.meisterschueler.gdx.screens;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;

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

	private class Background extends Actor {

		private ShapeRenderer shapeRenderer;

		public Background() {
			shapeRenderer = new ShapeRenderer();
		}

		@Override
		public void draw(Batch batch, float parentAlpha) {
			batch.end();

			int velocitySum = 0;
			for (Bubble bubble : bubbles) {
				velocitySum += bubble.noteOn.getVelocity();
			}
			int meanVelocity = (bubbles.size() > 0) ? velocitySum / bubbles.size() : 0;

			shapeRenderer.begin(ShapeType.Filled);
			float rectHight = HEIGHT / 128f;
			for (Bubble bubble : bubbles) {
				Color color = Utils.getSpectralColor(bubble.noteOn.getVelocity(), meanVelocity - 20, meanVelocity + 20, 5, 1);
				shapeRenderer.setColor(color);

				if (bubble.noteOff == null) {
					shapeRenderer.rect(bubble.fromX, bubble.y, WIDTH - bubble.fromX, rectHight);
				} else {
					shapeRenderer.rect(bubble.fromX, bubble.y, bubble.toX - bubble.fromX, rectHight);
				}

				bubble.fromX -= bubbleSpeed * Gdx.graphics.getDeltaTime();
				bubble.toX -= bubbleSpeed * Gdx.graphics.getDeltaTime();

				if (bubble.noteOff != null && bubble.toX < 0) {
					bubbles.remove(bubble);
				}
			}
			shapeRenderer.end();

			batch.begin();
		}
	}

	private Background background;

	List<Bubble> bubbles = new CopyOnWriteArrayList<Bubble>();
	private float bubbleSpeed = 100;

	public ScrollEffect() {
		super();

		background = new Background();
		gameGroup.addActor(background);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		updateFPS();

		stage.act(delta);
		stage.draw();
	}

	@Override
	public void onMidiNoteOn(NoteOn noteOn) {
		System.out.println("NoteOn " + noteOn.getNote());

		Bubble bubble = new Bubble(noteOn);
		bubble.fromX = WIDTH;
		bubble.y = (int) (HEIGHT * bubble.noteOn.getNote() / 128f);
		bubbles.add(bubble);
	}

	@Override
	public void onMidiNoteOff(NoteOff noteOff) {
		System.out.println("NoteOff " + noteOff.getNote());

		for (int i = bubbles.size() - 1; i >= 0; i--) {
			Bubble bubble = bubbles.get(i);
			if (bubble.noteOn.getNote() == noteOff.getNote() && bubble.noteOff == null) {
				bubble.toX = WIDTH;
				bubble.noteOff = noteOff;
				return;
			}
		}
	}
}
