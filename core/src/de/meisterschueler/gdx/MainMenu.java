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
	private Button buttonExit;
	private TextButton buttonChromaticContest;

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
		
		buttonExit = new TextButton("EXIT", skin);
		buttonExit.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
			}
		});
		
		table = new Table();
		table.setFillParent(true);
		table.add(buttonScrollingNotes);
		table.row();
		table.add(buttonChromaticContest);
		table.row();
		table.add(buttonExit);	
		
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
