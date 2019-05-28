package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.IMove;

import java.util.List;

public class Piece {

	private IChess.ChessColor color;
	private IChess.ChessType type;
	private IMove move;

	public Piece(IChess.ChessColor color, IChess.ChessType type, IMove move) {
		this.color = color;
		this.type = type;
		this.move = move;
	}

	public List<IChess.ChessPosition> getMove(IChess.ChessPosition pos, Board board){
		return this.move.getPossibleMoves(pos, board);
	}


	public IChess.ChessColor getColor() {
		return color;
	}

	public IChess.ChessType getType() {
		return type;
	}

}

