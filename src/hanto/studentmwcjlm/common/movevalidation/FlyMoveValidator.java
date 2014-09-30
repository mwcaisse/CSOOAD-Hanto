package hanto.studentmwcjlm.common.movevalidation;

import hanto.studentmwcjlm.common.HantoBoard;
import hanto.studentmwcjlm.common.HantoCoordinateImpl;

public class FlyMoveValidator implements MoveValidator {

	@Override
	public boolean isMoveValid(HantoBoard board, HantoCoordinateImpl from,
			HantoCoordinateImpl to) {
		return true;
	}

}
