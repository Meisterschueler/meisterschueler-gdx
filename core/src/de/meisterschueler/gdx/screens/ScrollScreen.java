package de.meisterschueler.gdx.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.SnapshotArray;

import de.meisterschueler.basic.NoteOff;
import de.meisterschueler.basic.NoteOn;
import de.meisterschueler.utils.Utils;

public class ScrollScreen extends MidiScreen {

	private class Bubble extends Actor {

		private ShapeRenderer shapeRenderer;
		private NoteOn noteOn;
		private NoteOff noteOff;

		public Bubble(NoteOn noteOn) {
			shapeRenderer = new ShapeRenderer();
			this.noteOn = noteOn;
			setPosition(WIDTH, (float)(noteOn.getNote()*WIDTH/127.0));
			setHeight(HEIGHT/127.0f);
		}
		
		@Override
		public void act(float delta) {
			moveBy(-1, 0);
		}

		@Override
		public void draw(Batch batch, float parentAlpha) {
			batch.end();

			shapeRenderer.begin(ShapeType.Filled);
			Color color = Utils.getSpectralColor(noteOn.getVelocity(), meanVelocity - 20, meanVelocity + 20, 5, 1);
			shapeRenderer.setColor(color);

			if (noteOff == null) {
				shapeRenderer.rect(getX(), getY(), WIDTH-getX(), getHeight());
			} else {
				shapeRenderer.rect(getX(), getY(), getWidth(), getHeight());
			}
			shapeRenderer.end();

			batch.begin();
		}

		public NoteOn getNoteOn() {
			return noteOn;
		}

		public NoteOff getNoteOff() {
			return noteOff;
		}

		public void setNoteOff(NoteOff noteOff) {
			this.noteOff = noteOff;
		}
	}

	private class Pianoroll extends Group {

		@Override
		public void act(float delta) {
			super.act(delta);
			moveBy(-1, 0);

			int velocitySum = 0;
			int n = 0;
			for (Actor actor : getChildren()) {
				Bubble bubble = (Bubble)actor;
				velocitySum += bubble.noteOn.getVelocity();
				n++;
			}
			meanVelocity = (n > 0) ? velocitySum / n : 0;
		}
	}

	Pianoroll pianoroll;
	private int meanVelocity;

	NoteOn[] touchedNotes = new NoteOn[10];

	public ScrollScreen() {
		pianoroll = new Pianoroll();
		gameGroup.addActor(pianoroll);
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		NoteOn noteOn = new NoteOn(0, (int)screenX*127/WIDTH, (int)(HEIGHT-screenY)*127/HEIGHT);
		touchedNotes[pointer] = noteOn;

		onMidiNoteOn(noteOn);
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {

		NoteOn noteOff = touchedNotes[pointer];
		onMidiNoteOff(new NoteOff(0, noteOff.getNote(), noteOff.getVelocity()));
		touchedNotes[pointer] = null;

		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		NoteOn noteOn = new NoteOn(0, (int)screenX*127/WIDTH, (int)(HEIGHT-screenY)*127/HEIGHT);

		NoteOn noteOff = touchedNotes[pointer];

		if (noteOn.getNote() != noteOff.getNote() || noteOn.getVelocity() == noteOff.getVelocity()) {
			onMidiNoteOff(new NoteOff(0, noteOff.getNote(), noteOff.getVelocity()));
			onMidiNoteOn(noteOn);
			touchedNotes[pointer] = noteOn;
		}

		return true;
	}

	@Override
	public void onMidiNoteOn(NoteOn noteOn) {
		Bubble bubble = new Bubble(noteOn);
		pianoroll.addActor(bubble);
	}

	@Override
	public void onMidiNoteOff(NoteOff noteOff) {
		SnapshotArray<Actor> actors = pianoroll.getChildren();
		for (Actor actor : actors) {
			if (actor instanceof Bubble) {
				Bubble bubble = (Bubble)actor;
				if (bubble.getNoteOn().getNote() == noteOff.getNote() && bubble.getNoteOff() == null) {
					bubble.setNoteOff(noteOff);
					bubble.setWidth(WIDTH-bubble.getX());
				}
			}
		}
	}
}
