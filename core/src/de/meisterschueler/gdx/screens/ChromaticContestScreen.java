package de.meisterschueler.gdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

import de.meisterschueler.basic.NoteOn;
import de.meisterschueler.gpgs.ScoreService;

public class ChromaticContestScreen extends MidiScreen {

	private Stage stage;
	private TextureAtlas atlas;
	private Skin skin;
	
	private Label fpsLabel;
	private Label fpsValue;
	private Label timeLabel;
	private Label timeValue;
	private ProgressBar scoresProgress;
	private Table table;
	
	static final int LOWEST_NOTE = 48;
	static final int HIGHEST_NOTE = 55;
	int current = 0;

	long startTime;
	long deltaTime;

	private enum Status {READY, RUNNING, FINISHED};
	private Status status;

	private ScoreService scoreService;

	public ChromaticContestScreen(ScoreService scoreService) {
		this.scoreService = scoreService;
		
		stage = new Stage();

		atlas = new TextureAtlas(Gdx.files.internal("uiskin.atlas"));
		skin = new Skin(Gdx.files.internal("uiskin.json"), atlas);
		
		fpsLabel = new Label("FPS: ", skin);
		fpsValue = new Label("", skin);
		timeLabel = new Label("Time: ", skin);
		timeValue = new Label("", skin);
		scoresProgress = new ProgressBar(LOWEST_NOTE, HIGHEST_NOTE, 1, false, skin);
		
		table = new Table();
		table.setFillParent(true);
		table.add(fpsLabel).left();
		table.add(fpsValue);
		table.row();
		table.add(timeLabel).left();
		table.add(timeValue);
		table.row();
		table.add(scoresProgress);
		
		stage.addActor(table);
		
		init();
	}

	private void init() {
		status = Status.READY;
		current = LOWEST_NOTE-1;
		deltaTime = 0;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if (status == Status.RUNNING) {
			deltaTime = System.currentTimeMillis()-startTime;
		}
		
		fpsValue.setText(Integer.toString(Gdx.graphics.getFramesPerSecond()));
		timeValue.setText(Double.toString(deltaTime/1000.0));
		scoresProgress.setValue(current);

		stage.act(delta);
		stage.draw();
	}

	@Override
	public void onMidiNoteOn(NoteOn noteOn) {
		if (noteOn.getNote() == current + 1) {
			current++;
		}

		if (current == LOWEST_NOTE) {
			status = Status.RUNNING;
			startTime = System.currentTimeMillis();
		} else if (current == HIGHEST_NOTE) {
			status = Status.FINISHED;
			long deltaTime = System.currentTimeMillis()-startTime;

			if (deltaTime >= 0 && deltaTime <= 2147483647) {
				int delta = (int)deltaTime;
				scoreService.submitScoreGPGS_chromatic(delta);

				Timer.schedule(new Task() {
					@Override
					public void run() {
						init();
					}
				}, 1);
			}
		}
	}
}
