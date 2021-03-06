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

import hanto.common.HantoPiece;
import hanto.studentmwcjlm.common.ComparableHantoCoordinate;
import hanto.studentmwcjlm.common.HantoBoard;

import java.util.ArrayList;
import java.util.List;

/** Move Validator for the Walking movement type
 * 
 * @author Mitchell Caisse
 *
 */
public class WalkMoveValidator extends BasicMoveValidator {

	/** The singleton instance */
	private static WalkMoveValidator instance = new WalkMoveValidator();
	
	/** Gets the singleton instance
	 * 
	 * @return The singleton instance
	 */
	public static WalkMoveValidator getInstance() {
		return instance;
	}
	
	protected WalkMoveValidator() {
	}
	
	/** Determines if the given move is valid or not
	 * 
	 * @param board The current board
	 * @param piece The piece to move
	 * @param from The from coordinate to move the piece from
	 * @param to The coordinate to move the piece too
	 * @return True if the move is valid, false otherwise
	 */
	
	@Override
	public boolean isMoveValid(HantoBoard board, HantoPiece piece, ComparableHantoCoordinate from, ComparableHantoCoordinate to) {
		if (!super.isMoveValid(board, piece, from, to)) {
			return false;
		}
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
	
	public boolean hasLegalMove(HantoBoard board, HantoPiece piece, ComparableHantoCoordinate start) {
		List<ComparableHantoCoordinate> adjCoords = start.getAdjacentCoords();
		for(ComparableHantoCoordinate coord : adjCoords) {
			if(isMoveValid(board, piece, start, coord)) {
				return true;
			}
		}
		return false;
	}
	
	/** Returns a list of all the places the specified piece is able to move
	 * 
	 * @param board The board
	 * @param piece The piece to move
	 * @param currentPosition The current position of the piece
	 * @return
	 */
	public List<ComparableHantoCoordinate> getValidMovementCoordinates(HantoBoard board, HantoPiece piece, ComparableHantoCoordinate currentPosition) {
		List<ComparableHantoCoordinate> validDesitnations = new ArrayList<ComparableHantoCoordinate>();
		List<ComparableHantoCoordinate> adjCoords = currentPosition.getAdjacentCoords();
		for(ComparableHantoCoordinate coord : adjCoords) {
			if(isMoveValid(board, piece, currentPosition, coord)) {
				validDesitnations.add(coord);
			}
		}
		return validDesitnations;
	}
	
}
