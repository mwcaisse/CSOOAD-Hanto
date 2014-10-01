package hanto.studentmwcjlm.delta;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentmwcjlm.common.AbstractHantoGame;
import hanto.studentmwcjlm.common.ComparableHantoCoordinate;
import hanto.studentmwcjlm.common.HantoBoard;
import hanto.studentmwcjlm.common.HantoPieceFactory;
import hanto.studentmwcjlm.common.HantoPieceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DeltaHantoGame extends AbstractHantoGame {

	private HantoPieceFactory pieceFactory;
	
	/** Creates a new Delta Hanto Game
	 * 
	 * @param firstPlayer The player that will move first
	 */
	public DeltaHantoGame(HantoPlayerColor firstPlayer) {
		super(firstPlayer);
		init();
	}
	
	/** Initialize Delta */
	private void init() {			
		turnLimit = 20 * 2;		
		pieceFactory = new DeltaHantoPieceFactory();
	}
	
	/** Defines the starting inventory for this game
	 * 
	 */	
	protected HashMap<HantoPieceType, Integer> getStartingInventory() {
		HashMap<HantoPieceType, Integer> startingPieces = new HashMap<HantoPieceType, Integer>();
		startingPieces.put(HantoPieceType.BUTTERFLY, 1);
		startingPieces.put(HantoPieceType.SPARROW, 4);
		startingPieces.put(HantoPieceType.CRAB, 4);
		return startingPieces;
	}

	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from,
			HantoCoordinate to) throws HantoException {
		//check if the player resigns
		if(pieceType == null && from == null && to == null) {
			resigned = true;
			return getMoveResult();
		}
		ComparableHantoCoordinate toCoord = convertHantoCoordinate(to);
		ComparableHantoCoordinate fromCoord = null;
		if (from != null) {
			 fromCoord = convertHantoCoordinate(from);
		}
		return makeMove(pieceType, fromCoord, toCoord);
		
	}
	
	/** Moves a piece of the specified type, from the given coordinate, to the given coordinate
	 * 
	 * @param pieceType The type of piece to move
	 * @param from The position to move the piece from
	 * @param to The position to move the piece to
	 * @throws HantoException if the move is invalid
	 */
	protected void movePiece(HantoPieceType pieceType, ComparableHantoCoordinate from,
			ComparableHantoCoordinate to) throws HantoException {	
		
		if (isValidMove(pieceType, from, to)) {
			board.movePiece(from, to);	
		}
		else {
			throw new HantoException("Invalid peice movement");
		}
	}
	
	/** Places a piece of the given type at the given location
	 * 
	 * @param pieceType The type of piece to player
	 * @param to The location to place the piece
	 * @throws HantoException If the piece placement is invalid
	 */
	
	protected void placePiece(HantoPieceType pieceType	, ComparableHantoCoordinate to) throws HantoException {
		//check if this placement is valid
		if (canPlayPieceType(pieceType) && isValidPlacement(pieceType, to)) {		
			//add piece to the board
			board.addPieceToBoard(new HantoPieceImpl(currentPlayer.getColor(), pieceType), to);		
			currentPlayer.placePiece(pieceType, to);	
		}
		else {
			throw new HantoException("Invalid piece placement");
		}
	}
	
	/** Checks if the given placement of the piece is valid
	 * 
	 * @param pieceType The type of piece that is being moved
	 * @param from The from position
	 * @param to The to position
	 * @return Whether it is valid or not
	 */
	private boolean isValidPlacement(HantoPieceType pieceType, ComparableHantoCoordinate to) {
		//check that peice is not next to a peice of a different color, if not moving, and not first 2 places
		if (turnCount >= 2) {
			List<HantoPiece> adjacentPeices = board.getAdjacentPieces(to);
			//check that the color of all the adjacent peices are same color
			for (HantoPiece piece : adjacentPeices) {
				if (!piece.getColor().equals(currentPlayer.getColor())) {
					return false; //placed a peice next to a color not his own
				}
			}
		}
		
		return  true;
	}
	
	/** Determines if moving the given piece from the given coord to the given coord is valid
	 * 
	 * @param pieceType THe type of piece to move
	 * @param from The position to move it from
	 * @param to The position to move it to
	 * @return True if the move is valid
	 */
	private boolean isValidMove(HantoPieceType pieceType, ComparableHantoCoordinate from, ComparableHantoCoordinate to) throws HantoException{
		//check if the piece is the right color + type + exists
		HantoPiece toMove = board.getPieceAt(from);
		if (toMove == null) {
			throw new HantoException("No piece at given spot to move from");
		}
		if (toMove.getColor() != currentPlayer.getColor()) {
			throw new HantoException("Can only move pieces of your own color");
		}		
		if (toMove.getType() != pieceType) {
			throw new HantoException("Piece tyes do not match");
		}		
		//check that the board space is empty
		if (!board.isLocationEmpty(to)) {
			throw new HantoException("Destination is occupied");
		}		
		//check that the new board is contiguous
		if (!isMoveContigous(from, to)) {
			throw new HantoException("Resulting board is not contigious");
		}
		if(!pieceFactory.makePiece(currentPlayer.getColor(), pieceType).validateMove(board, from, to)) {
			throw new HantoException("Invalid movement");
		}
		
		return true;
	}
	
	/** Checks if the board resulting of the move is contiguous
	 * 
	 * @param from The coordinate to move piece from
	 * @param to Coordinate to move the piece to
	 * @return True if contiguous false otherwise
	 */
	private boolean isMoveContigous(ComparableHantoCoordinate from, ComparableHantoCoordinate to) {
		//clone the board
		HantoBoard testBoard = board.clone();
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

}
