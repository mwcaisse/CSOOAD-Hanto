package hanto.studentmwcjlm.tournament;

import hanto.studentmwcjlm.common.ComparableHantoCoordinate;

/** Keep track of where a potential move is coming from and where it is going
 * @author James, Mitchell
 *
 */
public class CoordinateMoveDistance {

	private ComparableHantoCoordinate destination;
	private ComparableHantoCoordinate currentPosition;
	
	public CoordinateMoveDistance(ComparableHantoCoordinate destination, ComparableHantoCoordinate currentPosition) {
		this.destination = destination;
		this.currentPosition = currentPosition;
	}
	
	public ComparableHantoCoordinate getDestination() {
		return destination;
	}
	
	public ComparableHantoCoordinate getCurrentPosition() {
		return currentPosition;
	}
	
}
