package com.resamsel.bluedots;

import processing.core.PShape;

import com.resamsel.InteractiveExportPApplet;
import com.resamsel.util.Utils;

/**
 * (c) 2014 René Samselnig
 * <p>
 *
 * @author $ Author:resamsel $
 * @version $ Date:09.09.2014 $
 */
public class BlueDots extends InteractiveExportPApplet {
	private static final long serialVersionUID = 781043387093923341L;

	float circleMargin = 36;
	float circleRadius = 12;
	float circleScale = 1;
	float waveRadius = 0;
	float waveAdvance = 2;
	float halfDiagonal;

	float x, y;
	float pos, diff, scale;

	PShape circle;

	/**
	 * 
	 * @see processing.core.PApplet#setup()
	 */
	@Override
	public void setup() {
		background(22, 41, 100);
		size(684, 468, P2D);
		smooth(8);

		halfDiagonal = (float) Math.sqrt(Math.pow(width, 2)
				+ Math.pow(height, 2)) / 2;
		circle = createShape(PShape.ELLIPSE, -circleRadius / 2,
				-circleRadius / 2, circleRadius, circleRadius);
		circle.setFill(color(126, 160, 188));
		circle.setStroke(false);
	}

	/**
	 * 
	 * @see processing.core.PApplet#draw()
	 */
	@Override
	public void draw() {
		background(22, 41, 100);

		for (float i = 0; i < width / circleMargin; i++) {
			for (float j = 0; j < height / circleMargin; j++) {
				x = i * circleMargin + (circleMargin - (width % circleMargin))
						/ 2;
				y = j * circleMargin + (circleMargin - (height % circleMargin))
						/ 2;
				pos = (float) Math.sqrt(Math.pow(x - width / 2, 2)
						+ Math.pow(y - height / 2, 2));
				diff = Utils.diff(pos, waveRadius, halfDiagonal) / circleMargin;
				scale = sin(Math.min(Math.max(diff, 0), 2) * HALF_PI / 2)
						* circleScale;

				// println(String.format(Locale.ENGLISH, "%.1f,%.1f,%.1f",
				// Math.min(Math.max(diff, 0), 2), scale, diff));

				pushMatrix();
				translate(x, y);
				scale(scale);
				shape(circle);
				popMatrix();
			}
		}

		// DEBUG
		// noFill();
		// stroke(color(200));
		// ellipse(width / 2, height / 2, waveRadius, waveRadius);

		super.draw();
	}

	/**
	 * 
	 * @see com.resamsel.InteractiveExportPApplet#advance()
	 */
	@Override
	protected void advance() {
		waveRadius = (waveRadius + waveAdvance) % halfDiagonal;
	}

	@Override
	protected void advanceFromMouse() {
		waveRadius = (float) Math.sqrt(Math.pow(mouseX - width / 2, 2)
				+ Math.pow(mouseY - height / 2, 2));
	}

	@Override
	protected int getMaxFrameCount() {
		return (int) (halfDiagonal / waveAdvance);
	}

	@Override
	protected void resetAdvance() {
		waveRadius = 0;
	}
}
