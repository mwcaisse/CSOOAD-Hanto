/**
 * 
 */
package hanto.studentmwcjlm.common.movevalidator;

import hanto.common.HantoPiece;
import hanto.studentmwcjlm.common.ComparableHantoCoordinate;
import hanto.studentmwcjlm.common.HantoBoard;

/**
 * @author Mitchell Caisse
 *
 */
public class BasicMoveValidator implements MoveValidator {
	
	private MoveValidator contiguousMoveValidator = ContiguousMoveValidator.getInstance();
	
	@Override
	public boolean isMoveValid(HantoBoard board, HantoPiece piece, ComparableHantoCoordinate from, ComparableHantoCoordinate to) {
		HantoPiece toMove = board.getPieceAt(from);
		//check that the piece on the board, is the same as the piece we were told to move
		if (toMove == null || toMove.getColor() != piece.getColor() || toMove.getType() != piece.getType()) {
			return false; //given piece differsnt from board piece
		}
		if (!board.isLocationEmpty(to)) {
			return false; //destination is not empty
		}
		return contiguousMoveValidator.isMoveValid(board, piece, from, to);
	}
}
