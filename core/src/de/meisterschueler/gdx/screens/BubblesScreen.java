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
		private int note;

		public Bubble(int note) {
			shapeRenderer = new ShapeRenderer();
			this.note = note;
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

		public int getNote() {
			return note;
		}
	}

	@Override
	public void onMidiNoteOn(NoteOn noteOn) {
		Bubble bubble = new Bubble(noteOn.getNote());
		bubble.setPosition((float)(noteOn.getNote()*WIDTH/127.0), (float)(noteOn.getVelocity()*HEIGHT/127.0));
		gameGroup.addActor(bubble);
	}

	@Override
	public void onMidiNoteOff(NoteOff noteOff) {
		SnapshotArray<Actor> actors = gameGroup.getChildren();
		for (Actor actor : actors) {
			if (actor instanceof Bubble) {
				Bubble bubble = (Bubble)actor;
				if (bubble.getNote() == noteOff.getNote()) {
					bubble.addAction(Actions.sequence(Actions.fadeOut(1), Actions.removeActor()));
				}
			}
		}
	}
}
