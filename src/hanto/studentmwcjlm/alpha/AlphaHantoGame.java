/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentmwcjlm.alpha;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentmwcjlm.common.BasicHantoPiece;
import hanto.studentmwcjlm.common.ComparableHantoCoordinate;
import hanto.studentmwcjlm.common.HantoBoard;

/** The implementation of HantoGame for Alpha
 * 
 * @author Mitchell Caisse, James Megin
 *
 */
public class AlphaHantoGame implements HantoGame {

	private int turnCount;
	
	private HantoBoard board;
	
	public AlphaHantoGame() {
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
	
	public MoveResult makeMove(HantoPieceType pieceType, ComparableHantoCoordinate from,
			ComparableHantoCoordinate to) throws HantoException {
		
		//check if the game is over
		if (isGameOver()) {
			throw new HantoException("Game is over!");
		}
		
		//check that pieces are not moving
		if(from != null) {
			throw new HantoException("Pieces cannot move in Alpha Hanto");
		}
		
		//check if it is a valid piece type
		if (isValidPieceType(pieceType)) {		
			//add piece to the board
			board.addPieceToBoard(new BasicHantoPiece(getTurnColor(), pieceType), to);		
			turnCount ++;			
			if (turnCount > 1) {
				return MoveResult.DRAW;
			}
			return MoveResult.OK;
		}
		else {
			throw new HantoException("Invalid piece type");
		}
	}
	

	/** Returns the player color for the current turn
	 * 
	 * @return The player color
	 */
	private HantoPlayerColor getTurnColor() {
		if (turnCount % 2 == 0) {
			return HantoPlayerColor.BLUE;
		}
		else {
			return HantoPlayerColor.RED;
		}
	}
	
	/** Determines if the given piece is a valid piece type for the game
	 * 
	 * @param type The type of the piece
	 * @return True if the piece is valid, false otherwise
	 */
	
	private boolean isValidPieceType(HantoPieceType type) {
		return type == HantoPieceType.BUTTERFLY;
	}
	
	/** Returns if the game is over or not
	 * 
	 * @return True if the game is over, false otherwise
	 */

	protected boolean isGameOver() {
		return turnCount >= 2;
	}
	
	/** Converts the given hanto coordinate into a Hanto Coord implementation
	 * 
	 * @param coord The coordinate to convert
	 * @return The HantoCoord impl of this coord
	 */
	private ComparableHantoCoordinate convertHantoCoordinate(HantoCoordinate coord) {
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
		return board.getPieceAt(convertHantoCoordinate(where));
	}

}
