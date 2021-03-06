/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentmwcjlm.epsilon;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.HantoPrematureResignationException;
import hanto.common.MoveResult;
import hanto.studentmwcjlm.common.AbstractHantoGame;
import hanto.studentmwcjlm.common.ComparableHantoCoordinate;

import java.util.HashMap;
import java.util.Map;

/** Elpsioln version of Hanto
 * 
 * @author Mitchell Caisse, James Megin
 *
 */
public class EpsilonHantoGame extends AbstractHantoGame {

	/** Create a new epsilon hanto game
	 * @param firstPlayerColor the color of the player that goes first
	 */
	public EpsilonHantoGame(HantoPlayerColor firstPlayerColor) {
		super(firstPlayerColor);
		init();
	}
	
	/** Initialize Epsilon */
	private void init() {
		pieceFactory = new EpsilonHantoPieceFactory();
	}

	/** Defines the starting inventory for this game
	 * 	
	 * @return The starting inventory for this game
	 */	
	@Override
	protected Map<HantoPieceType, Integer> getStartingInventory() {
		HashMap<HantoPieceType, Integer> startingPieces = new HashMap<HantoPieceType, Integer>();
		startingPieces.put(HantoPieceType.BUTTERFLY, 1);
		startingPieces.put(HantoPieceType.SPARROW, 2);
		startingPieces.put(HantoPieceType.CRAB, 6);
		startingPieces.put(HantoPieceType.HORSE, 4);
		return startingPieces;
	}
	
	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from,
			HantoCoordinate to) throws HantoException {
		//check if the player resigns
		if(pieceType == null && from == null && to == null) {
			if (currentPlayer.hasLegalMove(board)) {
				throw new HantoPrematureResignationException();
			}
			resigned = true;
			return getMoveResult();
		}
		ComparableHantoCoordinate toCoord = convertHantoCoordinate(to);
		ComparableHantoCoordinate fromCoord = null;
		if (from != null) {
			 fromCoord = convertHantoCoordinate(from);
		}
		return makeMove(pieceFactory.makePiece(currentPlayer.getColor(), pieceType), fromCoord, toCoord);
		
	}

}
