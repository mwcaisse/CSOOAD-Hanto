/**
 * 
 */
package hanto.student.mwcjlm.gamma;

import static org.junit.Assert.*;
import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentmwcjlm.common.ComparableHantoCoordinate;
import hanto.studentmwcjlm.common.HantoGameFactory;
import hanto.studentmwcjlm.gamma.GammaHantoGame;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import common.HantoTestGame;
import common.HantoTestGame.PieceLocationPair;
import common.HantoTestGameFactory;

/**
 * @author Mitchell Caisse
 *
 */
public class GammaHantoGameTest {
	
	/** The hanto game to use for testing */
	private HantoTestGame game;
	
	@Before
	public void setup() {
		game = HantoTestGameFactory.getInstance().makeHantoTestGame(HantoGameID.GAMMA_HANTO, HantoPlayerColor.BLUE);
	}
	
	private void initWithButterflies() throws HantoException {
		game.makeMove(HantoPieceType.BUTTERFLY, null, new ComparableHantoCoordinate(0,0)); //blue butteryfly
		game.makeMove(HantoPieceType.BUTTERFLY, null, new ComparableHantoCoordinate(0,1)); //red butterfly
	}
	
	/** Test gamma ends after 20 turns
	 * 
	 * @throws HantoException if the move failed
	 */
	@Test
	public void gameShouldEndAfterTwentyTurns() throws HantoException {
		initWithButterflies();
		game.setTurnNumber(20);
		
		game.makeMove(HantoPieceType.SPARROW, null, new ComparableHantoCoordinate(0,-1)); //blue sparrow
		assertEquals(MoveResult.DRAW, game.makeMove(HantoPieceType.SPARROW, null, new ComparableHantoCoordinate(0,2)));
		
	}
	
	/** Test gamma should throw an exception after game is over
	 * 
	 * @throws HantoException
	 */
	@Test (expected=HantoException.class)
	public void gameShouldThrowExceptionAfterGameOver() throws HantoException {
		initWithButterflies();
		game.setTurnNumber(21);		
		game.makeMove(HantoPieceType.SPARROW, null, new ComparableHantoCoordinate(0,-1)); //blue sparrow		
		
	}
	
	@Test (expected = HantoException.class)
	public void placingPieceNextToDifferentColorPieceShouldThrowException() throws HantoException {
		initWithButterflies();
		game.makeMove(HantoPieceType.SPARROW, null, new ComparableHantoCoordinate(0,2)); //blue at 0,2 . exception		
	}
	
	@Test
	public void playingPieceNextToSameColorShouldBeOkay() throws HantoException {
		initWithButterflies();
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW, null, new ComparableHantoCoordinate(0, -1)));
	}
	
	@Test
	public void walkingPeiceShouldBeOkay() throws HantoException {
		initWithButterflies();
		game.makeMove(HantoPieceType.SPARROW, null, new ComparableHantoCoordinate(1, -1)); //blue
		game.makeMove(HantoPieceType.SPARROW, null, new ComparableHantoCoordinate(-1, 2)); //red
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW, createCoord(1,-1), createCoord(1, 0)));
		
		
		HantoPiece movedPiece = game.getPieceAt(createCoord(1,0));
		assertEquals(HantoPlayerColor.BLUE, movedPiece.getColor());
		assertEquals(HantoPieceType.SPARROW, movedPiece.getType());
		
		movedPiece = game.getPieceAt(createCoord(1,-1));
		assertNull(movedPiece);	
		
	}
	
	@Test (expected = HantoException.class)
	public void nonContigiousPieceShouldThrowException() throws HantoException {
		initWithButterflies();
		game.makeMove(HantoPieceType.SPARROW, null, new ComparableHantoCoordinate(1, -1)); //blue
		game.makeMove(HantoPieceType.SPARROW, null, new ComparableHantoCoordinate(-1, 2)); //red
		game.makeMove(HantoPieceType.SPARROW, createCoord(1,-1), createCoord(2,-2));	
	}
	
	@Test (expected = HantoException.class)
	public void blockingWalkingShouldThrowException() throws HantoException {
		List<PieceLocationPair> initialPieces = new ArrayList<PieceLocationPair>();
		
		initialPieces.add(new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, createCoord(0,0)));
		initialPieces.add(new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, createCoord(0,1)));
		initialPieces.add(new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, createCoord(1,-1)));
		initialPieces.add(new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, createCoord(-1,1)));
		
		game.initializeBoard(initialPieces.toArray(new PieceLocationPair[0]));
		
		
		game.makeMove(HantoPieceType.SPARROW, createCoord(0,0), createCoord(1,0));
		
		
	}
	
	@Test (expected = HantoException.class)
	public void invalidPieceThrowsException() throws HantoException {
		game.makeMove(HantoPieceType.HORSE, null, createCoord(0,0));
	}
	
	@Test
	public void gameFactoryMakesGammaHanto() {
		HantoGame gammaGame = HantoGameFactory.getInstance().makeHantoGame(HantoGameID.GAMMA_HANTO);
		assertTrue(gammaGame instanceof GammaHantoGame);
	}
	
	
	public HantoCoordinate createCoord(int x, int y) {
		return new ComparableHantoCoordinate(x,y);
	}
			

}
