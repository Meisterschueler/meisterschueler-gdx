package de.meisterschueler.basic;

import java.util.ArrayList;
import java.util.List;

public class AbstractNoteCluster {
	private long time;
	private List<AbstractNote> abstractNotes = new ArrayList<AbstractNote>();

	public AbstractNoteCluster(AbstractNote note) {
		this.time = note.getTime();
		abstractNotes.add(note);
	}

	public long getTime() {
		return time;
	}

	public List<AbstractNote> getNotes() {
		return abstractNotes;
	}

	public void setNotes(List<AbstractNote> abstractNotes) {
		this.abstractNotes = abstractNotes;
	}
}
