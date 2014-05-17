package de.meisterschueler.basic;

public class ControlChange extends AbstractChannelEvent {

	private int function;
	private int value;

	public ControlChange(long time, int cable, int channel,
			int function, int value) {
		super(time, cable, channel);
		this.setFunction(function);
		this.setValue(value);
	}

	public int getFunction() {
		return function;
	}

	public void setFunction(int function) {
		this.function = function;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
