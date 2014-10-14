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
import hanto.studentmwcjlm.common.BasicHantoPiece;
import hanto.studentmwcjlm.common.ComparableHantoCoordinate;
import hanto.studentmwcjlm.common.placementvalidator.AdjacentPlacementValidator;
import hanto.studentmwcjlm.common.placementvalidator.PlacementValidator;
import hanto.studentmwcjlm.epsilon.EpsilonHantoGame;
import hanto.tournament.HantoMoveRecord;

import java.util.List;

/** The Hanto AI for playing before our butterfly has been placed, and after our first move has been made
 * 
 * @author Mitchell Caisse, James Megin
 *
 */

public class EarlyGameHantoAI extends BaseHantoAI {
	
	/** Creates a new Hanto AI for early game
	 * 
	 * @param game The hanto game currently being played
	 * @param myColor The piece color of the player
	 * @param turnCount The current turn count
	 */
	public EarlyGameHantoAI(EpsilonHantoGame game, HantoPlayerColor myColor,
			int turnCount) {
		super(game, myColor, turnCount);
	}

	@Override
	public HantoAIResult getNextMove() {
		HantoPieceType piece = HantoPieceType.CRAB;
		HantoAI nextAI = this;
		turnCount++;
		if(turnCount == 4) {
			piece = HantoPieceType.BUTTERFLY;
			nextAI = new LateGameHantoAI(game, myColor, turnCount);
		}
		HantoMoveRecord nextMove = new HantoMoveRecord(piece, null, getBestPlacementCoordinate());
		return new HantoAIResult(nextAI, nextMove);
	}
	
	/** Get the furest coordinate from (0, 0) where we can place a piece
	 * @param board the game board
	 * @param myColor my player color
	 * @return the coordinate
	 */
	private ComparableHantoCoordinate getBestPlacementCoordinate() {
		List<ComparableHantoCoordinate> myPieceLocations = board.getPiecesForPlayer(myColor);
		ComparableHantoCoordinate furthestCoord = null;
		int furthestDistance = 0;
		PlacementValidator placementValidator = AdjacentPlacementValidator.getInstance();
		for(ComparableHantoCoordinate coord: myPieceLocations) {
			List<ComparableHantoCoordinate> unoccupiedCoords = coord.getAdjacentCoords();
			unoccupiedCoords.removeAll(board.getAdjacentLocationsWithPieces(coord));
			for(ComparableHantoCoordinate c: unoccupiedCoords) {
				if(c.getDistance(new ComparableHantoCoordinate(0, 0)) > furthestDistance &&
						placementValidator.isPlacementValid(board, new BasicHantoPiece(myColor, HantoPieceType.CRAB), c)) {
					furthestCoord = c;
					furthestDistance = c.getDistance(new ComparableHantoCoordinate(0, 0));
				}
			}
		}
		return furthestCoord;
	}

}
