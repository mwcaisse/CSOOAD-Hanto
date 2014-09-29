/**
 * 
 */
package hanto.studentmwcjlm.common.movevalidation;

import hanto.studentmwcjlm.common.HantoBoard;
import hanto.studentmwcjlm.common.HantoCoordinateImpl;

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
	
	public boolean isMoveValid(HantoBoard board, HantoCoordinateImpl from, HantoCoordinateImpl to) {
		List<HantoCoordinateImpl> fromAdj = from.getAdjacentCoords();
		List<HantoCoordinateImpl> toAdj = to.getAdjacentCoords();
		
		fromAdj.retainAll(toAdj);
		
		//we moved only one space
		if (fromAdj.size() == 2) {
			int freeSpots = 0;
			for (HantoCoordinateImpl coord : fromAdj) {
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
