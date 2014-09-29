/**
 * 
 */
package hanto.student.mwcjlm.gamma;

import static org.junit.Assert.*;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGameID;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentmwcjlm.common.HantoCoordinateImpl;

import org.junit.Before;
import org.junit.Test;

import common.HantoTestGame;
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
		game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(0,0)); //blue butteryfly
		game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(0,1)); //red butterfly
	}
	
	/** Test gamma ends after 20 turns
	 * 
	 * @throws HantoException if the move failed
	 */
	@Test
	public void gameShouldEndAfterTwentyTurns() throws HantoException {
		initWithButterflies();
		game.setTurnNumber(19);
		
		game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,-1)); //blue sparrow
		assertEquals(MoveResult.DRAW, game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,2)));
		
	}
	
	@Test (expected = HantoException.class)
	public void placingPieceNextToDifferentColorPieceShouldThrowException() throws HantoException {
		initWithButterflies();
		game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,2)); //blue at 0,2 . exception		
	}
	
	@Test
	public void playingPieceNextToSameColorShouldBeOkay() throws HantoException {
		initWithButterflies();
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0, -1)));
	}
	
	@Test
	public void walkingPeiceShouldBeOkay() throws HantoException {
		initWithButterflies();
		game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(1, -1)); //blue
		game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(-1, 2)); //red
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW, createCoord(1,-1), createCoord(1, 0)));
		
		
		HantoPiece movedPiece = game.getPieceAt(createCoord(1,0));
		assertEquals(HantoPlayerColor.BLUE, movedPiece.getColor());
		assertEquals(HantoPieceType.SPARROW, movedPiece.getType());
		
		movedPiece = game.getPieceAt(createCoord(1,-1));
		assertNull(movedPiece);	
		
	}
	
	public HantoCoordinate createCoord(int x, int y) {
		return new HantoCoordinateImpl(x,y);
	}
			

}
