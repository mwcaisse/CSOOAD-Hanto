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

import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentmwcjlm.common.ComparableHantoCoordinate;
import hanto.studentmwcjlm.epsilon.EpsilonHantoGame;
import hanto.tournament.HantoMoveRecord;

import java.util.List;

/** The Hanto AI to determine what our first move will be
 * 
 * @author Mitchell Caisse, James Megin
 *
 */
public class FirstMoveHantoAI extends BaseHantoAI {
	
	/** Creates a new First Move Hanto AI
	 * 
	 * @param game The game to use to play Hanto
	 * @param myColor The color of the player's pieces
	 */
	public FirstMoveHantoAI(EpsilonHantoGame game, HantoPlayerColor myColor) {
		super(game, myColor, 0);
	}
	
	/** Makes a move on the given game
	 * 
	 * @param game The game to make the move on
	 * @param myColor The color that we are
	 * @return The result of the move
	 */
	public HantoAIResult getNextMove() {
		HantoMoveRecord move;
		
		//check if we are moving first
		if (game.getBoard().getPieceCount() == 0) {
			//we are moving first, place crab on first spot
			move = new HantoMoveRecord(HantoPieceType.CRAB, null, new ComparableHantoCoordinate(0, 0));
		}
		else {
			//we are moving second
			move = new HantoMoveRecord(HantoPieceType.CRAB, null, getRandomNeighborCoord(
					new ComparableHantoCoordinate(0, 0)));			
		}			
		return new HantoAIResult(new EarlyGameHantoAI(game, myColor, turnCount + 1), move);
	}
	
	/** Gets a random coord adjacent to the given coordinate
	 * 
	 * @param coord The coordinate to get the adjacent coord of
	 * @return  The random coord
	 */
	private ComparableHantoCoordinate getRandomNeighborCoord(ComparableHantoCoordinate coord) {
		List<ComparableHantoCoordinate> adjCoords = coord.getAdjacentCoords();
		int num = (int)(Math.random() * adjCoords.size() - 1);
		return adjCoords.get(num);
	}

	
	
}
