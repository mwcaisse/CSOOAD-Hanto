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

public class HantoPlayerPieceCounter {
	private HashMap<HantoPieceType, Integer> piecesRemaining;
	
	public HantoPlayerPieceCounter(HashMap<HantoPieceType, Integer> maxCounts) {
		piecesRemaining = new HashMap<HantoPieceType, Integer>();
		init(maxCounts);
	}
	
	private void init(HashMap<HantoPieceType, Integer> maxCounts) {
		for(HantoPieceType type : HantoPieceType.values()) {
			piecesRemaining.put(type, 0);
		}
		piecesRemaining.putAll(maxCounts);
	}
	
	public int getPiecesRemaining(HantoPieceType type) {
		return piecesRemaining.get(type);
	}
	
	public void decrementPieceType(HantoPieceType type) {
		int oldVal = getPiecesRemaining(type);
		piecesRemaining.put(type, oldVal - 1);
	}
}
