package de.meisterschueler.gdx.android;

import java.util.List;
import java.util.Set;

import jp.kshoji.driver.midi.device.MidiInputDevice;
import jp.kshoji.driver.midi.device.MidiOutputDevice;
import jp.kshoji.driver.midi.listener.OnMidiDeviceAttachedListener;
import jp.kshoji.driver.midi.listener.OnMidiDeviceDetachedListener;
import jp.kshoji.driver.midi.listener.OnMidiInputEventListener;
import jp.kshoji.driver.midi.thread.MidiDeviceConnectionWatcher;
import jp.kshoji.driver.midi.util.Constants;
import jp.kshoji.driver.midi.util.UsbMidiDeviceUtils;
import jp.kshoji.driver.usb.util.DeviceFilter;
import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;
import android.view.WindowManager;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import de.meisterschueler.basic.ControlChange;
import de.meisterschueler.basic.Derepeater;
import de.meisterschueler.basic.NoteOff;
import de.meisterschueler.basic.NoteOn;
import de.meisterschueler.gdx.Meisterschueler;
import de.meisterschueler.gdx.MidiOutput;

/**
 * base Activity for using USB MIDI interface.
 * In this implement, the only one device (connected at first) will be detected.
 * launchMode must be "singleTask" or "singleInstance".
 * 
 * @author K.Shoji
 */
public class AndroidLauncher extends AndroidApplication implements OnMidiDeviceDetachedListener, OnMidiDeviceAttachedListener, OnMidiInputEventListener, MidiOutput {
	/**
	 * Implementation for single device connections.
	 * 
	 * @author K.Shoji
	 */
	final class OnMidiDeviceAttachedListenerImpl implements OnMidiDeviceAttachedListener {
		private final UsbManager usbManager;

		/**
		 * constructor
		 * 
		 * @param usbManager
		 */
		public OnMidiDeviceAttachedListenerImpl(UsbManager usbManager) {
			this.usbManager = usbManager;
		}

		/*
		 * (non-Javadoc)
		 * @see jp.kshoji.driver.midi.listener.OnMidiDeviceAttachedListener#onDeviceAttached(android.hardware.usb.UsbDevice, android.hardware.usb.UsbInterface)
		 */
		@Override
		public synchronized void onDeviceAttached(final UsbDevice attachedDevice) {
			if (device != null) {
				// already one device has been connected
				return;
			}

			deviceConnection = usbManager.openDevice(attachedDevice);
			if (deviceConnection == null) {
				return;
			}

			List<DeviceFilter> deviceFilters = DeviceFilter.getDeviceFilters(getApplicationContext());

			Set<MidiInputDevice> foundInputDevices = UsbMidiDeviceUtils.findMidiInputDevices(attachedDevice, deviceConnection, deviceFilters, AndroidLauncher.this);
			if (foundInputDevices.size() > 0) {
				midiInputDevice = (MidiInputDevice) foundInputDevices.toArray()[0];
			}

			Set<MidiOutputDevice> foundOutputDevices = UsbMidiDeviceUtils.findMidiOutputDevices(attachedDevice, deviceConnection, deviceFilters);
			if (foundOutputDevices.size() > 0) {
				midiOutputDevice = (MidiOutputDevice) foundOutputDevices.toArray()[0];
			}

			Log.d(Constants.TAG, "Device " + attachedDevice.getDeviceName() + " has been attached.");

			AndroidLauncher.this.onDeviceAttached(attachedDevice);
		}
	}

	/**
	 * Implementation for single device connections.
	 * 
	 * @author K.Shoji
	 */
	final class OnMidiDeviceDetachedListenerImpl implements OnMidiDeviceDetachedListener {
		/*
		 * (non-Javadoc)
		 * @see jp.kshoji.driver.midi.listener.OnMidiDeviceDetachedListener#onDeviceDetached(android.hardware.usb.UsbDevice)
		 */
		@Override
		public synchronized void onDeviceDetached(final UsbDevice detachedDevice) {

			AsyncTask<UsbDevice, Void, Void> task = new AsyncTask<UsbDevice, Void, Void>() {

				@Override
				protected Void doInBackground(UsbDevice... params) {
					if (params == null || params.length < 1) {
						return null;
					}

					UsbDevice usbDevice = params[0];

					if (midiInputDevice != null) {
						midiInputDevice.stop();
						midiInputDevice = null;
					}

					if (midiOutputDevice != null) {
						midiOutputDevice.stop();
						midiOutputDevice = null;
					}

					if (deviceConnection != null) {
						deviceConnection.close();
						deviceConnection = null;
					}
					device = null;

					Log.d(Constants.TAG, "Device " + usbDevice.getDeviceName() + " has been detached.");

					Message message = Message.obtain(deviceDetachedHandler);
					message.obj = usbDevice;
					deviceDetachedHandler.sendMessage(message);
					return null;
				}
			};
			task.execute(detachedDevice);
		}
	}



	UsbDevice device = null;
	UsbDeviceConnection deviceConnection = null;
	MidiInputDevice midiInputDevice = null;
	MidiOutputDevice midiOutputDevice = null;
	OnMidiDeviceAttachedListener deviceAttachedListener = null;
	OnMidiDeviceDetachedListener deviceDetachedListener = null;
	Handler deviceDetachedHandler = null;
	private MidiDeviceConnectionWatcher deviceConnectionWatcher = null;


	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
		//cfg.useGL20 = false;
		initialize(new Meisterschueler(this), cfg);

		UsbManager usbManager = (UsbManager) getApplicationContext().getSystemService(Context.USB_SERVICE);
		deviceAttachedListener = new OnMidiDeviceAttachedListenerImpl(usbManager);
		deviceDetachedListener = new OnMidiDeviceDetachedListenerImpl(); 

		deviceDetachedHandler = new Handler(new Callback() {
			/*
			 * (non-Javadoc)
			 * @see android.os.Handler.Callback#handleMessage(android.os.Message)
			 */
			@Override
			public boolean handleMessage(Message msg) {
				UsbDevice usbDevice = (UsbDevice) msg.obj;
				onDeviceDetached(usbDevice);
				return true;
			}
		});

		deviceConnectionWatcher = new MidiDeviceConnectionWatcher(getApplicationContext(), usbManager, deviceAttachedListener, deviceDetachedListener);
	}

	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onDestroy()
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();

		if (deviceConnectionWatcher != null) {
			deviceConnectionWatcher.stop();
		}
		deviceConnectionWatcher = null;

		if (midiInputDevice != null) {
			midiInputDevice.stop();
			midiInputDevice = null;
		}

		midiOutputDevice = null;

		deviceConnection = null;
	}


	/**
	 * Suspends receiving/transmitting MIDI messages.
	 * All events will be discarded until the devices being resumed.
	 */
	protected final void suspendMidiDevices() {
		if (midiInputDevice != null) {
			midiInputDevice.suspend();
		}

		if (midiOutputDevice != null) {
			midiOutputDevice.suspend();
		}
	}

	/**
	 * Resumes from {@link #suspendMidiDevices()}
	 */
	protected final void resumeMidiDevices() {
		if (midiInputDevice != null) {
			midiInputDevice.resume();
		}

		if (midiOutputDevice != null) {
			midiOutputDevice.resume();
		}
	}

	/**
	 * Get MIDI output device, if available.
	 * 
	 * @param usbDevice
	 * @return MidiOutputDevice, null if not available
	 */
	public final MidiOutputDevice getMidiOutputDevice() {
		if (deviceConnectionWatcher != null) {
			deviceConnectionWatcher.checkConnectedDevicesImmediately();
		}

		return midiOutputDevice;
	}

	private Derepeater derepeater = new Derepeater() {

		@Override
		public void onNoteOn(NoteOn noteOn) {
			((Meisterschueler)listener).onMidiNoteOn(noteOn);
		}

		@Override
		public void onNoteOff(NoteOff noteOff) {
			((Meisterschueler)listener).onMidiNoteOff(noteOff);
		}

	};

	@Override
	public void onMidiNoteOff(MidiInputDevice sender, int cable, int channel,
			int note, int velocity) {
		derepeater.noteOffEvent(cable, channel, note, velocity);
	}

	@Override
	public void onMidiNoteOn(MidiInputDevice sender, int cable, int channel,
			int note, int velocity) {
		derepeater.noteOnEvent(cable, channel, note, velocity);
	}

	@Override
	public void onMidiMiscellaneousFunctionCodes(MidiInputDevice sender,
			int cable, int byte1, int byte2, int byte3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMidiCableEvents(MidiInputDevice sender, int cable, int byte1,
			int byte2, int byte3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMidiSystemCommonMessage(MidiInputDevice sender, int cable,
			byte[] bytes) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMidiSystemExclusive(MidiInputDevice sender, int cable,
			byte[] systemExclusive) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMidiPolyphonicAftertouch(MidiInputDevice sender, int cable,
			int channel, int note, int pressure) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMidiControlChange(MidiInputDevice sender, int cable,
			int channel, int function, int value) {
		ControlChange controlChange = new ControlChange(System.currentTimeMillis(), cable, channel, function, value);
		((Meisterschueler)listener).onMidiControlChange(controlChange);
	}

	@Override
	public void onMidiProgramChange(MidiInputDevice sender, int cable,
			int channel, int program) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMidiChannelAftertouch(MidiInputDevice sender, int cable,
			int channel, int pressure) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMidiPitchWheel(MidiInputDevice sender, int cable,
			int channel, int amount) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMidiSingleByte(MidiInputDevice sender, int cable, int byte1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMidiRPNReceived(MidiInputDevice sender, int cable,
			int channel, int function, int valueMSB, int valueLSB) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMidiNRPNReceived(MidiInputDevice sender, int cable,
			int channel, int function, int valueMSB, int valueLSB) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDeviceAttached(UsbDevice usbDevice) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDeviceDetached(UsbDevice usbDevice) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see de.meisterschueler.gdx.android.MidiOutputFuck#sendNoteOn(de.meisterschueler.basic.NoteOn)
	 */
	@Override
	public void sendNoteOn(NoteOn noteOn) {
		getMidiOutputDevice().sendMidiNoteOn(noteOn.getCable(), noteOn.getChannel(), noteOn.getNote(), noteOn.getVelocity());
	}

	/* (non-Javadoc)
	 * @see de.meisterschueler.gdx.android.MidiOutputFuck#sendNoteOff(de.meisterschueler.basic.NoteOff)
	 */
	@Override
	public void sendNoteOff(NoteOff noteOff) {
		getMidiOutputDevice().sendMidiNoteOff(noteOff.getCable(), noteOff.getChannel(), noteOff.getNote(), noteOff.getVelocity());
	}
}

