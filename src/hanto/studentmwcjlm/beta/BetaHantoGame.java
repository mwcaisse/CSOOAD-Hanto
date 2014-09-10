package hanto.studentmwcjlm.beta;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentmwcjlm.common.HantoBoard;
import hanto.studentmwcjlm.common.HantoPlayerPieceCounter;

import java.util.HashMap;
import java.util.List;

public class BetaHantoGame implements HantoGame {

	/** The number of turns in the game */
	private int turnCount;
	
	/** The board for hanto */
	private HantoBoard board;
	private HashMap<HantoPlayerColor, HantoPlayerPieceCounter> piecesRemaining;
	private HantoPlayerColor firstPlayer;
	
	public BetaHantoGame(HantoPlayerColor firstPlayer) {
		this.firstPlayer = firstPlayer;
		init();
	}
	
	
	private void init() {
		turnCount = 0;
		
		board = new HantoBoard();
		
		piecesRemaining = new HashMap<HantoPlayerColor, HantoPlayerPieceCounter>();
		HashMap<HantoPieceType, Integer> initPieces = new HashMap<HantoPieceType, Integer>();
		initPieces.put(HantoPieceType.BUTTERFLY, 1);
		initPieces.put(HantoPieceType.SPARROW, 5);
		for(HantoPlayerColor color : HantoPlayerColor.values()) {
			piecesRemaining.put(color, new HantoPlayerPieceCounter(initPieces));
		}
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
		
		//check if the game is over
		if (isGameOver()) {
			throw new HantoException("Game is over!");
		}
		
		//check that pieces are not moving
		if(from != null) {
			throw new HantoException("Pieces cannot move in Alpha Hanto");
		}
		
		//check if it is a valid piece type
		if (canPlayPieceType(pieceType)) {		
			//add piece to the board
			board.addPieceToBoard(new HantoPieceImpl(getTurnColor(), pieceType), to);		
			decrementPieceType(pieceType);
			turnCount ++;
			return getMoveResult();
		}
		else {
			throw new HantoException("Invalid piece type");
		}
	}
	
	/**
	 * @param where the coordinate to query
	 * @return the piece at the specified coordinate or null if there is no 
	 * 	piece at that position
	 */
	public HantoPiece getPieceAt(HantoCoordinate where) {
		return board.getPieceAt(where);
	}

	/**
	 * @return a printable representation of the board.
	 */	
	public String getPrintableBoard() {
		// TODO Auto-generated method stub
		return null;
	}
	/** Returns the player color for the current turn
	 * 
	 * @return The player color
	 */
	private HantoPlayerColor getTurnColor() {
		if (turnCount % 2 == 0) {
			return firstPlayer;
		}
		else if(firstPlayer == HantoPlayerColor.BLUE) {
			return HantoPlayerColor.RED;
		}
		else {
			return HantoPlayerColor.BLUE;
		}
	}
	
	/** Determines if the given piece type can be placed on this turn
	 * 
	 * @param type The type of the piece
	 * @return True if the piece is valid, false otherwise
	 */
	private boolean canPlayPieceType(HantoPieceType type) {
		// if it's after the third turn and a butterfly has not been played by that color yet
		if((turnCount/2) >= 3 &&
				board.getPieceCount(HantoPieceType.BUTTERFLY, getTurnColor()) < 1 &&
				type != HantoPieceType.BUTTERFLY) {
			return false;
		}
		return piecesRemaining.get(getTurnColor()).getPiecesRemaining(type) > 0;
	}
	
	/** Decrement the number of pieces remaining for a type
	 * @param type the type of hanto piece to be decremented
	 */
	private void decrementPieceType(HantoPieceType type) {
		piecesRemaining.get(getTurnColor()).decrementPieceType(type);
	}
	
	/** Returns if the game is over or not
	 * 
	 * @return True if the game is over, false otherwise
	 */
	private boolean isGameOver() {
		return (getMoveResult() != MoveResult.OK);
	}
	
	private MoveResult getMoveResult() {
		boolean blueWon = hasPlayerWon(HantoPlayerColor.BLUE);
		boolean redWon = hasPlayerWon(HantoPlayerColor.RED);
		if(redWon && blueWon) {
			return MoveResult.DRAW;
		}
		else if(redWon) {
			return MoveResult.RED_WINS;
		}
		else if(blueWon) {
			return MoveResult.BLUE_WINS;
		}
		else if (turnCount > 11) {
			return MoveResult.DRAW;
		}
		else {
			return MoveResult.OK;
		}
	}
	
	private boolean hasPlayerWon(HantoPlayerColor color) {
		List<HantoCoordinate> butterflyLoc = board.getPieceCoordinates(HantoPieceType.BUTTERFLY, oppositeColor(color));
		if(!butterflyLoc.isEmpty()) {
			if(board.getAdjacentPieces(butterflyLoc.get(0)).size() >= 6) {
				return true;
			}
		}
		return false;
	}
	
	private HantoPlayerColor oppositeColor(HantoPlayerColor color) {
		if(color == HantoPlayerColor.BLUE) {
			return HantoPlayerColor.RED;
		}
		else {
			return HantoPlayerColor.BLUE;
		}
	}

}
