/**
 * 
 */
package hanto.student.mwcjlm.common.movevalidator;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentmwcjlm.common.BasicHantoPiece;
import hanto.studentmwcjlm.common.ComparableHantoCoordinate;
import hanto.studentmwcjlm.common.HantoBoard;
import hanto.studentmwcjlm.common.movevalidator.MoveValidator;
import hanto.studentmwcjlm.common.movevalidator.NoMovementMoveValidator;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

/** Tests No Movement Validator
 * @author Mitchell Caisse
 *
 */
public class NoMovementValidatorTest {

	/** The movement validator to use */
	MoveValidator moveValidator;
	HantoBoard board;
	BasicHantoPiece testPiece;
	
	@Before
	public void init() throws HantoException {
		moveValidator = NoMovementMoveValidator.getInstance();
		board = new HantoBoard();
		testPiece = new BasicHantoPiece(HantoPlayerColor.BLUE, HantoPieceType.CRAB);
		
		board.addPieceToBoard(testPiece, new ComparableHantoCoordinate(0, 0));
	}
	
	@Test
	public void testValidatorHasNoValidDestinations() {
		List<ComparableHantoCoordinate> destinations = moveValidator.getValidMovementCoordinates(board, 
				testPiece, new ComparableHantoCoordinate(0, 0));
		assertTrue(destinations.isEmpty());
	}
	
	@Test
	public void testValidatorCannotMove() {
		boolean canMove = moveValidator.hasLegalMove(board, 
				testPiece, new ComparableHantoCoordinate(0, 0));
		assertFalse(canMove);
	}
	
}
