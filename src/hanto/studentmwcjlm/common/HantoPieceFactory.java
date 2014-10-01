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

import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;

/** Factory to make Hanto Pieces
 * @author James
 *
 */
public interface HantoPieceFactory {
	
	/** Makes a new HantoPiece with the given color and type
	 * 
	 * @param color The color of the piece to create
	 * @param type The type of the piece to create
	 * @return The piece
	 * @throws HantoException If the specified piece type is unsupported
	 */
	HantoMovingPiece makePiece(HantoPlayerColor color, HantoPieceType type) throws HantoException;
}
