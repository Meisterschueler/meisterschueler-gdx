package de.meisterschueler.gdx.screens;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;

import de.meisterschueler.basic.AbstractChannelEvent;
import de.meisterschueler.basic.ControlChange;
import de.meisterschueler.basic.NoteOff;
import de.meisterschueler.basic.NoteOn;

public class MidiStreamScreen extends MidiScreen {

	List<String> strings = new ArrayList<String>();
	List<AbstractChannelEvent> events = new ArrayList<AbstractChannelEvent>();
	
	private TextArea midiInputArea;

	public MidiStreamScreen() {
		super();
		
		midiInputArea = new TextArea("", skin);
		midiInputArea.setDisabled(true);
		midiInputArea.setPosition(0,  BUTTON_HEIGHT);
		midiInputArea.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()-BUTTON_HEIGHT);
		
		gameGroup.addActor(midiInputArea);
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
		String text = midiInputArea.getText();
		text = timeToString(noteOn.getTime()) + ": NoteOn  " + noteOn.getNote() + " " + noteOn.getVelocity() + "\n" + text;
		midiInputArea.setText(text);
	}

	@Override
	public void onMidiNoteOff(NoteOff noteOff) {
		String text = midiInputArea.getText();
		text = timeToString(noteOff.getTime()) + ": NoteOff  " + noteOff.getNote() + " " + noteOff.getVelocity() + "\n" + text;
		midiInputArea.setText(text);
	}

	@Override
	public void onMidiControlChange(ControlChange controlChange) {
		String text = midiInputArea.getText();
		text = timeToString(controlChange.getTime()) + ": ControlChange  " + controlChange.getFunction() + " " + controlChange.getValue();
		midiInputArea.setText(text);
	}

	private String timeToString(Long time) {
		time = time % (1000*60*60*24);
		return String.format("%02d:%02d:%02d:%03d", time / 3600000, (time % 3600000) / 60000, (time % 60000) / 1000, (time % 1000) );
	}
}
