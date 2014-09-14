package de.meisterschueler.gdx.effects;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.AudioRecorder;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import de.meisterschueler.basic.ControlChange;
import de.meisterschueler.basic.NoteOff;
import de.meisterschueler.basic.NoteOn;
import edu.emory.mathcs.jtransforms.fft.FloatFFT_1D;

public class SpectrumEffect extends MidiScreen {	

	private AudioRecorder recorder;
	private float[] spectrum;
	
	private TextureRegion background;

	private static final int SPEED = 100;
	private static final boolean IS_MONO = true;
	private static final int SAMPLING_RATE = 44100;
	private static final int N = 1024;

	String FILE = "data/justice-new-lands.mp3";
	boolean playing = false;
	
	private ShapeRenderer shapeRenderer;
	private SpriteBatch spriteBatch;
	private BitmapFont font;
	
	public SpectrumEffect() {
		shapeRenderer = new ShapeRenderer();
		spriteBatch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		
		Gdx.graphics.setContinuousRendering(false);

		recorder = Gdx.audio.newAudioRecorder(44100, true);
		spectrum = new float[1024];

		final short[] shortPCM = new short[8192];

		Thread t = new Thread() {
			@Override
			public void run()  {
				while (true) {
					recorder.read(shortPCM, 0, shortPCM.length);
					spectrum = calcSpectrum(shortPCM);
					Gdx.graphics.requestRendering();
				}
			}
		};
		t.setDaemon(true);
		t.start();
	}


	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		float[] frequencies = new float[spectrum.length/2];
		float frequencyStep = (44100/frequencies.length);

		float power;
		for (int i=1; i<spectrum.length/2; i++) {
			power = (float) Math.sqrt(spectrum[i*2]*spectrum[i*2]+spectrum[i*2+1]*spectrum[i*2+1]);
			frequencies[i] = power/i;
		}

		float lowerFrequency = 110;
		float upperFrequency = 880;

		float max = 0;	
		int fromIdx = (int) Math.floor(lowerFrequency/frequencyStep);
		int toIdx = (int) Math.ceil(upperFrequency/frequencyStep);
		float fromFrequency = frequencyStep*fromIdx;
		float toFrequency = frequencyStep*toIdx;

		// f(x) = a*log(x) - b
		float a = (float) (Gdx.graphics.getWidth() / (Math.log(toFrequency) - Math.log(fromFrequency)));
		float b = (float) (a*Math.log(fromFrequency));

		for (int i=1; i<frequencies.length; i++) {
			max = Math.max(frequencies[i], max);
		}

		float[] vertices = new float[(toIdx-fromIdx)*2];
		for (int i=fromIdx; i<toIdx; i++) {
			float x = (float) (a*Math.log(frequencyStep*i)-b);
			float y = (float) (frequencies[i]/100);
			vertices[(i-fromIdx)*2] = x;
			vertices[(i-fromIdx)*2+1] = y;

			if (frequencies[i] == max) {
				//shapeRenderer.box(x-5, 0, 0, 10, max/200, 0);
			}
		}

		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(Color.RED);

		shapeRenderer.end();

		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(Color.WHITE);
		shapeRenderer.polyline(vertices);
		shapeRenderer.end();
	}
	
	private static float[] calcSpectrum(byte[] samples) {
		ShortBuffer sbuf = ByteBuffer.wrap(samples).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer();
		short[] audioShorts = new short[sbuf.capacity()];
		sbuf.get(audioShorts);
		
		return calcSpectrum(audioShorts);
	}
	
	private static float[] calcSpectrum(short[] samples) {
		float[] audioFloats = new float[samples.length];
		for (int i=0; i<audioFloats.length; i++)
			audioFloats[i] = samples[i];

		FloatFFT_1D fft = new FloatFFT_1D(audioFloats.length);
		fft.realForward(audioFloats);

		return audioFloats;
	}
}
