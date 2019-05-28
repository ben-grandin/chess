package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

import java.util.ArrayList;
import java.util.List;

public class Queen {

	public List<IChess.ChessPosition> getPossibleMoves(IChess.ChessPosition pos, Board board) {

		List<IChess.ChessPosition> list = new ArrayList<IChess.ChessPosition>();

		list.addAll(Rook.getPossibleMoves(pos,board));
		list.addAll(Bishop.getPossibleMoves(pos,board));

		return list;
	}
}
