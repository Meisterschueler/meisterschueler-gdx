package de.meisterschueler.gdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.meisterschueler.gdx.Meisterschueler;
import de.meisterschueler.gpgs.ScoreService;

public class GpgsScreen extends MidiScreen {
	
	ScoreService scoreService;

	private Table table;
	private Label labelLogin;
	private TextButton buttonLogin;
	private TextButton buttonLeaderboard;
	
	public GpgsScreen() {
		scoreService = ((Meisterschueler) Gdx.app.getApplicationListener()).getScoreService();
		
		labelLogin = new Label("", skin);
		
		buttonLogin = new TextButton("Login", skin);
		buttonLogin.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				scoreService.loginGPGS();
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
		table.add(labelLogin).prefSize(200, 60);
		table.row();
		table.add(buttonLogin).prefSize(200, 60);
		table.row();
		table.add(buttonLeaderboard).prefSize(200, 60);
		
		uiGroup.addActor(table);
	}
	
	@Override
	public void render(float delta) {
		if (scoreService.getSignedInGPGS()) {
			labelLogin.setText("Logged in");
			buttonLogin.setText("Logout");
		} else {
			labelLogin.setText("Not logged in");
			buttonLogin.setText("Login");
		}
		super.render(delta);
	}
}
