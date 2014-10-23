package de.meisterschueler.gdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.meisterschueler.basic.NoteOff;
import de.meisterschueler.basic.NoteOn;
import de.meisterschueler.gdx.screens.BubblesScreen;
import de.meisterschueler.gdx.screens.ChromaticContestScreen;
import de.meisterschueler.gdx.screens.GpgsScreen;
import de.meisterschueler.gdx.screens.MidiStreamScreen;
import de.meisterschueler.gdx.screens.ParticlesScreen;
import de.meisterschueler.gdx.screens.SpectrumEffect;
import de.meisterschueler.gdx.screens.SynthesiaScreen;
import de.meisterschueler.gdx.screens.legato.LegatoScreen;
import de.meisterschueler.gpgs.ScoreService;
import de.meisterschueler.utils.MidiOutput;

public class MainMenu implements Screen {

	private Stage stage;
	private Skin skin;
	private TextureAtlas atlas;
	private Table table;
	private TextButton buttonLegatoTrainer;
	private TextButton buttonBubbles;
	private TextButton buttonParticles;
	private TextButton buttonMidiStream;
	private TextButton buttonGpgs;
	private TextButton buttonChromaticContest;
	private TextButton buttonSpectrum;
	private TextButton buttonSynthesia;
	private TextButton buttonExit;

	public MainMenu() {
		stage = new Stage();

		Gdx.input.setInputProcessor(stage);
		Gdx.input.setCatchBackKey(true);

		atlas = new TextureAtlas(Gdx.files.internal("uiskin.atlas"));
		skin = new Skin(Gdx.files.internal("uiskin.json"), atlas);

		buttonLegatoTrainer = new TextButton("Legato Training", skin);
		buttonLegatoTrainer.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				((Game) Gdx.app.getApplicationListener()).setScreen(new LegatoScreen());
			}
		});

		buttonBubbles = new TextButton("Bubbles", skin);
		buttonBubbles.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				((Game) Gdx.app.getApplicationListener()).setScreen(new BubblesScreen());
			}
		});

		buttonParticles = new TextButton("Particles", skin);
		buttonParticles.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				((Game) Gdx.app.getApplicationListener()).setScreen(new ParticlesScreen(new MidiOutput() {

					@Override
					public void sendNoteOn(NoteOn noteOn) {
					}

					@Override
					public void sendNoteOff(NoteOff noteOff) {
					}

				}));
			}
		});

		buttonMidiStream = new TextButton("MIDI Stream", skin);
		buttonMidiStream.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				((Game) Gdx.app.getApplicationListener()).setScreen(new MidiStreamScreen());
			}
		});

		buttonGpgs = new TextButton("GPGS", skin);
		buttonGpgs.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				((Game) Gdx.app.getApplicationListener()).setScreen(new GpgsScreen());
			}
		});

		buttonChromaticContest = new TextButton("Chromatic Contest", skin);
		buttonChromaticContest.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				((Game) Gdx.app.getApplicationListener()).setScreen(new ChromaticContestScreen());
			}
		});

		buttonSpectrum = new TextButton("Spectrum", skin);
		buttonSpectrum.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				((Game) Gdx.app.getApplicationListener()).setScreen(new SpectrumEffect());
			}
		});

		buttonSpectrum = new TextButton("Synthesia", skin);
		buttonSpectrum.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				((Game) Gdx.app.getApplicationListener()).setScreen(new SynthesiaScreen(new MidiOutput() {
					@Override
					public void sendNoteOn(NoteOn noteOn) {
					}

					@Override
					public void sendNoteOff(NoteOff noteOff) {
					}
				}));
			}
		});

		buttonExit = new TextButton("Exit", skin);
		buttonExit.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
			}
		});

		table = new Table();
		table.setFillParent(true);
		table.add(buttonLegatoTrainer).prefSize(200, 60);
		table.add(buttonBubbles).prefSize(200, 60);
		table.row();
		table.add(buttonParticles).prefSize(200, 60);
		table.add(buttonMidiStream).prefSize(200, 60);
		table.row();
		table.add(buttonGpgs).prefSize(200, 60);
		table.add(buttonChromaticContest).prefSize(200, 60);
		table.row();
		table.add(buttonSpectrum).prefSize(200, 60);
		table.add(buttonSynthesia).prefSize(200, 60).padBottom(10);
		table.row();
		table.add(buttonExit).prefSize(200, 60);

		stage.addActor(table);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		stage.dispose();
		atlas.dispose();
		skin.dispose();
	}
}
