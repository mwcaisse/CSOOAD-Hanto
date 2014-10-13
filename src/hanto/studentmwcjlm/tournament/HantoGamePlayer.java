/**
 * 
 */
package hanto.studentmwcjlm.tournament;

import hanto.common.HantoException;
import hanto.common.HantoGameID;
import hanto.common.HantoPlayerColor;
import hanto.studentmwcjlm.common.AbstractHantoGame;
import hanto.studentmwcjlm.epsilon.EpsilonHantoGame;
import hanto.tournament.HantoMoveRecord;

/** Player for Hanto Epsilon Tournament
 * 
 * @author Mitchell Caisse, James Megin
 *
 */
public class HantoGamePlayer implements hanto.tournament.HantoGamePlayer {

	/** The hanto game to use to play */
	private EpsilonHantoGame game;
	
	/** The color that I am */
	private HantoPlayerColor myColor;
	
	/** Whether or not we move first */
	private boolean moveFirst;
	
	/** The Ai to use for the game */
	private HantoAI hantoAI;
	/**
	 * This method must be called first after the player is constructed. It tells the
	 * player what version of the game to play, what the player's color is, and whether the
	 * player moves first.
	 * @param version the specific Hanto game to play
	 * @param myColor the color for this player
	 * @param doIMoveFirst true if the player will make the first move
	 */
	public void startGame(HantoGameID version, HantoPlayerColor myColor, boolean doIMoveFirst) {
		HantoPlayerColor firstColor = doIMoveFirst ? myColor : AbstractHantoGame.oppositeColor(myColor);
		game = new EpsilonHantoGame(firstColor);
		this.myColor = myColor;
		moveFirst = doIMoveFirst;
		hantoAI = new FirstMoveHantoAI();
	}
	
	/**
	 * Make the player's next move.
	 * @param opponentsMove this is the result of the opponent's last move, in response
	 * 	to your last move. This will be null if you are making the first move of the game.
	 * @return your move
	 */
	public HantoMoveRecord makeMove(HantoMoveRecord opponentsMove) {
		try {
			boolean firstMove = false;
			if (opponentsMove != null) {		
				makeHantoMove(opponentsMove);
				game.makeMove(opponentsMove.getPiece(),opponentsMove.getFrom(), opponentsMove.getTo());
				firstMove = true;
			}
			HantoAIResult res = hantoAI.getNextMove(game, myColor);
			hantoAI = res.getAi();
			makeHantoMove(res.getMove());			
			return res.getMove();
		}
		catch (Exception e) {
			System.out.println("Opponenets move created exception...");
			e.printStackTrace();
		}
		
		return null;		
	}
	
	/** Makes the specified move
	 * 
	 * @param move The move to make
	 * @throws HantoException If the move was invalid
	 */
	private void makeHantoMove(HantoMoveRecord move) throws HantoException {
		game.makeMove(move.getPiece(),move.getFrom(), move.getTo());
	}
	


}
