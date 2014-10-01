/**
 * 
 */
package hanto.studentmwcjlm.common.movevalidator;

import hanto.common.HantoPiece;
import hanto.studentmwcjlm.common.ComparableHantoCoordinate;
import hanto.studentmwcjlm.common.HantoBoard;

/** Move Validator for when movement is not allowed
 * 
 * @author Mitchell Caisse
 *
 */

public class NoMovementMoveValidator implements MoveValidator {

	/** The singleton instance */
	private static NoMovementMoveValidator instance;
	
	/** Get the singleton instance
	 * 
	 * @return the singleton instance
	 */
	public static NoMovementMoveValidator getInstance() {
		if (instance == null) {
			instance = new NoMovementMoveValidator();
		}
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

	
	
}
