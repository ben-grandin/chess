package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.IMove;

import java.util.ArrayList;
import java.util.List;

public class  Rook implements IMove {
            
    /** The possibles moves to Rook on a board
	*
	* @param pos is the position of the piece that you want his moves
	* @param board
	* @return List<IChess.ChessPosition>
	*
	*/

	public List<IChess.ChessPosition> getPossibleMoves(IChess.ChessPosition pos, Board board) {

	return ChessUtil.orthoMove(pos,board, 7);

	}

}
