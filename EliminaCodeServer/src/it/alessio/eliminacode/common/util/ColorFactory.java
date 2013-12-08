package it.alessio.eliminacode.common.util;

import java.awt.Color;

public class ColorFactory {
	public static Color getColor(String name) {
		Color color = Color.BLACK;
		if (name == null || name.equals("") || name.equals("NERO")) {
			color = Color.BLACK;
		} else if (name.equals("ROSSO")) {
			color = Color.RED;
		} else if (name.equals("GIALLO")) {
			color = Color.YELLOW;
		} else if (name.equals("BLU")) {
			color = Color.BLUE;
		} else if (name.equals("BIANCO")) {
			color = Color.WHITE;
		} else if (name.equals("ARANCIONE")) {
			color = Color.ORANGE;
		} else if (name.equals("VERDE")) {
			color = Color.GREEN;
		} else if (name.equals("ROSA")) {
			color = Color.PINK;
		}
		return color;
	}
}
