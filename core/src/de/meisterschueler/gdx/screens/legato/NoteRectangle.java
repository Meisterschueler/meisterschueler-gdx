package de.meisterschueler.gdx.screens.legato;

import com.badlogic.gdx.math.Rectangle;

class NoteRectangle extends Rectangle {
	private long time;
	
	public NoteRectangle(long time) {
		this.time = time;
	}
	
	public long getTime() {
		return time;
	}
}
