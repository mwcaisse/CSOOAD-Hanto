/**
 * 
 */
package hanto.studentmwcjlm.tournament;

import hanto.tournament.HantoMoveRecord;

/**
 * @author Mitchell Caisse
 *
 */
public class HantoAIResult {

	/** The new AI to use */
	private HantoAI ai;
	
	/** The hanto move to make */
	private HantoMoveRecord move;

	/** Creates a hanto move result with the given ai + move
	 * 
	 * @param ai The next AI to use
	 * @param move The move to make
	 */
	public HantoAIResult(HantoAI ai, HantoMoveRecord move) {
		this.ai = ai;
		this.move = move;
	}
	
	/**
	 * @return the ai
	 */
	
	public HantoAI getAi() {
		return ai;
	}

	/**
	 * @return the move
	 */
	
	public HantoMoveRecord getMove() {
		return move;
	}
	
	
	
}
