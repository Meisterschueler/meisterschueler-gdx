package de.meisterschueler.gdx.effects;

import com.badlogic.gdx.Screen;

import de.meisterschueler.basic.ControlChange;
import de.meisterschueler.basic.NoteOff;
import de.meisterschueler.basic.NoteOn;

public class MidiScreen implements Screen {

    public void onMidiNoteOn(NoteOn noteOn) {};
    public void onMidiNoteOff(NoteOff noteOff) {};
	public void onMidiControlChange(ControlChange controlChange) {};
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
