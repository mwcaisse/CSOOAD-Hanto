package hanto.student.mwcjlm.alpha;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPieceType;
import hanto.common.MoveResult;
import hanto.studentmwcjlm.alpha.AlphaHantoGame;
import hanto.studentmwcjlm.common.HantoCoordinateImpl;

import org.junit.Test;



/** Test for the Alpha Hanto Game
 * 
 * @author Mitchell
 *
 */
public class AlphaHantoGameTest {

	/** Test the first for alpha
	 * 
	 * @throws HantoException if the move failed
	 */
	@Test
	public void bluesFirstMoveShouldBeOK() throws HantoException {
		HantoGame game = new AlphaHantoGame();		
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl()));		
		assertTrue(true);
	}
	
}
