package de.meisterschueler.gdx.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import de.meisterschueler.basic.NoteOff;
import de.meisterschueler.basic.NoteOn;
import de.meisterschueler.gdx.MidiOutput;
import de.meisterschueler.gdx.Meisterschueler;

public class DesktopLauncher {	
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new Meisterschueler(new MidiOutput() {

			@Override
			public void sendNoteOn(NoteOn noteOn) {
				// TODO Auto-generated method stub	
			}

			@Override
			public void sendNoteOff(NoteOff noteOff) {
				// TODO Auto-generated method stub
			}
			
		}), config);
	}


}
