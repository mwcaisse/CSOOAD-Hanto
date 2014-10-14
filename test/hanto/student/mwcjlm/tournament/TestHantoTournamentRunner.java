/**
 * 
 */
package hanto.student.mwcjlm.tournament;

import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentmwcjlm.common.AbstractHantoGame;
import hanto.studentmwcjlm.common.HantoGameFactory;
import hanto.studentmwcjlm.tournament.HantoPlayer;
import hanto.tournament.HantoGamePlayer;
import hanto.tournament.HantoMoveRecord;

/**
 * @author Mitchell Caisse
 *
 */
public class TestHantoTournamentRunner {

	private HantoGamePlayer playerOne;
	
	private HantoGamePlayer playerTwo;
	
	private HantoPlayerColor firstPlayer;
	
	private HantoGame game;
	
	private HantoGamePlayer currentPlayer;
	
	private int turnCount;
	
	private static final int TURN_LIMIT = 120;
	
	public TestHantoTournamentRunner(HantoPlayerColor firstPlayer) {
		this.firstPlayer = firstPlayer;
	}
	
	private void initializePlayers() {
		playerOne = new HantoPlayer();
		playerTwo = new HantoPlayer();
		
		game = HantoGameFactory.getInstance().makeHantoGame(HantoGameID.EPSILON_HANTO, firstPlayer);
		
		playerOne.startGame(HantoGameID.EPSILON_HANTO, firstPlayer, true);
		playerTwo.startGame(HantoGameID.EPSILON_HANTO, AbstractHantoGame.oppositeColor(firstPlayer), false);
	}
	
	public MoveResult runTournament() throws HantoException {
		initializePlayers();
		
		turnCount = 0;
		MoveResult moveResult = MoveResult.OK;
		
		HantoMoveRecord lastMove = null;
		
		while (moveResult == MoveResult.OK && turnCount < TURN_LIMIT) {
			HantoMoveRecord currentMove = getNextPlayer().makeMove(lastMove);
			moveResult = game.makeMove(currentMove.getPiece(), currentMove.getFrom(), currentMove.getTo());
			lastMove = currentMove;
			turnCount ++;
		}
		
		if (moveResult == MoveResult.OK) {
			moveResult = MoveResult.DRAW;
		}
		return moveResult;
		
	}
	
	private HantoGamePlayer getNextPlayer() {
		if (currentPlayer == playerOne) {
			currentPlayer = playerTwo;
		}
		else {
			currentPlayer = playerOne;
		}
		return currentPlayer;
	}
	
}
