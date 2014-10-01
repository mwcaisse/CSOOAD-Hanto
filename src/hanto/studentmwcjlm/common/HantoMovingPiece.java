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

import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentmwcjlm.common.movevalidation.MoveValidator;

/**
 * @author James
 * Moving Hanto Pieces
 */
public class HantoMovingPiece extends HantoPieceImpl {

	/** Move validator for the piece */
	private MoveValidator moveValidator;
	
	/** Creates a new Movable Hanto Piece
	 * 
	 * @param color The color of this piece
	 * @param type The type of this piece
	 * @param moveValidator The move validator of this piece to use
	 */
	public HantoMovingPiece(HantoPlayerColor color, HantoPieceType type, MoveValidator moveValidator) {
		super(color, type);
		this.moveValidator = moveValidator;
	}

	/** Check to see if a move is valid
	 * @param board the board the piece is being moved on
	 * @param from the starting location
	 * @param to the ending location
	 * @return true if the move is valid, false otherwise
	 */
	public boolean validateMove(HantoBoard board, ComparableHantoCoordinate from, ComparableHantoCoordinate to) {
		return moveValidator.isMoveValid(board, from, to);
	}

}
