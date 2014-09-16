package com.resamsel.starrotation;

import processing.core.PShape;

import com.resamsel.InteractiveExportPApplet;
import com.resamsel.util.LineShape;

/**
 * (c) 2014 René Samselnig
 * <p>
 *
 * @author $ Author:resamsel $
 * @version $ Date:11.09.2014 $
 */
public class StarRotation extends InteractiveExportPApplet {
	private static final long serialVersionUID = 6102393616903189008L;

	float angle;
	float angleAdvance = 0.5f;
	float maxAngle = 120;

	float radius = 160;
	float radius1, radius2;
	float marginX, marginY;
	float patternHeight;

	float x, y;

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

		for (int i = 0; i < 3; i++) {
			// pattern.addChild(LineShape.build(this, 1, color(100),//
			// cos(i * PI / 3) * radius1,//
			// sin(i * PI / 3) * radius1, //
			// cos(i * PI / 3 + PI) * radius1, //
			// sin(i * PI / 3 + PI) * radius1));
			pattern.addChild(LineShape.build(this, 3, color(200),//
					cos(i * 2 * PI / 3 + PI / 6) * radius2,//
					sin(i * 2 * PI / 3 + PI / 6) * radius2, //
					// 0, 0, //
					cos(i * 2 * PI / 3 + PI / 6 + PI) * radius2, //
					sin(i * 2 * PI / 3 + PI / 6 + PI) * radius2));
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

		for (int i = 0; i < width / radius + 2; i++) {
			for (int j = 0; j < height / radius / 2 + 2; j++) {
				x = marginX + (i - 1) * radius;
				y = marginY + (j - 1) * patternHeight;

				pushMatrix();
				translate(x, y);
				rotate(radians(rotation(angle)));
				shape(pattern);
				popMatrix();

				pushMatrix();
				translate(x + cos(radians(60)) * radius, y + sin(radians(60))
						* radius);
				rotate(radians(rotation(angle)));
				shape(pattern);
				popMatrix();
			}
		}

		super.draw();
	}

	private float rotation(float angle) {
		if (angle < 30)
			return angle;
		if (angle < 60)
			return 30;
		if (angle < 90)
			return angle - 30;
		return 60;
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
		// TODO: implement
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
