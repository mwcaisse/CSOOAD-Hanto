/**
 * 
 */
package hanto.studentmwcjlm.common.movevalidator;

import hanto.common.HantoPiece;
import hanto.studentmwcjlm.common.ComparableHantoCoordinate;
import hanto.studentmwcjlm.common.HantoBoard;

import java.util.ArrayList;
import java.util.List;

/** Move Validator for Contiguous Moves.
 * 
 * 
 * 
 * @author Mitchell Caisse
 *
 */
public class ContiguousMoveValidator implements MoveValidator {

	/** The single ton instance */
	private static ContiguousMoveValidator instance = new ContiguousMoveValidator();
	
	/** Gets the singleton instance
	 * 
	 * @return The singleton insance
	 */
	public static ContiguousMoveValidator getInstance() {
		return instance;
	}
	
	/** Private constructor */
	private ContiguousMoveValidator() {
		
	}
	
	/** Determines if the given move is Contiguous
	 * 
	 * 	@param board The board that the move is taking place on
	 *  @param piece The piece to move
	 *  @param from The starting coordinate
	 *  @param to The ending coordinate
	 */
	public boolean isMoveValid(HantoBoard board, HantoPiece piece, ComparableHantoCoordinate from, ComparableHantoCoordinate to) {
		HantoBoard testBoard = board.copy();
		testBoard.movePiece(from, to);
		
		ComparableHantoCoordinate current = to;
		List<ComparableHantoCoordinate> visited = new ArrayList<ComparableHantoCoordinate>();
		List<ComparableHantoCoordinate> toVisit =  new ArrayList<ComparableHantoCoordinate>();
		
		visited.add(current);
		toVisit.addAll(testBoard.getAdjacentLocationsWithPieces(current));
		
		while (toVisit.size() > 0) {
			current = toVisit.remove(0);			
			if (visited.contains(current)) {
				continue;
			}
			visited.add(current);			
			List<ComparableHantoCoordinate> adjCoords = testBoard.getAdjacentLocationsWithPieces(current);
			for (ComparableHantoCoordinate coord : adjCoords) {
				if (!visited.contains(coord)) {
					toVisit.add(coord);
				}
			}
		}		
		//if we visited every spot on the board is is contigous
		return testBoard.getPieceCount() == visited.size();
	}

	
	
}
