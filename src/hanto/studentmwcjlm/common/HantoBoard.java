/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentmwcjlm.common;

import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/** Board for keeping track of Hanto Pieces
 * 
 * @author Mitchell
 *
 */

public class HantoBoard implements Cloneable {

	/** The mapping of pieces and coordinates */
	private Map<ComparableHantoCoordinate, HantoPiece> pieces;
	
	/** Creates a mew Hanto Board and initializes the board
	 * 
	 */
	public HantoBoard() {
		pieces = new HashMap<ComparableHantoCoordinate, HantoPiece>();
	}
	
	/** Creates a new Hanto board with the given pieces 
	 * 
	 * @param pieces The initial pieces on the board
	 */
	public HantoBoard(Map<ComparableHantoCoordinate, HantoPiece> pieces) {
		this.pieces = pieces;
	}
	
	/** Returns the piece at the given location
	 * 
	 * @param at The coordinate to get the piece of
	 * @return The piece at the given coordinate
	 */
	public HantoPiece getPieceAt(ComparableHantoCoordinate at) {
		return pieces.get(at);
	}
	
	/** Determines if the piece at the given location is empty
	 * 
	 * @param at The location to check
	 * @return True if the spot is empty, false otherwise
	 */
	public boolean isLocationEmpty(ComparableHantoCoordinate at) {
		return getPieceAt(at) == null;
	}
	
	/** Adds a piece to the board, and determines if is a valid spot
	 * 
	 * @param piece The piece to add
	 * @param to The position to add the piece to
	 * @throws HantoException if the spot is invalid  
	 */
	public void addPieceToBoard(HantoPiece piece, ComparableHantoCoordinate to) throws HantoException {		
		if (isValidMove(piece, to)) {
			pieces.put(to, piece);
		}
		else {
			throw new HantoException("Invalid Move");
		}
	}
	
	/** Removes the peice at the given location from the board, and returns a copy of it
	 * 
	 * @param at The location of the piece to remove
	 * @return A copy of the piece removed
	 */
	public HantoPiece removePeiceFromBoard(ComparableHantoCoordinate at) {
		return pieces.remove(at);
	}
	
	/** Moves the peice from the specified location to the given location
	 * 
	 * @param from The location to move the piece from
	 * @param to The location to move the peice to
	 */
	public void movePiece(ComparableHantoCoordinate from, ComparableHantoCoordinate to) {
		pieces.put(to, removePeiceFromBoard(from));
	}
	
	/** Checks that moving the specified piece to the specified location is valid
	 * 
	 * @param piece The piece to add
	 * @param to The coordinate to move the peice to
	 * @return True if it is a valid move, false othter wise
	 */
	public boolean isValidMove(HantoPiece piece, ComparableHantoCoordinate to){
		if (pieces.isEmpty()) { //if the board is empty only valid move is 0,0
			return to.equals(new ComparableHantoCoordinate(0, 0));	
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
	protected boolean hasAdjacentPiece(ComparableHantoCoordinate coord) {		
	
		List<ComparableHantoCoordinate> adjacentCoordinates = coord.getAdjacentCoords();		
		for (ComparableHantoCoordinate other : adjacentCoordinates) {
			if (getPieceAt(other) != null) {
				return true;
			}
		}
		return false;
		
	}
	
	/** Get a list of pieces adjacent to a coordinate
	 * @param coord the coordinate of the desired piece
	 * @return the pieces that are adjacent to the coordinate
	 */
	public List<HantoPiece> getAdjacentPieces(ComparableHantoCoordinate coord) {
		List<HantoPiece> adjacentPieces = new ArrayList<HantoPiece>();
		List<ComparableHantoCoordinate> adjacentCoordinates = coord.getAdjacentCoords();
		for (ComparableHantoCoordinate other : adjacentCoordinates) {
			if (getPieceAt(other) != null) {
				adjacentPieces.add(getPieceAt(other));
			}
		}
		return adjacentPieces;
	}
	
	/** Gets a list of coordinates adjacent to the given coordinate with pieces
	 * 
	 * @param coord The coord of the spot to check
	 * @return The list of adj coords with pieces
	 */
	public List<ComparableHantoCoordinate> getAdjacentLocationsWithPieces(ComparableHantoCoordinate coord) {
		List<ComparableHantoCoordinate> adjacentCoords = new ArrayList<ComparableHantoCoordinate>();
		List<ComparableHantoCoordinate> adjacentCoordinates = coord.getAdjacentCoords();
		for (ComparableHantoCoordinate other : adjacentCoordinates) {
			if (getPieceAt(other) != null) {
				adjacentCoords.add(other);
			}
		}
		return adjacentCoords;
	}
	
	/** Get the number of a kind and color of piece on the board
	 * @param type the type of piece
	 * @param color the color of piece
	 * @return the number of pieces
	 */
	public int getPieceCount(HantoPieceType type, HantoPlayerColor color) {
		int count = 0;
		for(HantoPiece piece : pieces.values()) {
			if(piece.getType() == type && piece.getColor() == color) {
				count++;
			}
		}
		return count;
	}
	
	/** Returns the number of pieces currently on the board
	 * 
	 * @return The number of pieces on the board
	 */
	public int getPieceCount() {
		return pieces.size();
	}
	
	/** Returns the list of coordinates for the peice with the specified type and color
	 * 
	 * @param type The type of piece
	 * @param color The color of the piece
	 * @return The list of coordinates that have a piece of this type + color
	 */
	public List<ComparableHantoCoordinate> getPieceCoordinates(HantoPieceType type, HantoPlayerColor color) {
		List<ComparableHantoCoordinate> coords = new ArrayList<ComparableHantoCoordinate>();
		for(ComparableHantoCoordinate location : pieces.keySet()) {
			HantoPiece piece = pieces.get(location);
			if(piece.getType() == type && piece.getColor() == color) {
				coords.add(location);
			}
		}
		return coords;
	}
	
	/** Returns a string containing a printable copy of the board
	 * 
	 * @return The printabl copy of the board
	 */
	public String getPrintableBoard() {
		
		List<ComparableHantoCoordinate> sortedCoords = new ArrayList<ComparableHantoCoordinate>(pieces.keySet());
		Collections.sort(sortedCoords);
		
		String board = "";
		for (ComparableHantoCoordinate coord : sortedCoords) {
			HantoPiece piece = pieces.get(coord);
			board += String.format("[%d,%d] %s:%s\n", coord.getX(), coord.getY(), piece.getColor(), piece.getType().getPrintableName());
		}
		
		return board;
	}
	
	/** Clones this board
	 * 
	 */
	public HantoBoard clone() {
		HantoBoard board = new HantoBoard();
		board.pieces = new HashMap<ComparableHantoCoordinate, HantoPiece>(pieces);
		return board;
	}
	
}
