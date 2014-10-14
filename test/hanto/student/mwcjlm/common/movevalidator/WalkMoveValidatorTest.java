/**
 * 
 */
package hanto.student.mwcjlm.common.movevalidator;

import static org.junit.Assert.*;
import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentmwcjlm.common.BasicHantoPiece;
import hanto.studentmwcjlm.common.ComparableHantoCoordinate;
import hanto.studentmwcjlm.common.HantoBoard;
import hanto.studentmwcjlm.common.movevalidator.MoveValidator;
import hanto.studentmwcjlm.common.movevalidator.WalkMoveValidator;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Mitchell Caisse
 *
 */
public class WalkMoveValidatorTest {
	
	MoveValidator moveValidator;
	HantoBoard board;
	
	@Before
	public void setup() {
		this.board = new HantoBoard();
		moveValidator = WalkMoveValidator.getInstance();
	}
	
	@Test
	public void testWalkMoveValidatorWithNoPieces() throws HantoException {
		BasicHantoPiece piece = new BasicHantoPiece(HantoPlayerColor.BLUE, HantoPieceType.CRAB);
		board.addPieceToBoard(piece, createCoord(0, 0));
		List<ComparableHantoCoordinate> expectedList = new ArrayList<ComparableHantoCoordinate>();
		expectedList.add(createCoord(1, 0));
		expectedList.add(createCoord(0, 1));
		expectedList.add(createCoord(-1, 1));
		expectedList.add(createCoord(-1, 0));
		expectedList.add(createCoord(0, -1));
		expectedList.add(createCoord(1, -1));
		assertTrue(moveValidator.getValidMovementCoordinates(board, piece, createCoord(0, 0)).containsAll(expectedList));
	}
	
	@Test
	public void testWalkMoveValidatorWithNoMoves() throws HantoException {
		BasicHantoPiece piece = new BasicHantoPiece(HantoPlayerColor.BLUE, HantoPieceType.CRAB);
		BasicHantoPiece rpiece = new BasicHantoPiece(HantoPlayerColor.RED, HantoPieceType.CRAB);
		board.addPieceToBoard(piece, createCoord(0, 0));
		board.addPieceToBoard(rpiece, createCoord(0, 1));
		board.addPieceToBoard(rpiece, createCoord(-1, 0));
		board.addPieceToBoard(rpiece, createCoord(1, -1));
		assertTrue(moveValidator.getValidMovementCoordinates(board, piece, createCoord(0, 0)).isEmpty());
	}
	
	public ComparableHantoCoordinate createCoord(int x, int y) {
		return new ComparableHantoCoordinate(x,y);
	}

}
