/**
 * 
 */
package hanto.studentmwcjlm.common;

import java.util.List;
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
	
	/** The turn limit */
	protected int turnLimit;
	
	/** Tell if a player has resigned */
	protected boolean resigned;
	
	/** Creates a new abstract hanto
	 * 
	 */
	protected AbstractHantoGame(HantoPlayerColor firstPlayer) {
		this.currentPlayerColor = firstPlayer;
		board = new HantoBoard();
		turnCount = 0;
		turnLimit = 0;
		resigned = false;
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
	protected HantoCoordinateImpl convertHantoCoordinate(HantoCoordinate coord) {
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
		currentPlayerColor = oppositeColor(currentPlayerColor);
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
	
	
	/** Decrement the number of pieces remaining for a type
	 * @param type the type of hanto piece to be decremented
	 */
	protected void decrementPieceType(HantoPieceType type) {
		piecesRemaining.get(currentPlayerColor).decrementPieceType(type);
	}
	
	/** Determines if the given piece type can be placed on this turn
	 * 
	 * @param type The type of the piece
	 * @return True if the piece is valid, false otherwise
	 */
	protected boolean canPlayPieceType(HantoPieceType type) {
		// if it's after the third turn and a butterfly has not been played by that color yet
		if((turnCount/2) >= 3 &&
				board.getPieceCount(HantoPieceType.BUTTERFLY, currentPlayerColor) < 1 &&
				type != HantoPieceType.BUTTERFLY) {
			return false;
		}
		return piecesRemaining.get(currentPlayerColor).getPiecesRemaining(type) > 0;
	}
	
	/** Returns the opposite color of the given color
	 * 
	 * @param color The color to get the opposite of
	 * @return The opposite color
	 */
	protected HantoPlayerColor oppositeColor(HantoPlayerColor color) {
		if(color == HantoPlayerColor.BLUE) {
			return HantoPlayerColor.RED;
		}
		else {
			return HantoPlayerColor.BLUE;
		}
	}
	
	/** Gest the move result of the last move (aka check win condition)
	 * 
	 * @return The movie result of the last move 
	 */
	protected MoveResult getMoveResult() {
		boolean blueWon = hasPlayerWon(HantoPlayerColor.BLUE);
		boolean redWon = hasPlayerWon(HantoPlayerColor.RED);
		if(resigned) {
			if(currentPlayerColor == HantoPlayerColor.BLUE) {
				return MoveResult.RED_WINS;
			}
			else {
				return MoveResult.BLUE_WINS;
			}
		}
		if(redWon && blueWon) {
			return MoveResult.DRAW;
		}
		else if(redWon) {
			return MoveResult.RED_WINS;
		}
		else if(blueWon) {
			return MoveResult.BLUE_WINS;
		}
		else if (turnLimit > 0 && turnCount >= turnLimit) {
			return MoveResult.DRAW;
		}
		else {
			return MoveResult.OK;
		}
	}
	
	/** Determines whether the player of the given color has won the game
	 * 
	 * @param color The color of the player
	 * @return True/False if the player won/loss
	 */
	private boolean hasPlayerWon(HantoPlayerColor color) {
		List<HantoCoordinateImpl> butterflyLoc = board.getPieceCoordinates(HantoPieceType.BUTTERFLY, oppositeColor(color));
		if(!butterflyLoc.isEmpty()) {
			if(board.getAdjacentPieces(butterflyLoc.get(0)).size() >= 6) {
				return true;
			}
		}
		return false;
	}
	
	/** Returns if the game is over or not
	 * 
	 * @return True if the game is over, false otherwise
	 */
	protected boolean isGameOver() {
		return (getMoveResult() != MoveResult.OK);
	}
	
}
