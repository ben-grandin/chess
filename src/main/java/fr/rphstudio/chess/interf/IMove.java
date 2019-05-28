package fr.rphstudio.chess.interf;

import fr.rphstudio.chess.game.Board;

import java.util.List;

public interface IMove {
	public List<IChess.ChessPosition> getPossibleMoves(IChess.ChessPosition pos, Board board);
}