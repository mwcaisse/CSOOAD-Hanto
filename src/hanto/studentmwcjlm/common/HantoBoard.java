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

public class HantoBoard {

	/** The mapping of pieces and coordinates */
	private Map<HantoCoordinateImpl, HantoPiece> pieces;
	
	/** Creates a mew Hanto Board and initializes the board
	 * 
	 */
	public HantoBoard() {
		pieces = new HashMap<HantoCoordinateImpl, HantoPiece>();
	}
	
	/** Returns the piece at the given location
	 * 
	 * @param at The coordinate to get the piece of
	 * @return The piece at the given coordinate
	 */
	public HantoPiece getPieceAt(HantoCoordinateImpl at) {
		return pieces.get(at);
	}
	
	/** Adds a piece to the board, and determines if is a valid spot
	 * 
	 * @param piece The piece to add
	 * @param to The position to add the piece to
	 * @throws HantoException if the spot is invalid  
	 */
	public void addPieceToBoard(HantoPiece piece, HantoCoordinateImpl to) throws HantoException {		
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
	public boolean isValidMove(HantoPiece piece, HantoCoordinateImpl to){
		if (pieces.isEmpty()) { //if the board is empty only valid move is 0,0
			return to.equals(new HantoCoordinateImpl(0, 0));	
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
	protected boolean hasAdjacentPiece(HantoCoordinateImpl coord) {		
	
		List<HantoCoordinateImpl> adjacentCoordinates = coord.getAdjacentCoords();		
		for (HantoCoordinateImpl other : adjacentCoordinates) {
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
	public List<HantoPiece> getAdjacentPieces(HantoCoordinateImpl coord) {
		List<HantoPiece> adjacentPieces = new ArrayList<HantoPiece>();
		List<HantoCoordinateImpl> adjacentCoordinates = coord.getAdjacentCoords();
		for (HantoCoordinateImpl other : adjacentCoordinates) {
			if (getPieceAt(other) != null) {
				adjacentPieces.add(getPieceAt(other));
			}
		}
		return adjacentPieces;
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
	
	/** Returns the list of coordinates for the peice with the specified type and color
	 * 
	 * @param type The type of piece
	 * @param color The color of the piece
	 * @return The list of coordinates that have a piece of this type + color
	 */
	public List<HantoCoordinateImpl> getPieceCoordinates(HantoPieceType type, HantoPlayerColor color) {
		List<HantoCoordinateImpl> coords = new ArrayList<HantoCoordinateImpl>();
		for(HantoCoordinateImpl location : pieces.keySet()) {
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
		
		List<HantoCoordinateImpl> sortedCoords = new ArrayList<HantoCoordinateImpl>(pieces.keySet());
		Collections.sort(sortedCoords);
		
		String board = "";
		for (HantoCoordinateImpl coord : sortedCoords) {
			HantoPiece piece = pieces.get(coord);
			board += String.format("[%d,%d] %s:%s\n", coord.getX(), coord.getY(), piece.getColor(), piece.getType().getPrintableName());
		}
		
		return board;
	}
	
}
