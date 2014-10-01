/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentmwcjlm.common.movevalidator;


/** Move validator for Pieces that can flt
 * 
 * @author Mitchell Caisse
 *
 */
public class FlyMoveValidator extends BasicMoveValidator {

	/** The singleton instance */
	private static FlyMoveValidator instance = new FlyMoveValidator();
	
	/** Gets the singleton instance
	 * 
	 * @return The singleton instance
	 */
	public static FlyMoveValidator getInstance() {
		return instance;
	}
	
	/** Protected constructor
	 * 
	 */
	protected FlyMoveValidator() {
		
	}

}
