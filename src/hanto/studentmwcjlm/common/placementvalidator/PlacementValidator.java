/**
 * 
 */
package hanto.studentmwcjlm.common.placementvalidator;

import hanto.common.HantoPiece;
import hanto.studentmwcjlm.common.ComparableHantoCoordinate;
import hanto.studentmwcjlm.common.HantoBoard;

/** Validator for piece placement
 * 
 * @author Mitchell Caisse
 *
 */
public interface PlacementValidator {

	
	/** Determines if the given piece placement is valid or invalid
	 * 
	 * @param board The board to place the piece on
	 * @param toPlace The piece to palce
	 * @param to The destination coordinate
	 * @return True if the placement is valid, false otherwise
	 */
	
	boolean isPlacementValid(HantoBoard board, HantoPiece toPlace, ComparableHantoCoordinate to);
	
}
