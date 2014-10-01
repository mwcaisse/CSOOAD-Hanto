/**
 * 
 */
package hanto.studentmwcjlm.common.placementvalidator;

import hanto.common.HantoPiece;
import hanto.studentmwcjlm.common.ComparableHantoCoordinate;
import hanto.studentmwcjlm.common.HantoBoard;

import java.util.List;

/**
 * @author Mitchell Caisse
 *
 */
public class AdjacentPlacementValidator extends BasicPlacementValidator{

	/** The singleton instance */
	private static AdjacentPlacementValidator instance;
	
	/** Gets teh singleton instance
	 * 
	 * @return The singleton instance
	 */
	public static AdjacentPlacementValidator getInstance() {
		if (instance == null) {
			instance = new AdjacentPlacementValidator();
		}
		return instance;
	}
	
	/** Protected constructor
	 * 
	 */
	protected AdjacentPlacementValidator() {
	}
	
	
	/** Checks to  see if the board is empty and move is to 0,0, The piece has an adjacent piece, and it is not being placed
	 * 	on an existing piece
	 */
	
	public boolean isPlacementValid(HantoBoard board, HantoPiece toPlace, ComparableHantoCoordinate to) {		
		if (!super.isPlacementValid(board, toPlace, to)) {
			return false;
		}
		
		if (board.getPieceCount() >= 2) {
			List<HantoPiece> adjacentPeices = board.getAdjacentPieces(to);
			//check that the color of all the adjacent peices are same color
			for (HantoPiece piece : adjacentPeices) {
				if (!piece.getColor().equals(toPlace.getColor())) {
					return false; //placed a peice next to a color not his own
				}
			}
		}
		 
		 return true;
		
	}
	
	

}
