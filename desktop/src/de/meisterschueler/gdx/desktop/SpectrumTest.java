package de.meisterschueler.gdx.desktop;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.AudioRecorder;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import edu.emory.mathcs.jtransforms.fft.FloatFFT_1D;

public class SpectrumTest implements ApplicationListener {	
	static AudioRecorder recorder;
	static float[] spectrum;

	static Sound sound;

	ShapeRenderer shapeRenderer;

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		LwjglApplication application = new LwjglApplication(new SpectrumTest(), config);

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

		/*FileHandle fileHandle = Gdx.files.internal("Aufnahme_0003.wav");
		sound = Gdx.audio.newSound(fileHandle);
		sound.play();

		try {
			File file = new File("Aufnahme_0003.wav"); 
			final AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);

			final byte[] samples = new byte[8192];
			final int sleepTime = (int) ((samples.length/2)/44.1);

			while (audioInputStream.read(samples) > 0) {
				Thread t = new Thread() {
					@Override
					public void run()  {

						try {
							spectrum = calcSpectrum(samples);
							Gdx.graphics.requestRendering();
							Thread.sleep(sleepTime);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				};
				t.start();
				t.join();
			}
		} catch (Exception e) {

		} */
	}

	private static float[] calcSpectrum(short[] samples) {
		float[] audioFloats = new float[samples.length];
		for (int i=0; i<audioFloats.length; i++)
			audioFloats[i] = samples[i];

		FloatFFT_1D fft = new FloatFFT_1D(audioFloats.length);
		fft.realForward(audioFloats);

		return audioFloats;
	}

	private static float[] calcSpectrum(byte[] samples) {
		ShortBuffer sbuf = ByteBuffer.wrap(samples).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer();
		short[] audioShorts = new short[sbuf.capacity()];
		sbuf.get(audioShorts);

		float[] audioFloats = new float[audioShorts.length];
		for (int i=0; i<audioFloats.length; i++)
			audioFloats[i] = audioShorts[i];

		FloatFFT_1D fft = new FloatFFT_1D(audioFloats.length);
		fft.realForward(audioFloats);

		return audioFloats;
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resize(int width, int height) {
		shapeRenderer = new ShapeRenderer();
	}

	@Override
	public void render() {
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
			vertices[(i-fromIdx)*2] = x;
			vertices[(i-fromIdx)*2+1] = frequencies[i]/2000;

			if (frequencies[i] == max) {
				//shapeRenderer.box(x-5, 0, 0, 10, max/2000, 0);
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

	@Override
	public void pause() {
		recorder.dispose();
	}

	@Override
	public void resume() {
		recorder = Gdx.audio.newAudioRecorder(44100, true);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		sound.dispose();
	}
}
