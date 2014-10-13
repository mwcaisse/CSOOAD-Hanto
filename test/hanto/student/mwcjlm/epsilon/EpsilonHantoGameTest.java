package hanto.student.mwcjlm.epsilon;


import static hanto.common.HantoPieceType.*;
import static hanto.common.HantoPlayerColor.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import hanto.common.HantoException;
import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.HantoPrematureResignationException;
import hanto.common.MoveResult;
import hanto.studentmwcjlm.common.ComparableHantoCoordinate;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import common.HantoTestGame;
import common.HantoTestGame.PieceLocationPair;
import common.HantoTestGameFactory;

public class EpsilonHantoGameTest {
	
	private HantoTestGame game;
	
	@Before
	public void setup() {
		game = HantoTestGameFactory.getInstance().makeHantoTestGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.BLUE);
	}

	private void initWithButterflies() throws HantoException {
		game.makeMove(HantoPieceType.BUTTERFLY, null, new ComparableHantoCoordinate(0,0)); //blue butteryfly
		game.makeMove(HantoPieceType.BUTTERFLY, null, new ComparableHantoCoordinate(0,1)); //red butterfly
	}
	
	@Test
	public void checkCoordinateDistance() {
		ComparableHantoCoordinate from = createCoord(0, 0);
		ComparableHantoCoordinate to = createCoord(0, 4);
		assertEquals(4, from.getDistance(to));
		to = createCoord(4, 0);
		assertEquals(4, from.getDistance(to));
		to = createCoord(-2, 1);
		assertEquals(2, from.getDistance(to));
		from = createCoord(1, -2);
		to = createCoord(0, 2);
		assertEquals(4, from.getDistance(to));
		from = createCoord(-3, 0);
		to = createCoord(0, 3);
		assertEquals(6, from.getDistance(to));
	}
	
	@Test
	public void checkCoordinateAdjacentByRadius() {
		ComparableHantoCoordinate start = createCoord(0, 0);
		List<ComparableHantoCoordinate> area = new ArrayList<ComparableHantoCoordinate>();
		area.add(createCoord(0, 1));
		area.add(createCoord(1, 0));
		area.add(createCoord(1, -1));
		area.add(createCoord(0, -1));
		area.add(createCoord(-1, 0));
		area.add(createCoord(-1, 1));
		area.add(createCoord(0, 2));
		area.add(createCoord(1, 1));
		area.add(createCoord(2, 0));
		area.add(createCoord(2, -1));
		area.add(createCoord(2, -2));
		area.add(createCoord(1, -2));
		area.add(createCoord(0, -2));
		area.add(createCoord(-1, -1));
		area.add(createCoord(-2, 0));
		area.add(createCoord(-2, 1));
		area.add(createCoord(-2, 2));
		area.add(createCoord(-1, 2));
		assertEquals(18, start.getAdjacentCoordsRadius(2).size());
		assertTrue(start.getAdjacentCoordsRadius(2).containsAll(area));
		assertEquals(start.getAdjacentCoords(), start.getAdjacentCoordsRadius(1));
	}
	
	@Test(expected = HantoPrematureResignationException.class)
	public void resignOnFirstMoveShouldException() throws HantoException {
		game.makeMove(null, null, null);
	}
	
	@Test(expected = HantoPrematureResignationException.class)
	public void resignOnFirstMoveShouldException2() throws HantoException {
		initWithButterflies();
		game.makeMove(null, null, null);
	}
	
	@Test
	public void shouldBeAbleToPlaceHorse() throws HantoException {
		initWithButterflies();
		//0, -1
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.HORSE, null, createCoord(0, -1))); 
	}
	
	@Test
	public void shouldBeAbleToPlaceSparrow() throws HantoException {
		initWithButterflies();
		//0, -1
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW, null, createCoord(0, -1))); 
	}
	
	@Test
	public void shouldBeAbleToPlaceCrab() throws HantoException {
		initWithButterflies();
		//0, -1
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.CRAB, null, createCoord(0, -1))); 
	}
	
	@Test
	public void blueShouldBeAbleToResign() throws HantoException {
		final PieceLocationPair[] board = new PieceLocationPair[] {
			    plPair(BLUE, BUTTERFLY, 0, 0), 
			    plPair(RED, CRAB, 1, 0),
			    plPair(RED, CRAB, 0, -1),
			    plPair(RED, CRAB, -1, 1)
			    
		};
		game.initializeBoard(board);
		game.setPlayerMoving(BLUE);		
		game.makeMove(null, null, null);
	}
	
	@Test
	public void blueSparrowShouldBeAbleToResign() throws HantoException {
		final PieceLocationPair[] board = new PieceLocationPair[] {
			    plPair(BLUE, SPARROW, 0, 0), 
			    plPair(RED, CRAB, 1, 0),
			    plPair(RED, CRAB, 0, -1),
			    plPair(RED, CRAB, -1, 1)
			    
		};
		game.initializeBoard(board);
		game.setPlayerMoving(BLUE);		
		game.makeMove(null, null, null);
	}
	
	@Test(expected = HantoException.class)
	public void placingCraneShouldThrowException() throws HantoException {
		initWithButterflies();
		game.makeMove(HantoPieceType.CRANE, null, createCoord(0,-1));
	}
	
	@Test
	public void sparrowShouldOnlyBeableToFlyFourSpaces() throws HantoException {
		final PieceLocationPair[] board = new PieceLocationPair[] {
			    plPair(BLUE, BUTTERFLY, 0, 0), 
			    plPair(RED, BUTTERFLY, 0, 1),
			    plPair(RED, CRAB, 0, 2),
			  //  plPair(RED, CRAB, 0, 3),
			    plPair(BLUE, SPARROW, 0, -1)			    
		};
		game.initializeBoard(board);
		game.setPlayerMoving(BLUE);		
		assertEquals(MoveResult.OK, game.makeMove(SPARROW, createCoord(0,  -1), createCoord(0, 3)));
		
	}
	
	@Test(expected = HantoException.class)
	public void sparrowShouldNotBeableToFlyFiveSpaces() throws HantoException {
		final PieceLocationPair[] board = new PieceLocationPair[] {
			    plPair(BLUE, BUTTERFLY, 0, 0), 
			    plPair(RED, BUTTERFLY, 0, 1),
			    plPair(RED, CRAB, 0, 2),
			    plPair(RED, CRAB, 0, 3),
			    plPair(BLUE, SPARROW, 0, -1)			    
		};
		game.initializeBoard(board);
		game.setPlayerMoving(BLUE);		
		game.makeMove(SPARROW, createCoord(0,  -1), createCoord(0, 4));
		
	}
	
	@Test(expected = HantoException.class)
	public void horseShouldNotJumpOneSpace() throws HantoException {
		final PieceLocationPair[] board = new PieceLocationPair[] {
			    plPair(BLUE, HORSE, 0, 0), 		    
		};
		game.initializeBoard(board);
		game.setPlayerMoving(BLUE);		
		game.makeMove(HORSE, createCoord(0,  0), createCoord(0, 1));
	}
	
	@Test
	public void horseShouldJumpDiagnolly() throws HantoException {
		final PieceLocationPair[] board = new PieceLocationPair[] {
			    plPair(BLUE, HORSE, 2, -2), 
			    plPair(RED, BUTTERFLY, 1, -1),
			    plPair(RED, CRAB, 0, 0),
			    plPair(RED, CRAB, -1, 1),
			    plPair(BLUE, SPARROW, -2, 2)			    
		};
		game.initializeBoard(board);
		game.setPlayerMoving(BLUE);		
		assertEquals(MoveResult.OK, game.makeMove(HORSE, createCoord(2,  -2), createCoord(-3, 3)));
	}
	
	@Test
	public void horseShouldJumpDiagnolly2() throws HantoException {
		final PieceLocationPair[] board = new PieceLocationPair[] {
			    plPair(BLUE, HORSE, 3, -2), 
			    plPair(RED, BUTTERFLY, 2, -1),
			    plPair(RED, CRAB, 1, 0),
			    plPair(RED, CRAB, 0, 1),
			    plPair(BLUE, SPARROW, -1, 2)			    
		};
		game.initializeBoard(board);
		game.setPlayerMoving(BLUE);		
		assertEquals(MoveResult.OK, game.makeMove(HORSE, createCoord(3,  -2), createCoord(-2, 3)));
	}
	
	@Test
	public void horseShouldBeAbleJumpAlongX() throws HantoException {
		final PieceLocationPair[] board = new PieceLocationPair[] {
			    plPair(BLUE, HORSE, 3, 0), 
			    plPair(RED, BUTTERFLY, 2, 0),
			    plPair(RED, CRAB, 1, 0),
			    plPair(RED, CRAB, 0, 0),
			    plPair(BLUE, SPARROW, -1, 0)			    
		};
		game.initializeBoard(board);
		game.setPlayerMoving(BLUE);		
		assertEquals(MoveResult.OK, game.makeMove(HORSE, createCoord(3,  0), createCoord(-2, 0)));
	}
	
	@Test
	public void horseShouldBeAbleToJumpAlongY() throws HantoException {	
		final PieceLocationPair[] board = new PieceLocationPair[] {
			    plPair(BLUE, HORSE, 5, 2), 
			    plPair(RED, BUTTERFLY, 5, 1),
			    plPair(RED, CRAB, 5, 0),
			    plPair(RED, CRAB, 5, -1),
			    plPair(BLUE, SPARROW, 5, -2)			    
		};
		game.initializeBoard(board);
		game.setPlayerMoving(BLUE);		
		assertEquals(MoveResult.OK, game.makeMove(HORSE, createCoord(5,  2), createCoord(5, -3)));
	}
	
	
	
	@Test(expected = HantoException.class)
	public void horseShouldNotBeableToJumpOverMissingPiece() throws HantoException {
		final PieceLocationPair[] board = new PieceLocationPair[] {
			    plPair(BLUE, HORSE, 2, -2), 
			    plPair(RED, BUTTERFLY, 1, -1),
			    plPair(RED, CRAB, -1, 1),
			    plPair(BLUE, SPARROW, -1, 0),
			    plPair(BLUE, SPARROW, 0, -1)
		};
		game.initializeBoard(board);
		game.setPlayerMoving(BLUE);		
		game.makeMove(HORSE, createCoord(2,  -2), createCoord(-3, 3));
	}
	
	@Test(expected = HantoException.class)
	public void horseShouldntBeableToJumpInNonLine() throws HantoException {
		final PieceLocationPair[] board = new PieceLocationPair[] {
			    plPair(BLUE, HORSE, -1, -1), 
			    plPair(RED, BUTTERFLY, 0, -1),
			    plPair(RED, CRAB, 0,0),
			    plPair(BLUE, SPARROW, 1, 0),
			    plPair(BLUE, SPARROW, 0, 1)
		};
		game.initializeBoard(board);
		game.setPlayerMoving(BLUE);		
		game.makeMove(HORSE, createCoord(-1,  -1), createCoord(1, 1));
	}
	
	public ComparableHantoCoordinate createCoord(int x, int y) {
		return new ComparableHantoCoordinate(x,y);
	}
	
	/**
	 * Factory method to create a piece location pair.
	 * @param player the player color
	 * @param pieceType the piece type
	 * @param x starting location
	 * @param y end location
	 * @return
	 */
	private PieceLocationPair plPair(HantoPlayerColor player, HantoPieceType pieceType, 
			int x, int y)
	{
		return new PieceLocationPair(player, pieceType, new ComparableHantoCoordinate(x, y));
	}
	
}
