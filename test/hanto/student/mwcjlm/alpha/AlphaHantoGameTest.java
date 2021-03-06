package hanto.student.mwcjlm.alpha;
import static hanto.common.HantoPieceType.BUTTERFLY;
import static hanto.common.HantoPlayerColor.BLUE;
import static hanto.common.HantoPlayerColor.RED;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentmwcjlm.common.ComparableHantoCoordinate;
import hanto.studentmwcjlm.common.HantoGameFactory;

import org.junit.Before;
import org.junit.Test;

/*
 *  Tests to create
 *  
 *  X-test that the correct color pieces are being placed
 *  X-test that only butterflies can be placedX
 *  X-test that blue is going first
 *  X-test that red goes seccond
 *  X-test pieces are added to the boardX
 *  
 *  X-test that there can be no 3rd move
 * 
 * 
 */


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
		game = HantoGameFactory.getInstance().makeHantoGame(HantoGameID.ALPHA_HANTO);
	}
	/** Test the first for alpha
	 * 
	 * @throws HantoException if the move failed
	 */
	@Test
	public void bluesFirstMoveShouldBeOK() throws HantoException {
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.BUTTERFLY, null, new ComparableHantoCoordinate(0,0)));		
	}
	
	/** Second move should be draw
	 * 
	 * @throws HantoException
	 */
	@Test
	public void secondMoveShouldBeDraw() throws HantoException {
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.BUTTERFLY, null, new ComparableHantoCoordinate(0,0)));	
		assertEquals(MoveResult.DRAW, game.makeMove(HantoPieceType.BUTTERFLY, null, new ComparableHantoCoordinate(1,0)));			
	}
	
	@Test(expected = HantoException.class)
	public void testTwoPiecesSameCoordinateShouldThrowException() throws HantoException {
		try {
			assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.BUTTERFLY, null, new ComparableHantoCoordinate(0,0)));	
		}		
		catch (HantoException e) {
			fail();
		}
		game.makeMove(HantoPieceType.BUTTERFLY, null, new ComparableHantoCoordinate(0,0));	
	}
	
	@Test(expected = HantoException.class)
	public void testInvalidStartPositionShouldThrowException() throws HantoException {
		game.makeMove(HantoPieceType.BUTTERFLY, null, new ComparableHantoCoordinate(2,0));
	}
	
	@Test(expected = HantoException.class)
	public void testPlaceNonAdjacentPiece() throws HantoException {
		try {
			assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.BUTTERFLY, null, new ComparableHantoCoordinate(0,0)));	
		}		
		catch (HantoException e) {
			fail();
		}
		game.makeMove(HantoPieceType.BUTTERFLY, null, new ComparableHantoCoordinate(2,2));
	}
	
	@Test
	public void testFirstPieceIsButterfly() throws HantoException {
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.BUTTERFLY, null, new ComparableHantoCoordinate(0,0)));
		assertEquals(HantoPieceType.BUTTERFLY, game.getPieceAt(new ComparableHantoCoordinate(0,0)).getType());		
	}
	
	@Test(expected = HantoException.class)
	public void testCanOnlyAddButterflies() throws HantoException {
		game.makeMove(HantoPieceType.CRAB, null, new ComparableHantoCoordinate(0,0));
	}
	
	@Test
	public void testFirstPieceIsBlue() throws HantoException {
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.BUTTERFLY, null, new ComparableHantoCoordinate(0,0)));
		assertEquals(HantoPlayerColor.BLUE, game.getPieceAt(new ComparableHantoCoordinate(0,0)).getColor());		
	}
	
	@Test
	public void testSeccondPieceIsRed() throws HantoException {
		game.makeMove(HantoPieceType.BUTTERFLY, null, new ComparableHantoCoordinate(0,0));
		assertEquals(HantoPlayerColor.BLUE, game.getPieceAt(new ComparableHantoCoordinate(0,0)).getColor());		
		
		game.makeMove(HantoPieceType.BUTTERFLY, null, new ComparableHantoCoordinate(1,0));
		assertEquals(HantoPlayerColor.RED, game.getPieceAt(new ComparableHantoCoordinate(1,0)).getColor());		
	}
	
	@Test(expected = HantoException.class)
	public void testOnlyTwoMovesCanBeMade() throws HantoException {
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.BUTTERFLY, null, new ComparableHantoCoordinate(0,0)));
		assertEquals(MoveResult.DRAW, game.makeMove(HantoPieceType.BUTTERFLY, null, new ComparableHantoCoordinate(1,0)));
		game.makeMove(HantoPieceType.BUTTERFLY, null, new ComparableHantoCoordinate(-1,0));
	}
	
	@Test(expected = HantoException.class)
	public void testPiecesCannotMove() throws HantoException {
		game.makeMove(HantoPieceType.BUTTERFLY, null, new ComparableHantoCoordinate(0,0));
		game.makeMove(HantoPieceType.BUTTERFLY, new ComparableHantoCoordinate(0,0), new ComparableHantoCoordinate(1,0));
	}
	
	@Test
	public void testPrintBoardWithOnePiece() throws HantoException {
		HantoCoordinate coord = new ComparableHantoCoordinate(0, 0);
		
		game.makeMove(BUTTERFLY, null, coord);	
		
		String board = String.format("[%d,%d] %s:%s\n", coord.getX(), coord.getY(), BLUE, BUTTERFLY.getPrintableName());
		
		assertEquals(board, game.getPrintableBoard());
	}
	
	@Test
	public void testPrintBoardWithTwoPiece() throws HantoException {
		game.makeMove(HantoPieceType.BUTTERFLY, null, new ComparableHantoCoordinate(0,0));	
		game.makeMove(HantoPieceType.BUTTERFLY, null, new ComparableHantoCoordinate(1,0));	
		
		String board = "";
		
		board +=String.format("[%d,%d] %s:%s\n", 0, 0, BLUE, BUTTERFLY.getPrintableName());
		board +=String.format("[%d,%d] %s:%s\n", 1, 0, RED, BUTTERFLY.getPrintableName());
		
		assertEquals(board, game.getPrintableBoard());
	}
	
	
	
}
