package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.IMove;

import java.util.ArrayList;
import java.util.List;

public class Knight implements IMove {
 
    /** The possibles moves to Knight on a board
	*
	* @param pos is the position of the piece that you want his moves
	* @param board
	* @return List<IChess.ChessPosition>
	*
	*/

	public List<IChess.ChessPosition> getPossibleMoves(IChess.ChessPosition pos, Board board){

		List<IChess.ChessPosition> list = new ArrayList<IChess.ChessPosition>() ;
		IChess.ChessPosition test;

		for (int x = -2; x <= 2; x++) {
			for (int y = -2; y <= 2; y++) {

				if(Math.abs(x) + Math.abs(y) == 3) {
					test = new IChess.ChessPosition(pos.x + x,pos.y + y);
					if(ChessUtil.isValidPosition(pos, test, board)) list.add(test);
				}

			}
		}

		return list;
	}
}
