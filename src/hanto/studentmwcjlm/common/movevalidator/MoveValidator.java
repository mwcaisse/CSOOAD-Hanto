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
	boolean hasLegalMove(HantoBoard board, HantoPiece piece, ComparableHantoCoordinate start);
}