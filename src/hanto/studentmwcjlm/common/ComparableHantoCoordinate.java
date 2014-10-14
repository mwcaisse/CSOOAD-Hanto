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
 *  Implementation of a Hanto Coordinate that is comparable
 * @author Mitchell
 *
 */
public class ComparableHantoCoordinate implements HantoCoordinate, Comparable<ComparableHantoCoordinate> {

	/** The x value of this coordinate */
	private int x;
	
	/** The y value of this coordinate */
	private int y;
	
	/** Creates a new Hanto Coordinate with the given x and y coordinates
	 * 
	 * @param x The x coordinate of the hanto coordinate
	 * @param y The y coordinate of the hanto coordinate
	 */
	public ComparableHantoCoordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/** Creates a new HantoCoordImpl from the given hanto coord 
	 * 
	 * @param coord The coord to create from
	 */
	public ComparableHantoCoordinate(HantoCoordinate coord) {
		this(coord.getX(), coord.getY());
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	/** Calculates the distance between this coordinate and the given coordinate
	 * 
	 * @param to The coordinate to calculate the distance between
	 * @return The distance between the coordinates
	 */
	
	public int getDistance(ComparableHantoCoordinate to) {
		return (Math.abs(x - to.x) + Math.abs(y - to.y) + Math.abs(x + y - to.x - to.y)) / 2;
	}
	
	/** Returns the list of coordinates adjacent to this coordinate
	 * 
	 * @return The list of coordinates adjacent to this coordinate
	 */
	public List<ComparableHantoCoordinate> getAdjacentCoords() {		
		List<ComparableHantoCoordinate> adjacentCoordinates = new ArrayList<ComparableHantoCoordinate>();
		adjacentCoordinates.add(new ComparableHantoCoordinate(x + 1, y));
		adjacentCoordinates.add(new ComparableHantoCoordinate(x - 1, y));
		
		adjacentCoordinates.add(new ComparableHantoCoordinate(x, y + 1));
		adjacentCoordinates.add(new ComparableHantoCoordinate(x, y - 1));
		
		adjacentCoordinates.add(new ComparableHantoCoordinate(x + 1, y - 1));
		adjacentCoordinates.add(new ComparableHantoCoordinate(x - 1, y + 1));
		
		return adjacentCoordinates;
	}
	
	/** Determines whether the given coordinate is adjacent to this coordinate
	 * 
	 * @param coord The coordinate to check
	 * @return True if the coordinate is adjacent, false otherwise
	 */
	
	public boolean isAdjacent(ComparableHantoCoordinate coord) {
		return getAdjacentCoords().contains(coord);
	}
	
	/** Calculates the neighboring coordinates in the given radius
	 * 
	 * @param radius The radius
	 * @return The neighboring coordinates
	 */
	
	public List<ComparableHantoCoordinate> getAdjacentCoordsRadius(int radius) {
		List<ComparableHantoCoordinate> adjCoords = getAdjacentCoords();
		adjCoords.add(this);
		List<ComparableHantoCoordinate> newCoords = getAdjacentCoords();
		for(int i = 1; i < radius; i++) {
			List<ComparableHantoCoordinate> nextNew = new ArrayList<ComparableHantoCoordinate>();
			for(ComparableHantoCoordinate coord: newCoords) {
				List<ComparableHantoCoordinate> neighbors = coord.getAdjacentCoords();
				for(ComparableHantoCoordinate c: neighbors) {
					if(!adjCoords.contains(c)) {
						adjCoords.add(c);
						if(!nextNew.contains(c)) {
							nextNew.add(c);
						}
					}
				}
			}
			newCoords = nextNew;
		}
		adjCoords.remove(this);
		return adjCoords;
	}
	
	/** Increments Y by the given amount
	 * 
	 * @param amt The amount to increment Y by
	 */
	public void incrementY(int amt) {
		y += amt;
	}
	
	/** Increment X by the given amount
	 * 
	 * @param amt The amount to add to x
	 */
	public void incrementX(int amt) {
		x += amt;
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
	public int compareTo(ComparableHantoCoordinate o) {
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
