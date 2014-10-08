package hanto.studentmwcjlm.epsilon;

import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentmwcjlm.common.BasicHantoPiece;
import hanto.studentmwcjlm.common.HantoPieceFactory;
import hanto.studentmwcjlm.common.movevalidator.FlyMoveValidator;
import hanto.studentmwcjlm.common.movevalidator.JumpMoveValidator;
import hanto.studentmwcjlm.common.movevalidator.WalkMoveValidator;
import hanto.studentmwcjlm.common.placementvalidator.AdjacentPlacementValidator;
import hanto.studentmwcjlm.common.placementvalidator.PlacementValidator;

public class EpsilonHantoPieceFactory implements HantoPieceFactory {

	/** The placement validator to use for pieces */
	private PlacementValidator placementValidator;
	/** The flying movement validator */
	private FlyMoveValidator flyMoveValidator;
	
	/** Creates a new Piece Factory for Epsilon Hanto
	 * 
	 */
	public EpsilonHantoPieceFactory() {
		placementValidator = AdjacentPlacementValidator.getInstance();
		flyMoveValidator = new FlyMoveValidator(4);
	}
	
	@Override
	public BasicHantoPiece makePiece(HantoPlayerColor color, HantoPieceType type)
			throws HantoException {
		BasicHantoPiece piece = null;
		switch(type) {
		case CRAB:
		case BUTTERFLY:
			piece = new BasicHantoPiece(color, type, placementValidator, WalkMoveValidator.getInstance());
			break;
		case SPARROW:
			piece = new BasicHantoPiece(color, type, placementValidator, flyMoveValidator);
			break;
		case HORSE:
			piece = new BasicHantoPiece(color, type, placementValidator, JumpMoveValidator.getInstance());
			break;
		default:
			throw new HantoException("Unsupported Piece Type");
		}
		return piece;
	}

}
