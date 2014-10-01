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

import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentmwcjlm.common.AbstractHantoGame;

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
		turnLimit = 6;	
		pieceFactory = new BetaPieceFactory();
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

}
