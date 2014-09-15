package com.resamsel.util;

/**
 * (c) 2014 René Samselnig
 * <p>
 *
 * @author $ Author:resamsel $
 * @version $ Date:10.09.2014 $
 */
public class Utils {
	public static float diff(float a, float b, float max) {
		return Math.min((max + a - b) % max, (max + b - a) % max);
	}
}
