package fr.rphstudio.chess.interf;

/**
 * Exception used when an action is performed with a position where there is no chess piece.
 * It extends the ChessException class.
 * @author Romuald GRIGNON
 */
public class EmptyCellException extends ChessException
{
    public EmptyCellException()
    {
        super();
    }
    public EmptyCellException(String message)
    {
        super(message);
    }
    public EmptyCellException(Throwable cause)
    {
        super(cause);
    }
    public EmptyCellException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
    
    
