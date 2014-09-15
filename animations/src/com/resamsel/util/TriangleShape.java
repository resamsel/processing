package com.resamsel.util;

import processing.core.PApplet;
import processing.core.PShape;

/**
 * (c) 2014 René Samselnig
 * <p>
 *
 * @author $ Author:resamsel $
 * @version $ Date:11.09.2014 $
 */
public class TriangleShape {
	public static PShape build(PApplet pa, int color, float x1, float y1,
			float x2, float y2, float x3, float y3) {
		PShape shape = pa.createShape();

		shape.beginShape();

		// shape.fill(color);
		shape.noStroke();

		shape.vertex(x1, y1);
		shape.vertex(x2, y2);
		shape.vertex(x3, y3);

		shape.endShape();

		return shape;
	}
}
