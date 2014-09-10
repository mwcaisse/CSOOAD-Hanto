package hanto.studentmwcjlm.common;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPiece;

import java.util.HashMap;

/** Board for keeping track of Hanto Pieces
 * 
 * @author Mitchell
 *
 */

public class HantoBoard {

	/** The mapping of pieces and coordinates */
	private HashMap<HantoCoordinate, HantoPiece> pieces;
	
	/** Creates a mew Hanto Board and initializes the board
	 * 
	 */
	public HantoBoard() {
		pieces = new HashMap<HantoCoordinate, HantoPiece>();
	}
	
	/** Returns the piece at the given location
	 * 
	 * @param at The coordinate to get the piece of
	 * @return The piece at the given coordinate
	 */
	public HantoPiece getPieceAt(HantoCoordinate at) {
		return pieces.get(at);
	}
	
	/** Adds a piece to the board, and determines if is a valid spot
	 * 
	 * @param piece The piece to add
	 * @param to The position to add the piece to
	 * @throws HantoException if the spot is invalid  
	 */
	public void addPieceToBoard(HantoPiece piece, HantoCoordinate to) throws HantoException {
		if (getPieceAt(to) != null) {
			throw new HantoException("There is already a piece at this spot");
		}
		pieces.put(to, piece);
	}
	
}
