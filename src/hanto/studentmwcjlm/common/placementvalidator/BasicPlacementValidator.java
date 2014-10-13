/**
 * 
 */
package hanto.studentmwcjlm.common.placementvalidator;

import hanto.common.HantoPiece;
import hanto.studentmwcjlm.common.ComparableHantoCoordinate;
import hanto.studentmwcjlm.common.HantoBoard;

/** The basic piece placement validator	
 * 
 * @author Mitchell Caisse
 *
 */
public class BasicPlacementValidator implements PlacementValidator{
	
	/** Checks to  see if the board is empty and move is to 0,0, The piece has an adjacent piece, and it is not being placed
	 * 	on an existing piece
	 */
	
	public boolean isPlacementValid(HantoBoard board, HantoPiece toPlace, ComparableHantoCoordinate to) {		
		//if the board is empty, the piece must be at 0,0
		if (board.getPieceCount() == 0) {
			return to.equals(new ComparableHantoCoordinate(0, 0));
		}
		if (board.getPieceAt(to) != null) {
			return false; // there is a piece on this location
		}
		//only allow placement if it has an adjacent piece
		return board.hasAdjacentPiece(to);
	}

}
