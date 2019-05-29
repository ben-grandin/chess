package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.IMove;

import java.util.ArrayList;
import java.util.List;

public class  Rook implements IMove {

	public List<IChess.ChessPosition> getPossibleMoves(IChess.ChessPosition pos, Board board) {

	return ChessUtil.orthoMove(pos,board);

	}

}
