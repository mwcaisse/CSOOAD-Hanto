/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentmwcjlm.common.movevalidator;

import hanto.common.HantoPiece;
import hanto.studentmwcjlm.common.ComparableHantoCoordinate;
import hanto.studentmwcjlm.common.HantoBoard;

/** Move Validator that checks Jumping movement is valid
 * 
 * @author Mitchell Caisse, James Megin
 *
 */
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
