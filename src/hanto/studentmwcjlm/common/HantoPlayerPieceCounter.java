/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentmwcjlm.common;

import hanto.common.HantoPieceType;

import java.util.HashMap;
import java.util.Map;

/** Piece Counter to keep track of how many pieces a player has left.
 * 
 * @author Mitchell Caisse, James Megin
 *
 */
public class HantoPlayerPieceCounter {
	private HashMap<HantoPieceType, Integer> piecesRemaining;
	
	/** Creates a new Piece Counter with the given piece counts
	 * 
	 * @param maxCounts Mapping of Piece type to their counts
	 */
	public HantoPlayerPieceCounter(Map<HantoPieceType, Integer> maxCounts) {
		piecesRemaining = new HashMap<HantoPieceType, Integer>();
		init(maxCounts);
	}
	
	private void init(Map<HantoPieceType, Integer> maxCounts) {
		for(HantoPieceType type : HantoPieceType.values()) {
			piecesRemaining.put(type, 0);
		}
		piecesRemaining.putAll(maxCounts);
	}
	
	/** Returns the number of pieces remaining, not yet placed, for the given piece type
	 * 
	 * @param type The PieceType to get the remaining pieces for
	 * @return The number of pieces of that type remaining
	 */
	public int getPiecesRemaining(HantoPieceType type) {
		return piecesRemaining.get(type);
	}
	
	/** Decrements the number of pieces of this type left
	 * 
	 * @param type The type of piece to decrement
	 */
	public void decrementPieceType(HantoPieceType type) {
		int oldVal = getPiecesRemaining(type);
		piecesRemaining.put(type, oldVal - 1);
	}
}
