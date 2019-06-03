package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.IMove;

import java.util.ArrayList;
import java.util.List;

public class King implements IMove {
    
        /** The possibles moves to King on a board
	*
	* @param pos is the position of the piece that you want his moves
	* @param board
	* @return List<IChess.ChessPosition>
	*
	*/

	int count = 0;

	public List<IChess.ChessPosition> getPossibleMoves(IChess.ChessPosition pos, Board board) {

		List<IChess.ChessPosition> list = new ArrayList<IChess.ChessPosition>();
		IChess.ChessPosition test = pos;

		list.addAll(ChessUtil.orthoMove(pos,board, 1));
		list.addAll(ChessUtil.diagoMove(pos,board, 1));

		if(count == 0){
			
		}
 // test
		// test4
		this.count ++;

		return list;
	}
}
