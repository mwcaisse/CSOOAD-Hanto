/**
 * 
 */
package hanto.student.mwcjlm.tournament;

import static org.junit.Assert.*;
import hanto.common.HantoGameID;
import hanto.common.HantoPlayerColor;
import hanto.studentmwcjlm.common.ComparableHantoCoordinate;
import hanto.studentmwcjlm.tournament.HantoPlayer;
import hanto.tournament.HantoGamePlayer;
import hanto.tournament.HantoMoveRecord;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Mitchell Caisse
 *
 */
public class HantoPlayerTest {

	
	/** First Hanto player */
	private HantoGamePlayer playerBlue;
	
	/** Second Hanto Player */
	private HantoGamePlayer playerRed;
	
	@Before
	public void setup() {
		playerBlue = new HantoPlayer();
		playerRed = new HantoPlayer();
		playerBlue.startGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.BLUE, true);
		playerRed.startGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.RED, false);
	}
	
	
	@Test
	public void testFirstMoveShouldBeZeroZero() {
		HantoMoveRecord moveResult = playerBlue.makeMove(null);
		assertNull(moveResult.getFrom());
		assertEquals(new ComparableHantoCoordinate(0, 0), moveResult.getTo());
	}
	
}
