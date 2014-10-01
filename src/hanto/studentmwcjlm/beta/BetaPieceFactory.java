/**
 * 
 */
package hanto.studentmwcjlm.beta;

import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentmwcjlm.common.BasicHantoPiece;
import hanto.studentmwcjlm.common.HantoPieceFactory;
import hanto.studentmwcjlm.common.movevalidator.NoMovementMoveValidator;
import hanto.studentmwcjlm.common.placementvalidator.BasicPlacementValidator;

/**
 * @author Mitchell Caisse
 *
 */
public class BetaPieceFactory implements HantoPieceFactory {


	public BasicHantoPiece makePiece(HantoPlayerColor color, HantoPieceType type)
			throws HantoException {

		BasicHantoPiece piece = null;
		
		switch(type) {
		case BUTTERFLY:
		case SPARROW:
			piece = new BasicHantoPiece(color, type, BasicPlacementValidator.getInstance(), NoMovementMoveValidator.getInstance());
			break;
			
			default:
				throw new HantoException("Invalid Piece Type");
		}
		
		return piece;
	}

}
