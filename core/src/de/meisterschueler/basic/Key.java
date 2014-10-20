package de.meisterschueler.basic;

public enum Key {
	Des(1,-5), As(8,-4), Es(3,-3), B(10,-2), F(5,-1), C(0,0), G(7,1), D(2,2), A(9,3), E(4,4), H(11,5), Fis(6,6), 
	
	Cis(1,7), Gis(8,8), Dis(3,9), Ais(10,10), Eis(5,11),
	Fes(4,-8), Ces(11,-7), Ges(6,-6);

	private int pitch;
	private int accidental;
	
	Key(int pitch, int accidental) {
		this.pitch = pitch;
		this.accidental = accidental;
	}
	
	public Key transposeByPitch(int pitch) {
		int sollPitch = (this.pitch + pitch + 144) % 12;
		for (Key key : Key.values()) {
			if (key.pitch == sollPitch) {
				return key;
			}
		}
		
		return C;	// TODO: Throw hier etwas
	}
	
	public Key transposeByAccidental(int accidental) {
		int sollAccidental = this.accidental + accidental;
		for (Key key : Key.values()) {
			if (key.accidental == sollAccidental) {
				return key;
			}
		}
		
		return C;	// TODO: Throw hier etwas
	}
	
	public static Key getKeyByPitch(int pitch) {
		return C.transposeByPitch(pitch);
	}
}