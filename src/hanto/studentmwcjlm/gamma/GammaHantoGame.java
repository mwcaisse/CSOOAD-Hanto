/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/


package hanto.studentmwcjlm.gamma;

import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentmwcjlm.common.AbstractHantoGame;
import hanto.studentmwcjlm.common.ComparableHantoCoordinate;
import hanto.studentmwcjlm.common.HantoBoard;
import hanto.studentmwcjlm.common.BasicHantoPiece;
import hanto.studentmwcjlm.common.movevalidator.ContiguousMoveValidator;
import hanto.studentmwcjlm.common.movevalidator.MoveValidator;
import hanto.studentmwcjlm.common.movevalidator.WalkMoveValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Gamma Hanto
 * @author Mitchell Caisse
 *
 */
public class GammaHantoGame extends AbstractHantoGame {

	/** The validator to use to ensure moves are valid */
	private MoveValidator moveValidator;
	
	/** Creates a new Gamma Hanto Game
	 * 
	 * @param firstPlayer The player that will move first
	 */
	public GammaHantoGame(HantoPlayerColor firstPlayer) {
		super(firstPlayer);
		init();
	}
	
	/** Initialize Gamma */
	private void init() {		
		moveValidator = new WalkMoveValidator();		
		turnLimit = 38; //20 turns, 2 in our count, 0 based
	}
	
	/** Defines the starting inventory for this game
	 * 
	 * 	@return The starting inventory for this game
	 */
	
	protected Map<HantoPieceType, Integer> getStartingInventory() {
		HashMap<HantoPieceType, Integer> startingPieces = new HashMap<HantoPieceType, Integer>();
		startingPieces.put(HantoPieceType.BUTTERFLY, 1);
		startingPieces.put(HantoPieceType.SPARROW, 5);
		return startingPieces;
	}
	
	/** Moves a piece of the specified type, from the given coordinate, to the given coordinate
	 * 
	 * @param pieceType The type of piece to move
	 * @param from The position to move the piece from
	 * @param to The position to move the piece to
	 * @throws HantoException if the move is invalid
	 */
	protected void movePiece(HantoPieceType pieceType, ComparableHantoCoordinate from,
			ComparableHantoCoordinate to) throws HantoException {
		
		if (isValidMove(pieceType, from, to)) {
			board.movePiece(from, to);	
		}
		else {
			throw new HantoException("Invalid peice movement");
		}
	}
	
	/** Places a piece of the given type at the given location
	 * 
	 * @param pieceType The type of piece to player
	 * @param to The location to place the piece
	 * @throws HantoException If the piece placement is invalid
	 */
	
	protected void placePiece(HantoPieceType pieceType, ComparableHantoCoordinate to) throws HantoException {
		//check if this placement is valid
		if (canPlayPieceType(pieceType) && isValidPlacement(pieceType, to)) {		
			//add piece to the board
			board.addPieceToBoard(new BasicHantoPiece(currentPlayer.getColor(), pieceType), to);		
			currentPlayer.placePiece(pieceType, to);		
		}
		else {
			throw new HantoException("Invalid piece placement");
		}
	}
	
	/** Checks if the given placement of the piece is valid
	 * 
	 * @param pieceType The type of piece that is being moved
	 * @param from The from position
	 * @param to The to position
	 * @return Whether it is valid or not
	 */
	private boolean isValidPlacement(HantoPieceType pieceType, ComparableHantoCoordinate to) {
		//check that peice is not next to a peice of a different color, if not moving, and not first 2 places
		if (turnCount >= 2) {
			List<HantoPiece> adjacentPeices = board.getAdjacentPieces(to);
			//check that the color of all the adjacent peices are same color
			for (HantoPiece piece : adjacentPeices) {
				if (!piece.getColor().equals(currentPlayer.getColor())) {
					return false; //placed a peice next to a color not his own
				}
			}
		}
		
		return  true;
	}
	
	/** Determines if moving the given piece from the given coord to the given coord is valid
	 * 
	 * @param pieceType THe type of piece to move
	 * @param from The position to move it from
	 * @param to The position to move it to
	 * @return True if the move is valid
	 */
	private boolean isValidMove(HantoPieceType pieceType, ComparableHantoCoordinate from, ComparableHantoCoordinate to) throws HantoException{
		//check if the piece is the right color + type + exists
		HantoPiece toMove = board.getPieceAt(from);
		if (toMove == null) {
			throw new HantoException("No piece at given spot to move from");
		}
		if (toMove.getColor() != currentPlayer.getColor()) {
			throw new HantoException("Can only move pieces of your own color");
		}		
		if (toMove.getType() != pieceType) {
			throw new HantoException("Piece tyes do not match");
		}		
		//check that the board space is empty
		if (!board.isLocationEmpty(to)) {
			throw new HantoException("Destination is occupied");
		}		
		//check that the new board is contiguous
		if (!isMoveContigous(from, to)) {
			throw new HantoException("Resulting board is not contigious");
		}
		if (!moveValidator.isMoveValid(board, from, to)) {
			throw new HantoException("Invalid movement");
		}
		
		return true;
	}
	
	/** Checks if the board resulting of the move is contigious
	 * 
	 *  TODO: Placeholder method
	 * 
	 * @param from The ocordinate to move piece from
	 * @param to Coordinate to moave the piece to
	 * @return True if contigious false otherwise
	 */
	private boolean isMoveContigous(ComparableHantoCoordinate from, ComparableHantoCoordinate to) {
		return new ContiguousMoveValidator().isMoveValid(board, from, to);
	}
	
}
