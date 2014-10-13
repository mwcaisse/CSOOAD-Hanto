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

import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentmwcjlm.common.movevalidator.BasicMoveValidator;
import hanto.studentmwcjlm.common.movevalidator.MoveValidator;
import hanto.studentmwcjlm.common.placementvalidator.BasicPlacementValidator;
import hanto.studentmwcjlm.common.placementvalidator.PlacementValidator;

/** The implementation of a Hanto Piece
 * 
 * @author Mitchell
 *
 */
public class BasicHantoPiece implements HantoPiece {

	/** The color of the player whoose piece this is */
	private HantoPlayerColor color;
	
	/** The type of piece */
	private HantoPieceType type;
	
	/** The validator to use to determine if placements are valid */
	private PlacementValidator placementValidator;
	
	/** The validator to use to determine if moves are valid */
	private MoveValidator moveValidator;
	
	/** Creates a new Hanto Piece Implementation with the given color and type, And the default move and placement validators
	 * 
	 * @param color The color of the piece
	 * @param type The type of the piece 
	 */
	
	public BasicHantoPiece(HantoPlayerColor color, HantoPieceType type) {
		this(color, type, new BasicPlacementValidator(), new BasicMoveValidator());
	}
	
	/** Creates a new Hanto Piece Implementation with the given color, type, placement validator, and move validator
	 * 
	 * @param color The color of the piece
	 * @param type The type of the piece
	 * @param placementValidator The placement validator for the piece
	 * @param moveValidator The movement validator for the piece
	 */
	public BasicHantoPiece(HantoPlayerColor color, HantoPieceType type, PlacementValidator placementValidator, MoveValidator moveValidator) {
		this.color = color;
		this.type = type;
		this.placementValidator = placementValidator;
		this.moveValidator = moveValidator;
	}
	
	public HantoPlayerColor getColor() {
		return color;
	}

	public HantoPieceType getType() {
		return type;
	}
	
	/** Determines if placing this at a specified location is valid
	 * 
	 * @param board The board to place the piece on
	 * @param to The position to place the piece
	 * @return True if the placement is valid, false otherwise
	 */
	public boolean isPlacementValid(HantoBoard board, ComparableHantoCoordinate to) {	
		return placementValidator.isPlacementValid(board, this, to);
	}
	
	/** Determines if moving this piece from a location to a location is valid
	 * 
	 * @param board The board the piece will be moving on
	 * @param from The starting coordinate of the move
	 * @param to The destination coordinate of the move
	 * @return True if the movement is valid, false otherwise
	 */
	public boolean isMoveValid(HantoBoard board, ComparableHantoCoordinate from, ComparableHantoCoordinate to) {
		return moveValidator.isMoveValid(board, this, from, to);
	}
	
	/** Determines if the piece has any legal moves on the given board
	 * 
	 * @param board The board to check for legal moves on
	 * @param position The position of the piece on the board
	 * @return True if the piece has any legal moves, false otherwise
	 */
	
	public boolean hasLegalMove(HantoBoard board, ComparableHantoCoordinate position) {
		return moveValidator.hasLegalMove(board, this, position);
	}

	
	
}
