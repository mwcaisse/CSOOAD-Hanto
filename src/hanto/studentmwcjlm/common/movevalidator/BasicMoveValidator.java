/**
 * 
 */
package hanto.studentmwcjlm.common.movevalidator;

import hanto.studentmwcjlm.common.ComparableHantoCoordinate;
import hanto.studentmwcjlm.common.HantoBoard;

/**
 * @author Mitchell Caisse
 *
 */
public class BasicMoveValidator implements MoveValidator {

	@Override
	public boolean isMoveValid(HantoBoard board, ComparableHantoCoordinate from, ComparableHantoCoordinate to) {
		return false;
	}

}
