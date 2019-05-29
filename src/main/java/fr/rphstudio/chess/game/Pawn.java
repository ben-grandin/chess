package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.IMove;

import java.util.ArrayList;
import java.util.List;

public class Pawn implements IMove {

	public List<IChess.ChessPosition> getPossibleMoves(IChess.ChessPosition pos, Board board) {

		List<IChess.ChessPosition> list = new ArrayList<IChess.ChessPosition>();
		IChess.ChessPosition test;

		switch (board.getPiece(pos).getColor()){
			case CLR_WHITE:
				test = new IChess.ChessPosition( pos.x,pos.y - 1);

				if(board.getPiece(test) == null) list.add(test);

				if( (board.getPiece(test) != null)
						&&	board.getPiece(pos).getColor() != board.getPiece(test).getColor()
				) {
					test = new IChess.ChessPosition( pos.x + 1,pos.y - 1);
					if (ChessUtil.isValidPosition(pos ,test, board)) list.add(test);

					test = new IChess.ChessPosition( pos.x - 1,pos.y - 1);
					if (ChessUtil.isValidPosition(pos ,test, board)) list.add(test);
				}

				break;

			case CLR_BLACK:
				test = new IChess.ChessPosition(pos.x, pos.y + 1);

				if(board.getPiece(test) == null) list.add(test);

				if((board.getPiece(test) != null)
						&& board.getPiece(pos).getColor() != board.getPiece(test).getColor()) {
					test = new IChess.ChessPosition( pos.x + 1,pos.y + 1);
					if (ChessUtil.isValidPosition(pos ,test, board)) list.add(test);

					test = new IChess.ChessPosition( pos.x - 1,pos.y + 1);
					if (ChessUtil.isValidPosition(pos ,test, board)) list.add(test);
				}
				break;


		}




		return list;

	}
}