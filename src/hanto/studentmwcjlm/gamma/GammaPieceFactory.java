/**
 * 
 */
package hanto.studentmwcjlm.gamma;

import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentmwcjlm.common.BasicHantoPiece;
import hanto.studentmwcjlm.common.HantoPieceFactory;
import hanto.studentmwcjlm.common.movevalidator.WalkMoveValidator;
import hanto.studentmwcjlm.common.placementvalidator.AdjacentPlacementValidator;
import hanto.studentmwcjlm.common.placementvalidator.PlacementValidator;

/**
 * @author Mitchell Caisse
 *
 */
public class GammaPieceFactory implements HantoPieceFactory {

	/** The placement validator for Gamma */
	private PlacementValidator placementValidator;
	
	/** A Piece factory for Gamma
	 * 
	 */
	public GammaPieceFactory() {
		placementValidator = AdjacentPlacementValidator.getInstance();
	}
	
	@Override
	public BasicHantoPiece makePiece(HantoPlayerColor color, HantoPieceType type)
			throws HantoException {
		BasicHantoPiece piece = null;
		
		switch (type) {
			case BUTTERFLY:		
			case SPARROW:
				piece = new BasicHantoPiece(color, type, placementValidator, WalkMoveValidator.getInstance());
				break;		
				
			default:
				throw new HantoException("Unsupported Piece Type");
		}	
		
		return piece;
	}

}
