package de.meisterschueler.scorefollower;

import java.util.ArrayList;
import java.util.List;

import de.meisterschueler.basic.Key;
import de.meisterschueler.basic.Score;
import de.meisterschueler.basic.Song;

public class BrahmsItemFactory implements MatchingItemFactory {
	private GuidoService guidoService = new GuidoService();
	
	@Override
	public List<MatchingItem> getItems() {
		List<MatchingItem> items = new ArrayList<MatchingItem>();
		for (int i = 0; i <= 6; i++) {
			Song brahmsSong = getNo(i);
			MatchingItem item = brahmsSong.toMatchingItem();
			items.add(item);
		}

		return items;
	}

	private Song getNo(int no) {
		Song song = new Song();
		List<Score> leftScores = null;
		List<Score> rightScores = null;
		switch (no) {
		case 0: {
			song.setName("1a");
			song.setId(20101L);
			song.setKey(Key.D);
			String rightPattern = "f#1/12 d a0 f#/16 g a b c#1 d e f# g/12 e c# g0/16 a b c#1 d e f# g a/12 f# d a0/16 b c#1 d e f# g a b/12 g d b0/16 c#1 d e f# g a b c#2/12 g1 e c#/16 d e f# g a b c#2 d/12 b1 g d/16 e f# g a b c#2 d e/12 c# g1 e/16 f# g a b c#2 d e f#/12 d a1 f#/16 g a b c#2 d e f# g/12 e c# g1/16 a b c#2 d e f# g a/12 f# d a1/16 b c#2 d e f# g a b/12 g d b1/16 c#2 d e f# g a b c#3/12 g2 e c#/16 d e f# g a b c#3 d/12 b2 g d/16 e f# g a b c#3 d e/12 c# g2 e/16 f# g a b c#3 d e f#/12 d a2 f# g a b c#3 d e c# g2 e f# g a b c#3 d b2 f# d e f# g a b c#3 a2 f# c# d e f# g a b g d b1 c#2 d e f# g a f# d a1 b c#2 d e f# g e c# g1 a b c#2 d e f# d a1 f# g a b c#2 d e c# g1 e f# g a b c#2 d b1 f# d e f# g a b c#2 a1 f# c# d e f# g a b g d b0 c#1 d e f# g a f# d a0 b c#1 d e f# g e c# g0 a b c#1 d e";
			String leftPattern = "d-1/12 f# a d0 c# b-1 a g f# e g c#0 e d c# b-1 a g f# a d0 f# e d c# b-1 a g b d0 g f# e d c# b-1 a c#0 e a g f# e d c# b-1 d0 g b a g f# e d c# e a c#1 b0 a g f# e d f# a d1 c# b0 a g f# e g c#1 e d c# b0 a g f# a d1 f# e d c# b0 a g b d1 g f# e d c# b0 a c#1 e a g f# e d c# b0 d1 g b a g f# e d c# e a c#2 b1 a g f# e d f# a d2/16 c# b1 a g f# e d c#/12 e a c#2/16 b1 a g f# e d c# b0/12 d1 f# b/16 a g f# e d c# b0 a/12 c#1 f# a/16 g f# e d c# b0 a g/12 b d1 g/16 f# e d c# b0 a g f#/12 a d1 f#/16 e d c# b0 a g f# e/12 g c#1 e/16 d c# b0 a g f# e d/12 f# a d1/16 c# b0 a g f# e d c#/12 e a c#1/16 b0 a g f# e d c# b-1/12 d0 f# b/16 a g f# e d c# b-1 a/12 c#0 f# a/16 g f# e d c# b-1 a g/12 b d0 g/16 f# e d c# b-1 a g f#/12 a d0 f#/16 e d c# b-1 a g f# e/12 g c#0 e/16 d c# b-1 a g f# e";

			rightScores = guidoService.gmnToScores(rightPattern);
			leftScores = guidoService.gmnToScores(leftPattern);
			break;
		}

		case 1: {
			song.setName("1b");
			song.setId(20102L);
			song.setKey(Key.D);
			String rightPattern = "f#0/12 a d1 f# e d c# b0 a g c#1 e g f# e d c# b0 a d1 f# a g f# e d c# b0 d1 g b a g f# e d c# e g c#2 b1 a g f# e d g b d2 c# b1 a g f# e g c#2 e d c# b1 a g f# a d2 f# e d c# b1 a g c#2 e g f# e d c# b1 a d2 f# a g f# e d c# b1 d2 g b a g f# e d c# e g c#3 b2 a g f# e d g b d3 c# b2 a g f# e g c#3 e d c# b2 a g f# a d3 f#/16 e d c# b2 a g f# e/12 g c#3 e/16 d c# b2 a g f# e d/12 f# b d3/16 c# b2 a g f# e d c#/12 f# a c#3/16 b2 a g f# e d c# b1/12 d2 g b/16 a g f# e d c# b1 a/12 d2 f# a/16 g f# e d c# b1 a g/12 c#2 e g/16 f# e d c# b1 a g f#/12 a d2 f#/16 e d c# b1 a g f# e/12 g c#2 e/16 d c# b1 a g f# e d/12 f# b d2/16 c# b1 a g f# e d c#/12 f# a c#2/16 b1 a g f# e d c# b0/12 d1 g b/16 a g f# e d c# b0 a/12 d1 f# a/16 g f# e d c# b0 a g/12 c#1 e g/16 f# e d c# b0 a g";
			String leftPattern = "d0/12 a-1 f# d/16 e f# g a b c#0 d e/12 c# g-1 e/16 f# g a b c#0 d e f#/12 d a-1 f#/16 g a b c#0 d e f# g/12 d b-1 g/16 a b c#0 d e f# g a/12 e c# a-1/16 b c#0 d e f# g a b/12 g d b-1/16 c#0 d e f# g a b c#1/12 a0 e c#/16 d e f# g a b c#1 d/12 a0 f# d/16 e f# g a b c#1 d e/12 c# g0 e/16 f# g a b c#1 d e f#/12 d a0 f#/16 g a b c#1 d e f# g/12 d b0 g/16 a b c#1 d e f# g a/12 e c# a0/16 b c#1 d e f# g a b/12 g d b0/16 c#1 d e f# g a b c#2/12 a1 e c#/16 d e f# g a b c#2 d/12 a1 f# d e f# g a b c#2 a1 e c# d e f# g a b f# d b0 c#1 d e f# g a f# c# a0 b c#1 d e f# g d b0 g a b c#1 d e f# d a0 f# g a b c#1 d e c# g0 e f# g a b c#1 d a0 f# d e f# g a b c#1 a0 e c# d e f# g a b f# d b-1 c#0 d e f# g a f# c# a-1 b c#0 d e f# g d b-1 g a b c#0 d e f# d a-1 f# g a b c#0 d e c# g-1 e f# g a b c#0";

			rightScores = guidoService.gmnToScores(rightPattern);
			leftScores = guidoService.gmnToScores(leftPattern);
			break;
		}

		case 2: {
			song.setName("1c");
			song.setId(20103L);
			song.setKey(Key.D);
			String rightPattern = "f#1/16 d a0 f# d/20 e f# g a b c#1 d e f# g/16 e c# g0 e/20 f# g a b c#1 d e f# g a/16 f# d a0 f#/20 g a b c#1 d e f# g a b/16 g d b0 g/20 a b c#1 d e f# g a b c#2/16 g1 e c# a0/20 b c#1 d e f# g a b c#2 d/16 b1 g d b0/20 c#1 d e f# g a b c#2 d e/16 c# g1 e c#/20 d e f# g a b c#2 d e f#/16 d a1 f# d/20 e f# g a b c#2 d e f# g/16 e c# g1 e/20 f# g a b c#2 d e f# g a/16 f# d a1 f#/20 g a b c#2 d e f# g a b/16 g d b1 g/20 a b c#2 d e f# g a b c#3/16 g2 e c# a1/20 b c#2 d e f# g a b c#3 d/16 b2 g d b1/20 c#2 d e f# g a b c#3 d e/16 c# g2 e c#/20 d e f# g a b c#3 d e f#/16 d a2 f# d e f# g a b c#3 d e c# g2 e c# d e f# g a b c#3 d b2 g d b1 c#2 d e f# g a b c#3 a2 f# c# a1 b c#2 d e f# g a b g d b1 g a b c#2 d e f# g a f# d a1 f# g a b c#2 d e f# g e c# g1 e f# g a b c#2 d e f# d a1 f# d e f# g a b c#2 d e c# g1 e c# d e f# g a b c#2 d b1 g d b0 c#1 d e f# g a b c#2 a1 f# c# a0 b c#1 d e f# g a b g d b0 g a b c#1 d e f# g a f# d a0 f# g a b c#1 d e f# g e c# g0 e f# g a b c#1 d e";
			String leftPattern = "d-2/16 f# a d-1 f# e d c# b-2 a g f# e g c#-1 e g f# e d c# b-2 a g f# a d-1 f# a g f# e d c# b-2 a g b d-1 g b a g f# e d c# b-2 a c#-1 e a c#0 b-1 a g f# e d c# b-2 d-1 g b d0 c# b-1 a g g& e d c# e a c#0 e d c# b-1 a g f# e d f# a d0 f# e d c# b-1 a g f# e g c#0 e g f# e d c# b-1 a g f# a d0 f# a g f# e d c# b-1 a g b d0 g b a g f# e d c# b-1 a c#0 e a c#1 b0 a g f# e d c# b-1 d0 g b d1 c# b0 a g f# e d c# e a c#1 e d c# b0 a g f# e d f# a d1 f#/20 e d c# b0 a g f# e d c#/16 e a c#1 e/20 d c# b0 a g f# e d c# b-1/16 d0 g b d1/20 c# b0 a g f# e d c# b-1 a/16 c#0 f# a c#1/20 b0 a g f# e d c# b-1 a g/16 b d0 g b/20 a g f# e d c# b-1 a g f#/16 a d0 f# a/20 g f# e d c# b-1 a g f# e/16 g c#0 e g/20 f# e d c# b-1 a g f# e d/16 f# a d0 f#/20 e d c# b-1 a g f# e d c#/16 e a c#0 e/20 d c# b-1 a g f# e d c# b-2/16 d-1 g b d0/20 c# b-1 a g f# e d c# b-2 a/16 c#-1 f# a c#0/20 b-1 a g f# e d c# b-2 a g/16 b d-1 g b/20 a g f# e d c# b-2 a g f#/16 a d-1 f# a/20 g f# e d c# b-2 a g f# e/16 g c#-1 e g/20 f# e d c# b-2 a g f# e";

			rightScores = guidoService.gmnToScores(rightPattern);
			leftScores = guidoService.gmnToScores(leftPattern);
			break;
		}

		case 3: {
			song.setName("2a");
			song.setId(20201L);
			song.setKey(Key.A);
			String rightPattern = "{a-1/16,c#0} {b-1,d0} {c#,e} {d,f#} {e,g#} {f#,a} {g#,b} {a,c#1} {b0/8,d1} _/8 _/4 {c#0/16,e} {d,f#} {e,g#} {f#,a} {g#,b} {a,c#1} {b0,d1} {c#,e} {d/8,f#} _/8 _/4 {e0/16,g#} {f#,a} {g#,b} {a,c#1} {b0,d1} {c#,e} {d,f#} {e,g#} {f#/8,a} _/8 _/4 {g#0/16,b} {a,c#1} {b0,d1} {c#,e} {d,f#} {e,g#} {f#,a} {g#,b} {a/8,c#2} _/8 _/4 {b0/16,d1} {c#,e} {d,f#} {e,g#} {f#,a} {g#,b} {a,c#2} {b1,d2} {c#/8,e} _/8 _/4 {d1/16,f#} {e,g#} {f#,a} {g#,b} {a,c#2} {b1,d2} {c#,e} {d,f#} {e/8,g#} _/8 _/4 {f#1/16,a} {g#,b} {a,c#2} {b1,d2} {c#,e} {d,f#} {e,g#} {f#,a} {g#/8,b} _/8 _/4 {a/16,c#3} {g#2,b} {f#,a} {e,g#} {d,f#} {c#,e} {b1,d2} {a1,c#2} {g#1/8,b} _/8 _/4 {f#2/16,a} {e,g#} {d,f#} {c#,e} {b1,d2} {a1,c#2} {g#1,b} {f#,a} {e/8,g#} _/8 _/4 {d2/16,f#} {c#,e} {b1,d2} {a1,c#2} {g#1,b} {f#,a} {e,g#} {d,f#} {c#/8,e} _/8 _/4 {b/16,d2} {a1,c#2} {g#1,b} {f#,a} {e,g#} {d,f#} {c#,e} {b0,d1} {a0/8,c#1} _/8 _/4 {g#/16,b} {f#,a} {e,g#} {d,f#} {c#,e} {b0,d1} {a0,c#1} {g#0,b} {f#/8,a} _/8 _/4 {e1/16,g#} {d,f#} {c#,e} {b0,d1} {a0,c#1} {g#0,b} {f#,a} {e,g#} {d/8,f#} _/8 _/4 {c#1/16,e} {b0,d1} {a0,c#1} {g#0,b} {f#,a} {e,g#} {d,f#} {c#,e} {b-1/8,d0} _/8 _/4";
			String leftPattern = "_/2 {b-1/16,d0} {c#,e} {d,f#} {e,g#} {f#,a} {g#,b} {a,c#1} {b0,d1} {c#/8,e} _/8 _/4 {d0/16,f#} {e,g#} {f#,a} {g#,b} {a,c#1} {b0,d1} {c#,e} {d,f#} {e/8,g#} _/8 _/4 {f#0/16,a} {g#,b} {a,c#1} {b0,d1} {c#,e} {d,f#} {e,g#} {f#,a} {g#/8,b} _/8 _/4 {a0/16,c#1} {b0,d1} {c#,e} {d,f#} {e,g#} {f#,a} {g#,b} {a,c#2} {b1/8,d2} _/8 _/4 {c#1/16,e} {d,f#} {e,g#} {f#,a} {g#,b} {a,c#2} {b1,d2} {c#,e} {d/8,f#} _/8 _/4 {e1/16,g#} {f#,a} {g#,b} {a,c#2} {b1,d2} {c#,e} {d,f#} {e,g#} {f#/8,a} _/8 _/4 {g#1/16,b} {a,c#2} {b1,d2} {c#,e} {d,f#} {e,g#} {f#,a} {g#,b} _/2 {g#,b} {f#,a} {e,g#} {d,f#} {c#,e} {b1,d2} {a1,c#2} {g#1,b} {f#/8,a} _/8 _/4 {e2/16,g#} {d,f#} {c#,e} {b1,d2} {a1,c#2} {g#1,b} {f#,a} {e,g#} {d/8,f#} _/8 _/4 {c#2/16,e} {b1,d2} {a1,c#2} {g#1,b} {f#,a} {e,g#} {d,f#} {c#,e} {b0/8,d1} _/8 _/4 {a/16,c#2} {g#1,b} {f#,a} {e,g#} {d,f#} {c#,e} {b0,d1} {a0,c#1} {g#0/8,b} _/8 _/4 {f#1/16,a} {e,g#} {d,f#} {c#,e} {b0,d1} {a0,c#1} {g#0,b} {f#,a} {e/8,g#} _/8 _/4 {d1/16,f#} {c#,e} {b0,d1} {a0,c#1} {g#0,b} {f#,a} {e,g#} {d,f#} {c#/8,e} _/8 _/4 {b/16,d1} {a0,c#1} {g#0,b} {f#,a} {e,g#} {d,f#} {c#,e} {b-1,d0}";

			rightScores = guidoService.gmnToScores(rightPattern);
			leftScores = guidoService.gmnToScores(leftPattern);
			break;
		}

		case 4: {
			song.setName("2b");
			song.setId(20202L);
			song.setKey(Key.A);
			String rightPattern = "{a0/12,c#1} {g#0,b} {f#,a} {e,g#} {d,f#} {c#,e} {b-1/8,d0} _/8 _/4 {c#1/12,e} {b0,d1} {a0,c#1} {g#0,b} {f#,a} {e,g#} {d/8,f#} _/8 _/4 {g#1/12,b} {f#,a} {e,g#} {d,f#} {c#,e} {b0,d1} {a0/8,c#1} _/8 _/4 {b/12,d2} {a1,c#2} {g#1,b} {f#,a} {e,g#} {d,f#} {c#/8,e} _/8 _/4 {d2/12,f#} {c#,e} {b1,d2} {a1,c#2} {g#1,b} {f#,a} {e/8,g#} _/8 _/4 {f#2/12,a} {e,g#} {d,f#} {c#,e} {b1,d2} {a1,c#2} {g#1/8,b} _/8 _/4 {a2/12,c#3} {g#2,b} {f#,a} {e,g#} {d,f#} {c#,e} {b1/8,d2} _/8 _/4 {c#/12,e} {d,f#} {e,g#} {f#,a} {g#,b} {a,c#3} {b2/8,d3} _/8 _/4 {a1/12,c#2} {b1,d2} {c#,e} {d,f#} {e,g#} {f#,a} {g#/8,b} _/8 _/4 {f#1/12,a} {g#,b} {a,c#2} {b1,d2} {c#,e} {d,f#} {e/8,g#} _/8 _/4 {d1/12,f#} {e,g#} {f#,a} {g#,b} {a,c#2} {b1,d2} {c#/8,e} _/8 _/4 {b0/12,d1} {c#,e} {d,f#} {e,g#} {f#,a} {g#,b} {a/8,c#2} _/8 _/4 {e0/12,g#} {f#,a} {g#,b} {a,c#1} {b0,d1} {c#,e} {d/8,f#} _/8 _/4 {c#0/12,e} {d,f#} {e,g#} {f#,a} {g#,b} {a,c#1} {b0/8,d1} _/8 _/4";
			String leftPattern = "_/2 {b0/12,d1} {a0,c#1} {g#0,b} {f#,a} {e,g#} {d,f#} {c#/8,e} _/8 _/4 {d1/12,f#} {c#,e} {b0,d1} {a0,c#1} {g#0,b} {f#,a} {e/8,g#} _/8 _/4 {a1/12,c#2} {g#1,b} {f#,a} {e,g#} {d,f#} {c#,e} {b0/8,d1} _/8 _/4 {c#2/12,e} {b1,d2} {a1,c#2} {g#1,b} {f#,a} {e,g#} {d/8,f#} _/8 _/4 {e2/12,g#} {d,f#} {c#,e} {b1,d2} {a1,c#2} {g#1,b} {f#/8,a} _/8 _/4 {g#2/12,b} {f#,a} {e,g#} {d,f#} {c#,e} {b1,d2} {a1/8,c#2} _/8 _/4 {b/12,d3} {a2,c#3} {g#2,b} {f#,a} {e,g#} {d,f#} _/2 {b1,d2} {c#,e} {d,f#} {e,g#} {f#,a} {g#,b} {a/8,c#3} _/8 _/4 {g#1/12,b} {a,c#2} {b1,d2} {c#,e} {d,f#} {e,g#} {f#/8,a} _/8 _/4 {e1/12,g#} {f#,a} {g#,b} {a,c#2} {b1,d2} {c#,e} {d/8,f#} _/8 _/4 {c#1/12,e} {d,f#} {e,g#} {f#,a} {g#,b} {a,c#2} {b1/8,d2} _/8 _/4 {a0/12,c#1} {b0,d1} {c#,e} {d,f#} {e,g#} {f#,a} {g#/8,b} _/8 _/4 {d0/12,f#} {e,g#} {f#,a} {g#,b} {a,c#1} {b0,d1} {c#/8,e} _/8 _/4 {b-1/12,d0} {c#,e} {d,f#} {e,g#} {f#,a} {g#,b}";

			rightScores = guidoService.gmnToScores(rightPattern);
			leftScores = guidoService.gmnToScores(leftPattern);
			break;
		}

		case 5: {
			song.setName("8a");
			song.setId(20801L);
			song.setKey(Key.C);
			String pattern1 = 
					"\\repeatBegin d0/16 c e g b c1 a0 f \\repeatEnd " + 
							"\\repeatBegin d& c e g b c1 a&0 f \\repeatEnd " +
							"\\repeatBegin e& d f# a c#1 d b&0 g \\repeatEnd " +
							"\\repeatBegin f e g# b d#1 e c a0 \\repeatEnd " +
							"\\repeatBegin g f a c1 e f d b&0 \\repeatEnd " +
							"\\repeatBegin g& f a c1 e f d& b&0 \\repeatEnd " +
							"\\repeatBegin a&0 g b d1 f# g e& c \\repeatEnd " +
							"\\repeatBegin b&0 a c#1 e g# a f d \\repeatEnd " +
							"\\repeatBegin c b0 d#1 f# a# b g e \\repeatEnd";
			String pattern2 = "d c e g b c2 a1 f d c a0 f d c a-1 f";

			int leftFingers1[] = { 4, 5, 4, 3, 2, 1, 2, 3 };
			int leftFingers2[] = { 4, 5, 4, 3, 2, 1, 2, 3, 4, 1, 2, 3 };
			int rightFingers1[]  = { 2, 1, 2, 3, 4, 5, 4, 3 };
			int rightFingers2[]  = { 2, 1, 2, 3, 4, 5, 4, 3, 2, 1, 4, 3 };

			leftScores = guidoService.gmnToScores(pattern1, leftFingers1);
			leftScores.addAll(guidoService.gmnToScores(pattern2, leftFingers2));

			rightScores = guidoService.gmnToScores(guidoService.oneOctaveUp(pattern1), rightFingers1);
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(pattern2), rightFingers2));

			break;
		}

		case 6: {
			song.setName("8b");
			song.setId(20802L);
			song.setKey(Key.C);
			String pattern1 = 
					"\\repeatBegin d-1/16 c e g b c0 e g b c1 e g b c2 a1 f d c a0 f d c a-1 f \\repeatEnd " +
							"\\repeatBegin d& c e g b c0 e g b c1 e g b c2 a&1 f d& c a&0 f d& c a&-1 f \\repeatEnd " +
							"\\repeatBegin e& d f# a c#0 d f# a c#1 d f# a c#2 d b&1 g e& d b&0 g e& d b&-1 g \\repeatEnd " +
							"\\repeatBegin f e g# b d#0 e g# b d#1 e g# b d#2 e c a1 f e c a0 f e c a-1 \\repeatEnd " +
							"\\repeatBegin g f a c0 e f a c1 e f a c2 e f d b&1 g f d b&0 g f d b&-1 \\repeatEnd " +
							"\\repeatBegin g& f a c0 e f a c1 e f a c2 e f d& b&1 g& f d& b&0 g& f d& b&-1 \\repeatEnd " +
							"\\repeatBegin a& g b d0 f# g b d1 f# g b d2 f# g e& c a&1 g e& c a&0 g e& c \\repeatEnd " +
							"\\repeatBegin b&-1 a c#0 e g# a c#1 e g# a c#2 e g# a f d b&1 a f d b&0 a f d \\repeatEnd " +
							"\\repeatBegin c b-1 d#0 f# a# b d#1 f# a# b d#2 f# a# b g e c b1 g e c b0 g e \\repeatEnd";
			String pattern2left = "d c e g b c1 e g b c2 e g c3/4 {c0,g0,c1} _/4"; 
			String pattern2right = "d c e g b c2 e g b c3 e g c4/4 {e1,c2} _/4";

			int leftFingers1[] = { 4, 5, 4, 3, 2, 1, 4, 3, 2, 1, 4, 3, 2, 1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3 };
			int leftFingers2[] = { 4, 5, 4, 3, 2, 1, 4, 3, 2, 1, 4, 3, 1,   5, 2, 1 };
			int rightFingers1[]  = { 2, 1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4, 5, 4, 3, 2, 1, 4, 3, 2, 1, 4, 3 };
			int rightFingers2[]  = { 2, 1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 5,   1, 5 };

			leftScores = guidoService.gmnToScores(pattern1, leftFingers1);
			leftScores.addAll(guidoService.gmnToScores(pattern2left, leftFingers2));

			rightScores = guidoService.gmnToScores(guidoService.oneOctaveUp(pattern1), rightFingers1);
			rightScores.addAll(guidoService.gmnToScores(guidoService.oneOctaveUp(pattern2right), rightFingers2));


			break;
		}
		
		default:
			return null;

		}

		song.setLeftScores(leftScores);
		song.setRightScores(rightScores);

		return song;
	}
}
