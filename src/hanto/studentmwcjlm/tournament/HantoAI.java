/**
 * 
 */
package hanto.studentmwcjlm.tournament;

import hanto.common.HantoPlayerColor;
import hanto.studentmwcjlm.epsilon.EpsilonHantoGame;

/**
 * @author Mitchell Caisse
 *
 */
public interface HantoAI {

	/** Determines the next move to make
	 * 
	 * @param game The game to make the move on
	 * @param myColor The color that we are
	 * @return A tuple of the next mvoe to make, and the next hanto AI
	 */
	HantoAIResult getNextMove(EpsilonHantoGame game, HantoPlayerColor myColor);
	
}
