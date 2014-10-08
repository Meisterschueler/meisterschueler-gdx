package de.meisterschueler.gdx.screens;

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

	private Label progressValueLabel;
	private Label progressUnitLabel;
	
	private Label timeValueLabel;
	private Label timeUnitLabel;
	
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

		progressValueLabel = new Label("0", skin, "huge");
		progressUnitLabel = new Label("%", skin, "huge");
		
		timeValueLabel = new Label("", skin);
		timeUnitLabel = new Label("s", skin);
		
		scoresProgress = new ProgressBar(LOWEST_NOTE, HIGHEST_NOTE, 1, false, skin);

		table = new Table();
		table.setPosition(0, 0);
		table.setSize(WIDTH, HEIGHT);
		table.add(progressValueLabel).right();
		table.add(progressUnitLabel).left();
		table.row();
		table.add(scoresProgress).colspan(2).prefWidth(WIDTH);
		table.row();
		table.add(timeValueLabel).right();
		table.add(timeUnitLabel).right();
		

		highscoresButton = new TextButton("Highscores", skin);
		highscoresButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		highscoresButton.setPosition(WIDTH - BUTTON_WIDTH, HEIGHT - 2 * BUTTON_HEIGHT);
		highscoresButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				scoreService.getLeaderboardGPGS_chromatic();
			}
		});

		achievmentsButton = new TextButton("Achievments", skin);
		achievmentsButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		achievmentsButton.setPosition(WIDTH - BUTTON_WIDTH, HEIGHT - 3 * BUTTON_HEIGHT);
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
		if (status == Status.RUNNING) {
			deltaTime = System.currentTimeMillis() - startTime;
		}

		progressValueLabel.setText(String.format("%2d", 100*(current-LOWEST_NOTE+1)/(HIGHEST_NOTE-LOWEST_NOTE+1)));
		timeValueLabel.setText(String.format("%1.3f", deltaTime / 1000.0));
		scoresProgress.setValue(current);

		super.render(delta);
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
