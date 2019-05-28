package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

import java.util.ArrayList;
import java.util.List;

public class  Rook {

	public static List<IChess.ChessPosition> getPossibleMoves(IChess.ChessPosition pos, Board board) {

		List<IChess.ChessPosition> list = new ArrayList<IChess.ChessPosition>();
		IChess.ChessPosition test;

		for (int x = 0; x <= 7; x++) {
			test = new IChess.ChessPosition(x, pos.y);
			if (board.getPiece(test) != null /* && board.getPiece(test) != "empty" */ ) list.add(test);
		}

		for (int y = 0; y <= 7; y++) {
			test = new IChess.ChessPosition(pos.x, y);
			if (board.getPiece(test) != null /* && board.getPiece(test) != "empty" */ ) list.add(test);
		}

		return list;

	}

}
