package hanto.studentmwcjlm.tournament;

import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentmwcjlm.common.BasicHantoPiece;
import hanto.studentmwcjlm.common.ComparableHantoCoordinate;
import hanto.studentmwcjlm.common.HantoBoard;
import hanto.studentmwcjlm.common.placementvalidator.AdjacentPlacementValidator;
import hanto.studentmwcjlm.common.placementvalidator.PlacementValidator;
import hanto.studentmwcjlm.epsilon.EpsilonHantoGame;
import hanto.tournament.HantoMoveRecord;

import java.util.List;

public class EarlyGameHantoAI implements HantoAI {

	/** Keep track of how many turns */
	private int turnCount;
	
	public EarlyGameHantoAI(int turnCount) {
		this.turnCount = turnCount;
	}
	
	@Override
	public HantoAIResult getNextMove(EpsilonHantoGame game, HantoPlayerColor myColor) {
		HantoPieceType piece = HantoPieceType.CRAB;
		HantoAI nextAI = this;
		turnCount++;
		if(turnCount == 4) {
			piece = HantoPieceType.BUTTERFLY;
			nextAI = new LateGameHantoAI(turnCount);
		}
		HantoMoveRecord nextMove = new HantoMoveRecord(piece, null, getBestPlacementCoordinate(game.getBoard(), myColor));
		return new HantoAIResult(nextAI, nextMove);
	}
	
	/** Get the furest coordinate from (0, 0) where we can place a piece
	 * @param board the game board
	 * @param myColor my player color
	 * @return the coordinate
	 */
	private ComparableHantoCoordinate getBestPlacementCoordinate(HantoBoard board, HantoPlayerColor myColor) {
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
