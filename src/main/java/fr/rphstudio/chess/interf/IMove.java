package fr.rphstudio.chess.interf;

import fr.rphstudio.chess.game.Board;

import java.util.List;

public interface IMove {

	/** getPossibleMoves return the possibles moves to a piece on a board
	 *
	* @param pos is the position of the piece that you want his moves
	* @param board
	* @return List<IChess.ChessPosition>
	*
	 */
	public List<IChess.ChessPosition> getPossibleMoves(IChess.ChessPosition pos, Board board);
}