/**
 * 
 */
package hanto.student.mwcjlm.common.movevalidator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentmwcjlm.common.BasicHantoPiece;
import hanto.studentmwcjlm.common.ComparableHantoCoordinate;
import hanto.studentmwcjlm.common.HantoBoard;
import hanto.studentmwcjlm.common.movevalidator.FlyMoveValidator;
import hanto.studentmwcjlm.common.movevalidator.MoveValidator;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/** Tests No Movement Validator
 * @author Mitchell Caisse
 *
 */
public class FlyMoveValidatorTest {

	/** The movement validator to use */
	MoveValidator moveValidator;
	HantoBoard board;
	BasicHantoPiece testPiece;
	
	@Before
	public void init() throws HantoException {
		moveValidator = new FlyMoveValidator(2);
		board = new HantoBoard();
		testPiece = new BasicHantoPiece(HantoPlayerColor.BLUE, HantoPieceType.CRAB);
		
		board.addPieceToBoard(testPiece, new ComparableHantoCoordinate(0, 0));
	}
	
	@Test
	public void testFlyValidatorValidDestinations() {
		List<ComparableHantoCoordinate> validDestinations = new ArrayList<ComparableHantoCoordinate>();
		validDestinations.add(createCoord(0, 1));
		validDestinations.add(createCoord(1, 0));
		validDestinations.add(createCoord(1, -1));
		validDestinations.add(createCoord(0, -1));
		validDestinations.add(createCoord(-1, 0));
		validDestinations.add(createCoord(-1, 1));
		validDestinations.add(createCoord(0, 2));
		validDestinations.add(createCoord(1, 1));
		validDestinations.add(createCoord(2, 0));
		validDestinations.add(createCoord(2, -1));
		validDestinations.add(createCoord(2, -2));
		validDestinations.add(createCoord(1, -2));
		validDestinations.add(createCoord(0, -2));
		validDestinations.add(createCoord(-1, -1));
		validDestinations.add(createCoord(-2, 0));
		validDestinations.add(createCoord(-2, 1));
		validDestinations.add(createCoord(-2, 2));
		validDestinations.add(createCoord(-1, 2));
		
		List<ComparableHantoCoordinate> destinations = moveValidator.getValidMovementCoordinates(board, 
				testPiece, new ComparableHantoCoordinate(0, 0));
		assertTrue(destinations.containsAll(validDestinations));
		assertEquals(validDestinations.size(), destinations.size());
	}
	
	@Test
	public void testValidatorCanMove() {
		boolean canMove = moveValidator.hasLegalMove(board, 
				testPiece, new ComparableHantoCoordinate(0, 0));
		assertTrue(canMove);
	}
	
	@Test
	public void testValidatorCanMoveInfinitivly() {
		moveValidator = new FlyMoveValidator(0);
		boolean canMove = moveValidator.hasLegalMove(board, 
				testPiece, new ComparableHantoCoordinate(0, 0));
		assertTrue(canMove);
	}
	
	public ComparableHantoCoordinate createCoord(int x, int y) {
		return new ComparableHantoCoordinate(x,y);
	}
	
	
}
