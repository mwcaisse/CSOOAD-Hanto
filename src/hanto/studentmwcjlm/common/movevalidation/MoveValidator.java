/**
 * 
 */
package hanto.studentmwcjlm.common.movevalidation;

import hanto.studentmwcjlm.common.HantoBoard;
import hanto.studentmwcjlm.common.HantoCoordinateImpl;

/**
 * @author Mitchell Caisse
 *
 */
public interface MoveValidator {

	/** Determines if the given move is valid or not
	 * 
	 * @param board The current board
	 * @param from The from coordinate to move the piece from
	 * @param to The coordinate to move the piece too
	 * @return True if the move is valid, false otherwise
	 */
	public boolean isMoveValid(HantoBoard board, HantoCoordinateImpl from, HantoCoordinateImpl to);
	
}
