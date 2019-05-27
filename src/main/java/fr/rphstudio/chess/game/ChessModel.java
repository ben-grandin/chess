package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

public class ChessModel {

	static ChessModel nom  ;

	private ChessModel(){

	}

	public ChessModel getInstance(){

		// soit elle instancie jamais

		// ou elle renvoi l'attribut
		return this.nom;
	}

}
