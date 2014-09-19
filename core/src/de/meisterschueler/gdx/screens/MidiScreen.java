package de.meisterschueler.gdx.screens;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.meisterschueler.basic.ControlChange;
import de.meisterschueler.basic.NoteOff;
import de.meisterschueler.basic.NoteOn;
import de.meisterschueler.gdx.MainMenu;

public class MidiScreen extends InputAdapter implements Screen {

	private static final Map<Integer, Integer> noteMap;
	static {
		Map<Integer, Integer> tempMap = new HashMap<Integer, Integer>();
		tempMap.put(Keys.A, 48);
		tempMap.put(Keys.W, 49);
		tempMap.put(Keys.S, 50);
		tempMap.put(Keys.E, 51);
		tempMap.put(Keys.D, 52);
		tempMap.put(Keys.F, 53);
		tempMap.put(Keys.T, 54);
		tempMap.put(Keys.G, 55);
		tempMap.put(Keys.Z, 56);
		tempMap.put(Keys.H, 57);
		tempMap.put(Keys.U, 58);
		tempMap.put(Keys.J, 59);
		noteMap = Collections.unmodifiableMap(tempMap);
	}

	protected Stage stage;
	protected Group gameGroup;
	protected Group labelsGroup;
	protected Group uiGroup;

	protected TextureAtlas atlas;
	protected Skin skin;

	protected int BUTTON_WIDTH;
	protected int BUTTON_HEIGHT;
	protected TextButton backButton;
	protected TextButton helpButton;
	private Label fpsLabel;

	public MidiScreen() {
		stage = new Stage();
		gameGroup = new Group();
		labelsGroup = new Group();
		uiGroup = new Group();

		atlas = new TextureAtlas(Gdx.files.internal("uiskin.atlas"));
		skin = new Skin(Gdx.files.internal("uiskin.json"), atlas);

		InputMultiplexer inputMultiplexer = new InputMultiplexer();
		inputMultiplexer.addProcessor(stage);
		inputMultiplexer.addProcessor(this);

		Gdx.input.setInputProcessor(inputMultiplexer);
		Gdx.input.setCatchBackKey(true);

		BUTTON_WIDTH = Gdx.app.getGraphics().getWidth()/7;
		BUTTON_HEIGHT = Gdx.app.getGraphics().getHeight()/8;

		backButton = new TextButton("Back", skin);
		backButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		backButton.setPosition(0, Gdx.graphics.getHeight()-BUTTON_HEIGHT);
		backButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				((Game)Gdx.app.getApplicationListener()).setScreen(new MainMenu());
			}
		});

		helpButton = new TextButton("Help", skin);
		helpButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		helpButton.setPosition(Gdx.graphics.getWidth()-BUTTON_WIDTH, Gdx.graphics.getHeight()-BUTTON_HEIGHT);
		helpButton.addListener(new ClickListener() {
			private boolean showUi;

			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (showUi == true) {
					uiGroup.addAction(Actions.sequence(Actions.fadeOut(0.5f)));
					showUi = false;
				} else {
					uiGroup.addAction(Actions.sequence(Actions.fadeIn(0.5f)));
					showUi = true;
				}
			}
		});

		fpsLabel = new Label("", skin);
		fpsLabel.setPosition(20, 20);
		
		labelsGroup.addActor(fpsLabel);

		uiGroup.addActor(backButton);
		uiGroup.addActor(helpButton);
		
		stage.addActor(gameGroup);
		stage.addActor(labelsGroup);
		stage.addActor(uiGroup);
	}

	public void updateFPS() {
		fpsLabel.setText("FPS: " + Gdx.graphics.getFramesPerSecond());
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.ESCAPE || keycode == Keys.BACK) {
			((Game)Gdx.app.getApplicationListener()).setScreen(new MainMenu());
		}

		// Parse noteOn
		int shift = 0;
		if ((keycode == Keys.SHIFT_LEFT) || (keycode == Keys.SHIFT_RIGHT)) {
			shift = 12;
		}
		for (Integer i : noteMap.keySet()) {
			if (keycode == i) {
				int note = noteMap.get(i)+shift;
				onMidiNoteOn(new NoteOn(System.currentTimeMillis(), 0, 0, note, 10));
			}
		}

		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// Parse noteOff
		int shift = 0;
		if ((keycode == Keys.SHIFT_LEFT) || (keycode == Keys.SHIFT_RIGHT)) {
			shift = 0;
		}
		for (Integer i : noteMap.keySet()) {
			if (keycode == i) {
				int note = noteMap.get(i)+shift;
				onMidiNoteOff(new NoteOff(System.currentTimeMillis(), 0, 0, note, 64));
			}
		}

		return false;
	}

	public void onMidiNoteOn(NoteOn noteOn) {};
	public void onMidiNoteOff(NoteOff noteOff) {};
	public void onMidiControlChange(ControlChange controlChange) {};

	@Override
	public void render(float delta) {
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
