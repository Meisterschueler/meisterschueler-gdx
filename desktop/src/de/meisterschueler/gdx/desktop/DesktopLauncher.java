package de.meisterschueler.gdx.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import de.meisterschueler.basic.NoteOff;
import de.meisterschueler.basic.NoteOn;
import de.meisterschueler.gdx.Meisterschueler;
import de.meisterschueler.utils.MidiOutput;

public class DesktopLauncher {	
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		Meisterschueler meisterschueler = new Meisterschueler(new MidiOutput() {

			@Override
			public void sendNoteOn(NoteOn noteOn) {
				// TODO Auto-generated method stub	
			}

			@Override
			public void sendNoteOff(NoteOff noteOff) {
				// TODO Auto-generated method stub
			}
			
		});
		meisterschueler.setScoreService(new ScoreServiceDesktop());
		LwjglApplication application = new LwjglApplication(meisterschueler, config);
		
//		final ApplicationListener listener = application.getApplicationListener();
//		
//		String file = "/Users/konstantin/Downloads/joseftest";
//		try {
//			MidiLogPlayer midiLogPlayer = new MidiLogPlayer(file) {
//
//				@Override
//				public void onNoteOn(NoteOn noteOn) {
//					System.out.println("NoteOn: " + noteOn.getNote() + " " + noteOn.getVelocity());
//					((Meisterschueler)listener).onMidiNoteOn(noteOn);
//				}
//
//				@Override
//				public void onNoteOff(NoteOff noteOff) {
//					System.out.println("NoteOff: " + noteOff.getNote() + " " + noteOff.getVelocity());
//					((Meisterschueler)listener).onMidiNoteOff(noteOff);
//				}
//			};
//		} catch (NumberFormatException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}	
	}
}
