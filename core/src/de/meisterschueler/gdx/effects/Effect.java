package de.meisterschueler.gdx.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import de.meisterschueler.basic.ControlChange;
import de.meisterschueler.basic.NoteOff;
import de.meisterschueler.basic.NoteOn;

public abstract class Effect {
	protected ShapeRenderer shapeRenderer;
	protected SpriteBatch spriteBatch;
	protected BitmapFont font;
    
    public Effect(ShapeRenderer shapeRenderer, SpriteBatch spriteBatch, BitmapFont font) {
		this.shapeRenderer = shapeRenderer;
		this.spriteBatch = spriteBatch;
		this.font = font;
		
		Gdx.graphics.setContinuousRendering(true);
	}

	public abstract void onRender();
    public abstract void onMidiNoteOn(NoteOn noteOn);
    public abstract void onMidiNoteOff(NoteOff noteOff);
	public abstract void onMidiControlChange(ControlChange controlChange);
	public void onReset() {};
    
	Color getSpectralColor(float value, float vMin, float vMax, float cMin, float cMax) {
		Color color = new Color(0, 0, 0, 0);

		float normValue = (Math.min(vMax, Math.max(value, vMin)) - vMin) / (vMax-vMin);
		normValue = normValue*(cMax-cMin)+cMin;

		if (normValue >= 0.0 && normValue < 1.0)		// From 0-1: black to red
			color = new Color(normValue, 0, 0, 1);
		else if (normValue >= 1.0 && normValue < 2.0)	// From 1-2: red to orange
			color = new Color(1, normValue-1.0f, 0, 1);
		else if (normValue >= 2.0 && normValue < 3.0)	// From 2-3: orange to yellow
			color = new Color(3.0f-normValue, 1, 0, 1);
		else if (normValue >= 3.0 && normValue < 4.0)	// From 3-4: yellow to green
			color = new Color(0, 1, normValue-3.0f, 1);
		else if (normValue >= 4.0 && normValue < 5.0)	// From 4-5: green to blue
			color = new Color(0, 5.0f-normValue, 1, 1);
		else if (normValue >= 5.0 && normValue < 6.0)	// From 5-6: blue to black
			color = new Color(0, 0, 6.0f-normValue, 1);
		
		return color;
	}
}
