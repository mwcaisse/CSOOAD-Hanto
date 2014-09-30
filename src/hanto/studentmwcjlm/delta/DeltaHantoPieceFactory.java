package hanto.studentmwcjlm.delta;

import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentmwcjlm.common.HantoMovingPiece;
import hanto.studentmwcjlm.common.HantoPieceFactory;
import hanto.studentmwcjlm.common.movevalidation.FlyMoveValidator;
import hanto.studentmwcjlm.common.movevalidation.WalkMoveValidator;

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
