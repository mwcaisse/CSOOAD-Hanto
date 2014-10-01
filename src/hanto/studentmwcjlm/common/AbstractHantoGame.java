/**
 * 
 */
package hanto.studentmwcjlm.common;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mitchell Caisse
 *
 */
public abstract class AbstractHantoGame implements HantoGame{

	/** The board for hanto */
	protected HantoBoard board;
	
	/** The state of the current players */
	protected Map<HantoPlayerColor, HantoPlayer> players;
	
	/** The current turn count */
	protected int turnCount;
	
	/** The color of the player who moves first */
	protected final HantoPlayerColor firstPlayerColor;
	
	/** The current Hanto Player */
	protected HantoPlayer currentPlayer;
	
	/** The turn limit */
	protected int turnLimit;
	
	/** Tell if a player has resigned */
	protected boolean resigned;
	
	/** Creates a new abstract hanto
	 * 
	 */
	protected AbstractHantoGame(HantoPlayerColor firstPlayer) {
		this.firstPlayerColor = firstPlayer;
		board = new HantoBoard();
		turnCount = 0;
		turnLimit = 0;
		resigned = false;
		
		//initialize the players
		initializePlayers();
	}
	
	/** Initializes the Hanto players
	 * 
	 */
	private void initializePlayers() {
		players = new HashMap<HantoPlayerColor, HantoPlayer>();
		
		//add a player for each color
		for (HantoPlayerColor color : HantoPlayerColor.values()) {
			HantoPlayer player = new HantoPlayer(color, getStartingInventory());
			players.put(color, player);
		}
		
		//set the current player to the first player
		currentPlayer = players.get(firstPlayerColor);
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
		ComparableHantoCoordinate toCoord = convertHantoCoordinate(to);
		ComparableHantoCoordinate fromCoord = null;
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
	
	protected abstract MoveResult makeMove(HantoPieceType pieceType, ComparableHantoCoordinate from,
			ComparableHantoCoordinate to) throws HantoException;
	
	/** Converts the given hanto coordinate into a Hanto Coord implementation
	 * 
	 * @param coord The coordinate to convert
	 * @return The HantoCoord impl of this coord
	 */
	protected ComparableHantoCoordinate convertHantoCoordinate(HantoCoordinate coord) {
		return new ComparableHantoCoordinate(coord);
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
	protected HantoPiece getPieceAt(ComparableHantoCoordinate where) {
		return board.getPieceAt(where);
	}
	
	/** Sets the color of the current player 
	 * 
	 * @param color The color to set the current player color to
	 */
	public void setCurrentPlayerColor(HantoPlayerColor color) {
		this.currentPlayer = players.get(color);
	}
	
	/** Updates the Hanto player to the next player
	 * 
	 * @return A copy of the new player
	 */
	protected HantoPlayer updateHantoPlayer() {
		currentPlayer = players.get(oppositeColor(currentPlayer.getColor()));
		return currentPlayer;
	}
	
	/** Sets the hanto board to use
	 * 
	 * @param board The board to use
	 */
	public void setHantoBoard(HantoBoard board) {
		this.board = board;
	}
	
	/** Sets the current turn count where 1 is the first turn. And one turn is when both players have moved.
	 * 
	 * @param turnCount The new turn count
	 */
	public void setTurnCount(int turnCount) {
		this.turnCount = (turnCount - 1) * 2; //zero base it, then convert into one move is one turn
	}
	
	/** Returns the Hanto Player of the given color
	 * 
	 * @param color The color of the player to fetch
	 * @return The Hanto Player
	 */
	public HantoPlayer getHantoPlayer(HantoPlayerColor color) {
		return players.get(color);
	}
	
	/** Determines if the given piece type can be placed on this turn
	 * 
	 * @param type The type of the piece
	 * @return True if the piece is valid, false otherwise
	 */
	protected boolean canPlayPieceType(HantoPieceType type) {		
		//check if is greater than the 4th turn and the player has not, or is not, placing thier butterfly
		if((turnCount/2) >= 3 &&
				!currentPlayer.hasPlacedButterfly() &&
				type != HantoPieceType.BUTTERFLY) {
			return false;
		}
		//if they player has one of the pieces, then they can place it
		return currentPlayer.hasPiece(type);
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
			if(currentPlayer.getColor() == HantoPlayerColor.BLUE) {
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
		// the other player
		HantoPlayer otherPlayer = players.get(oppositeColor(color));
		//check if the player has placed thier butterfly
		if (otherPlayer.hasPlacedButterfly()) {
			ComparableHantoCoordinate butterflyLocation = otherPlayer.getButterflyLocation();
			//check if the butterfly is surrounded, if it is, the calling player has won
			if(board.getAdjacentPieces(butterflyLocation).size() >= 6) {
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
	
	/** Returns the starting pieces for a player
	 * 
	 * @return The starting inventory for a player
	 */
	protected abstract HashMap<HantoPieceType, Integer> getStartingInventory();
	
	/** Updates the turn count + player at the end of a move
	 * 
	 */
	protected void finalizeMove() {
		turnCount ++;
		updateHantoPlayer();
	}
	
	
}
