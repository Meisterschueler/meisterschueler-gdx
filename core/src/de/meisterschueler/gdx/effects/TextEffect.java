package de.meisterschueler.gdx.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import de.meisterschueler.basic.NoteOff;
import de.meisterschueler.basic.NoteOn;

public class TextEffect extends Effect {

	public TextEffect(ShapeRenderer shapeRenderer, SpriteBatch spriteBatch) {
		super(shapeRenderer, spriteBatch);
	}

	@Override
	public void onRender() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		spriteBatch.begin();
		font.draw(spriteBatch, "TextEffect", 20, 20);
		spriteBatch.end();
	}

	@Override
	public void onMidiNoteOn(NoteOn noteOn) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMidiNoteOff(NoteOff noteOff) {
		// TODO Auto-generated method stub

	}

}
