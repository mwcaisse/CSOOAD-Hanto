package hanto.student.mwcjlm.common.movevalidator;

import static org.junit.Assert.assertTrue;
import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentmwcjlm.common.BasicHantoPiece;
import hanto.studentmwcjlm.common.ComparableHantoCoordinate;
import hanto.studentmwcjlm.common.HantoBoard;
import hanto.studentmwcjlm.common.movevalidator.JumpMoveValidator;
import hanto.studentmwcjlm.common.movevalidator.MoveValidator;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class JumpMoveValidatorTest {
	MoveValidator moveValidator;
	HantoBoard board;
	
	@Before
	public void setup() {
		this.board = new HantoBoard();
		moveValidator = JumpMoveValidator.getInstance();
	}
	
	@Test
	public void testJumpMoveValidatorWithNoPieces() throws HantoException {
		BasicHantoPiece piece = new BasicHantoPiece(HantoPlayerColor.BLUE, HantoPieceType.HORSE);
		board.addPieceToBoard(piece, createCoord(0, 0));
		assertTrue(moveValidator.getValidMovementCoordinates(board, piece, createCoord(0, 0)).isEmpty());
	}
	
	@Test
	public void testJumpMoveValidatorWhenSurrounded() throws HantoException {
		BasicHantoPiece piece = new BasicHantoPiece(HantoPlayerColor.BLUE, HantoPieceType.HORSE);
		BasicHantoPiece rpiece = new BasicHantoPiece(HantoPlayerColor.RED, HantoPieceType.CRAB);
		board.addPieceToBoard(piece, createCoord(0, 0));
		board.addPieceToBoard(rpiece, createCoord(0, 1));
		board.addPieceToBoard(rpiece, createCoord(-1, 0));
		board.addPieceToBoard(rpiece, createCoord(1, -1));
		board.addPieceToBoard(rpiece, createCoord(1, 0));
		board.addPieceToBoard(rpiece, createCoord(0, -1));
		board.addPieceToBoard(rpiece, createCoord(-1, 1));
		List<ComparableHantoCoordinate> expectedList = new ArrayList<ComparableHantoCoordinate>();
		expectedList.add(createCoord(0, 2));
		expectedList.add(createCoord(0, -2));
		expectedList.add(createCoord(2, -2));
		expectedList.add(createCoord(-2, 2));
		expectedList.add(createCoord(2, 0));
		expectedList.add(createCoord(-2, 0));
		assertTrue(moveValidator.getValidMovementCoordinates(board, piece, createCoord(0, 0)).containsAll(expectedList));
	}
	
	@Test
	public void testJumpHasLegalMove() throws HantoException {
		BasicHantoPiece piece = new BasicHantoPiece(HantoPlayerColor.BLUE, HantoPieceType.HORSE);
		BasicHantoPiece rpiece = new BasicHantoPiece(HantoPlayerColor.RED, HantoPieceType.CRAB);
		board.addPieceToBoard(piece, createCoord(0, 0));
		board.addPieceToBoard(rpiece, createCoord(0, 1));
		assertTrue(moveValidator.hasLegalMove(board, piece, createCoord(0, 0)));
		board.removePeiceFromBoard(createCoord(0, 1));
		
		board.addPieceToBoard(rpiece, createCoord(-1, 0));
		assertTrue(moveValidator.hasLegalMove(board, piece, createCoord(0, 0)));
		board.removePeiceFromBoard(createCoord(-1, 0));
		
		board.addPieceToBoard(rpiece, createCoord(1, -1));
		assertTrue(moveValidator.hasLegalMove(board, piece, createCoord(0, 0)));
		board.removePeiceFromBoard(createCoord(1, -1));
		
		board.addPieceToBoard(rpiece, createCoord(1, 0));
		assertTrue(moveValidator.hasLegalMove(board, piece, createCoord(0, 0)));
		board.removePeiceFromBoard(createCoord(1, 0));
		
		board.addPieceToBoard(rpiece, createCoord(0, -1));
		assertTrue(moveValidator.hasLegalMove(board, piece, createCoord(0, 0)));
		board.removePeiceFromBoard(createCoord(0, -1));
		
		board.addPieceToBoard(rpiece, createCoord(-1, 1));
		assertTrue(moveValidator.hasLegalMove(board, piece, createCoord(0, 0)));
	}
	
	public ComparableHantoCoordinate createCoord(int x, int y) {
		return new ComparableHantoCoordinate(x,y);
	}
}
