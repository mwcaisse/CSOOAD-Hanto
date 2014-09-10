package hanto.student.mwcjlm.alpha;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPieceType;
import hanto.common.MoveResult;
import hanto.studentmwcjlm.alpha.AlphaHantoGame;
import hanto.studentmwcjlm.common.HantoCoordinateImpl;

import org.junit.Before;
import org.junit.Test;



/** Test for the Alpha Hanto Game
 * 
 * @author Mitchell
 *
 */
public class AlphaHantoGameTest {

	/** The hanto game to use for testing */
	private HantoGame game;
	
	@Before
	public void setup() {
		game = new AlphaHantoGame();
	}
	/** Test the first for alpha
	 * 
	 * @throws HantoException if the move failed
	 */
	@Test
	public void bluesFirstMoveShouldBeOK() throws HantoException {
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(0,0)));		
	}
	
	/** Second move should be draw
	 * 
	 * @throws HantoException
	 */
	@Test
	public void secondMoveShouldBeDraw() throws HantoException {
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(0,0)));	
		assertEquals(MoveResult.DRAW, game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(1,0)));	
		
	}
	
}
