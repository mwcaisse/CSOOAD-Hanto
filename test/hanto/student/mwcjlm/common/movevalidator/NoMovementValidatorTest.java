/**
 * 
 */
package hanto.student.mwcjlm.common.movevalidator;

import hanto.studentmwcjlm.common.movevalidator.MoveValidator;
import hanto.studentmwcjlm.common.movevalidator.NoMovementMoveValidator;

import org.junit.Before;

/** Tests No Movement Validator
 * @author Mitchell Caisse
 *
 */
public class NoMovementValidatorTest {

	/** The movement validator to use */
	MoveValidator moveValidator;
	
	@Before
	public void init() {
		moveValidator = NoMovementMoveValidator.getInstance();
	}
	
	public void testValidatorHasNoValidDestinations() {
		List<ComparableHantoCoordinate> destinations = 
	}
	
}
