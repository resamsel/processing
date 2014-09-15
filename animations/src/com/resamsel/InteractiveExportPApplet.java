package com.resamsel;

/**
 * (c) 2014 René Samselnig
 * <p>
 *
 * @author $ Author:resamsel $
 * @version $ Date:10.09.2014 $
 */
public abstract class InteractiveExportPApplet extends ExportPApplet {
	private static final long serialVersionUID = 4742908307123193790L;

	boolean interactive = false;

	boolean showFrames = false;

	/**
	 * 
	 * @see com.resamsel.ExportPApplet#draw()
	 */
	@Override
	public void draw() {
		if (!interactive)
			advance();

		if (showFrames) {
			text(String.format("fps = %.1f", frameRate), 20, height - 20);
		}

		super.draw();
	}

	protected abstract void advance();

	/**
	 * 
	 */
	protected abstract void advanceFromMouse();

	/**
	 * 
	 * @see com.resamsel.ExportPApplet#keyPressed()
	 */
	@Override
	public void keyPressed() {
		super.keyPressed();
		if (key == 'f')
			showFrames = !showFrames;
	}

	public void mousePressed() {
		if (!isExport()) {
			interactive = !interactive;
			advanceFromMouse();
		}
	}

	public void mouseMoved() {
		if (!isExport() && interactive)
			advanceFromMouse();
	}
}
