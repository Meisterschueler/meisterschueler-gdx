package de.meisterschueler.gdx.screens;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MatcherScreen extends MidiScreen {
	private Table table;
	private Label labelSongName;
	private Label labelSongDescription;
	private TextButton buttonLeft;
	private TextButton buttonRight;
	
	private Table subTable;
	private Label labelCorrect;
	private Label labelCorrectValue;
	private Label labelWrong;
	private Label labelWrongValue;
	private Label labelMissed;
	private Label labelMissedValue;
	private Label labelOpen;
	private Label labelOpenValue;

	public MatcherScreen() {
		
		labelSongName = new Label("", skin, "big");
		labelSongDescription = new Label("", skin);

		buttonLeft = new TextButton("Left", skin);
		buttonRight = new TextButton("Right", skin);
		
		
		labelCorrect = new Label("Good", skin);
		labelCorrectValue = new Label("0", skin);
		labelWrong = new Label("Bad", skin);
		labelWrongValue = new Label("0", skin);
		labelMissed = new Label("Missed", skin);
		labelMissedValue = new Label("0", skin);
		labelOpen = new Label("Open", skin);
		labelOpenValue = new Label("0", skin);
		
		subTable = new Table();
		subTable.setFillParent(true);
		subTable.add(labelCorrect);
		subTable.add(labelWrong);
		subTable.add(labelMissed);
		subTable.add(labelOpen);
		subTable.row();
		subTable.add(labelCorrectValue);
		subTable.add(labelWrongValue);
		subTable.add(labelMissedValue);
		subTable.add(labelOpenValue);

		table = new Table();
		table.setFillParent(true);
		table.add(labelSongName).colspan(2).prefSize(300, 60);
		table.row();
		table.add(labelSongDescription).colspan(2).prefSize(300, 60).padBottom(60);
		table.row();
		table.add(buttonLeft).prefSize(150, 60);
		table.add(buttonRight).prefSize(150, 60);

		uiGroup.addActor(table);
		uiGroup.addActor(subTable);
	}

	@Override
	public void render(float delta) {
		labelSongName.setText("Hanon No. 1 - F#");
		labelSongDescription.setText("Special exercise for the 3rd, 4th and 5th fingers of the hand.");

		super.render(delta);
	}
}
