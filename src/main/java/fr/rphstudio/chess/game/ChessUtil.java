package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

public class ChessUtil {
	static boolean isValidPosition(IChess.ChessPosition pos, Board board){

		IChess.ChessPosition test;
		test = new IChess.ChessPosition(pos.y - 2, pos.y + 1);

		if (board.getPiece(test) == null || board.getPiece(test).getColor() != board.getPiece(pos).getColor()) return true;
		else return false ;
	}
}
