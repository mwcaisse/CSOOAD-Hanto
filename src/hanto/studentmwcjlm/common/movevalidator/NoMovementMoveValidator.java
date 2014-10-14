/**
 * 
 */
package hanto.studentmwcjlm.common.movevalidator;

import hanto.common.HantoPiece;
import hanto.studentmwcjlm.common.ComparableHantoCoordinate;
import hanto.studentmwcjlm.common.HantoBoard;

import java.util.ArrayList;
import java.util.List;

/** Move Validator for when movement is not allowed
 * 
 * @author Mitchell Caisse
 *
 */

public class NoMovementMoveValidator implements MoveValidator {

	/** The singleton instance */
	private static NoMovementMoveValidator instance = new NoMovementMoveValidator();
	
	/** Get the singleton instance
	 * 
	 * @return the singleton instance
	 */
	public static NoMovementMoveValidator getInstance() {
		return instance;
	}
	
	/** Protected constructor
	 * 
	 */
	protected NoMovementMoveValidator() {
		
	}
	
	@Override
	public boolean isMoveValid(HantoBoard board, HantoPiece piece,
			ComparableHantoCoordinate from, ComparableHantoCoordinate to) {
		return false; // move ment is not allowed
	}

	@Override
	public boolean hasLegalMove(HantoBoard board, HantoPiece piece,
			ComparableHantoCoordinate start) {
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
		return new ArrayList<ComparableHantoCoordinate>();
	}

	
	
}
