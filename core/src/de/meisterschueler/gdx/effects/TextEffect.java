package de.meisterschueler.gdx.effects;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.meisterschueler.basic.AbstractChannelEvent;
import de.meisterschueler.basic.ControlChange;
import de.meisterschueler.basic.NoteOff;
import de.meisterschueler.basic.NoteOn;

public class TextEffect extends MidiScreen {

	List<String> strings = new ArrayList<String>();
	List<AbstractChannelEvent> events = new ArrayList<AbstractChannelEvent>();
	
	private SpriteBatch spriteBatch;
	private BitmapFont font;

	public TextEffect() {
		spriteBatch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.WHITE);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		spriteBatch.begin();
		font.draw(spriteBatch, "TextEffect", 20, 20);

		int idx=0;
		for (int i=Gdx.graphics.getHeight(); i>20; i=i-20) {
			if (idx >= events.size())
				break;

			AbstractChannelEvent event = events.get(idx);
			String s = "";
			if (event instanceof NoteOn) {
				NoteOn noteOn = (NoteOn)event;
				font.setColor(Color.RED);
				s = timeToString(event.getTime()) + ": NoteOn  " + noteOn.getNote() + " " + noteOn.getVelocity();
			} else if (event instanceof NoteOff) {
				NoteOff noteOff = (NoteOff)event;
				font.setColor(Color.GREEN);
				s = timeToString(event.getTime()) + ": NoteOff " + noteOff.getNote() + " " + noteOff.getVelocity();
			} else if (event instanceof ControlChange) {
				ControlChange controlChange = (ControlChange)event;
				font.setColor(Color.BLUE);
				s = timeToString(event.getTime()) + ": ControlChange " + controlChange.getFunction() + " " + controlChange.getValue();
			}

			font.draw(spriteBatch, s, 20, i);
			idx++;
		}
		font.setColor(Color.WHITE);

		spriteBatch.end();
	}

	@Override
	public void onMidiNoteOn(NoteOn noteOn) {
		events.add(0, noteOn);
	}

	@Override
	public void onMidiNoteOff(NoteOff noteOff) {
		events.add(0, noteOff);
	}

	@Override
	public void onMidiControlChange(ControlChange controlChange) {
		events.add(0, controlChange);
	}

	private String timeToString(Long time) {
		time = time % (1000*60*60*24);
		return String.format("%02d:%02d:%02d:%03d", time / 3600000, (time % 3600000) / 60000, (time % 60000) / 1000, (time % 1000) );
	}
}
