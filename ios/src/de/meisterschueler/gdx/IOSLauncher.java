package de.meisterschueler.gdx;

import org.robovm.apple.foundation.NSAutoreleasePool;
import org.robovm.apple.uikit.UIApplication;

import com.badlogic.gdx.backends.iosrobovm.IOSApplication;
import com.badlogic.gdx.backends.iosrobovm.IOSApplicationConfiguration;

import de.meisterschueler.basic.NoteOff;
import de.meisterschueler.basic.NoteOn;

public class IOSLauncher extends IOSApplication.Delegate {
    @Override
    protected IOSApplication createApplication() {
        IOSApplicationConfiguration config = new IOSApplicationConfiguration();
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
        
        meisterschueler.setScoreService(new ScoreServiceIOS(){
        	
        });
        
        return new IOSApplication(meisterschueler, config);
    }

    public static void main(String[] argv) {
        NSAutoreleasePool pool = new NSAutoreleasePool();
        UIApplication.main(argv, null, IOSLauncher.class);
        pool.close();
    }
}