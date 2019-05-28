package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

import java.util.ArrayList;
import java.util.List;

public class King {

	public List<IChess.ChessPosition> getPossibleMoves(IChess.ChessPosition pos, Board board) {

		List<IChess.ChessPosition> list = new ArrayList<IChess.ChessPosition>();
		IChess.ChessPosition test = pos;

		for (int x = -1; x <= 1; x++) {
			for (int y = -1; y <= 1; y++) {
				if(x == 0 && y == 0) break;

				test = new IChess.ChessPosition(pos.x + x, pos.y + y);
				if (board.getPiece(test) == null || board.getPiece(test).getColor() != board.getPiece(pos).getColor()) list.add(test);
			}
		}

		return list;
	}
}
