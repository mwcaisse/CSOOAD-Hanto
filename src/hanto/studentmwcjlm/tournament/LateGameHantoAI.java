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
import hanto.studentmwcjlm.common.AbstractHantoGame;
import hanto.studentmwcjlm.common.BasicHantoPiece;
import hanto.studentmwcjlm.common.ComparableHantoCoordinate;
import hanto.studentmwcjlm.common.HantoPlayer;
import hanto.studentmwcjlm.common.placementvalidator.AdjacentPlacementValidator;
import hanto.studentmwcjlm.common.placementvalidator.PlacementValidator;
import hanto.studentmwcjlm.epsilon.EpsilonHantoGame;
import hanto.tournament.HantoMoveRecord;

import java.util.List;
import java.util.Map;

/** The Hanto AI for playing after the butterfly has been placed
 * 
 * @author Mitchell Caisse, James Megin
 *
 */

public class LateGameHantoAI extends BaseHantoAI {
	
	/** Creates a new Hanto AI for playing the game after the butterfly placement
	 * 
	 * @param game The game to use for playing Hanto
	 * @param myColor The color of the player's pieces
	 * @param turnCount The current turn count
	 */
	
	public LateGameHantoAI(EpsilonHantoGame game, HantoPlayerColor myColor, int turnCount) {
		super(game, myColor, turnCount);
	}
	
	@Override
	public HantoAIResult getNextMove() {
		turnCount++;
		HantoMoveRecord move;
		HantoAI nextAI = this;
		
		//check if we need to resign
		if (!hasValidMove()) {
			//no valid moves, time to resign
			move = createResignMove();
		}
		else if (shouldPlacePiece()) {
			move = placePiece();
		}
		else {
			move = movePiece();
		}	
		
		return new HantoAIResult(nextAI, move);
	}
	
	/** Determines if we have any valid moves left
	 * 
	 * @param game The current game
	 * @param myColor Our color
	 * @return True if we have valid moves, false otherwise
	 */
	private boolean hasValidMove() {
		return game.getHantoPlayer(myColor).hasLegalMove(board);
	}
	
	/** Determines if we will place a piece
	 * 
	 * @param game The hanto game
	 * @param myColor Our color
	 * @return True if we should place a piece, False if we should move a piece
	 */
	private boolean shouldPlacePiece() {
		HantoPlayer player = game.getHantoPlayer(myColor);
		//if we can't place a piece then we have to move
		if (!player.canPlacePiece(board)) {
			return false;
		}
		//if we can't move a piece then we have to play
		if (!player.canMovePiece(board)) {
			return true;
		}		
		//we can both place + move, 50% chance of either
		return Math.random() < 0.5;		
	}
	
	/** Places a piece
	 * 
	 * @param game
	 * @param myColor
	 * @return
	 */
	private HantoMoveRecord placePiece() {
		ComparableHantoCoordinate to = getBestPlacementCoordinate();
		HantoPieceType type = getRandomPiece();
		return new HantoMoveRecord(type, null, to);		
	}
	
	/** Get the coordinate closest to the opponents butterfly on which we can place a piece
	 * @param board the game board
	 * @param myColor my player color
	 * @return the coordinate
	 */
	private ComparableHantoCoordinate getBestPlacementCoordinate() {
		ComparableHantoCoordinate opponentButterflyLocation = game.getHantoPlayer(AbstractHantoGame.oppositeColor(myColor)).getButterflyLocation();
		List<ComparableHantoCoordinate> myPieceLocations = board.getPiecesForPlayer(myColor);
		ComparableHantoCoordinate shortestCoord = null;
		int shortestDistance = Integer.MAX_VALUE;
		PlacementValidator placementValidator = AdjacentPlacementValidator.getInstance();
		for(ComparableHantoCoordinate coord: myPieceLocations) {
			List<ComparableHantoCoordinate> unoccupiedCoords = coord.getAdjacentCoords();
			unoccupiedCoords.removeAll(board.getAdjacentLocationsWithPieces(coord));
			for(ComparableHantoCoordinate c: unoccupiedCoords) {
				if(c.getDistance(opponentButterflyLocation) < shortestDistance &&
						placementValidator.isPlacementValid(board, new BasicHantoPiece(myColor, HantoPieceType.CRAB), c)) {
					shortestCoord = c;
					shortestDistance = c.getDistance(new ComparableHantoCoordinate(0, 0));
				}
			}
		}
		return shortestCoord;
	}
	
	/** Selects a random piece from the players remaining pieces
	 * 
	 * @param game The game
	 * @param color Our color
	 * @return The piece selected
	 */
	private HantoPieceType getRandomPiece() {
		Map<HantoPieceType, Integer> piecesRemaining = game.getHantoPlayer(myColor).getPieceInventory();
	
		double num = Math.random() * countPiecesRemaining(piecesRemaining);
		
		int cur = 0;
		//weights each piece, and selects one randomly
		for (HantoPieceType type : piecesRemaining.keySet()) {
			cur += piecesRemaining.get(type);
			if (num <= cur) {
				return type;
			}
		}	
		
		return HantoPieceType.CRAB;
	}
	
	/** Counts the number of pieces reamining in the given inventory
	 * 
	 * @param inventory The inventory of pieces to count
	 * @return The number of pieces remaining
	 */
	private int countPiecesRemaining(Map<HantoPieceType, Integer> inventory) {
		int rem = 0;
		for (HantoPieceType type : inventory.keySet()) {
			rem += inventory.get(type);
		}
		return rem;
	}
	
	/** Moves a piece
	 * 
	 * @param game
	 * @param myColor
	 * @return
	 */
	private HantoMoveRecord movePiece() {
		if (!moveOffensive()) {
			//defensive moving
			//check if we can move butterfly, if so, we move it anywhere.
			ComparableHantoCoordinate myButterflyLocation = game.getHantoPlayer(myColor).getButterflyLocation();
			if(canMoveOwnButterfly()) {
				ComparableHantoCoordinate destination = getButterflyMoveCoord();
				return new HantoMoveRecord(HantoPieceType.BUTTERFLY, myButterflyLocation, destination);
			}

		}	
		//offensive moving
		//try and move a piece next to their butterfly
		//if cannot, then move a piece as close to butterfly as possible
		return movePieceClosestToButterfly();
	}
	
	/** Finds the piece that we can move closest to the opponents butterfly
	 * 
	 * @param game The current game
	 * @param myColor Our color
	 * @return The move record containing the piece + desitnation closest to opponenets butterfly
	 */
	private HantoMoveRecord movePieceClosestToButterfly() {
		ComparableHantoCoordinate opponentButterflyCoord = game.getHantoPlayer(AbstractHantoGame.oppositeColor(myColor)).getButterflyLocation();
		List<ComparableHantoCoordinate> ourPiecesCoords = board.getPiecesForPlayer(myColor);
		
		ComparableHantoCoordinate closestDestination = null;
		int closestDistance = Integer.MAX_VALUE;
		ComparableHantoCoordinate closestCurrentCoord = null;
		
		for (ComparableHantoCoordinate pieceCoord : ourPiecesCoords) {
			BasicHantoPiece piece = board.getPieceAt(pieceCoord);
			for (ComparableHantoCoordinate destCoord : piece.getValidMovementCoordinates(board, pieceCoord)) {
				int distToButterfly = destCoord.getDistance(opponentButterflyCoord);
				if (distToButterfly < closestDistance) {
					closestDistance = distToButterfly;
					closestDestination = destCoord;
					closestCurrentCoord = pieceCoord;
				}
			}
		}
		
		HantoPieceType type = board.getPieceAt(closestCurrentCoord).getType();
		return new HantoMoveRecord(type, closestCurrentCoord, closestDestination);
	}
	
	/** See if we can move our own butterfly
	 * @param game the game we are playing
	 * @param myColor our color
	 * @return true if we can legally move our butterfly, false otherwise
	 */
	private boolean canMoveOwnButterfly() {
		//System.out.println("CMOB: GAME: " + game);
		//System.out.println("CMOB: MyColor: " + myColor);
		//System.out.println("CMOB: HantoPlayer: " + game.getHantoPlayer(myColor));
		ComparableHantoCoordinate myButterflyLocation = game.getHantoPlayer(myColor).getButterflyLocation();
		//System.out.println("CMOB: BLoc: (" + myButterflyLocation.getX() + "," + myButterflyLocation.getY() );
		//System.out.println("CMOB: Board: " + board);
		//System.out.println("CMOB: ButterflyPiece: " + board.getPieceAt(myButterflyLocation));
		if(board.getPieceAt(myButterflyLocation).hasLegalMove(board, myButterflyLocation)) {
			return true;
		}
		return false;
	}
	
	/** Get the best move for our butterfly that will result with the least number of adjacent pieces
	 * @param game the game we are playing
	 * @param myColor our color
	 * @return the coordinate to move to
	 */
	private ComparableHantoCoordinate getButterflyMoveCoord() {
		ComparableHantoCoordinate myButterflyLocation = game.getHantoPlayer(myColor).getButterflyLocation();
		BasicHantoPiece myButterfly = board.getPieceAt(myButterflyLocation);
		ComparableHantoCoordinate bestCoord = null;
		int minAdjCount = Integer.MAX_VALUE;
		for(ComparableHantoCoordinate coord: myButterfly.getValidMovementCoordinates(board, myButterflyLocation)) {			
			if(board.getAdjacentPieces(coord).size() < minAdjCount) {
				bestCoord = coord;
				minAdjCount = board.getAdjacentPieces(coord).size();
			}

		}
		return bestCoord;
	}
	
	/** Determines if we should move offensivly or defensivly
	 * 
	 * @param board The board to move on
	 * @param myColor Our color
	 * @return True if we should move offensivly, false if defensivly
	 */
	private boolean moveOffensive() {
		ComparableHantoCoordinate myButterflyLocation = game.getHantoPlayer(myColor).getButterflyLocation();
		ComparableHantoCoordinate opponentButterflyLocation = game.getHantoPlayer(AbstractHantoGame.oppositeColor(myColor)).getButterflyLocation();
		
		int myButterflyAdjCount = board.getAdjacentLocationsWithPieces(myButterflyLocation).size();
		int opponentButterflyAdjCount = board.getAdjacentLocationsWithPieces(opponentButterflyLocation).size();	
	
		double chance = (opponentButterflyAdjCount) / (double) (myButterflyAdjCount + opponentButterflyAdjCount);		
		
		return Math.random() <= chance;
	}
	
	/** Creates the hanto move for resigning
	 * 
	 * @return The hanto move for resigning
	 */
	private HantoMoveRecord createResignMove() {
		return new HantoMoveRecord(null, null, null);
	}

}
