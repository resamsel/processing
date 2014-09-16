package com.resamsel.triangle;

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
public class Triangle extends InteractiveExportPApplet {
	private static final long serialVersionUID = 4611394101719614376L;

	float CIRCLE_RADIUS;
	float TRIANGLE_RADIUS;
	float circleRadius;
	float circleBounce = 3;
	float angle = 0;
	float rotation = 1;
	float r;
	float diff;
	float rotate;
	float scale;

	PShape triangle;

	public void setup() {
		size(450, 450, P2D);
		background(50);
		smooth(8);

		// DEBUG:
		// angle = 350;

		CIRCLE_RADIUS = Math.min(width, height) / 3.75f;
		TRIANGLE_RADIUS = CIRCLE_RADIUS / 4f;

		triangle = createShape();
		triangle.beginShape(TRIANGLE);
		triangle.stroke(200);
		triangle.strokeWeight(width / 225);
		triangle.strokeCap(ROUND);
		triangle.noFill();
		triangle.vertex(cos(radians(90)) * TRIANGLE_RADIUS, sin(radians(90))
				* TRIANGLE_RADIUS);
		triangle.vertex(cos(radians(210)) * TRIANGLE_RADIUS, sin(radians(210))
				* TRIANGLE_RADIUS);
		triangle.vertex(cos(radians(330)) * TRIANGLE_RADIUS, sin(radians(330))
				* TRIANGLE_RADIUS);
		triangle.endShape();
	}

	public void draw() {
		background(50);
		stroke(200);

		circleRadius = CIRCLE_RADIUS + sin(radians(angle * 3f)) * 10
				* circleBounce;

		pushMatrix();
		translate(width / 2, height / 2);
		// rotate(radians(angle));
		for (float i = 0; i < 12; i++) {
			pushMatrix();

			r = TWO_PI / 12f * i;
			diff = Utils.diff(degrees(r), angle, 360);
			rotate = r - PI / 2 - radians(angle);
			scale = sin(PI / 2f + diff * PI / 60f) + 2f;

			translate(cos(r) * circleRadius, sin(r) * circleRadius);
			rotate(rotate);
			/**
			 * println(String.format(java.util.Locale.ENGLISH,
			 * "%d,%.0f,%.2f,%.1f,%.2f,%.0f,%.2f", frameCount, i, r, rotate,
			 * scale, angle, diff)); /
			 **/
			if (diff <= 60)
				scale(Math.max(scale, 1f));

			shape(triangle);

			popMatrix();
		}
		popMatrix();

		super.draw();
	}

	/**
	 * 
	 * @see com.resamsel.InteractiveExportPApplet#advance()
	 */
	@Override
	protected void advance() {
		angle = (angle + rotation) % getMaxFrameCount();
	}

	/**
	 * 
	 * @see com.resamsel.InteractiveExportPApplet#advanceFromMouse()
	 */
	@Override
	protected void advanceFromMouse() {
		float a = width / 2 - mouseX;
		float b = height / 2 - mouseY;
		angle = (-degrees(asin((float) (b / Math.sqrt(Math.pow(a, 2)
				+ Math.pow(b, 2))))) + 360) % 360;
		if (mouseX < width / 2)
			angle = 180 - angle;
	}

	/**
	 * @return
	 * @see com.resamsel.ExportPApplet#getMaxFrameCount()
	 */
	@Override
	protected int getMaxFrameCount() {
		return 360;
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
