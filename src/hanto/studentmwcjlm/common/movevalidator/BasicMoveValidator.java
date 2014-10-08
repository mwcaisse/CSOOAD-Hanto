/**
 * 
 */
package hanto.studentmwcjlm.common.movevalidator;

import java.util.ArrayList;
import java.util.List;

import hanto.common.HantoPiece;
import hanto.studentmwcjlm.common.ComparableHantoCoordinate;
import hanto.studentmwcjlm.common.HantoBoard;

/**
 * @author Mitchell Caisse
 *
 */
public class BasicMoveValidator implements MoveValidator {
	
	@Override
	public boolean isMoveValid(HantoBoard board, HantoPiece piece, ComparableHantoCoordinate from, ComparableHantoCoordinate to) {
		HantoPiece toMove = board.getPieceAt(from);
		//check that the piece on the board, is the same as the piece we were told to move
		if (toMove == null || toMove.getColor() != piece.getColor() || toMove.getType() != piece.getType()) {
			return false; //given piece differsnt from board piece
		}
		if (!board.isLocationEmpty(to)) {
			return false; //destination is not empty
		}
		return isMoveContiguous(board, piece, from, to);
	}
	
	public boolean isMoveContiguous(HantoBoard board, HantoPiece piece, ComparableHantoCoordinate from, ComparableHantoCoordinate to) {
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

	@Override
	public boolean hasLegalMove(HantoBoard board, HantoPiece piece,
			ComparableHantoCoordinate start) {
		// TODO Auto-generated method stub
		return false;
	}
}
