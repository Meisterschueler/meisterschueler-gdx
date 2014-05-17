package de.meisterschueler.basic;

public abstract class AbstractNote extends AbstractChannelEvent implements Comparable<AbstractNote> {
	@Override
	public int compareTo(AbstractNote rhs) {
		return (this.note < rhs.getNote() ? -1 : (this.note < rhs.getNote() ? 0 : 1 ));
	}

	protected int note;
	protected int velocity;
	
	public AbstractNote(long time, int cable, int channel, int note,
			int velocity) {
		super(time, cable, channel);
		this.note = note;
		this.velocity = velocity;
	}
	
	public AbstractNote(long time, int note, int velocity) {
		super(time);
		this.note = note;
		this.velocity = velocity;
	}

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	public int getVelocity() {
		return velocity;
	}

	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}
}
