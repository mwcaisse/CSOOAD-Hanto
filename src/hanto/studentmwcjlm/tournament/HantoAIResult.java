/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentmwcjlm.tournament;

import hanto.tournament.HantoMoveRecord;

/** The result of Hanto AI's get next move, used to return both the move record and
 * 	the next AI to use.
 * 
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
