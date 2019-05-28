package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

import java.util.ArrayList;
import java.util.List;

public class Bishop {

	public static List<IChess.ChessPosition> getPossibleMoves(IChess.ChessPosition pos, Board board) {

		List<IChess.ChessPosition> list = new ArrayList<IChess.ChessPosition>();
		IChess.ChessPosition test = pos;

		do {
			test = new IChess.ChessPosition(pos.x, pos.y);
			if (board.getPiece(test) != null /* && board.getPiece(test) != "empty" */ ) list.add(test);
			pos.x --;
			pos.y --;
		} while (test.x < 0 && test.y < 0);

		test = pos;

		do {
			test = new IChess.ChessPosition(pos.x, pos.y);
			if (board.getPiece(test) != null /* && board.getPiece(test) != "empty" */ ) list.add(test);
			pos.x --;
			pos.y ++;
		} while (test.x < 0 && test.y > 8);

		test = pos;

		do {
			test = new IChess.ChessPosition(pos.x, pos.y);
			if (board.getPiece(test) != null /* && board.getPiece(test) != "empty" */ ) list.add(test);
			pos.x ++;
			pos.y --;
		} while (test.x < 8 && test.y < 0);

		test = pos;

		do {
			test = new IChess.ChessPosition(pos.x, pos.y);
			if (board.getPiece(test) != null /* && board.getPiece(test) != "empty" */ ) list.add(test);
			pos.x ++;
			pos.y ++;
		} while (test.x < 8 && test.y > 8);

		return list;

	}

}
