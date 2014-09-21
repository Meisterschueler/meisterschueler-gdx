package de.meisterschueler.basic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class MidiLogPlayer {
	public MidiLogPlayer(String file) throws NumberFormatException, IOException, InterruptedException {
		final List<AbstractNote> notes = new ArrayList<AbstractNote>();

		String regex = "([0-9]+):([0-9]+):([0-9]+):([0-9]+): ([A-Za-z\\s]+), ([\\w\\s,]+)";
		Pattern pattern = Pattern.compile(regex);

		String regex2 = "Channel ([0-9]+), ([0-9]+), ([0-9]+)";
		Pattern pattern2 = Pattern.compile(regex2);

		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		while ((line = br.readLine()) != null) {
			Matcher matcher = pattern.matcher(line);
			if (matcher.matches()) {
				int hour = Integer.parseInt(matcher.group(1));
				int min = Integer.parseInt(matcher.group(2));
				int sec = Integer.parseInt(matcher.group(3));
				int msec = Integer.parseInt(matcher.group(4));
				Long timestamp = (((hour * 60L + min) * 60L + sec) * 1000L + msec);

				String type = matcher.group(5);
				if (type.equals("Note on")) {
					Matcher matcher2 = pattern2.matcher(matcher.group(6));
					if (matcher2.matches()) {
						int channel = Integer.parseInt(matcher2.group(1));
						int note = Integer.parseInt(matcher2.group(2));
						int velocity = Integer.parseInt(matcher2.group(3));

						NoteOn noteOn = new NoteOn(timestamp, 0, channel, note, velocity);
						notes.add(noteOn);
					}
				} else if (type.equals("Note off")) {
					Matcher matcher2 = pattern2.matcher(matcher.group(6));
					if (matcher2.matches()) {
						int channel = Integer.parseInt(matcher2.group(1));
						int note = Integer.parseInt(matcher2.group(2));
						int velocity = Integer.parseInt(matcher2.group(3));

						NoteOff noteOff = new NoteOff(timestamp, 0, channel, note, velocity);
						notes.add(noteOff);
					}
				}
			}
		}
		br.close();

		Thread t = new Thread() {
			@Override
			public void run() {
				Long time = ((AbstractNote) notes.get(0)).getTime();
				for (Object o : notes) {
					AbstractNote note = (AbstractNote) o;
					Long delta = note.getTime() - time;
					try {
						Thread.sleep(delta);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					time = note.getTime();
					if (note instanceof NoteOn) {
						NoteOn noteOn = (NoteOn) note;
						onNoteOn(noteOn);
					} else if (note instanceof NoteOff) {
						NoteOff noteOff = (NoteOff) note;
						onNoteOff(noteOff);
					}
				}
			}
		};

		t.start();
		t.join();
	}

	public abstract void onNoteOn(NoteOn noteOn);

	public abstract void onNoteOff(NoteOff noteOff);
}
