package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

/**
* @author BenGrandin
*/


public class Piece {
	// attributs privés de type ChessColor et ChessType ainsi que les méthodes publiques pour y accéder.

	private IChess.ChessColor color;
	private IChess.ChessType type;

	public Piece( IChess.ChessColor a, IChess.ChessType b){
		color = a;
		type = b;
	}

	public IChess.ChessColor getColor() {
		return color;
	}

	public IChess.ChessType getType() {
		return type;
	}
}
