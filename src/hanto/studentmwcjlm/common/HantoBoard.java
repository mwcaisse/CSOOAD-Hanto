package hanto.studentmwcjlm.common;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/** Board for keeping track of Hanto Pieces
 * 
 * @author Mitchell
 *
 */

public class HantoBoard {

	/** The mapping of pieces and coordinates */
	private HashMap<HantoCoordinate, HantoPiece> pieces;
	
	/** Creates a mew Hanto Board and initializes the board
	 * 
	 */
	public HantoBoard() {
		pieces = new HashMap<HantoCoordinate, HantoPiece>();
	}
	
	/** Returns the piece at the given location
	 * 
	 * @param at The coordinate to get the piece of
	 * @return The piece at the given coordinate
	 */
	public HantoPiece getPieceAt(HantoCoordinate at) {
		return pieces.get(at);
	}
	
	/** Adds a piece to the board, and determines if is a valid spot
	 * 
	 * @param piece The piece to add
	 * @param to The position to add the piece to
	 * @throws HantoException if the spot is invalid  
	 */
	public void addPieceToBoard(HantoPiece piece, HantoCoordinate to) throws HantoException {		
		if (isValidMove(piece, to)) {
			pieces.put(to, piece);
		}
		else {
			throw new HantoException("Invalid Move");
		}
	}
	
	/** Checks that moving the specified piece to the specified location is valid
	 * 
	 * @param piece The piece to add
	 * @param to The coordinate to move the peice to
	 * @return True if it is a valid move, false othter wise
	 */
	public boolean isValidMove(HantoPiece piece, HantoCoordinate to){
		if (pieces.isEmpty()) { //if the board is empty only valid move is 0,0
			return to.equals(new HantoCoordinateImpl(0,0));	
		}
		if (getPieceAt(to) != null) {
			return false; //if there is a piece on the spot it is invalid
		}
		return hasAdjacentPiece(to);
		
	}
	
	/** Determines if the given coordinate has an adjacent piece
	 * 
	 * @param coord The coordinate to test
	 * @return True if there is an adjacent piece, false otherwise
	 */
	protected boolean hasAdjacentPiece(HantoCoordinate coord) {		
	
		List<HantoCoordinate> adjacentCoordinates = getAdjacentPieces(coord);		
		for (HantoCoordinate other : adjacentCoordinates) {
			if (getPieceAt(other) != null) {
				return true;
			}
		}
		return false;
		
	}
	/** Returns the list of coordinates adjacent to the given coordinate
	 * 
	 * @param coord
	 * @return
	 */
	public static List<HantoCoordinate> getAdjacentPieces(HantoCoordinate coord) {
		int cx = coord.getX();
		int cy = coord.getY();
		
		List<HantoCoordinate> adjacentCoordinates = new ArrayList<HantoCoordinate>();
		adjacentCoordinates.add(new HantoCoordinateImpl(cx + 1, cy));
		adjacentCoordinates.add(new HantoCoordinateImpl(cx - 1, cy));
		
		adjacentCoordinates.add(new HantoCoordinateImpl(cx, cy + 1));
		adjacentCoordinates.add(new HantoCoordinateImpl(cx, cy - 1));
		
		adjacentCoordinates.add(new HantoCoordinateImpl(cx + 1, cy - 1));
		adjacentCoordinates.add(new HantoCoordinateImpl(cx - 1, cy + 1));
		
		return adjacentCoordinates;
	}
	
	public int getPieceCount(HantoPieceType type, HantoPlayerColor color) {
		int count = 0;
		for(HantoPiece piece : pieces.values()) {
			if(piece.getType() == type && piece.getColor() == color) {
				count++;
			}
		}
		return count;
	}
	
}
