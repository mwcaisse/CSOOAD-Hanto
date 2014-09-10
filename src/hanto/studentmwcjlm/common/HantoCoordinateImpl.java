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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (! (o instanceof HantoCoordinate)) {
			return false;
		}
		HantoCoordinate other = (HantoCoordinate)o;		
		return other.getX() == getX() && other.getY() == getY();
	}	

}
