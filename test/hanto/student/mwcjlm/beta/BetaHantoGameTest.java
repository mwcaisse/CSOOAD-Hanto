package hanto.student.mwcjlm.beta;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentmwcjlm.beta.BetaHantoGame;
import hanto.studentmwcjlm.common.HantoCoordinateImpl;
import hanto.studentmwcjlm.common.HantoGameFactory;

import org.junit.Before;
import org.junit.Test;


/*
 * TODO:
 * -Ending conditions
 * 		Draw
 * 		Blue wins
 * 		Red wins
 */

public class BetaHantoGameTest {
	/** The hanto game to use for testing */
	private HantoGame game;
	
	@Before
	public void setup() {
		game = HantoGameFactory.getInstance().makeHantoGame(HantoGameID.BETA_HANTO);
	}
	/** Test the first for alpha
	 * 
	 * @throws HantoException if the move failed
	 */
	@Test
	public void firstMoveShouldBeOK() throws HantoException {
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(0,0)));		
	}
	
	@Test(expected = HantoException.class)
	public void testTwoPiecesSameCoordinateShouldThrowException() throws HantoException {
		try {
			assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(0,0)));	
		}		
		catch (HantoException e) {
			fail();
		}
		game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(0,0));	
	}
	
	@Test(expected = HantoException.class)
	public void testInvalidStartPositionShouldThrowException() throws HantoException {
		game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(2,0));
	}
	
	@Test(expected = HantoException.class)
	public void testPlaceNonAdjacentPiece() throws HantoException {
		try {
			assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(0,0)));	
		}		
		catch (HantoException e) {
			fail();
		}
		game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(2,2));
	}
	
	@Test(expected = HantoException.class)
	public void testPiecesCannotMove() throws HantoException {
		game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(0,0));
		game.makeMove(HantoPieceType.BUTTERFLY, new HantoCoordinateImpl(0,0), new HantoCoordinateImpl(1,0));
	}
	
	@Test
	public void testValidPieceTypes() throws HantoException {
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(0,0)));
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(1,0)));
	}
	
	@Test(expected = HantoException.class)
	public void testOnlyButterflySparrowValid() throws HantoException {
		game.makeMove(HantoPieceType.CRAB, null, new HantoCoordinateImpl(0,0));
	}
	
	@Test(expected = HantoException.class)
	public void testOnlyOneButterflyPerPlayer() throws HantoException {
		try {
			game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(0,0));
			game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(0,1));
		}
		catch(HantoException e) {
			fail();
		}
		game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(1,0));
	}
	
	@Test(expected = HantoException.class)
	public void testOnlyFiveSparrowPerPlayer() throws HantoException {
		try {
			game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(0,0));
			game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(0,1));
			game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,2));
			game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,3));
			game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,4));
			game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,5));
			game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,6));
			game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,7));
			game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,8));
			game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,9));
			game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,10));
			game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,11));
		}
		catch(HantoException e) {
			fail();
		}
		game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,12));
	}
	
	@Test(expected = HantoException.class)
	public void testButterflyByFourthTurn() throws HantoException {
		game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,0));
		game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(0,1));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,2));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,3));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,4));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,5));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,6));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,7));
	}
	
	@Test
	public void testFirstPieceIsBlue() throws HantoException {
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(0,0)));
		assertEquals(HantoPlayerColor.BLUE, game.getPieceAt(new HantoCoordinateImpl(0,0)).getColor());		
	}
	
	@Test
	public void testFirstPieceIsRed() throws HantoException {
		HantoGame redGame = HantoGameFactory.getInstance().makeHantoGame(HantoGameID.BETA_HANTO, HantoPlayerColor.RED);
		assertEquals(MoveResult.OK, redGame.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(0,0)));
		assertEquals(HantoPlayerColor.RED, redGame.getPieceAt(new HantoCoordinateImpl(0,0)).getColor());
	}
	
	@Test
	public void testGameEndsInDraw() throws HantoException {
		game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(0,0));
		game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(0,1));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,2));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,3));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,4));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,5));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,6));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,7));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,8));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,9));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,10));
		assertEquals(MoveResult.DRAW, game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,11)));
	}
	
	@Test
	public void testGameRedWon() throws HantoException {
		game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(0,0));
		game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(0,-1));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(1,-1));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(1,0));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,1));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(-1,1));
		assertEquals(MoveResult.RED_WINS, game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(-1,0)));
	}
	
	@Test
	public void testGameBlueWon() throws HantoException {
		HantoGame game = HantoGameFactory.getInstance().makeHantoGame(HantoGameID.BETA_HANTO, HantoPlayerColor.RED);
		game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(0,0));
		game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(0,-1));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(1,-1));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(1,0));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,1));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(-1,1));
		assertEquals(MoveResult.BLUE_WINS, game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(-1,0)));
	}

}
