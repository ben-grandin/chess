package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.EmptyCellException;
import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.OutOfBoardException;
import java.util.ArrayList;

import java.util.List;

public class ChessModel implements IChess {

	private static ChessModel nom;
	private Board board;

	private ChessModel(){
		this.board = new Board();
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
		this.nom = new ChessModel();

	}

	@Override
	public ChessType getPieceType(ChessPosition p) throws EmptyCellException, OutOfBoardException {

		Piece piece = board.getPiece(p);
		if(piece == null) throw new EmptyCellException();

		return piece.getType();
	}

	@Override
	public ChessColor getPieceColor(ChessPosition p) throws EmptyCellException, OutOfBoardException {
		Piece piece = board.getPiece(p);
		if(piece == null) throw new EmptyCellException();

		return piece.getColor();
	}

	@Override
	public int getNbRemainingPieces(ChessColor color) {
            int myCount = 0;
            
            myCount = board.getNbRemainingPieces(color);
            
                    
                    
            return myCount;
	}

	@Override
	public List<ChessPosition> getPieceMoves(ChessPosition p) {
		Piece piece = board.getPiece(p);
		if(piece != null) return piece.getMove(p,this.board) ;
 		else return new ArrayList<>();
	}

	@Override
	public void movePiece(ChessPosition p0, ChessPosition p1) {
		
           switch( board.getPiece(p0).getType() ){
               
               case TYP_PAWN:
                    if (board.getPiece(p0).getColor() == ChessColor.CLR_WHITE && p1.y == 0){
                        board.setPiece(p1, new Piece(board.getPiece(p0).getColor(), ChessType.TYP_QUEEN, new Queen() ));
                        break;
                    }
                    else if (board.getPiece(p0).getColor() == ChessColor.CLR_BLACK && p1.y == 7){
                        board.setPiece(p1, new Piece(board.getPiece(p0).getColor(), ChessType.TYP_QUEEN, new Queen() ));
                        break;
                    }
                
               default:
                   board.setPiece(p1,board.getPiece(p0));
           }      
                   
            board.setPiece(p0,null);

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
