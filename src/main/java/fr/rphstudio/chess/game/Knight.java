package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.IMove;

import java.util.ArrayList;
import java.util.List;

public class Knight implements IMove {


	public List<IChess.ChessPosition> getPossibleMoves(IChess.ChessPosition pos, Board board){

		List<IChess.ChessPosition> list = new ArrayList<IChess.ChessPosition>() ;
		IChess.ChessPosition test;

		for (int x = -2; x <= 2; x++) {
			for (int y = -2; y <= 2; y++) {

				

			}
		}

		return list;
	}
}