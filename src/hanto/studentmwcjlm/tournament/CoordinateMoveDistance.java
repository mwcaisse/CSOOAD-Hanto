/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentmwcjlm.tournament;

import hanto.studentmwcjlm.common.ComparableHantoCoordinate;

/** Keep track of where a potential move is coming from and where it is going
 * @author James, Mitchell
 *
 */
public class CoordinateMoveDistance {

	private ComparableHantoCoordinate destination;
	private ComparableHantoCoordinate currentPosition;
	
	/** Creates a new Coordinate Move Distance
	 * 
	 * @param destination The destination coordinate
	 * @param currentPosition The current position
	 */
	public CoordinateMoveDistance(ComparableHantoCoordinate destination, ComparableHantoCoordinate currentPosition) {
		this.destination = destination;
		this.currentPosition = currentPosition;
	}
	
	public ComparableHantoCoordinate getDestination() {
		return destination;
	}
	
	public ComparableHantoCoordinate getCurrentPosition() {
		return currentPosition;
	}
	
}
