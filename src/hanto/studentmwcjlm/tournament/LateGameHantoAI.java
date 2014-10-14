package hanto.studentmwcjlm.tournament;

import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentmwcjlm.common.AbstractHantoGame;
import hanto.studentmwcjlm.common.BasicHantoPiece;
import hanto.studentmwcjlm.common.ComparableHantoCoordinate;
import hanto.studentmwcjlm.common.HantoBoard;
import hanto.studentmwcjlm.common.HantoPlayer;
import hanto.studentmwcjlm.common.placementvalidator.AdjacentPlacementValidator;
import hanto.studentmwcjlm.common.placementvalidator.PlacementValidator;
import hanto.studentmwcjlm.epsilon.EpsilonHantoGame;
import hanto.tournament.HantoMoveRecord;

import java.util.List;
import java.util.Map;

public class LateGameHantoAI implements HantoAI {

	/** Keep track of how many turns */
	private int turnCount;
	
	public LateGameHantoAI(int turnCount) {
		this.turnCount = turnCount;
	}
	
	@Override
	public HantoAIResult getNextMove(EpsilonHantoGame game, HantoPlayerColor myColor) {
		turnCount++;
		HantoMoveRecord move;
		HantoAI nextAI = this;
		
		//check if we need to resign
		if (!hasValidMove(game, myColor)) {
			//no valid moves, time to resign
			move = createResignMove();
		}
		else if (shouldPlacePiece(game, myColor)) {
			move = placePiece(game, myColor);
		}
		else {
			move = movePiece(game, myColor);
		}	
		
		
		// check if valid move
		// decide if we will be defensive or aggressive?
		// decide if we will place or move
		// place or move
		return new HantoAIResult(nextAI, move);
	}
	
	/** Determines if we have any valid moves left
	 * 
	 * @param game The current game
	 * @param myColor Our color
	 * @return True if we have valid moves, false otherwise
	 */
	private boolean hasValidMove(EpsilonHantoGame game, HantoPlayerColor myColor) {
		return game.getHantoPlayer(myColor).hasLegalMove(game.getBoard());
	}
	
	/** Determines if we will place a piece
	 * 
	 * @param game The hanto game
	 * @param myColor Our color
	 * @return True if we should place a piece, False if we should move a piece
	 */
	private boolean shouldPlacePiece(EpsilonHantoGame game, HantoPlayerColor myColor) {
		HantoPlayer player = game.getHantoPlayer(myColor);
		//if we can't place a piece then we have to move
		if (!player.canPlacePiece(game.getBoard())) {
			return false;
		}
		//if we can't move a piece then we have to play
		if (!player.canMovePiece(game.getBoard())) {
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
	private HantoMoveRecord placePiece(EpsilonHantoGame game, HantoPlayerColor myColor) {
		ComparableHantoCoordinate to = getBestPlacementCoordinate(game, myColor);
		HantoPieceType type = getRandomPiece(game, myColor);
		return new HantoMoveRecord(type, null, to);		
	}
	
	/** Get the coordinate closest to the opponents butterfly on which we can place a piece
	 * @param board the game board
	 * @param myColor my player color
	 * @return the coordinate
	 */
	private ComparableHantoCoordinate getBestPlacementCoordinate(EpsilonHantoGame game, HantoPlayerColor myColor) {
		ComparableHantoCoordinate opponentButterflyLocation = game.getHantoPlayer(AbstractHantoGame.oppositeColor(myColor)).getButterflyLocation();
		List<ComparableHantoCoordinate> myPieceLocations = game.getBoard().getPiecesForPlayer(myColor);
		ComparableHantoCoordinate shortestCoord = null;
		int shortestDistance = Integer.MAX_VALUE;
		PlacementValidator placementValidator = AdjacentPlacementValidator.getInstance();
		for(ComparableHantoCoordinate coord: myPieceLocations) {
			List<ComparableHantoCoordinate> unoccupiedCoords = coord.getAdjacentCoords();
			unoccupiedCoords.removeAll(game.getBoard().getAdjacentLocationsWithPieces(coord));
			for(ComparableHantoCoordinate c: unoccupiedCoords) {
				if(c.getDistance(opponentButterflyLocation) < shortestDistance &&
						placementValidator.isPlacementValid(game.getBoard(), new BasicHantoPiece(myColor, HantoPieceType.CRAB), c)) {
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
	private HantoPieceType getRandomPiece(EpsilonHantoGame game, HantoPlayerColor color) {
		Map<HantoPieceType, Integer> piecesRemaining = game.getHantoPlayer(color).getPieceInventory();
	
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
	private HantoMoveRecord movePiece(EpsilonHantoGame game, HantoPlayerColor myColor) {
		if (!moveOffensive(game, myColor)) {
			//defensive moving
			//check if we can move butterfly, if so, we move it anywhere.
			ComparableHantoCoordinate myButterflyLocation = game.getHantoPlayer(myColor).getButterflyLocation();
			if(canMoveOwnButterfly(game, myColor)) {
				ComparableHantoCoordinate destination = getButterflyMoveCoord(game, myColor);
				return new HantoMoveRecord(HantoPieceType.BUTTERFLY, myButterflyLocation, destination);
			}
			//move any adjacent pieces next to butterfly
			
			//if neither, offensive move
		}
	
		//offensive moving
		//try and move a piece next to their butterfly
		//if cannot, then move a piece as close to butterfly as possible
			
			
		
		
		return null;
	}
	
	/** See if we can move our own butterfly
	 * @param game the game we are playing
	 * @param myColor our color
	 * @return true if we can legally move our butterfly, false otherwise
	 */
	private boolean canMoveOwnButterfly(EpsilonHantoGame game, HantoPlayerColor myColor) {
		ComparableHantoCoordinate myButterflyLocation = game.getHantoPlayer(myColor).getButterflyLocation();
		if(game.getBoard().getPieceAt(myButterflyLocation).hasLegalMove(game.getBoard(), myButterflyLocation)) {
			return true;
		}
		return false;
	}
	
	/** Get the best move for our butterfly that will result with the least number of adjacent pieces
	 * @param game the game we are playing
	 * @param myColor our color
	 * @return the coordinate to move to
	 */
	private ComparableHantoCoordinate getButterflyMoveCoord(EpsilonHantoGame game, HantoPlayerColor myColor) {
		ComparableHantoCoordinate myButterflyLocation = game.getHantoPlayer(myColor).getButterflyLocation();
		List<ComparableHantoCoordinate> adjCoords = myButterflyLocation.getAdjacentCoords();
		BasicHantoPiece myButterfly = game.getBoard().getPieceAt(myButterflyLocation);
		ComparableHantoCoordinate bestCoord = null;
		int minAdjCount = Integer.MAX_VALUE;
		for(ComparableHantoCoordinate coord: adjCoords) {
			if(myButterfly.isMoveValid(game.getBoard(), myButterflyLocation, coord)) {
				if(game.getBoard().getAdjacentPieces(coord).size() < minAdjCount) {
					bestCoord = coord;
					minAdjCount = game.getBoard().getAdjacentPieces(coord).size();
				}
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
	private boolean moveOffensive(EpsilonHantoGame game, HantoPlayerColor myColor) {
		ComparableHantoCoordinate myButterflyLocation = game.getHantoPlayer(myColor).getButterflyLocation();
		ComparableHantoCoordinate opponentButterflyLocation = game.getHantoPlayer(AbstractHantoGame.oppositeColor(myColor)).getButterflyLocation();
		
		int myButterflyAdjCount = game.getBoard().getAdjacentLocationsWithPieces(myButterflyLocation).size();
		int opponentButterflyAdjCount = game.getBoard().getAdjacentLocationsWithPieces(opponentButterflyLocation).size();	
	
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
