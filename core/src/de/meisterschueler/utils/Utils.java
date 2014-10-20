package de.meisterschueler.utils;

import com.badlogic.gdx.graphics.Color;

public class Utils {
	public static Color getSpectralColor(float value, float vMin, float vMax, float cMin, float cMax) {
		Color color = new Color(0, 0, 0, 0);

		float normValue = (Math.min(vMax, Math.max(value, vMin)) - vMin) / (vMax-vMin);
		normValue = normValue*(cMax-cMin)+cMin;

		if (normValue >= 0.0 && normValue < 1.0)		// From 0-1: black to red
			color = new Color(normValue, 0, 0, 1);
		else if (normValue >= 1.0 && normValue < 2.0)	// From 1-2: red to orange
			color = new Color(1, normValue-1.0f, 0, 1);
		else if (normValue >= 2.0 && normValue < 3.0)	// From 2-3: orange to yellow
			color = new Color(3.0f-normValue, 1, 0, 1);
		else if (normValue >= 3.0 && normValue < 4.0)	// From 3-4: yellow to green
			color = new Color(0, 1, normValue-3.0f, 1);
		else if (normValue >= 4.0 && normValue < 5.0)	// From 4-5: green to blue
			color = new Color(0, 5.0f-normValue, 1, 1);
		else if (normValue >= 5.0 && normValue < 6.0)	// From 5-6: blue to black
			color = new Color(0, 0, 6.0f-normValue, 1);
		
		return color;
	}
}
