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

import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentmwcjlm.common.placementvalidator.AdjacentPlacementValidator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Class to represent a HantoPlayer
 * 
 * @author Mitchell Caisse, James Megin
 *
 */
public class HantoPlayer {

	/** The color of this player */
	private HantoPlayerColor color;
	
	/** The location of the player's butterfly */
	private ComparableHantoCoordinate butterflyLocation;
	
	/** The pieces that the player has in thier inventory */
	private Map<HantoPieceType, Integer> pieceInventory;
	
	/** Creates a new Hanto Player with the given color and starting inventory
	 * 
	 * @param color The color of this player
	 * @param startingInvetory The player's starting inventory
	 */
	public HantoPlayer(HantoPlayerColor color, Map<HantoPieceType, Integer> startingInvetory) {
		this.color = color;
		pieceInventory = new HashMap<HantoPieceType, Integer>();
		initializeInventory(startingInvetory);
	}
	
	/** Initializes the player's inventory with the given starting pieces
	 * 
	 * @param startingInvetory The starting inventory
	 */
	private void initializeInventory(Map<HantoPieceType, Integer> startingInvetory) {
		for(HantoPieceType type : HantoPieceType.values()) {
			pieceInventory.put(type, 0);
		}
		pieceInventory.putAll(startingInvetory);
	}
	
	/** Notifies the player that the piece has been placed on the board
	 * 
	 * @param pieceType The type of piece that has been placed
	 * @param location The location that the piece was placed
	 */
	
	public void placePiece(HantoPieceType pieceType, ComparableHantoCoordinate location) {
		pieceInventory.put(pieceType, pieceInventory.get(pieceType) - 1);
		if (pieceType == HantoPieceType.BUTTERFLY) {
			butterflyLocation = location;
		}
	}
	
	/** Checks to see if the player has a legal move
	 * @param board the game board
	 * @return True if there is a legal move for the player, false otherwise
	 */
	public boolean hasLegalMove(HantoBoard board) {
		// check if have pieces, if so can they be places
		// check if pieces on board have legal moves
		return canPlacePiece(board) || canMovePiece(board);
	}
	
	public boolean canPlacePiece(HantoBoard board) {
		int totalPieceCount = 0;
		for(HantoPieceType type: pieceInventory.keySet()) {
			totalPieceCount += pieceInventory.get(type);
		}
		if(totalPieceCount <= 0) {
			return false;
		}
		AdjacentPlacementValidator apv = AdjacentPlacementValidator.getInstance();
		BasicHantoPiece dummyPiece = new BasicHantoPiece(color, HantoPieceType.CRAB);
		List<ComparableHantoCoordinate> pieceCoords = board.getPiecesForPlayer(color);
		if (pieceCoords.size() == 0) {
			return true; //player has not yet placed a piece, no way he cannot place one
		}
		for(ComparableHantoCoordinate coord: pieceCoords) {
			for(ComparableHantoCoordinate c: coord.getAdjacentCoords()) {
				if(apv.isPlacementValid(board, dummyPiece, c)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean canMovePiece(HantoBoard board) {
		List<ComparableHantoCoordinate> pieceCoords = board.getPiecesForPlayer(color);
		for(ComparableHantoCoordinate coord: pieceCoords) {
			if(board.getPieceAt(coord).hasLegalMove(board, coord)) {
				return true;
			}
		}
		return false;
	}
	
	/** Determines if the player has any pieces of the specified type remaining
	 * 
	 * @param pieceType The type of piece to check
	 * @return True if the player has atleast one of the given piece, false otherwise
	 */
	public boolean hasPiece(HantoPieceType pieceType) {
		return pieceInventory.get(pieceType) > 0;
	}
	
	/** Determines if the player has placed their butterfly yet
	 * 
	 * @return True if the player's butterfly has been placed, false otherwise
	 */
	public boolean hasPlacedButterfly() {
		return butterflyLocation != null; //if butterfly is not null, then it has been placed
	}
	
	/** Gets the color of this hanto player
	 * 
	 * @return The color of this hanto player
	 */
	public HantoPlayerColor getColor() {
		return color;
	}
	
	/** Gets the location of this players butterfly
	 * 
	 * @return The location of this players butterfly
	 */
	public ComparableHantoCoordinate getButterflyLocation() {
		return butterflyLocation;
	}
}
