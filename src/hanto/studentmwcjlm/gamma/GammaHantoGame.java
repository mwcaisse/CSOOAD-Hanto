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

import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentmwcjlm.common.AbstractHantoGame;

import java.util.HashMap;
import java.util.Map;

/** Gamma Hanto
 * @author Mitchell Caisse
 *
 */
public class GammaHantoGame extends AbstractHantoGame {
	
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
		turnLimit = 38; //20 turns, 2 in our count, 0 based
		pieceFactory = new GammaPieceFactory();
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

}
