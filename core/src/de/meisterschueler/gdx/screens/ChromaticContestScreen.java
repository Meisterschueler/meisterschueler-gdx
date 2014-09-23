package de.meisterschueler.gdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

import de.meisterschueler.basic.NoteOn;
import de.meisterschueler.gpgs.ScoreService;

public class ChromaticContestScreen extends MidiScreen {

	private Label timeLabel;
	private Label timeValue;
	private ProgressBar scoresProgress;
	private Table table;
	private TextButton highscoresButton;
	private TextButton achievmentsButton;

	static final int LOWEST_NOTE = 48;
	static final int HIGHEST_NOTE = 55;
	int current = 0;

	long startTime;
	long deltaTime;

	private enum Status {
		READY, RUNNING, FINISHED
	};

	private Status status;

	private ScoreService scoreService;

	public ChromaticContestScreen(final ScoreService scoreService) {
		super();

		this.scoreService = scoreService;

		timeLabel = new Label("Time: ", skin);
		timeValue = new Label("", skin);
		scoresProgress = new ProgressBar(LOWEST_NOTE, HIGHEST_NOTE, 1, false, skin);

		table = new Table();
		table.setPosition(BUTTON_WIDTH, 0);
		table.setSize(Gdx.graphics.getWidth() - 2 * BUTTON_WIDTH, Gdx.graphics.getHeight());
		table.add(timeLabel).left();
		table.add(timeValue).right();
		table.row();
		table.add(scoresProgress).colspan(2);

		highscoresButton = new TextButton("Highscores", skin);
		highscoresButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		highscoresButton.setPosition(Gdx.graphics.getWidth() - BUTTON_WIDTH, Gdx.graphics.getHeight() - 2 * BUTTON_HEIGHT);
		highscoresButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				scoreService.getLeaderboardGPGS_chromatic();
			}
		});

		achievmentsButton = new TextButton("Achievments", skin);
		achievmentsButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		achievmentsButton.setPosition(Gdx.graphics.getWidth() - BUTTON_WIDTH, Gdx.graphics.getHeight() - 3 * BUTTON_HEIGHT);
		achievmentsButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				scoreService.getAchievementsGPGS();
			}
		});

		gameGroup.addActor(table);

		uiGroup.addActor(helpButton);
		uiGroup.addActor(highscoresButton);
		uiGroup.addActor(achievmentsButton);

		init();
	}

	private void init() {
		status = Status.READY;
		current = LOWEST_NOTE - 1;
		deltaTime = 0;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (status == Status.RUNNING) {
			deltaTime = System.currentTimeMillis() - startTime;
		}

		timeValue.setText(String.format("%1.3f", deltaTime / 1000.0));
		scoresProgress.setValue(current);

		updateFPS();

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
			scoreService.submitEventGPGS_chromaticStart();
			startTime = System.currentTimeMillis();
		} else if (current == HIGHEST_NOTE) {
			status = Status.FINISHED;
			long deltaTime = System.currentTimeMillis() - startTime;

			if (deltaTime >= 0 && deltaTime <= 2147483647) {
				int delta = (int) deltaTime;
				scoreService.submitScoreGPGS_chromatic(delta);
			}
			Timer.schedule(new Task() {
				@Override
				public void run() {
					init();
				}
			}, 1);
		}
	}
}
