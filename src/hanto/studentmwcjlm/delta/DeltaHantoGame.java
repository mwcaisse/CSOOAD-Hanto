/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentmwcjlm.delta;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentmwcjlm.common.AbstractHantoGame;
import hanto.studentmwcjlm.common.ComparableHantoCoordinate;

import java.util.HashMap;
import java.util.Map;

/** Hanto Game for Delta Iteration
 * 
 * @author Mitchell Caisse
 *
 */
public class DeltaHantoGame extends AbstractHantoGame {
	
	/** Creates a new Delta Hanto Game
	 * 
	 * @param firstPlayer The player that will move first
	 */
	public DeltaHantoGame(HantoPlayerColor firstPlayer) {
		super(firstPlayer);
		init();
	}
	
	/** Initialize Delta */
	private void init() {			
		pieceFactory = new DeltaHantoPieceFactory();
	}
	
	/** Defines the starting inventory for this game
	 * 	
	 * @return The starting inventory for this game
	 */	
	protected Map<HantoPieceType, Integer> getStartingInventory() {
		HashMap<HantoPieceType, Integer> startingPieces = new HashMap<HantoPieceType, Integer>();
		startingPieces.put(HantoPieceType.BUTTERFLY, 1);
		startingPieces.put(HantoPieceType.SPARROW, 4);
		startingPieces.put(HantoPieceType.CRAB, 4);
		return startingPieces;
	}

	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from,
			HantoCoordinate to) throws HantoException {
		//check if the player resigns
		if(pieceType == null && from == null && to == null) {
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
