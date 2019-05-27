package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.EmptyCellException;
import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.OutOfBoardException;
import java.util.ArrayList;

import java.util.List;

public class ChessModel implements IChess {

	private static ChessModel nom;

	private ChessModel(){

	}

	public static ChessModel getInstance()
        {
            if (nom == null) 
            {
            nom = new ChessModel(); // on instancie si ChessModel n'existe pas
            return nom;
            }
            else 
            {
                return nom; // on retoune les attributs sans re-instancier 
            }
	}

	@Override
	public void reinit() {

	}

	@Override
	public ChessType getPieceType(ChessPosition p) throws EmptyCellException, OutOfBoardException {
		throw new EmptyCellException();
	}

	@Override
	public ChessColor getPieceColor(ChessPosition p) throws EmptyCellException, OutOfBoardException {
		return ChessColor.CLR_WHITE;
	}

	@Override
	public int getNbRemainingPieces(ChessColor color) {
		return 0;
	}

	@Override
	public List<ChessPosition> getPieceMoves(ChessPosition p) {
		return new ArrayList<>();
	}

	@Override
	public void movePiece(ChessPosition p0, ChessPosition p1) {

	}

	@Override
	public ChessKingState getKingState(ChessColor color) {
		return ChessKingState.KING_SAFE;
	}

	@Override
	public List<ChessType> getRemovedPieces(ChessColor color) {
		return new ArrayList<>();
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
