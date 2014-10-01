/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentmwcjlm.beta;

import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentmwcjlm.common.AbstractHantoGame;
import hanto.studentmwcjlm.common.ComparableHantoCoordinate;
import hanto.studentmwcjlm.common.BasicHantoPiece;

import java.util.HashMap;
import java.util.Map;

/** The implementation of Hanto Game for Beta
 * 
 * @author Mitchell Caisse, James Megin
 *
 */
public class BetaHantoGame extends AbstractHantoGame {
	
	/** Creates a new Beta Hanto Game
	 * 
	 * @param firstPlayer The color of the player to go first
	 */
	public BetaHantoGame(HantoPlayerColor firstPlayer) {
		super(firstPlayer);
		init();
	}	
	/** Initialize the settings for this hanto game
	 * 
	 */
	private void init() {
		turnLimit = 12;	
	}
	
	/** Defines the starting inventory for this game
	 * 
	 * @return The starting inventory
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
		
		throw new HantoException("Moving pieces is not allowed in Beta Hanto!");
	}
	
	/** Places a piece of the given type at the given location
	 * 
	 * @param pieceType The type of piece to player
	 * @param to The location to place the piece
	 * @throws HantoException If the piece placement is invalid
	 */
	
	protected void placePiece(HantoPieceType pieceType, ComparableHantoCoordinate to) throws HantoException {
		//check if this placement is valid
		if (canPlayPieceType(pieceType)) {		
			//add piece to the board
			board.addPieceToBoard(new BasicHantoPiece(currentPlayer.getColor(), pieceType), to);		
			currentPlayer.placePiece(pieceType, to);
		}
		else {
			throw new HantoException("Invalid piece placement");
		}
	}

}
