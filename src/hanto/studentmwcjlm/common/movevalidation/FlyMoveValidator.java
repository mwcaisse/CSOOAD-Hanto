package hanto.studentmwcjlm.common.movevalidation;

import hanto.studentmwcjlm.common.HantoBoard;
import hanto.studentmwcjlm.common.ComparableHantoCoordinate;

public class FlyMoveValidator implements MoveValidator {

	@Override
	public boolean isMoveValid(HantoBoard board, ComparableHantoCoordinate from,
			ComparableHantoCoordinate to) {
		return true;
	}

}
