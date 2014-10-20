package de.meisterschueler.gdx.screens;

import com.badlogic.gdx.graphics.Color;
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
import de.meisterschueler.utils.MidiOutput;

public class SynthesiaScreen extends MidiScreen {

	// Constants used by Key and Pianoroll
	private static final float white_small_width = 13;
	private static final float black_small_width = 12;
	private static final float octave_width = 151;

	private enum KeyType {
		WHITE, BLACK
	};

	private class Key extends Actor {

		private ShapeRenderer shapeRenderer;
		private int index;
		private boolean pressed;

		private float scale;
		private KeyType keyType;

		private float x;
		private float y;
		private float width;
		private float height;

		private static final float white_single_width = (1 * white_small_width + 0 * black_small_width) / 1; // {c}
		private static final float white_double_width = (2 * white_small_width + 1 * black_small_width) / 2; // {a,b}
		private static final float white_triple_width = (3 * white_small_width + 2 * black_small_width) / 3; // {c,d,e}
		private static final float white_quadrupel_width = (4 * white_small_width + 3 * black_small_width) / 4; // {f,g,a,b}
		private static final float white_quintupel_width = (5 * white_small_width + 4 * black_small_width) / 5; // {c,d,e,f,g}

		public Key(final int index) {
			shapeRenderer = new ShapeRenderer();

			this.index = index;

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

			calcGeometry();
		}

		@Override
		public void draw(Batch batch, float parentAlpha) {
			batch.end();

			Color colorNormal;
			Color colorPressed;

			if (keyType == KeyType.WHITE) {
				colorNormal = Color.WHITE;
				colorPressed = Color.RED;
			} else {
				colorNormal = Color.BLACK;
				colorPressed = Color.RED;
			}

			shapeRenderer.begin(ShapeType.Filled);
			shapeRenderer.setColor(Color.BLACK);
			shapeRenderer.rect(getX() + x, getY() + y, width, height);
			if (pressed) {
				shapeRenderer.rect(getX() + x + 1, getY() + y + 1, (width - 2) * scale, height - 1, colorPressed, colorPressed, colorNormal, colorNormal);
			} else {
				shapeRenderer.setColor(colorNormal);
				shapeRenderer.rect(getX() + x + 1, getY() + y + 1, (width - 2) * scale, height - 1);
			}
			shapeRenderer.end();

			batch.begin();
		}

		private void calcGeometry() {
			float keyOffset;
			if (index >= 21 && index <= 23) {
				switch (index) {
				case 21:
					keyType = KeyType.WHITE;
					keyOffset = calcWhiteKeyOffset(0, 0, 0, 5);
					calcKeyGeometry(keyOffset, white_double_width);
					break;
				case 22:
					keyType = KeyType.BLACK;
					keyOffset = calcBlackKeyOffset(6, 4);
					calcKeyGeometry(keyOffset, black_small_width);
					break;
				case 23:
					keyType = KeyType.WHITE;
					keyOffset = calcWhiteKeyOffset(1, 0, 0, 5);
					calcKeyGeometry(keyOffset, white_double_width);
					break;
				}
			} else if (index == 108) {
				keyType = KeyType.WHITE;
				keyOffset = calcWhiteKeyOffset(0, 0, 0, 0);
				calcKeyGeometry(keyOffset, white_single_width);
			} else {
				switch (index % 12) {
				case 0:
					keyType = KeyType.WHITE;
					keyOffset = calcWhiteKeyOffset(0, 0, 0, 0);
					calcKeyGeometry(keyOffset, white_triple_width);
					break;
				case 1:
					keyType = KeyType.BLACK;
					keyOffset = calcBlackKeyOffset(1, 0);
					calcKeyGeometry(keyOffset, black_small_width);
					break;
				case 2:
					keyType = KeyType.WHITE;
					keyOffset = calcWhiteKeyOffset(0, 1, 0, 0);
					calcKeyGeometry(keyOffset, white_triple_width);
					break;
				case 3:
					keyType = KeyType.BLACK;
					keyOffset = calcBlackKeyOffset(2, 1);
					calcKeyGeometry(keyOffset, black_small_width);
					break;
				case 4:
					keyType = KeyType.WHITE;
					keyOffset = calcWhiteKeyOffset(0, 2, 0, 0);
					calcKeyGeometry(keyOffset, white_triple_width);
					break;
				case 5:
					keyType = KeyType.WHITE;
					keyOffset = calcWhiteKeyOffset(0, 3, 0, 0);
					calcKeyGeometry(keyOffset, white_quadrupel_width);
					break;
				case 6:
					keyType = KeyType.BLACK;
					keyOffset = calcBlackKeyOffset(4, 2);
					calcKeyGeometry(keyOffset, black_small_width);
					break;
				case 7:
					keyType = KeyType.WHITE;
					keyOffset = calcWhiteKeyOffset(0, 3, 1, 0);
					calcKeyGeometry(keyOffset, white_quadrupel_width);
					break;
				case 8:
					keyType = KeyType.BLACK;
					keyOffset = calcBlackKeyOffset(5, 3);
					calcKeyGeometry(keyOffset, black_small_width);
					break;
				case 9:
					keyType = KeyType.WHITE;
					keyOffset = calcWhiteKeyOffset(0, 3, 2, 0);
					calcKeyGeometry(keyOffset, white_quadrupel_width);
					break;
				case 10:
					keyType = KeyType.BLACK;
					keyOffset = calcBlackKeyOffset(6, 4);
					calcKeyGeometry(keyOffset, black_small_width);
					break;
				case 11:
					keyType = KeyType.WHITE;
					keyOffset = calcWhiteKeyOffset(0, 3, 3, 0);
					calcKeyGeometry(keyOffset, white_quadrupel_width);
					break;
				}
			}
		}

		private float calcWhiteKeyOffset(int n_double_width, int n_triple_width, int n_quadrupel_width, int n_quintupel_width) {
			float octaveOffset = (index / 12) * (7 * white_small_width + 5 * black_small_width);
			return n_double_width * white_double_width + n_triple_width * white_triple_width + n_quadrupel_width * white_quadrupel_width + n_quintupel_width
					* white_quintupel_width + octaveOffset;
		}

		private float calcBlackKeyOffset(int n_white_small_width, int n_black_small_width) {
			float octaveOffset = (index / 12) * (7 * white_small_width + 5 * black_small_width);
			return n_white_small_width * white_small_width + n_black_small_width * black_small_width + octaveOffset;
		}

		private void calcKeyGeometry(float keyOffset, float keyWidth) {
			x = keyOffset * scale;
			width = keyWidth * scale;

			if (keyType == KeyType.WHITE) {
				y = 0;
				height = 150;
			} else {
				y = 50;
				height = 100;
			}

			setWidth(width);
			setHeight(height);
			setBounds(x, y, width, height);
		}

		public KeyType getKeyType() {
			return keyType;
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
			shapeRenderer.circle(getX(), getY(), 10);
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

		for (int i = 21; i <= 108; i++) {
			Key key = new Key(i);
			if (key.getKeyType() == KeyType.WHITE) {
				whiteKeys.addActor(key);
			} else {
				blackKeys.addActor(key);
			}
		}

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
