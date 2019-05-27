package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.EmptyCellException;
import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.OutOfBoardException;

import java.util.List;

public class ChessModel implements IChess {

	private static ChessModel nom;

	private ChessModel(){

	}

	public static ChessModel getInstance(){

		// soit elle instancie jamais

		// ou elle renvoi l'attribut
		return new ChessModel();
	}

	@Override
	public void reinit() {

	}

	@Override
	public ChessType getPieceType(ChessPosition p) throws EmptyCellException, OutOfBoardException {
		return null;
	}

	@Override
	public ChessColor getPieceColor(ChessPosition p) throws EmptyCellException, OutOfBoardException {
		return null;
	}

	@Override
	public int getNbRemainingPieces(ChessColor color) {
		return 0;
	}

	@Override
	public List<ChessPosition> getPieceMoves(ChessPosition p) {
		return null;
	}

	@Override
	public void movePiece(ChessPosition p0, ChessPosition p1) {

	}

	@Override
	public ChessKingState getKingState(ChessColor color) {
		return null;
	}

	@Override
	public List<ChessType> getRemovedPieces(ChessColor color) {
		return null;
	}

	@Override
	public boolean undoLastMove() {
		return false;
	}

	@Override
	public long getPlayerDuration(ChessColor color, boolean isPlaying) {
		return 0;
	}
}
