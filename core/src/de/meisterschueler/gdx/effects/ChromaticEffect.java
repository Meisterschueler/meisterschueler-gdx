package de.meisterschueler.gdx.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import de.meisterschueler.basic.NoteOn;
import de.meisterschueler.gpgs.ScoreService;

public class ChromaticEffect extends MidiScreen {
	
	int lowest = 48;
	int current = 0;
	int uppest = 53;
	
	boolean keys[] = new boolean[5];
	
	long startTime;
	
	private ScoreService scoreService;
	
	private ShapeRenderer shapeRenderer;
	private SpriteBatch spriteBatch;
	private BitmapFont font;
	
	public ChromaticEffect(ScoreService scoreService) {
		this.scoreService = scoreService;
		
		shapeRenderer = new ShapeRenderer();
		spriteBatch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		
		current = lowest-1;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		spriteBatch.begin();
		font.draw(spriteBatch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 20, Gdx.graphics.getHeight()-20);
		font.draw(spriteBatch, "Time: " + (System.currentTimeMillis()-startTime)/1000.0, 20, Gdx.graphics.getHeight()-40);
		spriteBatch.end();
		
		shapeRenderer.begin(ShapeType.Filled);
		for (int i = 0; i < keys.length; i++) {
			if (keys[i]) {
				shapeRenderer.setColor(Color.GREEN);
			} else {
				shapeRenderer.setColor(Color.RED);
			}
			shapeRenderer.box(Gdx.graphics.getWidth()/keys.length*i, 0, 0, Gdx.graphics.getWidth()/keys.length, 20, 0);
		}
		shapeRenderer.end();
		
		spriteBatch.begin();
		font.draw(spriteBatch, "ChromaticEffect", 20, 20);
		spriteBatch.end();
	}

	@Override
	public void onMidiNoteOn(NoteOn noteOn) {
		if (noteOn.getNote() == current + 1) {
			current++;
			keys[current-lowest]=true;
		}
		
		if (current == lowest) {
			startTime = System.currentTimeMillis();
		} else if (current == uppest) {
			long deltaTime = System.currentTimeMillis()-startTime;
			current = lowest-1;
			
			if (deltaTime >= 0 && deltaTime <= 2147483647) {
				int delta = (int)deltaTime;
				scoreService.submitScoreGPGS(delta);
				keys = new boolean[5];
			}
		}
	}
}
