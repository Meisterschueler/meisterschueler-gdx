package de.meisterschueler.basic;

public enum Finger {
	NONE, THUMB, POINTER, MIDDLE, RING, LITTLE;

	public static Finger getFinger(int i) {
		switch (i) {
		case 1:
			return THUMB;
		case 2:
			return POINTER;
		case 3:
			return MIDDLE;
		case 4:
			return RING;
		case 5:
			return LITTLE;
		default:
			return NONE;
		}
	}
}