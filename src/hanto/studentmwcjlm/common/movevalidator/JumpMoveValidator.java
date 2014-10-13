package hanto.studentmwcjlm.common.movevalidator;

import hanto.common.HantoPiece;
import hanto.studentmwcjlm.common.ComparableHantoCoordinate;
import hanto.studentmwcjlm.common.HantoBoard;

public class JumpMoveValidator extends BasicMoveValidator {
	
	/** The singleton instance */
	private static JumpMoveValidator instance = new JumpMoveValidator();
	
	/** Gets the singleton instance
	 * 
	 * @return The singleton instance
	 */
	public static JumpMoveValidator getInstance() {
		return instance;
	}
	
	/** Protected constructor
	 * 
	 */
	protected JumpMoveValidator() {
		
	}
	
	@Override
	public boolean isMoveValid(HantoBoard board, HantoPiece piece, ComparableHantoCoordinate from, ComparableHantoCoordinate to) {
		if (!super.isMoveValid(board, piece, from, to)) {
			return false;
		}
		if (from.getY() == to.getY()) {

		}
		else if (from.getX() == to.getY()) {
			//moved in straight line in the y direction
		}
		else if ((to.getX() - from.getX()) == -1 * (to.getY() - from.getY())) {
			//moved diagonally 
		}
		return false;
	}
}
