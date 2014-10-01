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
import hanto.studentmwcjlm.common.BasicHantoPiece;
import hanto.studentmwcjlm.common.HantoPieceFactory;
import hanto.studentmwcjlm.common.movevalidator.FlyMoveValidator;
import hanto.studentmwcjlm.common.movevalidator.WalkMoveValidator;
import hanto.studentmwcjlm.common.placementvalidator.AdjacentPlacementValidator;
import hanto.studentmwcjlm.common.placementvalidator.BasicPlacementValidator;
import hanto.studentmwcjlm.common.placementvalidator.PlacementValidator;

/** Piece Factory for Delta Hanto
 * 
 * @author Mitchell Caisse
 *
 */
public class DeltaHantoPieceFactory implements HantoPieceFactory {

	/** The placement validator to use for pieces */
	private PlacementValidator placementValidator;
	
	/** Creates a new Piece Factory for Delta Hanto
	 * 
	 */
	public DeltaHantoPieceFactory() {
		this.placementValidator = AdjacentPlacementValidator.getInstance();
	}
	
	@Override
	public BasicHantoPiece makePiece(HantoPlayerColor color,
			HantoPieceType type) throws HantoException {
		BasicHantoPiece piece = null;
		switch(type) {
		case CRAB:
		case BUTTERFLY:
			piece = new BasicHantoPiece(color, type, placementValidator, WalkMoveValidator.getInstance());
			break;
		case SPARROW:
			piece = new BasicHantoPiece(color, type, placementValidator, FlyMoveValidator.getInstance());
			break;
		default:
			throw new HantoException("Unsupported Piece Type");
		}
		return piece;
	}

}
