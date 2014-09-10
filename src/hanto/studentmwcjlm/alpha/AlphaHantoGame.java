package hanto.studentmwcjlm.alpha;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.MoveResult;
import hanto.studentmwcjlm.common.HantoBoard;

public class AlphaHantoGame implements HantoGame {

	/** The number of turns in the game */
	private int turnCount;
	
	/** The board for hanto */
	private HantoBoard board;
	
	public AlphaHantoGame() {
		init();
	}
	
	
	private void init() {
		turnCount = 0;
		
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
		
		//add piece to the board
		board.addPieceToBoard(new HantoPieceImpl(), to);		
		turnCount ++;			
		if (turnCount > 1) {
			return MoveResult.DRAW;
		}
		return MoveResult.OK;
	}
	
	/**
	 * @param where the coordinate to query
	 * @return the piece at the specified coordinate or null if there is no 
	 * 	piece at that position
	 */
	public HantoPiece getPieceAt(HantoCoordinate where) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return a printable representation of the board.
	 */	
	public String getPrintableBoard() {
		// TODO Auto-generated method stub
		return null;
	}

}
