package com.resamsel.starpattern;

import processing.core.PShape;

import com.resamsel.InteractiveExportPApplet;
import com.resamsel.util.TriangleShape;

/**
 * (c) 2014 René Samselnig
 * <p>
 *
 * @author $ Author:resamsel $
 * @version $ Date:11.09.2014 $
 */
public class StarPattern extends InteractiveExportPApplet {
	private static final long serialVersionUID = 6102393616903189008L;

	float angle;
	float angleAdvance = 0.5f;
	float maxAngle = 360;

	float radius = 160;
	float radius1, radius2;
	float marginX, marginY;
	float patternHeight;

	float x, y;

	int[] colors;
	PShape pattern, pattern2;

	/**
	 * 
	 * @see processing.core.PApplet#setup()
	 */
	@Override
	public void setup() {
		size(720, 450, P2D);
		background(50);
		smooth(2);

		marginX = width % radius / 2;
		patternHeight = sin(radians(60)) * radius * 2;
		marginY = height % patternHeight / 2;

		radius1 = radius / 2;
		radius2 = radius / 2 / cos(PI / 6);

		pattern = createShape(GROUP);

		int red = color(180, 50, 50);
		int blue = color(50, 50, 180);
		int yellow = color(255, 200, 50);
		colors = new int[] { blue, yellow, red, blue, red, blue, yellow, red,
				yellow, red, blue, yellow };

		for (int i = 0; i < 6; i++) {
			pattern.addChild(TriangleShape.build(this, colors[2 * i],//
					0,//
					0,//
					cos(i * PI / 3) * radius1,//
					sin(i * PI / 3) * radius1, //
					cos(i * PI / 3 + PI / 6) * radius2,//
					sin(i * PI / 3 + PI / 6) * radius2));
			pattern.addChild(TriangleShape.build(this, colors[2 * i + 1],//
					0,//
					0,//
					cos(i * PI / 3 + PI / 6) * radius2,//
					sin(i * PI / 3 + PI / 6) * radius2,//
					cos(i * PI / 3 + PI / 3) * radius1,//
					sin(i * PI / 3 + PI / 3) * radius1));
		}

		pattern2 = createShape(ELLIPSE, -radius, -radius, radius * 2,
				radius * 2);
		pattern2.setStroke(color(255));
		pattern2.setFill(false);
	}

	/**
	 * 
	 * @see com.resamsel.InteractiveExportPApplet#draw()
	 */
	@Override
	public void draw() {
		background(50);

		// translate(width / 2, height / 2);
		// shape(pattern);
		// shape(pattern2);

		float scale = (cos(radians(angle)) + 1) / 2f;
		for (int i = 0; i < colors.length / 2; i++) {
			int idx1 = (colors.length + 2 * i - 1) % colors.length;
			int idx2 = (colors.length + 2 * i) % colors.length;
			pattern.getChild(idx1).setFill(
					morph(colors[idx1], colors[idx2], scale));
			pattern.getChild(idx2).setFill(
					morph(colors[idx2], colors[idx1], scale));
		}

		for (int i = 0; i < width / radius + 2; i++) {
			for (int j = 0; j < height / radius / 2 + 2; j++) {
				x = marginX + (i - 1) * radius;
				y = marginY + (j - 1) * patternHeight;

				pushMatrix();
				translate(x, y);
				// rotate(radians(rotation(angle)));
				// rotate(PI);
				shape(pattern);
				popMatrix();

				pushMatrix();
				translate(x + cos(radians(60)) * radius, y + sin(radians(60))
						* radius);
				// rotate(radians(rotation(angle)));
				shape(pattern);
				popMatrix();
			}
		}

		super.draw();
	}

	private int morph(int from, int to, float scale) {
		final float red = red(from) * (1 - scale) + red(to) * scale;
		final float green = green(from) * (1 - scale) + green(to) * scale;
		final float blue = blue(from) * (1 - scale) + blue(to) * scale;

		return color(red, green, blue);
	}

	/**
	 * 
	 * @see com.resamsel.InteractiveExportPApplet#advance()
	 */
	@Override
	protected void advance() {
		angle = (angle + angleAdvance) % maxAngle;
	}

	/**
	 * 
	 * @see com.resamsel.InteractiveExportPApplet#advanceFromMouse()
	 */
	@Override
	protected void advanceFromMouse() {
		angle = mouseX % maxAngle;
	}

	/**
	 * @return
	 * @see com.resamsel.ExportPApplet#getMaxFrameCount()
	 */
	@Override
	protected int getMaxFrameCount() {
		return (int) (maxAngle / angleAdvance);
	}

	/**
	 * 
	 * @see com.resamsel.ExportPApplet#resetAdvance()
	 */
	@Override
	protected void resetAdvance() {
		angle = 0;
	}
}
