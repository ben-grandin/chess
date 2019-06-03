package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

import java.util.ArrayList;
import java.util.List;

public class ChessUtil {

	/**
	 *
	 * @param pos is the position of the piece you to test a position
	 * @param test is the position you want to test
	 * @param board
	 * @return boolean
	 */

	static boolean isValidPosition(IChess.ChessPosition pos, IChess.ChessPosition test, Board board){

		if ((test.y < 8 && test.x < 8 && test.x >= 0 && test.y >= 0)
			&&
			( board.getPiece(test) == null
				|| ( (board.getPiece(test) != null) && board.getPiece(pos).getColor() != board.getPiece(test).getColor())
			)
		)  return true;

		else return false ;
	}

	/** orthoMove give the valid orthogonal move possible for a piece, on a board. It have a length max to define.
	 *
	 * @param pos is the position of the piece you to test a position
	 * @param board
	 * @param x is the length that you want to test
	 * @return boolean
	 */

	static List<IChess.ChessPosition> orthoMove(IChess.ChessPosition pos, Board board, int x) {
		List<IChess.ChessPosition> list = new ArrayList<IChess.ChessPosition>();
		IChess.ChessPosition test;
		boolean empty;
		int i = 1;

		do {
			test = new IChess.ChessPosition(pos.x + i, pos.y);
			if(isValidPosition(pos,test, board)) list.add(test);
			empty = board.getPiece(test) == null;
			i++;
		} while(empty && i <= x);

		i = 1;
		do {
			test = new IChess.ChessPosition(pos.x - i, pos.y);
			if(isValidPosition(pos,test, board)) list.add(test);
			empty = board.getPiece(test) == null;
			i++;
		} while(empty && i <= x);

		i = 1;
		do {
			test = new IChess.ChessPosition(pos.x, pos.y + i);
			if(isValidPosition(pos,test, board)) list.add(test);
			empty = board.getPiece(test) == null;
			i++;
		} while(empty && i <= x);

		i = 1;
		do {
			test = new IChess.ChessPosition(pos.x, pos.y - i);
			if(isValidPosition(pos,test, board)) list.add(test);
			empty = board.getPiece(test) == null;
			i++;
		} while(empty && i <= x);



		return list;
	}

	/** diagoMove give the valid diagonal move possible for a piece, on a board. It have a length max to define.
	 *
	 * @param pos is the position of the piece you to test a position
	 * @param board
	 * @param x is the length that you want to test
	 * @return boolean
	 */

	static List<IChess.ChessPosition> diagoMove(IChess.ChessPosition pos, Board board, int x) {
		List<IChess.ChessPosition> list = new ArrayList<IChess.ChessPosition>();
		IChess.ChessPosition test = pos;

		boolean empty;
		int i = 1;

		do {
			test = new IChess.ChessPosition(pos.x + i, pos.y + i);
			if(isValidPosition(pos,test, board)) list.add(test);
			empty = board.getPiece(test) == null;
			i++;
		} while(empty && i <= x);

		i = 1;
		do {
			test = new IChess.ChessPosition(pos.x - i, pos.y + i);
			if(isValidPosition(pos,test, board)) list.add(test);
			empty = board.getPiece(test) == null;
			i++;
		} while(empty && i <= x);

		i = 1;
		do {
			test = new IChess.ChessPosition(pos.x + i, pos.y - i);
			if(isValidPosition(pos,test, board)) list.add(test);
			empty = board.getPiece(test) == null;
			i++;
		} while(empty && i <= x);

		i = 1;
		do {
			test = new IChess.ChessPosition(pos.x - i, pos.y - i);
			if(isValidPosition(pos,test, board)) list.add(test);
			empty = board.getPiece(test) == null;
			i++;
		} while(empty && i <= x);
		return list;
	}
}
