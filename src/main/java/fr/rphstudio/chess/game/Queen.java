package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.IMove;

import java.util.ArrayList;
import java.util.List;

public class Queen implements IMove {
    
        /** The possibles moves to Queen on a board
	*
	* @param pos is the position of the piece that you want his moves
	* @param board
	* @return List<IChess.ChessPosition>
	*
	*/

	public List<IChess.ChessPosition> getPossibleMoves(IChess.ChessPosition pos, Board board) {

		List<IChess.ChessPosition> list = new ArrayList<IChess.ChessPosition>();

		list.addAll(ChessUtil.orthoMove(pos,board, 7));
		list.addAll(ChessUtil.diagoMove(pos,board, 7));

		return list;
	}
}
