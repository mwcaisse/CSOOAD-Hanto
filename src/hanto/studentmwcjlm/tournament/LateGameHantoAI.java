package hanto.studentmwcjlm.tournament;

import hanto.common.HantoPlayerColor;
import hanto.studentmwcjlm.epsilon.EpsilonHantoGame;

public class LateGameHantoAI implements HantoAI {

	/** Keep track of how many turns */
	private int turnCount;
	
	public LateGameHantoAI(int turnCount) {
		this.turnCount = turnCount;
	}
	
	@Override
	public HantoAIResult getNextMove(EpsilonHantoGame game, HantoPlayerColor myColor) {
		turnCount++;
		// check if valid move
		// decide if we will be defensive or aggressive?
		// decide if we will place or move
		// place or move
		return null;
	}
	
	private boolean hasValidMove(EpsilonHantoGame game, HantoPlayerColor myColor) {
		return game.getPlayer(myColor).hasLegalMove(game.getBoard());
	}

}
