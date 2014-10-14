/**
 * 
 */
package hanto.student.mwcjlm.tournament;

import hanto.studentmwcjlm.epsilon.EpsilonHantoGame;
import hanto.studentmwcjlm.tournament.HantoAI;
import hanto.studentmwcjlm.tournament.HantoPlayer;

/**
 * @author Mitchell Caisse
 *
 */
public class TestHantoPlayer extends HantoPlayer {

	/** Returns the current hanto game
	 * 
	 * @return The hanto game being used
	 */
	public EpsilonHantoGame getHantoGame() {
		return game;
	}
	
	/** Returns the Hanto AI currently being used
	 * 
	 * @return The hanto AI
	 */
	public HantoAI getHantoAI() {
		return hantoAI;
	}
}
