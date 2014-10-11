package de.meisterschueler.gdx.screens;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.scenes.scene2d.Actor;

import de.meisterschueler.basic.NoteOn;

public class ParticlesScreen extends MidiScreen {

	public class ParticleEffectActor extends Actor {
		ParticleEffect particleEffect;

		public ParticleEffectActor(ParticleEffect particleEffect) {
			super();
			this.particleEffect = particleEffect;
		}

		@Override
		public void draw(Batch batch, float parentAlpha) {
			particleEffect.draw(batch);
		}

		@Override
		public void act(float delta) {
			super.act(delta);
			particleEffect.setPosition(getX(), getY());
			particleEffect.update(delta);
		}

		public void start() {
			particleEffect.start();
		}

		public void allowCompletion() {
			particleEffect.allowCompletion();
		}

	}

	public ParticlesScreen() {
		ParticleCache.Load();
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		NoteOn noteOn = new NoteOn(0, (int) screenX * 127 / WIDTH, (int) (HEIGHT - screenY) * 127 / HEIGHT);
		onMidiNoteOn(noteOn);
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		NoteOn noteOn = new NoteOn(0, (int) screenX * 127 / WIDTH, (int) (HEIGHT - screenY) * 127 / HEIGHT);
		onMidiNoteOn(noteOn);
		return true;
	}

	@Override
	public void onMidiNoteOn(NoteOn noteOn) {
		ParticleEffectActor particle = new ParticleEffectActor(ParticleCache.getParticleEffect(1));
		particle.setPosition((float) noteOn.getNote() * WIDTH / 127, (float) (noteOn.getVelocity()) * HEIGHT / 127);
		gameGroup.addActor(particle);
	}
}
