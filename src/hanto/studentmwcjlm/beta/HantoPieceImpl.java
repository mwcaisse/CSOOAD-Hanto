/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentmwcjlm.beta;

import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;

/** The implementation of a Hanto Piece
 * 
 * @author Mitchell
 *
 */
public class HantoPieceImpl implements HantoPiece {

	/** The color of the player whoose piece this is */
	private HantoPlayerColor color;
	
	/** The type of piece */
	private HantoPieceType type;
	
	/** Creates a new Hanto Piece 
	 * 
	 */
	public HantoPieceImpl(HantoPlayerColor color, HantoPieceType type) {
		this.color = color;
		this.type = type;
	}
	
	public HantoPlayerColor getColor() {
		return color;
	}

	public HantoPieceType getType() {
		return type;
	}

	
	
}
