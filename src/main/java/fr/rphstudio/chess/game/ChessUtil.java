package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

import java.util.ArrayList;
import java.util.List;

public class ChessUtil {
	static boolean isValidPosition(IChess.ChessPosition pos, IChess.ChessPosition test, Board board){

		if ((pos.y < 8 && pos.x < 8 && pos.x >= 0 && pos.y >= 0)
			|| board.getPiece(test) == null
			|| ( (board.getPiece(test) != null) && board.getPiece(pos).getColor() != board.getPiece(test).getColor() )
		) return true;

		else return false ;
	}

	static List<IChess.ChessPosition> OrthoMove(IChess.ChessPosition pos, Board board) {
		List<IChess.ChessPosition> list = new ArrayList<IChess.ChessPosition>();
		IChess.ChessPosition test;

		return list;
	}

	static List<IChess.ChessPosition> DiagoMove(IChess.ChessPosition pos, Board board) {
		List<IChess.ChessPosition> list = new ArrayList<IChess.ChessPosition>();
		IChess.ChessPosition test = pos;

		return list;
	}
}
