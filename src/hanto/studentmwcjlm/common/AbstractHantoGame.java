/**
 * 
 */
package hanto.studentmwcjlm.common;

import java.util.Map;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;

/**
 * @author Mitchell Caisse
 *
 */
public abstract class AbstractHantoGame implements HantoGame{

	/** The board for hanto */
	protected HantoBoard board;
	
	/** The store of peices that a player has remaining */
	protected Map<HantoPlayerColor, HantoPlayerPieceCounter> piecesRemaining;
	
	/** The current turn count */
	protected int turnCount;
	
	/** The color of the player whose turn it currently is */
	protected HantoPlayerColor currentPlayerColor;
	
	/** Creates a new abstract hanto
	 * 
	 */
	protected AbstractHantoGame(HantoPlayerColor firstPlayer) {
		this.currentPlayerColor = firstPlayer;
		board = new HantoBoard();
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
	
	/** Sets the color of the current player 
	 * 
	 * @param color The color to set the current player color to
	 */
	public void setCurrentPlayerColor(HantoPlayerColor color) {
		this.currentPlayerColor = color;
	}
	
	/** Updates the Hanto player color to the next player
	 * 
	 * @return A copy of the new player's color
	 */
	protected HantoPlayerColor updateHantoPlayerColor() {
		if (currentPlayerColor == HantoPlayerColor.RED) {
			currentPlayerColor = HantoPlayerColor.BLUE;
		}
		else {
			currentPlayerColor = HantoPlayerColor.RED;
		}
		return currentPlayerColor;
	}
	
	/** Sets the hanto board to use
	 * 
	 * @param board The board to use
	 */
	public void setHantoBoard(HantoBoard board) {
		this.board = board;
	}
	
	/** Sets the current turn count
	 * 
	 * @param turnCount The new turn count
	 */
	public void setTurnCount(int turnCount) {
		this.turnCount = turnCount * 2;
	}
	
	/** Returns the pieces remining
	 * 
	 * @return THe pieces remaining
	 */
	public Map<HantoPlayerColor, HantoPlayerPieceCounter> getPiecesRemaining() {
		return piecesRemaining;
	}
}
