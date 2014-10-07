package de.meisterschueler.gdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.meisterschueler.basic.NoteOff;
import de.meisterschueler.basic.NoteOn;
import de.meisterschueler.gdx.MidiOutput;

public class SynthesiaScreen extends MidiScreen {

	// Constants used by Key and Pianoroll
	private static final float white_small_width = 13;
	private static final float black_small_width = 12;
	private static final float octave_width = 151;

	private class Key extends Actor {

		private ShapeRenderer shapeRenderer;
		private int index;
		private float xoffset;
		private boolean pressed;

		private float scale;

		private static final float white_single_width = (1 * white_small_width + 0 * black_small_width) / 1; // {c}
		private static final float white_double_width = (2 * white_small_width + 1 * black_small_width) / 2; // {a,b}
		private static final float white_triple_width = (3 * white_small_width + 2 * black_small_width) / 3; // {c,d,e}
		private static final float white_quadrupel_width = (4 * white_small_width + 3 * black_small_width) / 4; // {f,g,a,b}

		public Key(final int index) {
			shapeRenderer = new ShapeRenderer();

			this.index = index;
			this.xoffset = (index / 12) * (7 * white_small_width + 5 * black_small_width);
			this.scale = octave_width / (7 * white_small_width + 5 * black_small_width);

			this.addListener(new ClickListener() {

				@Override
				public void clicked(InputEvent event, float x, float y) {
					if (pressed) {
						midiOutput.sendNoteOff(new NoteOff(0, index, 0));
						pressed = false;
					} else {
						midiOutput.sendNoteOn(new NoteOn(0, index, 64));
						pressed = true;
					}
				}
			});
		}

		@Override
		public void draw(Batch batch, float parentAlpha) {
			batch.end();

			shapeRenderer.begin(ShapeType.Filled);
			float keyOffset;
			if (index >= 21 && index <= 23) {
				switch (index) {
				case 21:
					keyOffset = xoffset + (5 * white_small_width + 4 * black_small_width);
					drawWhiteKey(keyOffset, white_double_width);
					break;
				case 22:
					keyOffset = calcBlackKeyOffset(6, 4);
					drawBlackKey(keyOffset, black_small_width);
					break;
				case 23:
					keyOffset = xoffset + (5 * white_small_width + 4 * black_small_width + white_double_width);
					drawWhiteKey(keyOffset, white_double_width);
					break;
				}
			} else if (index == 108) {
				keyOffset = calcWhiteKeyOffset(0, 0);
				drawWhiteKey(keyOffset, white_single_width);
			} else {
				switch (index % 12) {
				case 0:
					keyOffset = calcWhiteKeyOffset(0, 0);
					drawWhiteKey(keyOffset, white_triple_width);
					break;
				case 1:
					keyOffset = calcBlackKeyOffset(1, 0);
					drawBlackKey(keyOffset, black_small_width);
					break;
				case 2:
					keyOffset = calcWhiteKeyOffset(1, 0);
					drawWhiteKey(keyOffset, white_triple_width);
					break;
				case 3:
					keyOffset = calcBlackKeyOffset(2, 1);
					drawBlackKey(keyOffset, black_small_width);
					break;
				case 4:
					keyOffset = calcWhiteKeyOffset(2, 0);
					drawWhiteKey(keyOffset, white_triple_width);
					break;
				case 5:
					keyOffset = calcWhiteKeyOffset(3, 0);
					drawWhiteKey(keyOffset, white_quadrupel_width);
					break;
				case 6:
					keyOffset = calcBlackKeyOffset(4, 2);
					drawBlackKey(keyOffset, black_small_width);
					break;
				case 7:
					keyOffset = calcWhiteKeyOffset(3, 1);
					drawWhiteKey(keyOffset, white_quadrupel_width);
					break;
				case 8:
					keyOffset = calcBlackKeyOffset(5, 3);
					drawBlackKey(keyOffset, black_small_width);
					break;
				case 9:
					keyOffset = calcWhiteKeyOffset(3, 2);
					drawWhiteKey(keyOffset, white_quadrupel_width);
					break;
				case 10:
					keyOffset = calcBlackKeyOffset(6, 4);
					drawBlackKey(keyOffset, black_small_width);
					break;
				case 11:
					keyOffset = calcWhiteKeyOffset(3, 3);
					drawWhiteKey(keyOffset, white_quadrupel_width);
					break;
				}
			}
			shapeRenderer.end();

			batch.begin();
		}

		private float calcWhiteKeyOffset(int n_triple_width, int n_quadrupel_width) {
			return n_triple_width * white_triple_width + n_quadrupel_width * white_quadrupel_width + xoffset;
		}

		private float calcBlackKeyOffset(int n_white_small_width, int n_black_small_width) {
			return n_white_small_width * white_small_width + n_black_small_width * black_small_width + xoffset;
		}

		private void drawWhiteKey(float keyOffset, float width) {
			float height = 150;
			float yoffset = 0;

			shapeRenderer.setColor(Color.BLACK);
			shapeRenderer.rect(keyOffset * scale, yoffset, width * scale, height);
			if (pressed) {
				shapeRenderer.rect((keyOffset + 1) * scale, yoffset + 1, (width - 2) * scale, height - 1, Color.RED, Color.RED, Color.WHITE, Color.WHITE);
			} else {
				shapeRenderer.setColor(Color.WHITE);
				shapeRenderer.rect((keyOffset + 1) * scale, yoffset + 1, (width - 2) * scale, height - 1);
			}

			setWidth(width);
			setHeight(height);
			setBounds(keyOffset * scale, 0, width, height);
		}

		private void drawBlackKey(float keyOffset, float width) {
			float height = 100;
			float yoffset = 50;

			shapeRenderer.setColor(Color.BLACK);
			shapeRenderer.rect(keyOffset * scale, yoffset, width * scale, height);
			if (pressed) {
				shapeRenderer.rect((keyOffset + 1) * scale, yoffset + 1, (width - 2) * scale, height - 1, Color.RED, Color.RED, Color.BLACK, Color.BLACK);
			} else {
				shapeRenderer.setColor(Color.BLACK);
				shapeRenderer.rect((keyOffset + 1) * scale, yoffset + 1, (width - 2) * scale, height - 1);
			}

			setWidth(width);
			setHeight(height);
			setBounds(keyOffset * scale, 50, width, height);
		}
	}

	private class Bubble extends Actor {
	
		private ShapeRenderer shapeRenderer;
	
		public Bubble() {
			this.shapeRenderer = new ShapeRenderer();
		}
	
		@Override
		public void draw(Batch batch, float parentAlpha) {
			batch.end();
			
			shapeRenderer.begin(ShapeType.Filled);
			shapeRenderer.setColor(Color.RED);
			shapeRenderer.circle(200, 200, 10);
			shapeRenderer.end();
	
			batch.begin();
		}
	}

	private class Pianoroll extends Group {

		public Pianoroll() {
			Bubble bubble = new Bubble();
			bubble.addAction(Actions.moveTo(0, 0, 10));
			addActor(bubble);
			
			Bubble bubble2 = new Bubble();
			bubble2.addAction(Actions.moveTo(50, 50, 5));
			addActor(bubble2);
		}

		public void onMidiNoteOn(NoteOn noteOn) {
		}

		public void onMidiNoteOff(NoteOff noteOff) {
		}
	}

	private MidiOutput midiOutput;
	private Group keyboard;
	private Pianoroll pianoRoll;

	public SynthesiaScreen(MidiOutput midiOutput) {
		super();

		this.midiOutput = midiOutput;

		keyboard = createKeyboard();
		pianoRoll = createPianoroll();
		pianoRoll.addAction(Actions.moveTo(0, 0, 10));
		uiGroup.addActor(pianoRoll);
		uiGroup.addActor(keyboard);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act(delta);
		stage.draw();
	}

	@Override
	public void onMidiNoteOn(NoteOn noteOn) {
		pianoRoll.onMidiNoteOn(noteOn);
	}

	@Override
	public void onMidiNoteOff(NoteOff noteOff) {
		pianoRoll.onMidiNoteOff(noteOff);
	}

	private Group createKeyboard() {
		Group whiteKeys = new Group();
		Group blackKeys = new Group();

		whiteKeys.addActor(new Key(21));
		blackKeys.addActor(new Key(22));
		whiteKeys.addActor(new Key(23));
		for (int octave = 2; octave < 9; octave++) {
			whiteKeys.addActor(new Key(0 + 12 * octave));
			blackKeys.addActor(new Key(1 + 12 * octave));
			whiteKeys.addActor(new Key(2 + 12 * octave));
			blackKeys.addActor(new Key(3 + 12 * octave));
			whiteKeys.addActor(new Key(4 + 12 * octave));
			whiteKeys.addActor(new Key(5 + 12 * octave));
			blackKeys.addActor(new Key(6 + 12 * octave));
			whiteKeys.addActor(new Key(7 + 12 * octave));
			blackKeys.addActor(new Key(8 + 12 * octave));
			whiteKeys.addActor(new Key(9 + 12 * octave));
			blackKeys.addActor(new Key(10 + 12 * octave));
			whiteKeys.addActor(new Key(11 + 12 * octave));
		}
		whiteKeys.addActor(new Key(108));

		Group keyboard = new Group();
		keyboard.addActor(whiteKeys);
		keyboard.addActor(blackKeys);

		return keyboard;
	}

	private Pianoroll createPianoroll() {
		Pianoroll pianoroll = new Pianoroll();
		return pianoroll;
	}
}
