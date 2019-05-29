package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

import java.util.ArrayList;
import java.util.List;

public class ChessUtil {
	static boolean isValidPosition(IChess.ChessPosition pos, IChess.ChessPosition test, Board board){

		if ((test.y < 8 && test.x < 8 && test.x >= 0 && test.y >= 0)
			&&
			( board.getPiece(test) == null
				|| ( (board.getPiece(test) != null) && board.getPiece(pos).getColor() != board.getPiece(test).getColor())
			)
		) return true;

		else return false ;
	}

	static List<IChess.ChessPosition> orthoMove(IChess.ChessPosition pos, Board board) {
		List<IChess.ChessPosition> list = new ArrayList<IChess.ChessPosition>();
		IChess.ChessPosition test;
		boolean empty;
		int i = 1;

		do {
			test = new IChess.ChessPosition(pos.x + i, pos.y);
			if(isValidPosition(pos,test, board)) list.add(test);
			empty = board.getPiece(test) == null;
			i++;
		} while(empty && i < 8);

		i = 1;
		do {
			test = new IChess.ChessPosition(pos.x - i, pos.y);
			if(isValidPosition(pos,test, board)) list.add(test);
			empty = board.getPiece(test) == null;
			i++;
		} while(empty && i < 8);

		i = 1;
		do {
			test = new IChess.ChessPosition(pos.x, pos.y + i);
			if(isValidPosition(pos,test, board)) list.add(test);
			empty = board.getPiece(test) == null;
			i++;
		} while(empty && i < 8);

		i = 1;
		do {
			test = new IChess.ChessPosition(pos.x, pos.y - i);
			if(isValidPosition(pos,test, board)) list.add(test);
			empty = board.getPiece(test) == null;
			i++;
		} while(empty && i < 8);



		return list;
	}

	static List<IChess.ChessPosition> diagoMove(IChess.ChessPosition pos, Board board) {
		List<IChess.ChessPosition> list = new ArrayList<IChess.ChessPosition>();
		IChess.ChessPosition test = pos;

		return list;
	}
}
