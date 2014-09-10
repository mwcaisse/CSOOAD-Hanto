package hanto.studentmwcjlm.alpha;

import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;

/** The implementation of a Hanto Piece
 * 
 * @author Mitchell
 *
 */
public class HantoPieceImpl implements HantoPiece {

	/** The color of the player whoose piece this is */
	private HantoPlayerColor color;
	
	/** The type of piece */
	private HantoPieceType type;
	
	/** Creates a new Hanto Piece 
	 * 
	 */
	public HantoPieceImpl(HantoPlayerColor color, HantoPieceType type) {
		this.color = color;
		this.type = type;
	}
	
	public HantoPlayerColor getColor() {
		return color;
	}

	public HantoPieceType getType() {
		return type;
	}

	
	
}
