/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/


package hanto.studentmwcjlm.common.movevalidator;

import hanto.studentmwcjlm.common.HantoBoard;
import hanto.studentmwcjlm.common.ComparableHantoCoordinate;

import java.util.List;

/** Move Validator for the Walking movement type
 * 
 * @author Mitchell Caisse
 *
 */
public class WalkMoveValidator implements MoveValidator {

	/** Determines if the given move is valid or not
	 * 
	 * @param board The current board
	 * @param from The from coordinate to move the piece from
	 * @param to The coordinate to move the piece too
	 * @return True if the move is valid, false otherwise
	 */
	
	public boolean isMoveValid(HantoBoard board, ComparableHantoCoordinate from, ComparableHantoCoordinate to) {
		List<ComparableHantoCoordinate> fromAdj = from.getAdjacentCoords();
		List<ComparableHantoCoordinate> toAdj = to.getAdjacentCoords();
		
		fromAdj.retainAll(toAdj);
		
		//we moved only one space
		if (fromAdj.size() == 2) {
			int freeSpots = 0;
			for (ComparableHantoCoordinate coord : fromAdj) {
				if (board.isLocationEmpty(coord)) {
					freeSpots ++;
				}
			}			
			//there was atleast one freespot, so walking is allowed
			return freeSpots > 0;			
		}
		
		return false;
	}
	
}
