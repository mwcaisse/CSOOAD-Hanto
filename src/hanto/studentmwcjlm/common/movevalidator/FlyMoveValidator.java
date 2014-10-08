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


/** Move validator for Pieces that can flt
 * 
 * @author Mitchell Caisse
 *
 */
public class FlyMoveValidator extends BasicMoveValidator {
	
	private int maxMoveDistance;
	
	public FlyMoveValidator() {
		this(0);
	}
	
	public FlyMoveValidator(int maxMoveDistance) {
		this.maxMoveDistance = maxMoveDistance;
	}
	
	@Override
	public boolean isMoveValid(HantoBoard board, HantoPiece piece, ComparableHantoCoordinate from, ComparableHantoCoordinate to) {
		if (!super.isMoveValid(board, piece, from, to)) {
			return false;
		}
		return maxMoveDistance == 0 || from.getDistance(to) <= maxMoveDistance;
	}

}
