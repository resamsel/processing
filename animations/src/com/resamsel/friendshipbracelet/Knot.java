package com.resamsel.friendshipbracelet;

import processing.core.PApplet;
import processing.core.PShape;

/**
 * (c) 2014 René Samselnig
 * <p>
 *
 * @author $ Author:resamsel $
 * @version $ Date:15.09.2014 $
 */
public class Knot {

	/**
	 * @param size
	 * @param color
	 * @return
	 */
	public static PShape build(PApplet pa, int color) {
		PShape shape = pa.createShape(PApplet.RECT, -0.5f, -0.5f, 1, 1);
		shape.rotate(PApplet.PI / 4);
		shape.setFill(color);
		shape.setStroke(false);
		return shape;
	}

}
