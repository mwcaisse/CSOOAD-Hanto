package hanto.student.mwcjlm.epsilon;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import hanto.common.HantoException;
import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.HantoPrematureResignationException;
import hanto.studentmwcjlm.common.ComparableHantoCoordinate;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import common.HantoTestGame;
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
	
	@Test//(expected = HantoPrematureResignationException.class)
	public void resignOnFirstMoveShouldException() throws HantoException {
		game.makeMove(null, null, null);
	}
	
	public ComparableHantoCoordinate createCoord(int x, int y) {
		return new ComparableHantoCoordinate(x,y);
	}
}
