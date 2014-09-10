package hanto.studentmwcjlm.common;

import hanto.common.HantoCoordinate;

/**
 *  Basic implementation of the Hanto Coordinate
 * @author Mitchell
 *
 */
public class HantoCoordinateImpl implements HantoCoordinate {

	/** The x value of this coordinate */
	private int x;
	
	/** The y value of this coordinate */
	private int y;
	
	/** Creates a new Hanto Coordinate
	 * 
	 */
	public HantoCoordinateImpl(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
