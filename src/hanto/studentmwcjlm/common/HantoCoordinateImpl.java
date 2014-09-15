/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentmwcjlm.common;

import hanto.common.HantoCoordinate;

import java.util.ArrayList;
import java.util.List;

/**
 *  Basic implementation of the Hanto Coordinate
 * @author Mitchell
 *
 */
public class HantoCoordinateImpl implements HantoCoordinate, Comparable<HantoCoordinateImpl> {

	/** The x value of this coordinate */
	private int x;
	
	/** The y value of this coordinate */
	private int y;
	
	/** Creates a new Hanto Coordinate with the given x and y coordinates
	 * 
	 * @param x The x coordinate of the hanto coordinate
	 * @param y The y coordinate of the hanto coordinate
	 */
	public HantoCoordinateImpl(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/** Creates a new HantoCoordImpl from the given hanto coord 
	 * 
	 * @param coord The coord to create from
	 */
	public HantoCoordinateImpl(HantoCoordinate coord) {
		this(coord.getX(), coord.getY());
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	/** Returns the list of coordinates adjacent to this coordinate
	 * 
	 * @return The list of coordinates adjacent to this coordinate
	 */
	public List<HantoCoordinateImpl> getAdjacentCoords() {
		int cx = getX();
		int cy = getY();
		
		List<HantoCoordinateImpl> adjacentCoordinates = new ArrayList<HantoCoordinateImpl>();
		adjacentCoordinates.add(new HantoCoordinateImpl(cx + 1, cy));
		adjacentCoordinates.add(new HantoCoordinateImpl(cx - 1, cy));
		
		adjacentCoordinates.add(new HantoCoordinateImpl(cx, cy + 1));
		adjacentCoordinates.add(new HantoCoordinateImpl(cx, cy - 1));
		
		adjacentCoordinates.add(new HantoCoordinateImpl(cx + 1, cy - 1));
		adjacentCoordinates.add(new HantoCoordinateImpl(cx - 1, cy + 1));
		
		return adjacentCoordinates;
	}

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

	@Override
	public int compareTo(HantoCoordinateImpl o) {
		if (equals(o)) {
			return 0;
		}		
		if (o.getX() == this.getX()) {
			return this.getY() - o.getY();
		}
		else {
			return this.getX() - o.getX();
		}
	}	

}
