/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentmwcjlm.tournament;

import hanto.common.HantoPlayerColor;
import hanto.studentmwcjlm.common.HantoBoard;
import hanto.studentmwcjlm.epsilon.EpsilonHantoGame;

/** The Base Hanto AI, contains common functionality for each AI
 * 
 * @author Mitchell Caisse
 *
 */
public abstract class BaseHantoAI implements HantoAI {

	/** Keep track of how many turns */
	protected int turnCount;
	
	/** The Hanto Game being played */
	protected final EpsilonHantoGame game;
	
	/** My color */
	protected final HantoPlayerColor myColor;
	
	/** The board for playing Hanto */
	protected HantoBoard board;
	
	/** Creates a new Base Hanto AI with the given game, color, and turn count
	 * 
	 * @param game The game to use to play Hanto
	 * @param myColor The color of the player's pieces
	 * @param turnCount The current turn count
	 */
	
	protected BaseHantoAI(EpsilonHantoGame game, HantoPlayerColor myColor, int turnCount) {
		this.game = game;
		this.myColor = myColor;
		this.turnCount = turnCount;
		board = game.getBoard();
	}
	
}
