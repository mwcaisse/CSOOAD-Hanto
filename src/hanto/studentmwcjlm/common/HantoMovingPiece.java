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
