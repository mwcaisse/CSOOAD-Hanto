/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentmwcjlm.delta;

import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentmwcjlm.common.HantoMovingPiece;
import hanto.studentmwcjlm.common.HantoPieceFactory;
import hanto.studentmwcjlm.common.movevalidation.FlyMoveValidator;
import hanto.studentmwcjlm.common.movevalidation.WalkMoveValidator;

/** Piece Factory for Delta Hanto
 * 
 * @author Mitchell Caisse
 *
 */
public class DeltaHantoPieceFactory implements HantoPieceFactory {

	@Override
	public HantoMovingPiece makePiece(HantoPlayerColor color,
			HantoPieceType type) throws HantoException {
		HantoMovingPiece piece = null;
		switch(type) {
		case CRAB:
			piece = new HantoMovingPiece(color, type, new WalkMoveValidator());
			break;
		case BUTTERFLY:
			piece = new HantoMovingPiece(color, type, new WalkMoveValidator());
			break;
		case SPARROW:
			piece = new HantoMovingPiece(color, type, new FlyMoveValidator());
			break;
		default:
			throw new HantoException("Unsupported Piece Type");
		}
		return piece;
	}

}
