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

import java.util.List;

/**
 * @author Mitchell Caisse
 *
 */
public interface MoveValidator {

	/** Determines if the given move is valid or not
	 * 
	 * @param board The current board
	 * @param piece The hanto piece that will be moved
	 * @param from The from coordinate to move the piece from
	 * @param to The coordinate to move the piece too
	 * @return True if the move is valid, false otherwise
	 */
	boolean isMoveValid(HantoBoard board, HantoPiece piece, ComparableHantoCoordinate from, ComparableHantoCoordinate to);
	
	/** Determines if the piece has any legal moves on the given board
	 * 
	 * @param board The board to check for legal moves on
	 * @param piece The piece to check if it has legal moves
	 * @param position The position of the piece on the board
	 * @return True if the piece has any legal moves, false otherwise
	 */
	
	boolean hasLegalMove(HantoBoard board, HantoPiece piece, ComparableHantoCoordinate position);
	
	/** Returns a list of all the places the specified piece is able to move
	 * 
	 * @param board The board
	 * @param piece The piece to move
	 * @param currentPosition The current position of the piece
	 * @return
	 */
	List<ComparableHantoCoordinate> getValidMovementCoordinates(HantoBoard board, HantoPiece piece, ComparableHantoCoordinate currentPosition);
}
