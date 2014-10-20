package de.meisterschueler.scorefollower;

import java.util.ArrayList;
import java.util.List;

import de.meisterschueler.basic.score.Score;

public class BachItemFactory implements MatchingItemFactory {

	private GuidoService guidoService = new GuidoService();

	private List<MatchingItem> items = new ArrayList<MatchingItem>();

	@Override
	public List<MatchingItem> getItems() {
		// TODO Auto-generated method stub
		return null;
	}

	private Song getNo(int no) {
		String rightHand;
		String leftHand;
		Song song = new Song();
		switch (no) {
		case 0: {
			song.setName("Inventio 1");
			song.setId(10100L);
			song.setKey(Key.C);
			rightHand = "_/16 c1/16 d e f d e c g/8 c2 b1 c2 d/16 g1 a b c2 a1 b g d2/8 g f g e/16 a g f e g f a g f e d c e d f e d c b1 a c2 b1 d2 c b1 a g f# a g b a/8 d c2/8. d/16 b1 a g f# e g f# a g b a c2 b1 d2 c e d b1/32 c2 d/16 g b1/8 a/16 g g/8 _/8 _/4 _/16 g/16 a b c2 a1 b g f#/8 _/8 _/4 _/16 a/16 b c2 d b1 c2 a1 b/8 _/8 _/4 _/16 d2/16 c b1 a c2 b1 d2 c/8 _/8 _/4 _/16 e/16 d c b1 d2 c# e d/8 c# d e f a1 b c#2 d f#1 g# a b c2 d*5/16 e1/16 f# g# a f# g# e e2 d c e d c b1 d2 c a g# b a e f d a&1 f2 e d c/8 b1/16 a a a2 g f e g f a g*9/16 e/16 f g a f g e f*9/16 g/16 f e d f e g f*9/16 d/16 e f g e f d e*9/16 c/16 d e f d e c d e f g a f g e f g a b c3 a2 b g c3/8 g2 e d/16 c c b&1 a g f a g b& a b c2 e1 d c2 f1 b {e/1,g,c2}";
			leftHand = "_/2 _/16 c0/16 d e f d e c g/8 g-1 _/4 _/16 g0/16 a b c1 a0 b g c1/8 b0 c1 d e g0 a b c1 e0 f# g a b c1*5/16 d0/16 e f# g e f# d g/8 b-1 c0 d e f# g e b-1/8. c0/16 d/8 d-1 _/16 g/16 a b c0 a-1 b g d0/8 g f# g a/16 d e f# g e f# d a/8 d1 c d g0/16 g1 f e d f e g f/8 e f d e/16 a g f e g f a g/8 f g e f/16 b& a g f a g b& a g f e d f e g f e d c b0 d1 c e d c b0 a g# b a c1 b0/8 e d1/8. e/16 c b0 a g f# a g# b a c1 b0 d1 c e d f e/8 a0 e1 e0 a a-1 _/4 _/16 e1/16 d c b0 d1 c# e d*9/16 a0/16 b c1 d b0 c1 a0 b*9/16 d1/16 c b0 a c1 b0 d1 c*9/16 g0/16 a b& c1 a0 b& g a/8 b& a g f d1 c b&0 a f1 e d e/16 d0 e f g e f d e/8 c d e f/16 d e f g/8 g-1 {c/1,c0}";

			break;
		}

		case 1: {
			song.setName("Inventio 2");
			song.setId(10200L);
			song.setKey(Key.Es);
			rightHand = "_/8 c2/16 b1 c2 d e& g1 a& b& a& f f2 e& d c b1 a&2 g f e& d c b1 c2 d c d d/8. c/32 d e&/16 d c d e& f g/8 g f _/8 f f e& _/8 d e& a&1/8. f/16 b& f g/8 e&2/8. d/16 e& b&1 c2 e& a& g f e& d c b&1 d2 g f e& d c b&1 a& c2 f c d/8. d/16 e& b&1 c2 b&1 g2*5/16 b&1/16 c2 b&1 a&2*5/16 b&1/16 e&2 d g f a& g c3 b&2 a& g f b& a& b& g b& a& b& e& g f g c d e& f d e& f g e& f g a& b& a& c3 b&2 a& g f a& d*5/16 d/16 g d e& c d b&1 c2*5/16 g/16 f# a c b&1 c2/8. f#/16 e d b&/8. e/16 f#/8. g/16 g/8 g1/16 f# g a b& d e& f e& c c2 b&1 a g f# e&2 d c b&1 a g f# g a g a a/8. g/32 a b&/16 a g a b& c2 d/8 d c _/8 c c b&1 _/8 a b& e&/8. c/16 f c d/8 b&/8. a/16 b& f g b& e&2 d c b&1 a g f a d2 c b&1 a g f e& g c2 g1 a/8. a/16 b& f g f d2*5/16 f1/16 g f e&2*5/16 f1/16 b& a d2 c e& d g f e& d c f e& f d c b&1 a d2 c e& d c b&1 a c2 f*5/16 g1/16 c2 b1 e&2 d f e& a& g f e& d g f g e& d c b1 c2 d e& g1 a& b& a& f f2 e& d c b1 a&2 g f e& d c b1 c2 d c d d/8. c/32 d e&/16 d c d e& f g/8 g f _/8 f f e&/16 d g f a& g g/8. a&/16 d/8. c/16 c/1";
			leftHand = "_/1 _/1 _/8 c1/16 b0 c1 d e& g0 a& b& a& f f1 e& d c b0 a&1 g f e& d c b0 c1 d c d d/8. c/32 d e&/16 d c d e& f g/8 g f _/8 f f e& _/8 d e& a&0/8. f/16 b& f g/8 e&1/8. d/16 e& b&0 c1 e& a& g f e& d c b&0 d1 g f e& d c b&0 a& c1 f c d/8. d/16 e& b&0 c1 b&0 g1*5/16 b&0/16 c1 b&0 a&1*5/16 b&0/16 e&1 d g f a& g c2 b&1 a& g f b& a& b& g/8 e&0/16 f g a b& d e& f e& c c1 b&0 a g f# e&1 d c b&0 a g f# g a g a a/8. g/32 a b&/16 a g a b& c1 d/8 d c _/8 c c b&0 _/8 a b& e&/8. c/16 f c d/8 b&/8. a/16 b& f g b& e&1 d c b&0 a g f a d1 c b&0 a g f e& g c1 g0 a/8. a/16 b& f g f d1*5/16 f0/16 g f e&1*5/16 f0/16 b& a d1 c e& d g f e& d c f e& f d f e& f b&0 d1 c d g0 a b& c1 a0 b& c1 d b&0 c1 d e& f e& g f e& d c e& a0*5/16 d/16 g f# b& a c1 b&0 e&1 d c b&0 a d1 c d b0 f1 e& d c b&0 a& g f e& d c g/8 g-1 c0/8. d/16 e& f g/8 g f _/8 f f e&/8. f/16 e& d c b&-1 a& g f e& f g c/8 c0/16 b-1 c0 d e& g-1 a& b& a& f f0 e& d c b-1 a&0 g f e& d c b-1 c0 g-1 a& f g/8 g c/1";

			break;
		}

		case 2: {
			song.setName("Inventio 3");
			song.setId(10300L);
			song.setKey(Key.D);
			rightHand = "d1/16 e f# e g f# e d a g f# e f# d a/8 b c#2/16 d c#/8 b1 a c#2/16 d e c# f# d c# b1 e2 c# d b1 a g# e2 c# f# d c# b1 e2 c# d b1 a g# c#2 b1 d2 c# b1 a f#2/8 g#1 a a/16 b b/8. a/16 a/4 _/8 _/8 e2/8 a/8. b1/16 c#2 d# e f# g f# a g f# e b/8 b1 _/8 _/8 f# b/8. a#/16 b c#2 d e f# e g f# e d e d f# e d c# d c# e d c# b1 c#2/8 a#1 b/8. c#2/16 c#/8. b1/8 f#/16 g# a# b c#2 d c# e d c# b1 g2*13/16 e1/16 f# g# a b c#2 b1 d2 c# b1 a f#2*13/16 b1/16 e2 d c# b1 c#2 b1 d2 c# b1 a g#/8 e a/16 b c#2 b1 d2 c# b1 a f#2/8. g#1/16 a g# a d2 b1/8. a/16 a/8. c#2/16 d e f#1/8 g a b c#2 d/8. c#/16 e d c# d e d c# b1 a g f# e g f# e d a g f# e f# d a/8 b c#2/16 d c#/8 b1 a d2 d d1 d2 d d1 d2 d d1 d2 d d1 d2/16 e f# e g f# e d b/8 c# d/8. e/16 e/8. d/16 d/8 d1 g/8. a0/16 b c#1 d e f# e g f# e d b/8. c#/16 d c# d g e/8. d/16 d/4.";
			leftHand = "_/8 _/4. _/8 _/8 d0/16 e f# e g f# e d a g f# e f# d a/8 a-1 a0 a a-1 a0 a a-1 a0 a a-1 a0 a b c#1 d/16 c# e d c# b0 c#1 d e/8 e0 b/16 e f# g# a b c#1 b0 d1 c# b0 a e1/8 e0 _/8 _/8 b e1/8. f#0/16 g# a# b c#1 d c# e d c# b0 f#1/8 f#0 e1 d b/16 a# b/8 c# a#/16 g# a#/8 b/16 a g f# e d e d f# e d c# d e f#/8 f#0 b f# d b-1/4 _/8 _/16 b/16 c#0 d# e f# g f# a g f# e a*13/16 a-1/16 b c#0 d e f# e g f# e d g#/8. e/16 f# g# a g# f# e d f# e d c# b-1 c#0 a-1 a0/8 f# c# d/16 c# e d c# b-1 c#0 d e/8 e-1 a/16 c#0 d e f# g a g b a g f# g f# a g f# e f# e g f# e d a/8 a-1 _/8 _/4. _/8 _/8 d0/16 e f# e g f# e d a g f# e f# d a f# b g f# e a f# g e d c# a f# b g f# e a f# g e d c# d/8 e f# g/16 f# a g f# e f# g a/8 a-1 b/16 a c0 b-1 a g d0/4.. c#/16 b-1 a g f# g f# a g f# e f# g a/4 d/4.";

			break;
		}

		case 3: {
			song.setName("Inventio 4");
			song.setId(10400L);
			song.setKey(Key.F);
			rightHand = "d1/16 e f g a b& c# b& a g f e f/8 a d2 g1 c#2 e d/16 e f g a b& c# b& a g f e f d e f g a b&1 a2 g f e d e c d e f g a1 g2 f e d c d e f d e f g1/8 _/8 _/8 c2/16 d e c d e f1/8 _/8 b&/4 a/8 g c2/16 b&1 a g f e f g g/8. f/16 f/8 c2 c c*19/16 b&1/16 a g f e c2 d1 e f# g a b& a g f e d b& c d e f g a b c2 d e f g#1 f2 e d c b1 c2 b1 d2 c b1 a g# a g# f# e d c d e f# g# a d c2 b1 a g# f# e f# g# a b c2 f#1 e2 d c b1 a g# a b c2 d e a1 f2 e d c b1 a2 g# f# e a/8. d/16 b1/8. a/16 a/8. a/16 b& c2 d1/8 f# a b&/16 g a b& c2 d e1 d2 c b&1 a g a/8 f2/16 e f/8 g1 e2 _/8 d/16 e f g a b& c# b& a g f e f/8 d g1/8. d2/16 c# e a1 c#2 d b1 c#2/8. d/16 d c b&1 a g f b& c# d e f g a d2 f1/8 e/16 d d/4.";
			leftHand = "_/4. _/4. d0/16 e f g a b& c# b& a g f e f/8 a d1 e0 g c#1 d0 d1 f0 g a b& c c1 e0 f g a b&/16 g a b& c1 d e0 d1 c b&0 a g a f g a b& c1 d0 c1 b&0 a g f e c d e f g a-1 g0 f e d c d b&-1 c0/8 c-1 f/16 g a b& c0 d e-1 d0 c b&-1 a g a b& c0 d e f g-1 f0 e d c b&-1 a b& c0 a-1 b& c0 f#-1/8 _/8 _/8 g/16 a b& g a b& e/8 _/8 _/8 f f0 d b-1 g# e a/16 g# a b c0 d e*2/1 e1/8 d c b0 a d1 e f d e e0 a/16 a-1 b& c0 d e& f#-1 e&0 d c b&-1 a g/8. g/16 a b& c/8 g c0 f/16 g a b c#1 d e0 d1 c# b0 a g f/8 a d1 e0 g c#1 d0/16 e f g a b& c# b& a g f e f g a/8 a-1 b&/8. c0/16 b&-1 a g b&0 a g f e f g a/8 a-1 d/4.";

			break;
		}

		case 4: {
			song.setName("Inventio 5");
			song.setId(10500L);
			song.setKey(Key.Es);
			rightHand = "_/8 e&1/16 d e&/8 f g/4 a& _/8 f/16 e& f/8 g a&/4 b& g/8 c2 b&1 a& g/16 a& b& a& g/8 f e& g b& e&2/8. c/16 d e& f/8 e& d/16 e& d c d e& c d b&1 b&2 a b& g a f g e& g f g e& f d e& c b& a g a b& g a b&1 b&2 a g a b& g a f b& a& g f g e& f d g f e& d e& c d f1 e&2 d c d e& c d e&/8 b&1/16 a& b&/8 c2 d&/4 e& _/8 c/16 b&1 c2/8 d e&/4 f _/8 d/16 c d/8 e& f/4 g e&/16 f e& d e& f d e& c c3 b&2 c3 a&2 b& g a& f a& g a& f g e& f d c3 b2 a b c3 a2 b c c3 b&2 a& b& c3 a&2 b& g c3 b&2 a& g a& f g e a& g f e f d e g1 f2 e d e f d e f/8 f1/16 e f/8 g a&/4 b& _/8 g/16 f g/8 a& b&/4 c2 a&1/8 d&2 c b&1 a/16 b& c2 b&1 a/8 g f a c2 e&/8. c/16 d& e& f/8 e& d&/16 e& d& c d& e& c d& b&1 a&2 g f g a& f g a& d& c b&1 c2 d& b&1 c2 a&1 g2 f e f g e f g c b&1 a& b& c2 a&1 b& g f2 e d e f d e f/8 a&1/16 g a&/8 b& c2/4 d& _/8 b&1/16 a& b&/8 c2 d&/4 e& c/16 a& g a& f g e& f d& b& a& b& c d& b&1 c2 a&1 c2 b&1 c2 a&1 b& g a& f e&2 d c d e& c d e&/8 e&1/16 d e&/8 f g/4 a& _/8 f/16 e& f/8 g a&/4 b& g/8 c2 b&1 a& g/16 a& b& a& g/8 f e& f2 e& d& c/16 d& e& d& c/8 b&1 a& c2 e& a&/8. f/16 g a& b&/8 a& g*5/32 f/32 e& f f/4 e&/2";
			leftHand = "e&0/4 e&-1 _/16 e&1/16 d e& c d b&0 c1 a&0 c1 b&0 c1 a&0 b& g a& f e&1 d c d e& c d e&0 e&1 d c d e& c d b&0 e&1 d c b&0 c1 a&0 b& g c1 b&0 a& g a& f g c b& a g a b& g a b&/8 b&-1/16 a b&/8 c0 d/4 e& _/8 c/16 b&-1 c0/8 d e&/4 f d/8 g f e& d/16 e& f e& d/8 c b&-1 d0 f a&/8. f/16 g a& b&/8 a& g/16 a& g f g a& f g e& e&1 d& e& c d& b&0 c1 a&0 b& a& g a& b& g a& f f1 e& f d e& c d b&0 c1 b&0 a b& c1 a&0 b& g a& f g e& f d e& c/8 c/16 b-1 c0/8 d e&/4 f _/8 d/16 c d/8 e& f/4 g e&/8 a& g f e/16 f g f e/8 d c e g b&/8. g/16 a& b& c1/8 b&0 a&/16 b& a& g a& b& g a& f f1 e& f d& e& c d& b&0 d&1 c d& b&0 c1 a&0 b& g f1 e d e f d e f0 f1 e& d& e& f d& e& c f e& d& c d& b&0 c1 a0 d&1 c b&0 a b& g a c b& a g a b& g a b&/8 b&-1/16 a b&/8 c0 d&/4 e& _/8 a&-1/16 g a&/8 b& c0/4 d& _/8 g-1/16 f g/8 a& b&/4 c0 f-1/16 c1 b&0 c1 a&0 b& g a& f a& g a& f g e& f d& f e& f d& e& c d& b&-1 a&0 g f g a& f g a&/8 e&/16 d e&/8 f g/4 a& _/8 f/16 e& f/8 g a&/4 b& g/16 a& g f g a& f g e& e&1 d e& c d b&0 c1 a&0 c1 b&0 c1 a&0 b& g a& f e&1 d c d e& c d e&0 e&1 d c d e& c d b&0 e&1 d c b&0 c1 a&0 b& g c1 b&0 a& g a& f g e& a& g f e& f d& e& c f e& d& c d& b&-1 c0 f-1 e&0 d c d e& c d e&/8 g-1 a& b& e&/2";

			break;
		}

		case 5: {
			song.setName("Inventio 6");
			song.setId(10600L);
			song.setKey(Key.E);
			rightHand = "_/16 e2/8 d# d c# b1 a g# f# g#/32 a g#/16 b/32 a b/16 g#/32 f# g#/16 e/32 d# e/8 f# g# a b c#2 d# e/16 d# c# b1 e2/8 e1 _/8 _/16 g#2 e c# e/32 d# e/16 c# a#1 f#/8 f#2 d# b1 d#2/32 c# d#/16 b1 g# e/8 e2 c# a#1 g#2 f# e d# c# b1/16 a# e/32 d# e/16 a#/32 g# a#/16 b/32 a# b/16 d# c# b c# a# b/8 _/16 b/16 d#2 f# b/8 _/16 b/16 f# d# b1 f# d# b0 _/8 b/8 c#1 d# e f# g# a# b/16 a# g# f# b/8 b0 _/8 _/16 b2 a# a g# f# e d# c# d#/32 e d#/16 f#/32 e f#/16 d#/32 c# d#/16 b1/32 a# b/16 d#2/32 c# d#/16 g#/32 f## g#/16 c#/32 b1 c#2/16 e/32 d# e/16 a#/32 g# a#/16 d#/32 c# d#/16 g#/32 f## g#/16 b/32 a# b/16 d#/32 c## d#/16 a#/32 g# a#/16 f##/32 e# f##/16 d#/32 c## d#/16 a/8 g# f# e/32 d# e/16 c#/32 b# c#/16 e/32 d# e/16 g#/8 f# e d#/32 c# d#/16 b1/32 a# b/16 c#2/32 b1 c#2/16 e/8 d# c# b1/32 a# b/16 g#/32 f## g#/16 a/32 g# a/16 c#2/32 b1 c#2/16 a1/32 g# a/16 e2/32 d# e/16 f##1/32 e# f##/16 a#/32 g# a#/16 b0/32 a# b/16 g#1 e c# a#0 f##1 g# d# b0 g# _/8 e1/8 f# g# a b c#2 d# e/16 d# c# b1 e2/8. b1/32 a b/16 g#/32 f# g#/16 e2/8 d# d c# b1 a g# f# g#/32 a g#/16 b/32 a b/16 g#/32 f# g#/16 e/32 d# e/8 f# a b d#2 f# d#1 e g# a c#2 e c#1 f# a b c#2 d# e c# a1 f#2/4.. e/16 d# c# b1 a g# e/32 d# e/16 g#/32 f# g#/16 b/32 a b/16 g#/32 f# g#/16 b/32 a b/16 e2/32 d# e/16 b1 g# e _/8";
			leftHand = "e0/8 f# g# a b c#1 d# e/16 d# c# b0 e1/8 e0 _/8 _/16 e d# d c# b-1 a g# f# g#/32 a g#/16 b/32 a b/16 g#/32 f# g#/16 e/32 d# e/8 c#0 e f# a# c#1 d#-1 b d#0 e g# b c#-1 c#0 e f# g# a# b e g# f#/16 c#1 a#0 c#1 e0 c#1 d#0/8 e f# b-1/16 b0/32 a# b/16 f#/32 e f#/16 d#/32 c# d#/16 f#/32 e f#/16 d#/32 c# d#/16 b-1/32 a# b/4 b-2/8 _/16 b0 a# a g# f# e d# c# d#/32 e d#/16 f#/32 e f#/16 d#/32 c# d#/16 b-1/32 a# b/8 c#0 d# e f# g# a# b/16 a# g# f# b/8 b-1 _/8 g#0 b d#1 f##0 a# d#1 g#0 b d#1 d#0 d#1 c# c c# d# g#0 c#1 b0 a# b c#1 f#0 b d#1 f##0 g# a# d# g# b c# _/8 c#1/4 a#0/8 f## g# c# d# g#-1 _/16 g#/16 g#0 f#/8 e d# d c# b-1 a g# f# g#/32 a g#/16 b/32 a b/16 g#/32 f# g#/16 e/32 d# e/8 f# g# a b c#0 d# e/16 d# c# b-1 e0/8. b/32 a b/16 g#/32 f# g#/16 c#1/8 a0 f# a/32 g# a/16 f# d# b-1/8 b0 g# e g#/32 f# g#/16 e c# a-1/8 a0 f# d# c#1 b0 a g# f# e a/32 g# a/16 d#/32 c# d#/16 g#/32 f# g#/8 a b e _/16 e1/16 b0 g# e b g# e b-1 g# e/4 e0/8";

			break;
		}

		case 6: {
			song.setName("Inventio 7");
			song.setId(10700L);
			song.setKey(Key.G);
			rightHand = "_/8 b1/16 a g f# g e b/8 b e2/8. f#/16 d#/8 f#/16 e d# c# d# b1 b2/8 a/16 g f#/8. e/32 f# g/8 b/16 a g f# g e c3/8. b2/16 a g f# e f#/8 a/16 g f# e f# d b/8. a/16 g f# e d e f# e d c b1 a g c2 b1 a b c2 d e f# g f# e d g/8. g/16 g f# e f# f#/8. g/16 g/8 d/16 c b1 a b g d2*27/16 e/16 c#/8. d/16 d/8 a1/16 g f# e f# d c2/8. b1/16 c2/8. d/16 b1/8 b e2/8. e/16 e g f# e d c# d b1 a#/8 f#2/16 e d c# d b1 e2/8 b/16 a g f# g e a#/8 c# f# a#1 b/8. c#2/16 c#/8. b1/16 b/8 d2 e1 d2 c a1 d c2 b1 d2 g b1 a f#2 g1 e2 d# f#/16 e d# c# b1 a g f# g b e2 b1 a g f# e f# a d2 a1 g f# e d# e g c2 g1 f# e d# c# d# f# b/8 b b*5/16 d#2/16 e g a1*5/16 c#2/16 d# f# g1*5/16 b/16 d#2 e f#1 a b c2 d#1 f# g a b0/8 a1/8. f#/16 b f# g/8 e c2/8. c/16 c/8 b1 g2/8. g/16 g a g f# f#/8. e/16 e d c b1 a g a f# d#2/8 f#1 b/8. c2/16 a1/8. g/16 f#/8. e/16 e/2";
			leftHand = "e0/8 e-1 _/4 _/8 b0/16 a g f# g e b/8 b-1 _/4 _/8 f#1/16 e d# c# d# b0 e1/8 e0 _/4 _/8 e1/16 d c b0 c1 a0 d1/8 d0 _/4 _/8 d1/16 c b0 a b g c1/8. b0/16 a g f# e a/8. g/16 f# e d c b-1/8. c0/16 b-1 a g f# e/8 c0 d d-1 g/4 _/4 _/8 g/16 a b a b g d0 c# d e f# e f# d g f# g a b a b f# g f# g e a/8 a-1 d0 d-1 _/4 _/8 a0/16 g f# e f# d g a g f# e d e c# f#/8 a#-1 b g f#/4 _/8 b0/16 a g f# g e d1/8. e/16 c#/8 g/16 f# e d e c# d c# d b0 f#1/8 f#0 b/16 c1 b0 a g# f# g# e a b a g f# e f# d g a g f# e d e c# d# c# d# b-1 e0 d# e e-1 b*37/16 c1/16 b0 a g f# g b e1 b0 a g f# e f# a d#1 a0 g f# e d# e g c1 g0 f# e d#/8 b-1 _/4 _/16 g0/16 f# e d# c# d# b-1 e0 d c b-1 a g a f# g a g f# e d e c a g a f# b a b g c0*9/16 c1/16 b0 a g f# g e d#/8 e b b-1 e/2";

			break;
		}

		case 7: {
			song.setName("Inventio 8");
			song.setId(10800L);
			song.setKey(Key.F);
			rightHand = "_/8 f1/8 a f c2 f1 f2 e/16 d c d c b&1 a b& a g f/8 a c2 a1 f2 c a/16 c3 b&2 c3 a2 c3 b&2 c3 a2 c3 b&2 c3 f2 a g a f a g a f a g a d f e f d f e f d f e f b1/8 g d2 b1 f2 d g/16 a g f e f e d c d c b&1 a/8 d2/16 c b1 c2 b1 a g a g f e f e d c/8 c2/16 b1 c2/8 e1 f c2 e1 c2 d1 b c2/4 _/2 _/8 c/8 e c g c c3 b2/16 a g a g f e f e d c b&1 c2 a c a b&1 a2 c a a1 a2 b&1/8 g b& g d2 g1 g2 f/16 e& d e& d c b&1 c2 b&1 a g/8 b& d2 b&1 g2 d b& c# b& c# b& c# d a1 f2 d a f g/16 f g b& c b& d b& e b& c b& f e f a b1 a2 c# a d a b1 a2 e d e g a1 g2 b1 g2 c# g a1 g2 f/8 d b&1 d2 g1 f2 e c a1 c2 f1 e&2 d/16 f e& f d f e& f d f e& f b&1 d2 c d b&1 d2 c d b&1 d2 c d g1 b& a b& g b& a b& g b& a b& e/8 c g e b& g c2/16 d c b&1 a b& a g f g f e& d/8 g/16 f e f e d c d c b&0 a b& a g f/8 f1/16 e f/8 a0 b& f1 a0 f1 g0 e1 {a0/4,c1,f} _/2";
			leftHand = "_/4 _/2 _/8 f0/8 a f c1 f0 f1 e/16 d c d c b&0 a b& a g f/8 a c1 a0 f1 c a/16 c2 b&1 c2 a1 c2 b&1 c2 a1 c2 b&1 c2 f1 a g a f a g a f a g a d f e f d f e f d f e f b0/8 g c1 g0 e1 c f/16 g f e d e d c b0 c1 b0 a g/8 c1/16 b0 a b a g f g f e d e d c g f e f g/8 g-1 _/8 c0 e c g c c1 b0/16 a g a g f e f e d c/8 e g e c1 g0 e&1 f#0 e&1 f#0 e&1 f#0 g f/16 e& d e& d c b&-1 c0 b&-1 a g/8 g0 b& g d1 g0 g1 f/16 e& d e& d c b&0 c1 b&0 a g f g e1 g0 e1 f0 e1 g0 e1 e0 e1 f0 e f d1 f0 d1 e0 d1 f0 d1 d0 d1 b&0/8 g e g c e a f d f b-1 d0 g e c# e a-1 c#0 d-1/16 d0 c d g-1 d0 a-1 d0 b&-1 d0 g-1 d0 c-1 c0 b&-1 c0 f-1 c0 g-1 c0 a-1 c0 f-1 c0 b&-1/8 d0 f d b& f d1/16 f e& f d f e& f d f e& f b&0 d1 c d b&0 d1 c d b&0 d1 c d g0 b& a b& g b& a b& g b& a b& e/8 c f c a f b&/16 c1 b&0 a g a g f e f e d c/8 f/16 e d e d c b&-1 c0 b&-1 a g a g f c0 b&-1 a b& c0/8 c-1 f/4 _/2";

			break;
		}

		case 8: {
			song.setName("Inventio 9");
			song.setId(10900L);
			song.setKey(Key.As);
			rightHand = "c2/16 b&1 a& g a&/8 f d&2*5/16 c/16 b&1 a b&/8 g e2*5/16 f/16 g a& b&/8 g/16 e d&/8 c b&1/16 a& g a& b& d&2 c b&1 a& g f e a&/8 f2/8. g/16 a& g f e& d& c b&1/8 g2/8. a&/16 b& a& g f e d c d e f g a& b& g f e f e f/8 c/8. b&1/16 a& g f e a& g c2 b&1 a& g a&/8 f a&2*5/16 g/16 f e f/8 d b1*5/16 c2/16 d e& f/8 d/16 b1 a&/8 g/8. f/16 e& d e&/8 c e&2*5/16 d/16 c b1 c2/8 a1 f#2*5/16 g/16 a b& c3/8 a2/16 f# e&/8 d b1/4. b/8 c2/4.. d/16 d/4. c/8 c*5/16 d/16 e& d c b&1 a& g f/8 d2/8. e&/16 f e& d c b1 a g a b c2 d e& f d c b1 c2 b1 g2*5/16 f/16 e& d c*5/16 b&1/16 a g a/8 f e&2*5/16 d&/16 c d& e& g& f e& d& c b&1 a& g/8 b&2/8. a&/16 g f e& d& c b&1 a&/8 c2 e& g a&*5/16 g/16 f e& d& b&1 a&2/8 g*5/16 f/16 e& d& c a&1 g&2/8 f*5/16 e&/16 d& c b&1 a& g a& b&*5/16 a&/16 g f e f g a& b& c2 d& b&1 c2 b&1 a& g a&/8 f d&2*5/16 c/16 b&1 a b&/8 g e2*5/16 f/16 g a& b&/8 g/16 e d&/8 c b&1/16 a& g a& b& d&2 c b&1 a& g f e c2/8. d&/16 g1/4. f/8 f/2.";
			leftHand = "f-1/8 f0/8. g/16 a& g f e& d& c b&-1/8 g0/8. a&/16 b& a& g f e d c d e f g a& b& g f e f e f/4 _/16 c/16 d e f g a& b& c1 b&0 a& g a&/8 f d&1*5/16 c/16 b&0 a b&/8 g e1*5/16 f/16 g a& b&/8 g/16 e d&/8 c b&0/16 a& g a& b& d&1 c b&0 a& g f e a&/8 f/8. g/16 a& g f e& d& c b-1/8 a&0/8. g/16 f e& d c b-1 a g a b c0 d e& f d c b-1 c0 b-1 e&0/8 c1/8. d/16 e& d c b&0 a& g f#/8 e&1/8. d/16 c b&0 a g f# e d e f# g a b& c1 a0 g f# g f# g a b c1 d e& f d e& c d e& f g a& f g d e& f g0 f1 e& d e& f0 e& d e&/8 c a&*5/16 g/16 f e f/8 d b*5/16 c1/16 d e& f/8 d/16 b0 a&/8 g f/16 e& d e& f a& g f e& d c b&-1 a/8 c1/8. b&0/16 a g f e& d& c b&-1/8 d&0 f a b&*5/16 a&/16 g f g/8 e& d&1*5/16 c/16 b&0 c1 d& f e& d& c b&0 a& g f/8 f1/8. e&/16 d& c b&0 a& g b& e&/8 e&1/8. d&/16 c b&0 a& g& f a& d&/8 d&1/8. c/16 b&0 a& g f e g c*5/16 d/16 e f g a& b& g a& b& c1/8. b&0/16 a& g f e& d& c b&-1 c0 d&/8. c/16 b&-1 a& g f e d c d e f g a& b& g f e f e f/4 _/16 c0/16 d e f e f g a& g a& b& c1/8 b&0 c1 c0 f-1/2.";

			break;
		}

		case 9: {
			song.setName("Inventio 10");
			song.setId(11000L);
			song.setKey(Key.G);
			rightHand = "g1/8 b d2 b1 g d2 b1 g g2 f#/4 d/8 a f# d a f# d g d b1 f2 d b1 f2 d b1 e2 c a1 f# a c2 e d c d b1 g e g b d2 c b1 c2 a1 f# d f# a c2 b1 a b c2 d e b1 g2 e b1 g2 a1 b c#2 d a1 f#2 d a1 f#2 g1/2 a/8 g f# g e f# g f# e f# d a b c2 b1 c2 b1 a b g e2 f# g c# d c# b1 c#2 a1 g2 a b a g f# e f# d a1 d2 c# d/4. _/4 _/2 a1/8 c#2 e c# a1 e2 c# a1 g2 f#/4. d _/8 _/4 g1/8 b d2 b1 g d2 b1 g f2 e c a1 a2 e c a1 b c2 d b1 g g2 d b1 g a b c2*9/4 b1*19/8 e2/8 d c d b1 a b g a c2 b1 a b g f# g e f#/4 d/8 a/4 f#/8 d2/4 f#1/8 g b d2 b1 g d2 b1 g g2 d f# a f# d a f# d c3 b2 a g f# g a d g f# g f# e d c b1 a b c2 b1 a g f# g a d g f# g d b0 g/4. _/8 _/4";
			leftHand = "_/8 _/1 d0/8 f# a f# d a f# d c1 b0/4 g/8 d1 b0 g d1 b0 g c1 e c a0 c1 a0 f# g a b d1 b0 g b g e f# g a c1 a0 f# a f# d e f# g/2 f#/8 e d e c# f#/2 e/8 d c# d b-1 e0 f# g a e c#1 a0 e c#1 d/2 d0/8 e f# e d g/2 e/8 f# g f# e a/2 g/8 f# e d c# d e f# g a b a/4 a-1/8 d0 f# a f# d a f# d d1 c#/4. a0 _/8 _/4 d/8 f# a f# d a f# d c1 b0/4. g _/8 _/4 c/2 d/8 e f#-1 g a b/2 c0/8 d e-1 f# g a c0 e a e c a-1 b c0 d f# a d1 a0 f# d e f# g b d1 g d b0 g a b e g b e1 b0 g e f# g c*19/8 d/8 f# a f# d c b-1 a b g b d0 b-1 g d0 b-1 g f# d f# a f# d a f# d g a b a b c0 b-1 c0 d e-1*5/8 e0/8 f# e d g/4 b-1/8 e0 d c d/4 d-1/8 g/2 b/8 d0 g/4.";

			break;
		}

		// case 10: {
		// song.setName("Inventio 11";
		// song.setId(11100L;
		// song.setKey(Key.C;
		// rightHand = "";
		// leftHand = "";
		//
		// break;
		// }

		case 11: {
			song.setName("Inventio 12");
			song.setId(11200L);
			song.setKey(Key.A);
			rightHand = "a1/4 a/8 a/4 a/8 b*13/16 a/16 g# a b c#2 d c# d f# e d c# a1 c#2 e a f# d# b1 d#2 f# a f# g# a g# f# e d# c# d# e d# c# b1 a c#2 e c# a1 c#2 f#1 a c#2 a1 f# a d#/8 f# d# b0/16 d#1 f# a g# f# e g# b e2 b1 g# e/4 _/8 e2 e/8 e/4 e/8 e/16 a e c# a1 c#2 e a e c# a1 c#2 d/4 d/8 d/4 d/8 d/16 b f# d b1 d2 f# b f# d b1 d2 c#/8 _/16 _/4 d1/16 f# a d2 c# b1 _/8 _/4 c# e# g# c#2 b1 a _/8 _/4 b0 d1 f# b a g# f# b a g# f# e# g# c#2 b1 a g# f#/8 f#2 f#1 f# c# f# g#*13/16 f#/16 e# f# g# a b a b d2 c# b1 a f# a c#2 f# d# b# g#1 b#2 d# f# d# e f# e d# c# b1 a b c#2 b1 a g# f# a c#2 a1 f# a d# f# a f# d# f# b#/8 d# f# a g# b#2 c#/16 e g# e c# e g# e c# e g# e c# e f# e c# e f# e c# e f# a#1 b d2 f# d b1 d2 f# d b1 d2 f# d b1 d2 e d b1 d2 e d b1 d2 e g#1 a/4 a/8 a/4 a/8 a/16 d#2 b1 a f# a b d#2 b1 a f# a g/4 g/8 g/4 g/8 g/16 e2 c# g1 e g c#2 e c# g1 e g f#/8 _/16 _/4 g#0/16 b d1 g# f# e/8 _/16 _/4 f#0/16 a c#1 f# e d/8 _/16 _/4 e0/16 g# b e1 d c# a0 c#1 e a e c#2 a1 c#2 e a f# d f# a f# d f# b1 d2 f# d b1 d2 g#1/8 b d2 f# e b1 f#2 e a1 e2 d g#1 d2 c# e1 f#/16 g# a b c#2 d g#1 e g# b e2 b1 g#2 e b g# e g# a/8 e c# a1/16 d2 c# b1 e2/8. f#/16 c#/8 b1/16 a a/4.";
			leftHand = "a0/16 b c#1 b0 a g# f# g# a g# f# e d f# a f# d f# b-1 d0 f# d b-1 d0 g#-1/8 b g# e f# g# a/4 _/2 e0 e/8 e/4 e/8 f#*13/16 e/16 d# e f# g# a g# a c#1 b0 a g# e g# b e1 b0 g# e g# b d1 b0 c#1 d e d c# b0 a b c#1 b0 a g# f#/8 f#1 c# a0 f# a b/16 c#1 d c# b0 a g# a b a g# f# e/8 b-1 g# e/4 _/8 _/16 a0/16 c#1 e a g# f#/8 _/16 _/4 g#0/16 b d#1 g# f# e#/8 _/4 _/16 f#0/16 a c#1 f# e d/8 _/16 _/4 b-1/16 d0 f# b a g# c# f g# c#1 b0 a b a g# f# e d e f# e d c# b-1 d0 f# d b-1 d0 g#-1 b d0 b-1 g# b e#/8 g# e# c# d# e# f# f#0 d# g# f# g# c#/4 c#/8 c#/4 c#/8 d#*13/16 c#/16 b# c# d# e f# e f# a g# f# e c# e g# c#1 g#0 e c# e g# c#1 b0 a# f# a# c#1 f# c# a#0 f# a# c#1 f# e d b-1 d0 f# b f# d b-1 d0 f# b a g# e g# b e1 b0 g# e g# b e1 d c# d e d c# b0 a b c#1 b0 a g# f#/8 a d#1/4 c#/8 b0 e1/16 f# g f# e d c# d e d c# b0 a/8 e c# a-1/4 _/16 _/8 d0/16 f# a d1 c# b0/8 _/4 _/16 c#/16 e g# c#1 b0 a/8 _/16 _/4 b-1/16 d0 f# b a g#/8 _/4 a-1/4 a/8 a/4 a/8 b*13/16 a/16 g# a b c#0 d c# d f# e d c# b-1 c#0 e d c# b-1 a b d0 c# b-1 a g# a b c#0 a-1 d0/4 d/8 d/2. c#/16 d c# b-1 a g# f# f#0 e d c# b-1 c#0 d e/8 e-1 a/4.";

			break;
		}

		case 12: {
			song.setName("Inventio 13");
			song.setId(11300L);
			song.setKey(Key.C);
			rightHand = "_/16 e1/16 a c2 b1 e b d2 c/8 e g#1 e2 a1/16 e a c2 b1 e b d2 c/8 a1 _/4 _/16 e2/16 c e a1 c2 e1 g f/8 a d2 f/8. d/16 b1 d2 g1 b d f e/8 g c2 e/8. c/16 a1 c2 f1/8 d2/8. b1/16 g b e/8 c2/8. a1/16 f a d/8 b c2 _/8 _/4 _/16 g1/16 c2 e d g1 d2 f e/8 g b1 g2 c/16 g1 c2 e d g1 d2 f e/8 c g e c3/16 a2 e a c e a1 c2 d/8 f# a c3 b2/16 g d g b1 d2 g1 b c2/8 e g b a/16 f# d# f# b1 d#2 f#1 a g/8 g2/8. e/16 c e a1/8 f#2/8. d/16 b1 d2 g1/8 e2/8. c/16 a1 c2 f#1 g2 f# e d# f# b1 d#2 e/8 _/8 _/4 _/16 g/16 b& g e g c# e g e c# e a1 _/16 _/8 _/16 f2 a f d f b1 d2 f d b1 d2 g1 _/16 _/8 _/16 e2 g e c e a1 c2 d# c a1 c2 f#1 _/16 _/8 _/16 d2 f d b1 d2 g#1 b d2 b1 g# b e _/16 _/8 _/16 e a c2 b1 e b d2 c/8 a1 g# e a/16 c2 e c a1 c2 f#1 a c2 a1 f# a d# c2 b1 a g# b d2 b1 g# b d f g# f d f b0 f1 e d c e a e c e a0 c1 d# c a0 c1 f#0 c1 b0 a g#/8 b1 g# e _/16 e/16 a c2 b1 e b d2 c a1 c2 e d b1 d2 f e c e g f e d c b1 c2 d e f d g# d b d c a f d b1 d2 g#1 b c2 a1 e a b g# a e c e a0/4";
			leftHand = "a-1/8 a0/4 g#/8 a/16 e a c1 b0 e b d1 c/8 a0 g# e a/16 e a c1 b0 e b d1 c/8 a0 c1 a0 d1/16 a0 f a d f a-1 c0 b-1/8 d0 g b/8. g/16 e g c e g-1 b a/8 c0 d/16 f b-1 d0 g-1/8 b c0/16 e a-1 c0 f-1/8 d g/16 g0 f g c g c1 e d g0 d1 f e/8 c b0 g c1/16 g0 c1 e d g0 d1 f e/8 c _/4 _/16 g/16 e g c e g0 b a/8 c1 e g f#/16 a d f# a0 d1 f#0 a g/8 b d1 f# e/16 g c e g0 c1 e0 g f#/8 a b d#1 _/16 e/16 c e a0 c1 e g f# d b0 d1 g0 b d1 f# e c a0 c1 f#0 a c1/8. b0/16 c1 a0 b/8 b-1 e0/16 e1 b0 g e b-1 g b e/8 e0 g a# c# _/8 _/16 g1/16 f e d/8 d0 f a& b-1 _/8 _/16 f1/16 e d c/8 c0 e f# a-1 _/8 _/16 e1/16 d# c# b0/8 b-1 d0 f g#-1 _/8 _/16 d1/16 c b0 c1/8 a0 g# e a/16 e a c1 b0 e b d1 c e a e c e a0 c1 f#0 a c1 a0 f# a d# f# e/8 g# b g# e b-1 g# e a c0 e c a-1 c0 d#-1 _/8 _/16 b0/16 g# e d b g# d c/8 e g#-1 e0 a-1 f#0 b-1 g#0 c a d b& g# f d b-1 g# a d e f d# e e0 a-1/2";

			break;
		}

		case 13: {
			song.setName("Inventio 14");
			song.setId(11400L);
			song.setKey(Key.B);
			rightHand = "_/16 b&1/32 c2 d c b&1/16 f2 d b& f d f/32 e& d e& f/16 b&1 d2 f1 a& g e&/32 f g f e&/16 b& g e&2 b&1 g b&/32 a& g a& b&/16 e& g c e& a0 c1/32 d e& d c/16 a f c2 a1 e&2 f1/32 g a g f/16 c2 a1 f2 c d/8 _/8 _/16 g/32 f e& f g/16 c/8 _/8 _/16 f/32 e& d e& f/16 b&1/8 _/8 _/16 e&2/32 d c d e&/16 a1 c2/32 b&1 a b& c2/16 f1/8 _/8 f2 f1 a c2 f/4 _/4 _/8 f1/8 b& d2 f/4 _/4 _/8 g1/8 b& c2 e/4 _/4 _/16 f1/32 g a g f/16 c2 a1 f2 c a e&/32 d c d e&/16 a1 c2 f#1 a b&/8 d2 b&1 g a& f2 a&1 f g/16 c/32 d e& d c/16 g e& c2 g1 d2 a&1/32 g f g a&/16 d f b0 g1 e&/8 _/8 _/16 c/32 d e& d c/16 c2/8. b&1/16 a f/32 g a g f/16 f2/8. e&/16 d b&1/32 c2 d c b&1/16 b&2/8. a&/16 g b&/32 a& g a& b&/16 e& g/32 f e& f g/16 c e&/32 d c d e&/16 a1 c2/32 d e& d c/16 f a&1/32 g f g a&/16 g b&/32 c2 d c b&1/16 e&2 g1/32 f e& f g/16 f a/32 b& c2 b&1 a/16 d2 f1/32 e& d e& f/16 e& g/32 a b& a g/16 c2 e&1/32 d c d e&/16 d/4 _/16 b&/32 c2 d c b&1/16 f2 d b& f d f/32 e& d e& f/16 b&1 e&2 b&1 e&2 g1 e&/32 f g f e&/16 b& g e&2 b&1 g b&/32 a& g a& b&/16 e&/8 e&2/8. e&/32 d c d e&/16 f1/8 e&2/8. c/32 d e& d c/16 f d/32 c b&1 c2 d/16 f1 b& c2 a1 b&/1";
			leftHand = "b&0/8 b&-1 d0 f b&/4 _/4 _/8 b&-1/8 e&0 g b&/4 _/4 _/8 b&-1/8 c0 e& a/4 _/4 _/16 b&/32 c1 d c b&0/16 e&1/8 _/8 _/16 a0/32 b& c1 b&0 a/16 d1/8 _/8 _/16 g0/32 a b& a g/16 c1 c0/32 d e& d c/16 f/8 f-1 _/16 c1/16 a0 f _/16 f/32 g a g f/16 c1 a0 f1 c a0 c1/32 b&0 a b& c1/16 f0 a c e& d b&-1/32 c0 d c b&-1/16 f0 d b& f d f/32 e& d e& f/16 b&-1 d0 g-1 b& e g/32 a b& a g/16 e0 c g e b& c/32 d e d c/16 g e c1 g0 a/8 c1 a0 f f# a f# d g/16 g-1/32 a b& a g/16 d0 b&-1 g0 d b& a&/32 g f g a&/16 d f b&-1 d0 e&/8 g e& c b-1 d0 b-1 g c0/16 c-1/32 d e& d c/16 c0/8. b&-1/16 a f/32 g a g f/16 f0/8. e&/16 d b&-1/32 c0 d c b&-1/16 b&0/8. a&/16 g e&/32 f g f e&/16 e&1/8. d/16 c e&/32 d c d e&/16 a0 c1/32 b&0 a b& c1/16 f0 a/32 b& c1 b&0 a/16 d1 f0/32 e& d e& f/16 e& g/32 a b& a g/16 c1 e&0/32 d c d e&/16 d f/32 g a g f/16 b& d/32 c b&-1 c0 d/16 c e&/32 f g f e&/16 a c/32 b&-1 a b& c0/16 b&-1 b&0/32 c1 d c b&0/16 f1 d b& f d f/32 e& d e& f/16 b&0 d1 f0 a& g e&/32 f g f e&/16 b& g e&1 b&0 g b&/32 a& g a& b&/16 e& g b&-1 d0 c f-1/32 g a g f/16 c0 a-1 e&0 c a-1 c0/32 b&-1 a b& c0/16 f-1 a c e& d b&/32 c0 d c b&-1/16 f0/8 f-1 b&/1";

			break;
		}

		default:
			return null;

		}

		List<Score> leftScores = guidoService.gmnToScores(leftHand);
		List<Score> rightScores = guidoService.gmnToScores(rightHand);
		song.setLeftScores(leftScores);
		song.setRightScores(rightScores);

		return song;
	}
}
