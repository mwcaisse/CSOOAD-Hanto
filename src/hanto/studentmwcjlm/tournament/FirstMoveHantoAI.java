/**
 * 
 */
package hanto.studentmwcjlm.tournament;

import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentmwcjlm.common.ComparableHantoCoordinate;
import hanto.studentmwcjlm.epsilon.EpsilonHantoGame;
import hanto.tournament.HantoMoveRecord;

import java.util.List;

/**
 * @author Mitchell Caisse, James Megin
 *
 */
public class FirstMoveHantoAI implements HantoAI {
	
	/** Determines our next move 
	 * 
	 * @return Our next move
	 */
	public HantoMoveRecord getNextMove(boolean firstMove) {
		if (firstMove) {
			return new HantoMoveRecord(HantoPieceType.CRAB, null, new ComparableHantoCoordinate(0,0));
		}
		
		
		return null;
	}
	
	/** Makes a move on the given game
	 * 
	 * @param game The game to make the move on
	 * @param myColor The color that we are
	 * @return The result of the move
	 */
	public HantoAIResult getNextMove(EpsilonHantoGame game, HantoPlayerColor myColor) {
		HantoMoveRecord move;
		
		//check if we are moving first
		if (game.getBoard().getPieceCount() == 0) {
			//we are moving first, place crab on first spot
			move = new HantoMoveRecord(HantoPieceType.CRAB, null, new ComparableHantoCoordinate(0,0));
		}
		else {
			//we are moving second
			move = new HantoMoveRecord(HantoPieceType.CRAB, null,getRandomNeighborCoord(
					new ComparableHantoCoordinate(0,0)));			
		}			
		return new HantoAIResult(this, move);
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
