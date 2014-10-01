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

import java.util.HashMap;
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
		this.pieceInventory = new HashMap<HantoPieceType, Integer>();
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
			this.butterflyLocation = location;
		}
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