package de.meisterschueler.gdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.meisterschueler.gdx.effects.ChromaticEffect;
import de.meisterschueler.gdx.effects.ScrollEffect;
import de.meisterschueler.gpgs.ScoreService;

public class MainMenu implements Screen {

	private Stage stage;
	private Skin skin;
	private TextureAtlas atlas;
	private Table table;
	private Button buttonScrollingNotes;
	private TextButton buttonChromaticContest;
	private TextButton buttonHighscores;
	private TextButton buttonAchievments;
	private Button buttonExit;

	public MainMenu() {
		stage = new Stage();

		Gdx.input.setInputProcessor(stage);

		atlas = new TextureAtlas(Gdx.files.internal("uiskin.atlas"));
		skin = new Skin(Gdx.files.internal("uiskin.json"), atlas);
		
		buttonScrollingNotes = new TextButton("SCROLLING NOTES", skin);
		buttonScrollingNotes.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				((Game)Gdx.app.getApplicationListener()).setScreen(new ScrollEffect());
			}
		});

		buttonChromaticContest = new TextButton("CHROMATIC CONTEST", skin);
		buttonChromaticContest.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				ScoreService scoreService = ((Meisterschueler)Gdx.app.getApplicationListener()).getScoreService();
				((Game)Gdx.app.getApplicationListener()).setScreen(new ChromaticEffect(scoreService));
			}
		});
		
		buttonHighscores = new TextButton("HIGHSCORES", skin);
		buttonHighscores.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				ScoreService scoreService = ((Meisterschueler)Gdx.app.getApplicationListener()).getScoreService();
				scoreService.getLeaderboardGPGS_chromatic();
			}
		});
		
		buttonAchievments = new TextButton("ACHIEVMENTS", skin);
		buttonAchievments.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				ScoreService scoreService = ((Meisterschueler)Gdx.app.getApplicationListener()).getScoreService();
				scoreService.getAchievementsGPGS();
			}
		});
		
		buttonExit = new TextButton("EXIT", skin);
		buttonExit.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
			}
		});
		
		table = new Table();
		table.setFillParent(true);
		table.add(buttonScrollingNotes).prefSize(200, 40);
		table.row();
		table.add(buttonChromaticContest).prefSize(200, 40).padBottom(10);
		table.row();
		table.add(buttonHighscores).prefSize(200, 40);
		table.row();
		table.add(buttonAchievments).prefSize(200, 40).padBottom(10);
		table.row();
		table.add(buttonExit).prefSize(200, 40);	
		
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
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		stage.dispose();
		atlas.dispose();
		skin.dispose();
	}

}
