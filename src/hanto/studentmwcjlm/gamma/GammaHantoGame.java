/**
 * 
 */
package hanto.studentmwcjlm.gamma;

import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentmwcjlm.common.AbstractHantoGame;
import hanto.studentmwcjlm.common.HantoBoard;
import hanto.studentmwcjlm.common.HantoCoordinateImpl;
import hanto.studentmwcjlm.common.HantoPieceImpl;
import hanto.studentmwcjlm.common.HantoPlayerPieceCounter;
import hanto.studentmwcjlm.common.movevalidation.MoveValidator;
import hanto.studentmwcjlm.common.movevalidation.WalkMoveValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/** Gamma Hanto
 * @author Mitchell Caisse
 *
 */
public class GammaHantoGame extends AbstractHantoGame {

	/** The validator to use to ensure moves are valid */
	private MoveValidator moveValidator;
	
	/** Creates a new Gamma Hanto Game
	 * 
	 * @param firstPlayer The player that will move first
	 */
	public GammaHantoGame(HantoPlayerColor firstPlayer) {
		super(firstPlayer);
		init();
	}
	
	/** Initialize Gamma */
	private void init() {		
		board = new HantoBoard();
		moveValidator = new WalkMoveValidator();
		
		turnLimit = 20 * 2;
		
		piecesRemaining = new HashMap<HantoPlayerColor, HantoPlayerPieceCounter>();
		HashMap<HantoPieceType, Integer> initPieces = new HashMap<HantoPieceType, Integer>();
		initPieces.put(HantoPieceType.BUTTERFLY, 1);
		initPieces.put(HantoPieceType.SPARROW, 5);
		for(HantoPlayerColor color : HantoPlayerColor.values()) {
			piecesRemaining.put(color, new HantoPlayerPieceCounter(initPieces));
		}
	}

	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinateImpl from,
			HantoCoordinateImpl to) throws HantoException {
		
		//check if the game is over
		if (isGameOver()) {
			throw new HantoException("Game is over!");
		}
		
		//we are moving a peice
		if (from != null) {
			if (isValidMove(pieceType, from, to)) {
				board.movePiece(from, to);	
				finalizeMove();
			}
			else {
				throw new HantoException("Invalid Peice Movement");
			}

		}		
		//check if we can place the peice
		else if (canPlayPieceType(pieceType) && isValidPlacement(pieceType, to)) {		
			//add piece to the board
			board.addPieceToBoard(new HantoPieceImpl(currentPlayerColor, pieceType), to);		
			decrementPieceType(pieceType);
			finalizeMove();			
		}
		else {
			throw new HantoException("Invalid piece type");
		}
		
		return getMoveResult();
	}
	
	private void finalizeMove() {
		turnCount ++;
		updateHantoPlayerColor();
	}
	
	/** Checks if the given placement of the piece is valid
	 * 
	 * @param pieceType The type of piece that is being moved
	 * @param from The from position
	 * @param to The to position
	 * @return Whether it is valid or not
	 */
	private boolean isValidPlacement(HantoPieceType pieceType, HantoCoordinateImpl to) {
		//check that peice is not next to a peice of a different color, if not moving, and not first 2 places
		if (turnCount >= 2) {
			List<HantoPiece> adjacentPeices = board.getAdjacentPieces(to);
			//check that the color of all the adjacent peices are same color
			for (HantoPiece piece : adjacentPeices) {
				if (!piece.getColor().equals(currentPlayerColor)) {
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
	private boolean isValidMove(HantoPieceType pieceType, HantoCoordinateImpl from, HantoCoordinateImpl to) throws HantoException{
		//check if the piece is the right color + type + exists
		HantoPiece toMove = board.getPieceAt(from);
		if (toMove == null) {
			throw new HantoException("No piece at given spot to move from");
		}
		if (toMove.getColor() != currentPlayerColor) {
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
		if (!moveValidator.isMoveValid(board, from, to)) {
			throw new HantoException("Invalid movement");
		}
		
		return true;
	}
	
	/** Checks if the board resulting of the move is contigious
	 * 
	 * @param from The ocordinate to move piece from
	 * @param to Coordinate to moave the piece to
	 * @return True if contigious false otherwise
	 */
	private boolean isMoveContigous(HantoCoordinateImpl from, HantoCoordinateImpl to) {
		//clone the board
		HantoBoard testBoard = board.clone();
		testBoard.movePiece(from, to);
		
		HantoCoordinateImpl current = to;
		List<HantoCoordinateImpl> visited = new ArrayList<HantoCoordinateImpl>();
		List<HantoCoordinateImpl> toVisit =  new ArrayList<HantoCoordinateImpl>();
		
		visited.add(current);
		toVisit.addAll(testBoard.getAdjacentLocationsWithPieces(current));
		
		while (toVisit.size() > 0) {
			current = toVisit.remove(0);			
			if (visited.contains(current)) {
				continue;
			}
			visited.add(current);			
			List<HantoCoordinateImpl> adjCoords = testBoard.getAdjacentLocationsWithPieces(current);
			for (HantoCoordinateImpl coord : adjCoords) {
				if (!visited.contains(coord)) {
					toVisit.add(coord);
				}
			}
		}
		
		//if we visited every spot on the board is is contigous
		return testBoard.getPieceCount() == visited.size();
	}
	
}
