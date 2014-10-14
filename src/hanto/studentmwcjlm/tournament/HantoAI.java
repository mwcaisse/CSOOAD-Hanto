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


/** Hanto AI for determining which move to make
 * 
 * @author Mitchell Caisse
 *
 */
public interface HantoAI {

	/** Determines the next move to make
	 * 
	 * @return A tuple of the next mvoe to make, and the next hanto AI
	 */
	
	HantoAIResult getNextMove();
	
}
