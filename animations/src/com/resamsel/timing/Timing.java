package com.resamsel.timing;

import processing.core.PShape;

import com.resamsel.InteractiveExportPApplet;

/**
 * (c) 2014 René Samselnig
 * <p>
 *
 * @author $ Author:resamsel $
 * @version $ Date:10.09.2014 $
 */
public class Timing extends InteractiveExportPApplet {
	private static final long serialVersionUID = 7800693701314257932L;

	float zoom = 1;

	float maxZoom = 5;
	float zoomAdvance = 0.02f;
	float zoomScale = 2;

	PShape clock;
	PShape second;

	/**
	 * 
	 * @see processing.core.PApplet#setup()
	 */
	@Override
	public void setup() {
		size(450, 450, P2D);
		background(200);
		smooth(2);
		stroke(50);

		clock = createShape(GROUP);

		PShape circle = createShape(ELLIPSE, -100, -100, 200, 200);
		circle.setStroke(color(50));
		circle.setFill(false);
		PShape center = createShape(ELLIPSE, -1, -1, 2, 2);
		center.setFill(color(50));
		center.setStroke(false);
		PShape minute = createShape(LINE, 0, 0, 0, -85);
		minute.setStroke(color(50));
		minute.setStrokeWeight(3);
		minute.setFill(false);
		PShape hour = createShape(LINE, 0, 0, 0, -45);
		hour.setStroke(color(50));
		hour.setStrokeWeight(5);
		hour.setFill(false);

		clock.addChild(circle);
		clock.addChild(center);
		clock.addChild(minute);
		clock.addChild(hour);

		second = createShape(LINE, 0, 0, 0, -95);
		second.setStroke(color(50));
		second.setStrokeWeight(1);
		second.setFill(false);
	}

	/**
	 * 
	 * @see com.resamsel.InteractiveExportPApplet#draw()
	 */
	@Override
	public void draw() {
		background(200);

		pushMatrix();
		translate(width / 2, height / 2);
		// scale(zoom * zoomScale);
		shape(clock);
		shape(second);
		rotate(radians(-6));
		shape(second);
		rotate(radians(-6));
		shape(second);
		popMatrix();

		super.draw();
	}

	/**
	 * 
	 * @see com.resamsel.InteractiveExportPApplet#advance()
	 */
	@Override
	protected void advance() {
		zoom = (zoom + zoomAdvance) % maxZoom;
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
		return 100;
	}

	/**
	 * 
	 * @see com.resamsel.ExportPApplet#resetAdvance()
	 */
	@Override
	protected void resetAdvance() {
		zoom = 1;
	}
}
