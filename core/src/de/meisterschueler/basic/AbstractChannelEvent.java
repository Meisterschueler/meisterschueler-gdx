package de.meisterschueler.basic;

public class AbstractChannelEvent {

	protected long time;
	protected int cable;
	protected int channel;

	public AbstractChannelEvent(long time) {
		this.time = time;
		this.cable = 0;
		this.channel = 0;
	}

	public AbstractChannelEvent(long time, int cable, int channel) {
		this.time = time;
		this.cable = cable;
		this.channel = channel;
	}
	
	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public int getCable() {
		return cable;
	}

	public void setCable(int cable) {
		this.cable = cable;
	}

	public int getChannel() {
		return channel;
	}

	public void setChannel(int channel) {
		this.channel = channel;
	}

}
