package de.meisterschueler.gdx;

import java.util.Timer;
import java.util.TimerTask;


public abstract class Debounce {

	private Timer timer = null;
	private long lastHit = 0;
	private long debounceDelay = 0;
	private long checkDelay = 0;
	private boolean state;

	public abstract void execute(boolean state);

	public Debounce(long debounceDelay, long checkDelay) {
		this.debounceDelay = debounceDelay;
		this.checkDelay = checkDelay;
	}

	public void hit(boolean state){
		lastHit = System.currentTimeMillis();
		if(this.timer != null){
			this.timer.cancel();
			this.timer = null;
		}
		this.timer = new Timer("Debounce", true);
		this.timer.schedule(new DebounceTask(this), 0, checkDelay);
		this.state = state;
	}

	private void checkExecute(){
		if((System.currentTimeMillis() - lastHit) > debounceDelay){
			this.timer.cancel();
			this.timer = null;
			execute(state);
		}
	}

	private class DebounceTask extends TimerTask {

		private Debounce debounceInstance = null;

		public DebounceTask(Debounce debounceInstance) {
			this.debounceInstance = debounceInstance;
		}

		@Override
		public void run() {
			debounceInstance.checkExecute();
		}
	}
}