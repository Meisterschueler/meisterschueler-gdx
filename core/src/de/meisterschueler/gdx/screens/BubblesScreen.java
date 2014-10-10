package de.meisterschueler.gdx.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.SnapshotArray;

import de.meisterschueler.basic.NoteOff;
import de.meisterschueler.basic.NoteOn;

public class BubblesScreen extends MidiScreen {
	
	private class Bubble extends Actor {
		
		private ShapeRenderer shapeRenderer;
		private NoteOn noteOn;

		public Bubble(NoteOn noteOn) {
			shapeRenderer = new ShapeRenderer();
			this.noteOn = noteOn;
			setPosition((float)(noteOn.getNote()*WIDTH/127.0), (float)(noteOn.getVelocity()*HEIGHT/127.0));
		}

		@Override
		public void draw(Batch batch, float parentAlpha) {
			batch.end();

			shapeRenderer.begin(ShapeType.Filled);
			shapeRenderer.setColor(Color.RED);
			shapeRenderer.circle(getX(), getY(), 5);
			shapeRenderer.end();

			batch.begin();
		}

		public NoteOn getNoteOn() {
			return noteOn;
		}
	}

	NoteOn[] touchedNotes = new NoteOn[10];
	
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
		gameGroup.addActor(bubble);
	}

	@Override
	public void onMidiNoteOff(NoteOff noteOff) {
		SnapshotArray<Actor> actors = gameGroup.getChildren();
		for (Actor actor : actors) {
			if (actor instanceof Bubble) {
				Bubble bubble = (Bubble)actor;
				if (bubble.getNoteOn().getNote() == noteOff.getNote()) {
					bubble.addAction(Actions.sequence(Actions.fadeOut(1), Actions.removeActor()));
				}
			}
		}
	}
}
