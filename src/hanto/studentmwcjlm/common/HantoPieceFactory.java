package hanto.studentmwcjlm.common;

import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;

/** Factory to make Hanto Pieces
 * @author James
 *
 */
public interface HantoPieceFactory {
	public HantoMovingPiece makePiece(HantoPlayerColor color, HantoPieceType type) throws HantoException;
}
