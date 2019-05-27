//--------------------------------------------------------------------
// IMPORTS
//--------------------------------------------------------------------
package fr.rphstudio.chess.interf;

import java.util.List;


//--------------------------------------------------------------------
// INTERFACE
//--------------------------------------------------------------------

/**
 * Interface used for Oriented-Object Programmming courses.
 * The goal is to create a class (at least one ^^) that implements this interface, in order to simulate the chess game rules. 
 * /!\ DO NOT MODIFY THIS INTERFACE /!\
 * @author Romuald GRIGNON 
 */
public interface IChess
{
    //--------------------------------------------------------------------
    // ENUMS
    //--------------------------------------------------------------------

    /**
    * Enum used for chess piece colors.
    */
    public enum ChessColor
    {
        /**
         * Color value used for all the BLACK pieces.
         */
        CLR_BLACK,

        /**
         * Color value used for all the WHITE pieces.
         */
        CLR_WHITE,
    }

    /**
     * Enum used for chess piece types.
     */
    public enum ChessType
    {
        /**
         * Type value used for a King piece.
         */
        TYP_KING,

        /**
         * Type value used for a Queen piece.
         */
        TYP_QUEEN,

        /**
         * Type value used for a Bishop piece.
         */
        TYP_BISHOP,

        /**
         * Type value used for a Knight piece.
         */
        TYP_KNIGHT,

        /**
         * Type value used for a Rook piece.
         */
        TYP_ROOK,

        /**
         * Type value used for a Pawn piece.
         */
        TYP_PAWN,
    }

    /**
     * Enum used for chess King piece status.
     */
    public enum ChessKingState
    {
        /**
         * Value used to indicate the King piece is safe.
         */
        KING_SAFE,

        /**
         * Value used to indicate the King piece is threaten by at least one opponent piece.
         */
        KING_THREATEN,
    }

    /**
     * Class used for chess piece positions.
     * It simply contains two public fields for X and Y coordinates. 
     */
    public class ChessPosition
    {
        /**
         * Integer value for X-axis position (from 0 to 7).
         */
        public int x;

        /**
         * Integer value for Y-axis position (from 0 to 7).
         */
        public int y;

        /**
         * Creates a default position object.
         * The default position is -1/-1.
         */
        public ChessPosition()
        {
            this.x = -1;
            this.y = -1;
        }

        /**
         * Creates a position using the given parameters
         * @param x0 X-axis value for the new position.
         * @param y0 Y-axis value for the new position.
         */
        public ChessPosition(int x0, int y0)
        {
            this.x = x0;
            this.y = y0;
        }

        
        /**
         * Redefinition of the Object class equals method.
         * It is used to compare easily two positions.
         * @param pos the position to compare
         * @return true if the two positions are equals, else false.
         */
        public boolean equals(ChessPosition pos)
        {
            return ( (this.x==pos.x) && (this.y==pos.y) );
        }
    }
    
    
    //--------------------------------------------------------------------
    // CONSTANTS
    //--------------------------------------------------------------------

    /**
     * Horizontal dimension of the chess board (in number of cells).
     */
    public final static int BOARD_WIDTH  = 8;

    /**
     * Vertical dimension of the chess board (in number of cells).
     */
    public final static int BOARD_HEIGHT = 8;

    /**
     * Vertical position of the white king row on the board (in cell unit, starts at 0).
     */
    public final static int BOARD_POS_Y_WHITE_PIECES = 7;

    /**
     * Vertical position of the white pawn row on the board (in cell unit, starts at 0).
     */
    public final static int BOARD_POS_Y_WHITE_PAWNS  = 6;

    /**
     * Vertical position of the black pawn row on the board (in cell unit, starts at 0).
     */
    public final static int BOARD_POS_Y_BLACK_PAWNS  = 1;

    /**
     * Vertical position of the black king row on the board (in cell unit, starts at 0).
     */
    public final static int BOARD_POS_Y_BLACK_PIECES = 0;
    // Init positions (X)

    /**
     * Horizontal position of the ROOK #1 on the board (in cell unit, starts at 0).
     */
    public final static int BOARD_POS_X_QUEENSIDE_ROOK   = 0;

    /**
     * Horizontal position of the KNIGHT #1 on the board (in cell unit, starts at 0).
     */
    public final static int BOARD_POS_X_QUEENSIDE_KNIGHT = 1;

    /**
     * Horizontal position of the BISHOP #1 on the board (in cell unit, starts at 0).
     */
    public final static int BOARD_POS_X_QUEENSIDE_BISHOP = 2;

    /**
     * Horizontal position of the QUEEN on the board (in cell unit, starts at 0).
     */
    public final static int BOARD_POS_X_QUEEN   = 3;

    /**
     * Horizontal position of the KING on the board (in cell unit, starts at 0).
     */
    public final static int BOARD_POS_X_KING    = 4;

    /**
     * Horizontal position of the BISHOP #2 on the board (in cell unit, starts at 0).
     */
    public final static int BOARD_POS_X_KINGSIDE_BISHOP = 5;

    /**
     * Horizontal position of the KNIGHT #2 on the board (in cell unit, starts at 0).
     */
    public final static int BOARD_POS_X_KINGSIDE_KNIGHT = 6;

    /**
     * Horizontal position of the ROOK #2 on the board (in cell unit, starts at 0).
     */
    public final static int BOARD_POS_X_KINGSIDE_ROOK   = 7;
    
    
    //--------------------------------------------------------------------
    // INTERFACE METHODS
    //--------------------------------------------------------------------

    /**
     * Creates a new chess game.
     * This methods must set all the piece positions on the board according to interface constants.
     */
    public void reinit();

    /**
     * Gets the type of a piece at the given position on the board.
     * @param p x/y position on the board where we want to get the piece type.
     * @return the piece type at the requested position. Values are taken from ChessType.
     * @throws EmptyCellException when the targeted cell contains no piece.
     * @throws OutOfBoardException when the position is outside the chess board.
     */
    public ChessType getPieceType(ChessPosition p) throws EmptyCellException, OutOfBoardException;

    /**
     * Gets the color of a piece at the given position on the board.
     * @param p x/y position on the board where we want to get the piece color.
     * @return the piece color at the requested position. Values are taken from ChessColor.
     * @throws EmptyCellException when the targeted cell contains no piece.
     * @throws OutOfBoardException when the position is outside the chess board.
     */
    public ChessColor getPieceColor(ChessPosition p) throws EmptyCellException, OutOfBoardException;

    /**
     * Count the remaining pieces on the board for a specific color.
     * @param color the requested color of the pieces to count.
     * @return count of remaining pieces.
     */
    public int getNbRemainingPieces(ChessColor color);

    /**
     * Gets the list of possible moves for a piece on the board.
     * This is up to this method to ensure the moves are possible or not for each piece on the board.
     * This list may be empty because either there is no possible move or because the requested position is empty or outside the board.
     * @param p requested piece position.
     * @return the list of possible moves for the requested piece position. The returned list reference must not be null.
     */
    public List<ChessPosition> getPieceMoves(ChessPosition p);

    /**
     * Moves a piece from a position to another.
     * This method does not check if the move is possible or not.
     * We assume the destinaton position has been taken from getPieceMoves returned list, so it is always valid.
     * @param p0 source position on the board.
     * @param p1 destination position on the board.
     */
    public void movePiece(ChessPosition p0, ChessPosition p1);

    /**
     * Gets the king status for the requested color.
     * It is not up to this method to check if the game is over.
     * This method just returns if the king is safe or not, depending on opponent piece positions.
     * @param color the requested king color.
     * @return the king status, either ChessKingState.KING_SAFE or ChessKingState.KING_SAFE.
     */
    public ChessKingState getKingState(ChessColor color);

    /**
     * Get the list of removed pieces from the beginning of the game.
     * @param color color of the removed pieces
     * @return list of removed chess piece types.
     * The list is empty if no piece has been removed yet.
     */
    public List<ChessType> getRemovedPieces(ChessColor color);
    
    /**
     * Cancel the very last move, setting the board with the state it was previously.
     * Once this method is called, any other call will do nothing until we have moved one piece again.
     * This method is designed to cancel the very last move, nothing less, nothing more.
     * In other words, the implementation class must only keep one move in memory.
     * @return a boolean value to indicate if a move has really been undone or not (very 1st move case).
     */
    public boolean undoLastMove();
    
    /**
     * Returns the sum of each player turn durations.
     * Each time a player turn begins, the time measurment begins too.
     * When the player has moved its piece, the measurment stops for this player and starts again for the other player, and so on.
     * @param color The color of the player from whom we want to get the current duration.
     * @param isPlaying Indicates if the player color is the one currently playing.
     * @return total player turn duration in milliseconds.
     */
    public long getPlayerDuration(ChessColor color, boolean isPlaying);
            
    
    //--------------------------------------------------------------------
    // END OF INTERFACE
    //--------------------------------------------------------------------
}
