/**
 * 
 */
package hanto.studentmwcjlm.common;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.MoveResult;

/**
 * @author Mitchell Caisse
 *
 */
public abstract class AbstractHantoGame implements HantoGame{

	/** The board for hanto */
	protected HantoBoard board;
	
	/** Creates a new abstract hanto
	 * 
	 */
	protected AbstractHantoGame() {
		this.board = new HantoBoard();
	}
	
	
	/**
	 * This method executes a move in the game. It is called for every move that must be
	 * made.
	 * 
	 * @param pieceType
	 *            the piece type that is being moved
	 * @param from
	 *            the coordinate where the piece begins. If the coordinate is null, then
	 *            the piece begins off the board (that is, it is placed on the board in
	 *            this move).
	 * @param to
	 *            the coordinated where the piece is after the move has been made.
	 * @return the result of the move
	 * @throws HantoException
	 *             if there are any problems in making the move (such as specifying a
	 *             coordinate that does not have the appropriate piece, or the color of
	 *             the piece is not the color of the player who is moving.
	 */
	
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from,
			HantoCoordinate to) throws HantoException {		
		if (to == null) {
			throw new HantoException("Move destination cannot be null");
		}
		HantoCoordinateImpl toCoord = convertHantoCoordinate(to);
		HantoCoordinateImpl fromCoord = null;
		if (from != null) {
			 fromCoord = convertHantoCoordinate(from);
		}
		return makeMove(pieceType, fromCoord, toCoord);
		
	}
	
	protected abstract MoveResult makeMove(HantoPieceType pieceType, HantoCoordinateImpl from,
			HantoCoordinateImpl to) throws HantoException;
	
	/** Converts the given hanto coordinate into a Hanto Coord implementation
	 * 
	 * @param coord The coordinate to convert
	 * @return The HantoCoord impl of this coord
	 */
	private HantoCoordinateImpl convertHantoCoordinate(HantoCoordinate coord) {
		return new HantoCoordinateImpl(coord);
	}
	
	/**
	 * @return a printable representation of the board.
	 */	
	public String getPrintableBoard() {
		return board.getPrintableBoard();
	}
	
	/**
	 * @param where the coordinate to query
	 * @return the piece at the specified coordinate or null if there is no 
	 * 	piece at that position
	 */
	public HantoPiece getPieceAt(HantoCoordinate where) {
		return getPieceAt(convertHantoCoordinate(where));
	}
	
	/**
	 * @param where the coordinate to query
	 * @return the piece at the specified coordinate or null if there is no 
	 * 	piece at that position
	 */
	protected HantoPiece getPieceAt(HantoCoordinateImpl where) {
		return board.getPieceAt(where);
	}
}
