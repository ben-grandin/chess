package fr.rphstudio.chess.interf;

/**
 * Exception used when an action is performed with a position outside of the chess board.
 * It extends the ChessException class.
 * @author Romuald GRIGNON
 */
public class OutOfBoardException extends ChessException
{
    public OutOfBoardException()
    {
        super();
    }
    public OutOfBoardException(String message)
    {
        super(message);
    }
    public OutOfBoardException(Throwable cause)
    {
        super(cause);
    }
    public OutOfBoardException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
    
    
