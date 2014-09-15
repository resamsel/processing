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
public class LineShape {
	/**
	 * @param pa
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 */
	public static PShape build(PApplet pa, float weight, int color, float x1,
			float y1, float x2, float y2) {
		PShape shape = pa.createShape(PApplet.LINE, x1, y1, x2, y2);

		shape.setStrokeWeight(weight);
		shape.setStroke(color);
		shape.setFill(false);

		return shape;
	}
}
