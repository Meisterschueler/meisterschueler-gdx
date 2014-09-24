package de.meisterschueler.gdx.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

import de.meisterschueler.basic.NoteOff;
import de.meisterschueler.basic.NoteOn;
import de.meisterschueler.gdx.Meisterschueler;
import de.meisterschueler.gdx.MidiOutput;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(480, 320);
        }

        @Override
        public ApplicationListener getApplicationListener () {
                return new Meisterschueler(new MidiOutput() {
					
					@Override
					public void sendNoteOn(NoteOn noteOn) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void sendNoteOff(NoteOff noteOff) {
						// TODO Auto-generated method stub
						
					}
				});
        }
}