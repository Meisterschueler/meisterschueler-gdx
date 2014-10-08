package de.meisterschueler.gdx.screens;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import de.meisterschueler.basic.NoteOff;
import de.meisterschueler.basic.NoteOn;
import de.meisterschueler.chords.Chordfinder;

public class ChordScreen extends MidiScreen {

	List<Integer> notes = new ArrayList<Integer>();

	private Label chordLabel;

	public ChordScreen() {
		super();
		
		chordLabel = new Label("", skin, "huge");

		gameGroup.addActor(chordLabel);
	}

	@Override
	public void onMidiNoteOn(NoteOn noteOn) {
		notes.add(noteOn.getNote());
		String chordName = getCurrentChordName();
		System.out.println(notes.size());
		chordLabel.setText(chordName);
		
		BitmapFont font = chordLabel.getStyle().font;
		TextBounds bounds = font.getBounds(chordName);
		chordLabel.setPosition(WIDTH/2-bounds.width/2, HEIGHT/2);
	}

	@Override
	public void onMidiNoteOff(NoteOff noteOff) {
		int idx = notes.indexOf(noteOff.getNote());
		if (idx >= 0) {
			notes.remove(idx);
		}
		String chordName = getCurrentChordName();
		chordLabel.setText(chordName);
		
		BitmapFont font = chordLabel.getStyle().font;
		TextBounds bounds = font.getBounds(chordName);
		chordLabel.setPosition(WIDTH/2-bounds.width/2, HEIGHT/2);
	}

	private String getCurrentChordName() {
		Collections.sort(notes);
		int[] tmpNotes = new int[notes.size()];
		for (int i=0; i<notes.size(); i++) {
			tmpNotes[i] = notes.get(i);
		}
		
		String chordName = Chordfinder.getChordName(tmpNotes);
		return chordName;
	}
}
