package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

import java.util.ArrayList;
import java.util.List;

public class ChessUtil {
	static boolean isValidPosition(IChess.ChessPosition pos, Board board){

		IChess.ChessPosition test;
		test = new IChess.ChessPosition(pos.y - 2, pos.y + 1);

		if (board.getPiece(test) == null || board.getPiece(test).getColor() != board.getPiece(pos).getColor()) return true;
		else return false ;
	}

	static List<IChess.ChessPosition> OrthoMove(IChess.ChessPosition pos, Board board) {
		List<IChess.ChessPosition> list = new ArrayList<IChess.ChessPosition>();
		IChess.ChessPosition test;

		for (int x = 0; x <= 7; x++) {
			test = new IChess.ChessPosition(x, pos.y);
			if (isValidPosition(test,board) ) list.add(test);
		}

		for (int y = 0; y <= 7; y++) {
			test = new IChess.ChessPosition(pos.x, y);
			if (isValidPosition(test,board)) list.add(test);
		}

		return list;
	}

	static List<IChess.ChessPosition> DiagoMove(IChess.ChessPosition pos, Board board) {
		List<IChess.ChessPosition> list = new ArrayList<IChess.ChessPosition>();
		IChess.ChessPosition test = pos;

		do {
			test = new IChess.ChessPosition(pos.x, pos.y);
			if (isValidPosition(test,board)) list.add(test);
			pos.x --;
			pos.y --;
		} while (test.x < 0 && test.y < 0);

		test = pos;

		do {
			test = new IChess.ChessPosition(pos.x, pos.y);
			if (isValidPosition(test,board)) list.add(test);
			pos.x --;
			pos.y ++;
		} while (test.x < 0 && test.y > 8);

		test = pos;

		do {
			test = new IChess.ChessPosition(pos.x, pos.y);
			if (isValidPosition(test,board)) list.add(test);
			pos.x ++;
			pos.y --;
		} while (test.x < 8 && test.y < 0);

		test = pos;

		do {
			test = new IChess.ChessPosition(pos.x, pos.y);
			if (isValidPosition(test,board)) list.add(test);
			pos.x ++;
			pos.y ++;
		} while (test.x < 8 && test.y > 8);

		return list;
	}
}
