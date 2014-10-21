package de.meisterschueler.gdx.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldFilter;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.meisterschueler.gpgs.ScoreService;

public class GpgsScreen extends MidiScreen {
	
	ScoreService scoreService;

	private Table table;
	private Label labelLogin;
	private TextButton buttonLogin;
	private TextButton buttonEvent;
	private TextField fieldScore;
	private TextButton buttonScore;
	private TextButton buttonLeaderboard;
	
	public GpgsScreen(final ScoreService scoreService) {
		this.scoreService = scoreService;
		
		labelLogin = new Label("", skin);
		
		buttonLogin = new TextButton("Login", skin);
		buttonLogin.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				scoreService.loginGPGS();
			}
		});
		
		buttonEvent = new TextButton("Event", skin);
		buttonEvent.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				scoreService.submitEventGPGS_chromaticStart();
			}
		});
		
		fieldScore = new TextField("0", skin);
		fieldScore.setTextFieldFilter(new TextFieldFilter() {

			@Override
			public boolean acceptChar(TextField textField, char c) {
				if (c >= 48 && c <= 57) {
					return true;
				} else {
					return false;
				}
			}
		});
		
		buttonScore = new TextButton("Score", skin);
		buttonScore.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				String scores = fieldScore.getText();
				if (scores.isEmpty()) {
					scores = "0";
				}
				scoreService.submitScoreGPGS_chromatic(Integer.valueOf(scores));
			}
		});
		
		buttonLeaderboard = new TextButton("Leaderboard", skin);
		buttonLeaderboard.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				scoreService.getLeaderboardGPGS_chromatic();
			}
		});
		
		table = new Table();
		table.setFillParent(true);
		table.add(labelLogin).prefSize(60, 60);
		table.add(buttonLogin).prefSize(200, 60);
		table.row();
		table.add(buttonEvent).colspan(2).prefSize(260, 60);
		table.row();
		table.add(fieldScore).prefSize(60, 60);
		table.add(buttonScore).prefSize(200, 60);
		table.row();
		table.add(buttonLeaderboard).colspan(2).prefSize(260, 60);
		
		uiGroup.addActor(table);
	}
	
	@Override
	public void render(float delta) {
		if (scoreService.getSignedInGPGS()) {
			labelLogin.setText("OK");
			buttonLogin.setText("Logout");
		} else {
			labelLogin.setText("!OK");
			buttonLogin.setText("Login");
		}
		super.render(delta);
	}
}
