package com.resamsel;

import processing.core.PApplet;

/**
 * (c) 2014 René Samselnig
 * <p>
 *
 * @author $ Author:resamsel $
 * @version $ Date:10.09.2014 $
 */
public abstract class ExportPApplet extends PApplet {
	private static final long serialVersionUID = 1666118877568163656L;

	private boolean export = false;

	/**
	 * 
	 * @see processing.core.PApplet#draw()
	 */
	@Override
	public void draw() {
		export();
	}
	
	public void keyPressed() {
		if (key == 'x')
			if (!export)
				startExport();
			else
				stopExport();
	}

	private void export() {
		if (!export)
			return;

		if (frameCount >= getMaxFrameCount())
			stopExport();
		if (frameCount % 3 == 0 && frameCount < getMaxFrameCount())
			saveFrame("image-####.png");
	}

	private void startExport() {
		export = true;
		frameCount = 0;
		resetAdvance();
		smooth(8);
	}

	private void stopExport() {
		export = false;
		smooth(2);
	}

	protected abstract float getMaxFrameCount();

	protected abstract void resetAdvance();

	/**
	 * @return the export
	 */
	protected boolean isExport() {
		return export;
	}
}
