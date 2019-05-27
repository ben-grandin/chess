//--------------------------------------------------------------------
// IMPORTS
//--------------------------------------------------------------------
package fr.rphstudio.chess.launcher;

import fr.rphstudio.chess.game.ChessModel;
import fr.rphstudio.chess.interf.ChessException;
import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.IChess.ChessColor;
import fr.rphstudio.chess.interf.IChess.ChessKingState;
import fr.rphstudio.chess.interf.IChess.ChessPosition;
import fr.rphstudio.chess.interf.IChess.ChessType;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


//--------------------------------------------------------------------
// CLASS
//--------------------------------------------------------------------
/**
 * Chess game controller. This class will handle the mouse, keyboard, screen in order to sequence the game steps.
 * This class will contain a field of type 'IChess' that is the interface between the user and the data.
 * In this Object-Oriented Programming course, this is up to the students to implement this 'IChess' interface correctly
 * to have a functional chess game application.
 * /!\ DO NOT MODIFY THIS CLASS /!\ except the IChess field that must be filled with your own 'IChess' interface implementation class.
 * @author Romuald GRIGNON
 */
class ChessGameController extends BasicGameState
{
    //------------------------------------------------
    // PRIVATE STATE MACHINE CONSTANTS
    //------------------------------------------------
    
    /**
     * Game state value for chess piece selection.
     */
    private final static int STATE_SELECT = 0;
    
    /**
     * Game state value for chess piece move validation.
     */
    private final static int STATE_PLAY   = 1;
    
    /**
     * Game state value when the game is ended.
     */
    private final static int STATE_END    = 2;
    
    /**
     * Game state value for game reinit request.
     */
    private final static int STATE_REINIT = 3;
    
    
    //------------------------------------------------
    // PRIVATE GAME LIBRARY PROPERTIES
    //------------------------------------------------
    
    /**
     * Game container containing a lot of system information.
     */
    private GameContainer  container;
    
    /**
     * Application version string to be displayed on screen.
     */
    private String         version;
    
    
    //------------------------------------------------
    // PRIVATE GAME INTERFACE
    //------------------------------------------------
    
    /**
     * Interface implementation of the chess game.
     */
    private IChess         board;
    
    
    //------------------------------------------------
    // PRIVATE GFX PROPERTIES
    //------------------------------------------------
    
    /**
     * Reference screen X-axis position for the chess board.
     */
    private int            refX;
    
    /**
     * Reference screen Y-axis position for the chess board.
     */
    private int            refY;    
    
    /**
     * X-Axis offset used to adjust gfx element display.
     */
    private int            offX;    
    
    /**
     * X-Axis offset used to adjust gfx element display.
     */
    private int            offY;    
    
    /**
     * Y-Axis offset used to adjust board and pieces
     */
    private int            offBoardY;    
    
    /**
     * Size of a chess board cell (X-axis).
     */
    private int            offCellX; 
    
    /**
     * Size of a chess board cell (Y-axis).
     */
    private int            offCellY; 
    
    /**
     * Color used to show the selected piece you want to move.
     */
    private Color          selectedColor;
    
    /**
     * Color used to highlight some cells, either to show the cell below your mouse pointer, or to show possible moves.
     */
    private Color          highlightColor;
    
    /**
     * Color used to show the possible move cells.
     */
    private Color          possibleColor;
    
    /**
     * Graphic element used for cell selection.
     */
    private Image          selectGfx;   
    
    /**
     * Graphic element used for cell highlight.
     */
    private Image          highlightGfx;   
    
    /**
     * Graphic element for the chess board.
     */
    private Image          boardGfx;
    
    /**
     * Graphic element for the background.
     */
    private Image          backGfx;
    
    /**
     * Sprite sheet of all chess pieces.
     */
    private SpriteSheet    pieceGfx;
    
    /**
     * Graphic element for the enabled undo button.
     */
    private Image          undoGfx;
    
    /**
     * Graphic element for the restart game button.
     */
    private Image          restartGfx;

    /**
     * Graphic element for the quit game button.
     */
    private Image          quitGfx;
    
    /**
     * Chess Font manager object, used to display sprite-font 
     */
    private ChessFontManager chessFont;
    
    
    //------------------------------------------------
    // PRIVATE GAME CONTROL PROPERTIES
    //------------------------------------------------
    
    /**
     * Current game state value.
     */
    private int                 state;
    
    /**
     * Boolean value indicating if we want to undo the last move or not. 
     */
    private boolean             isUndoRequest;
    
    /**
     * String showing the status of the white king.
     */
    private String              kingStateW;
    
    /**
     * String showing the status of the black king.
     */
    private String              kingStateB;
    
    /**
     * Position of the selected piece to move.
     */
    private ChessPosition       selectPos;
    
    /**
     * List of possible cells for a selected piece, depending on the selecte dpiece moves, its position, and the king status.
     */
    private List<ChessPosition> selectChoice;
    
    /**
     * List of all possible pieces that can move, depending on their kind of moves, their positions, and the king status.
     */
    private List<ChessPosition> possiblePos;
    
    /**
     * Current side color. Alterning betwen white and black. First to play is white.
     */
    private ChessColor          currentColor;
    
    
    //------------------------------------------------
    // PRIVATE METHODS
    //------------------------------------------------
    
    /**
     * Store the application string version in a field.
     * The version is taken from a text file of the project.
     */
    private void getVersion()
    {
        // Get display version
        BufferedReader br = null;
        try
        {
            this.version = "";
            br = new BufferedReader(new FileReader("info/version.txt"));
            String line;
            line = br.readLine();
            while(line != null)
            {
                this.version = this.version + line + "\n";
                line = br.readLine();
            }
            if (br != null)
            {
                br.close();
            }
        }
        catch (IOException e)
        {
            throw new Error(e);
        }
        finally
        {
            try
            {
                if (br != null)
                {
                    br.close();
                }
            }
            catch (IOException ex)
            {
                throw new Error(ex);
            }
        }
    }
    
    /**
     * Method used to convert a perspective position to a real position
     * @param perspPos perspective position
     * @return real position
     */
    private Vector2f getRealPosition(Vector2f perspPos)
    {
        // Init result
        Vector2f realPos = new Vector2f(0,0);
        
        // Init constants
        float W1 = 960;
        float W2 = 640;
        float H  = 800;
        float A  = (W1-W2)/2;
        float B  = (W1+W2)/2;
        float KA = ((W1*perspPos.y) + (W2*(H-perspPos.y))) / (W1*H);
        float KB = (A*(H-perspPos.y))/H;
        
        // Compute perspective position
        realPos.y = perspPos.y;
        realPos.x = (perspPos.x-KB)/KA;
        
        // Return result
        return realPos;
    }
    
    /**
     * Method used to convert a real position to a perspective position
     * @param realPos real position
     * @return perspective position
     */
    private Vector2f getPerspectivePosition(Vector2f realPos)
    {
        // Init result
        Vector2f persPos = new Vector2f(0,0);
        
        // Init constants
        float W1 = 960;
        float W2 = 640;
        float H  = 800;
        float A  = (W1-W2)/2;
        float B  = (W1+W2)/2;
        float KA = ((W1*realPos.y) + (W2*(H-realPos.y))) / (W1*H);
        float KB = (A*(H-realPos.y))/H;
        
        // Compute perspective position
        persPos.y = realPos.y;
        persPos.x = (realPos.x*KA)+KB;
        
        // Return result
        return persPos;
    }

    /**
     * Draw a selection/highlighting image with specific parameters
     * @param g graphic object used to display information on screen.
     * @param img image to draw.
     * @param clr color used as a filter for the image.
     * @param cellPos chess board position where the image will be displayed.
     */
    private void drawImage(Graphics g, Image img, Color clr, ChessPosition cellPos)
    {
        Vector2f realPos = new Vector2f((cellPos.x+0.5f)*this.offCellX, (cellPos.y+0.5f)*this.offCellY);
        Vector2f persPos = this.getPerspectivePosition(realPos);
        float    dx      = img.getWidth()/2;
        float    dy      = img.getHeight()/2;
        g.drawImage( img, this.refX+this.offX+persPos.x-dx, this.refY+persPos.y-dy+this.offBoardY, clr );
    }
    
    /**
     * Draw a cell highlight image
     * @param g graphic object used to display information on screen.
     * @param cellPos chess board position where the image will be displayed.
     */
    private void drawHighlight(Graphics g, ChessPosition cellPos)
    {
        this.drawImage(g, this.highlightGfx.getScaledCopy(0.5f), this.highlightColor, cellPos);
    }

    /**
     * Draw a possible move image
     * @param g graphic object used to display information on screen.
     * @param cellPos chess board position where the image will be displayed.
     */
    private void drawPossible(Graphics g, ChessPosition cellPos)
    {
        this.drawImage(g, this.selectGfx.getScaledCopy(0.45f), this.possibleColor, cellPos);
    }
    
    /**
     * Draw a selected cell image
     * @param g graphic object used to display information on screen.
     * @param cellPos chess board position where the image will be displayed.
     */
    private void drawSelect(Graphics g, ChessPosition cellPos)
    {
        this.drawImage(g, this.selectGfx.getScaledCopy(0.45f), this.selectedColor, cellPos);
    }
    
    /**
     * This method reinits the game to the beginning. It allows to erase the current game in progress and to start a new one.
     */
    private void reinit()
    {
        // Init game state
        this.state         = STATE_SELECT;
        this.currentColor  = ChessColor.CLR_WHITE;
        this.kingStateW    = "";
        this.kingStateB    = "";
        // Init selection positions
        this.selectPos     = null;
        this.selectChoice  = null;
        this.possiblePos   = new ArrayList<ChessPosition>();
        this.isUndoRequest = false;
        // font manager
        this.chessFont     = new ChessFontManager("./sprites/fontchess.png", 40, 40 );
        
        // Reinit the board and start the game
        this.board.reinit();
    }
    
    /**
     * Static method that converts screen mouse position into a chess board cell position
     * @param mouseX screen X-axis mouse position.
     * @param mouseY screen Y-axis mouse position.
     * @return the chess board cell position object.
     */
    private ChessPosition getCellPosition(int mouseX, int mouseY)
    {   
        // Convert mouse coords into the HD world (1920x1080)
        mouseX = (int)(mouseX/MainLauncher.RATIO_XY);
        mouseY = (int)(mouseY/MainLauncher.RATIO_XY);
        mouseX -= MainLauncher.OFFSET_X;
        mouseY -= MainLauncher.OFFSET_Y;
        // Set mouse position relative to the board position  
        mouseX -= this.refX;
        mouseY -= this.refY+this.offBoardY;
        if(mouseX < 0)
        {
            mouseX = -10000;
        }
        if(mouseY < 0)
        {
            mouseY = -10000;
        }
        // Transform perspective position to real position
        Vector2f persPos = new Vector2f(mouseX,mouseY);
        Vector2f realPos = getRealPosition(persPos);
        if(realPos.x < 0)
        {
            realPos.x -= 10000;
        }
        // Transform mouse position into cell position
        mouseX = (int)(realPos.x / this.offCellX);
        mouseY = (int)(realPos.y / this.offCellY);
        return new ChessPosition(mouseX,mouseY);
    }
    
    /**
     * This method checks all the fields in order to know what is the current game status and what needs to be displayed or not.
     */
    private void handleStateGames()
    {
        // Init possible moves
        this.possiblePos.clear();
                    
        // Get mouse position
        int mx = this.container.getInput().getMouseX();
        int my = this.container.getInput().getMouseY();
        ChessPosition cellPos = this.getCellPosition(mx, my);
        
        // Perform undo move
        if(this.isUndoRequest)
        {
            if( this.board.undoLastMove() )
            {
                // Back to previous state in order to REplay
                this.currentColor = this.currentColor==ChessColor.CLR_WHITE ? ChessColor.CLR_BLACK : ChessColor.CLR_WHITE;
                this.selectChoice = null;
                this.selectPos    = null;
                this.state        = STATE_SELECT;
            }
            this.isUndoRequest = false;
        }

        // Check if mouse button has been clicked or not
        if( container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON) )
        {
            // If we are in the selection phase, check if the current cell is a valid color piece
            switch(this.state)
            {
                // CURRENT PLAYER CHOOSES A MOVE TO PLAY WITH THIS PIECE                // CURRENT PLAYER CHOOSES A MOVE TO PLAY WITH THIS PIECE
                case STATE_PLAY:
                    // Check if the select position is in the select choice
                    boolean found = false;
                    for(ChessPosition p : this.selectChoice)
                    {
                        if( (p.x == cellPos.x) && (p.y == cellPos.y) )
                        {
                            found = true;
                            break;
                        }
                    }
                    // If the position has not been found, back to previous state (keep the same color)
                    if(!found)
                    {
                        this.selectChoice = null;
                        this.selectPos    = null;
                        this.state = STATE_SELECT;
                    }
                    else
                    {
                        // If the position is in the selection, "play it"
                        // We have to move the piece to its new location
                        this.board.movePiece(this.selectPos, cellPos);
                        // Go to next state (change color)
                        this.selectChoice = null;
                        this.selectPos    = null;
                        this.state        = STATE_SELECT;
                        this.currentColor = this.currentColor==ChessColor.CLR_WHITE ? ChessColor.CLR_BLACK : ChessColor.CLR_WHITE;
                        // Stop execution
                        break;
                    }
                    // No break here in order to reexecute SELECT state just after the PLAY state if new selection is the same color
                
                // CURRENT PLAYER SELECTS A PIECE 
                case STATE_SELECT : 
                    // Get piece and check its color is white
                    this.selectPos = cellPos;
                    try
                    {
                        if( this.board.getPieceColor(this.selectPos) == this.currentColor )
                        {
                            // Add all possible moves to be displayed
                            this.selectChoice = this.board.getPieceMoves(this.selectPos);
                        }
                    }
                    catch(ChessException chExc)
                    {
                        
                    }
                    // Go to next state, if needed
                    if(this.selectChoice != null)
                    {
                        if(this.selectChoice.size() > 0)
                        {
                            this.state = STATE_PLAY;
                        }
                        else
                        {
                            this.selectChoice = null;
                            this.selectPos    = null;
                        }
                    }
                    break;
                    
                // DEFAULT
                default :
                    break;
            }    
            
            // If we have clicked on the undo button, set the flag
            if( (cellPos.x==0) && (cellPos.y==IChess.BOARD_HEIGHT) )
            {
                this.isUndoRequest = true;
            }
            
            // If we have clicked on the Reinit game button,
            if( (cellPos.x==1) && (cellPos.y==IChess.BOARD_HEIGHT) )
            {
                this.state = STATE_REINIT;
            }
            
            // If we have clicked on the quit game button,
            if( (cellPos.x==2) && (cellPos.y==IChess.BOARD_HEIGHT) )
            {
                this.container.exit();
                return;
            }
        }
        
        // Display possible moves (if we have not clicked AND we are in the selection state)
        switch(this.state)
        {
            // CURRENT PLAYER SELECTS A PIECE 
            case STATE_SELECT:
                // Browse all current color pieces and get their possible moves
                for(int x=0;x<IChess.BOARD_WIDTH;x++)
                {
                    for(int y=0;y<IChess.BOARD_WIDTH;y++)
                    {
                        // Check if there is a piece and it is the same color than current one
                        ChessPosition tmpPos = new ChessPosition(x,y);
                        try
                        {
                            if( this.board.getPieceColor(tmpPos) == this.currentColor)
                            {
                                if( this.board.getPieceMoves(tmpPos).size() > 0 )
                                {
                                    this.possiblePos.add(tmpPos);
                                }
                            }
                        }
                        catch(ChessException chex)
                        {
                            
                        }
                    }    
                }
                // Check if there is no more possible moves
                if(this.possiblePos.size() == 0)
                {
                    this.state = STATE_END;
                }
                break;
                
            // DEFAULT
            default :
                break;
        }
        
        // Update king state message
        this.kingStateW = "SAFE";
        this.kingStateB = "SAFE";
        if(this.state == STATE_END)
        {
            if( this.board.getKingState(this.currentColor) == ChessKingState.KING_THREATEN )
            {
                if(this.currentColor == ChessColor.CLR_WHITE)
                {
                    this.kingStateW = "CHECKMATED !";
                }
                else
                {
                    this.kingStateB = "CHECKMATED !";
                }
            }
            else
            {
                if(this.currentColor == ChessColor.CLR_WHITE)
                {
                    this.kingStateW = "STALEMATED !";
                }
                else
                {
                    this.kingStateB = "STALEMATED !";
                }
            }
        }
        else
        {
            if( this.board.getKingState(ChessColor.CLR_WHITE) == ChessKingState.KING_THREATEN )
            {
                this.kingStateW = "IN CHECK !";
            }
            if( this.board.getKingState(ChessColor.CLR_BLACK) == ChessKingState.KING_THREATEN )
            {
                this.kingStateB = "IN CHECK !";
            }
        }
        
        // REINIT Game
        if(this.state == STATE_REINIT)
        {
            this.reinit();
        }
    }
    
    /**
     * This methods convert an integer value (in milliseconds) to a formatted string containing days/hours/minutes/seconds (homemade ^^).
     * @param duration number of milliseconds of the duration to display
     * @return the string containing the correct display format for this duration.
     */
    private String getFormattedTime(long duration)
    {
        String res   = "";
        long days    = 0;
        long hours   = 0;
        long minutes = 0;
        long seconds = 0;
        boolean isDisplayed = false;
        // Get duration in decimaml of seconds
        duration = duration/100;
        // Get seconds
        seconds   = duration%(600);
        duration -= seconds;
        duration /= 600;
        // Get minutes
        minutes   = duration%60;
        duration -= minutes;
        duration /= 60;
        // Get hours
        hours     = duration%24;
        duration -= hours;
        duration /= 24;
        // Get days
        days      = duration;
        // Display days
        isDisplayed |= days > 0;
        if(isDisplayed)
        {
            res += Long.toString(days)+" days ";
        }
        // display hours
        isDisplayed |= hours > 0;
        if(isDisplayed)
        {
            res += Long.toString(hours)+" hrs. ";
        }
        // display minutes
        isDisplayed |= minutes > 0;
        if(isDisplayed)
        {
            res += Long.toString(minutes)+" min. ";
        }
        // display seconds
        isDisplayed |= seconds > 0;
        if(isDisplayed)
        {
            res += Float.toString(seconds/10.0f)+" sec. ";
        }
        // return result string 
        return res;
    }
    
    /**
     * Display all information on player status. The information are the followings :
     * color, number of remaining pieces, pictures of removed pieces, king status, total play duration. 
     * @param g graphic object used to display information on screen.
     * @param color player color enum value.
     * @param playerColor String containing the player color value. 
     * @param nbRemain number of remaining pieces.
     * @param kingState king status (safe or not).
     * @param duration total play duration.
     * @param offX display offset on X-axis
     * @param offY display offset on Y-axis
     * @param displayColor color to be used to display strings on screen.
     */
    private void displayPlayerInfo(Graphics g, ChessColor color, String playerColor, int nbRemain, String kingState, long duration, int offX, int offY, Color displayColor)
    {
        // Display the number of remaining pieces        
        this.chessFont.displayString(g, playerColor+" remaining pieces = "+Integer.toString(nbRemain), offX, offY, displayColor, 1.0f );
        // Display the state of each king
        this.chessFont.displayString(g, playerColor+" KING is "+kingState, offX, offY+35, displayColor, 1.0f );
        // Display the total duration
        this.chessFont.displayString(g, playerColor+" time = "+this.getFormattedTime(duration), offX, offY+70, displayColor, 0.5f );
        // Display the removed pieces for each color
        int loopP = 1;
        int loopO = 1;
        for(ChessType t : this.board.getRemovedPieces(color))
        {   
            int loop   = 0;
            int deltaY = 0; 
            if(t == ChessType.TYP_PAWN)
            {
                loop   =  loopP++;
                deltaY = (int)(this.offCellY*1.1);
            }
            else
            {
                loop = loopO++;
            }
            g.drawImage(this.pieceGfx.getSprite(t.ordinal(), color.ordinal()).getScaledCopy(0.5f), offX+600-loop*(this.offCellX/1.5f), offY+70+deltaY);
        }
    }
    
    
    //------------------------------------------------
    // CONSTRUCTOR
    //------------------------------------------------
    
    /**
     * Constructor of the chess game controller. It does nothing special here.
     */
    public ChessGameController()
    {
        
    }
    
    
    //------------------------------------------------
    // INTERFACE INIT METHOD
    //------------------------------------------------
    
    /**
     * This methods is called when the game controller is created. This is the place where we do any initialisation.
     * @param container the game container containing a lot of system information.
     * @param sbGame StateBasedGame object. Not used for our chess application. 
     * @throws SlickException 
     */
    @Override
    public void init(GameContainer container, StateBasedGame sbGame) throws SlickException
    {
        // Init fields
        this.container = container;
        container.setClearEachFrame(true);

        // Retrieve verison string from config text file
        this.getVersion();

        // Create Gfx elements
        this.offCellX       = 120;
        this.offCellY       = 100;
        this.boardGfx       = new Image("./sprites/board2.png");
        this.backGfx        = new Image("./sprites/backgnd.jpg");
        this.refX           = (int)((MainLauncher.WIDTH -(this.boardGfx.getWidth() )) * 0.75 );
        this.refY           = (int)((MainLauncher.HEIGHT-(this.boardGfx.getHeight())) * 0.50 );    
        this.offX           = 0;
        this.offY           = 68;
        this.offBoardY      = 50;
        this.selectedColor  = new Color(1.0f,0.25f,0.25f,0.8f);
        this.highlightColor = new Color(1f,1f,0.25f,0.8f);
        this.possibleColor  = new Color(1f,1f,0.25f,0.8f);
        this.selectGfx      = new Image("./sprites/selection2.png");
        this.highlightGfx   = new Image("./sprites/highlight2.png");
        this.pieceGfx       = new SpriteSheet("./sprites/chess_sprites2.png",300,500);
        this.undoGfx        = new Image("./sprites/undo.png");
        this.restartGfx     = new Image("./sprites/reinit.png");
        this.quitGfx        = new Image("./sprites/quit.png");
    
        
        /* ***************************************************************************** 
         * /!\ You need to instanciate your "IChess" interface implementation class /!\
         * /!\ and store its reference into the "board" private field.              /!\
         * /!\ In order to do this you MUST use the singleton design pattern        /!\
         * /!\ to have only one possible instance of game.                          /!\
         * *****************************************************************************/
                                                 /* */
        this.board = ChessModel.getInstance();   /* */
                                                 /* */
        // *****************************************************************************                                
        // *****************************************************************************                                
        
        
        // Check the mono instance of IChess implementation
        for(int i=0;i<100;i++)
        {
            IChess ref = ChessModel.getInstance();
            if(ref != this.board)
            {
                throw new Error("Bad singleton implementation !");
            }
        }
        
        // Start a new game
        this.reinit();
    }
    
    
    //------------------------------------------------
    // INTERFACE RENDER METHOD
    //------------------------------------------------
    
    /**
     * Method called for each frame, used to display the graphical elements.
     * @param container the game container containing a lot of system information.
     * @param game StateBasedGame object. Not used for our chess application. 
     * @param g the graphical object used to access the graphic work area.
     * @throws SlickException Exception from the Slick2D library.
     */
    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
    {
        // Fit Screen
        MainLauncher.fitScreen(container, g);
        
        // display full area rectangle
        g.setColor(Color.yellow);
        g.drawRect(1, 1, MainLauncher.WIDTH-2, MainLauncher.HEIGHT-2);
        
        // display the background
        g.drawImage(this.backGfx, 0, 0, new Color(255,255,255,96));
        
        // display the background
        g.drawImage(this.backGfx, 0, 0, new Color(255,255,255,96));
        
        // Display the board
        g.drawImage(this.boardGfx, this.refX, this.refY+this.offBoardY);
        
        // Highlight the cell under the mouse pointer
        int mx = this.container.getInput().getMouseX();
        int my = this.container.getInput().getMouseY();
        ChessPosition cellPos = this.getCellPosition(mx, my);
        if(cellPos.x<0 || cellPos.x>=IChess.BOARD_WIDTH || cellPos.y<0 || cellPos.y>IChess.BOARD_HEIGHT)
        {
            cellPos.x = 100;
            cellPos.y = 100;
        }
        if(cellPos.y==IChess.BOARD_HEIGHT)
        {
            if( (cellPos.x < 0) || (cellPos.x > 2) )
            {
                cellPos.x = 100;
                cellPos.y = 100;
            }
        }
        // Draw mouse highlight (can be out of screen)
        this.drawHighlight(g, cellPos);
        
        // Display possible moves if any
        for(ChessPosition p : this.possiblePos )
        {
            //this.drawHighlight(g, p);
            this.drawPossible(g, p);
        }    
        
        // display selection if any
        if(this.selectChoice != null)
        {
            for(ChessPosition p : this.selectChoice )
            {
                //this.drawHighlight(g, p);
                this.drawPossible(g, p);
            }
            this.drawSelect(g, this.selectPos);
        }
        
        // Display the pieces
        ChessPosition locPos = new ChessPosition();
        for(int x=0;x<IChess.BOARD_WIDTH;x++)
        {
            for(int y=0;y<IChess.BOARD_HEIGHT;y++)
            {
                locPos.x = x;
                locPos.y = y;
                try
                {
                    ChessType type   = this.board.getPieceType(locPos);
                    ChessColor color = this.board.getPieceColor(locPos);
                    
                    Vector2f realPos  = new Vector2f((x+0.5f)*this.offCellX, (y+0.5f)*this.offCellY);
                    Vector2f persPos  = this.getPerspectivePosition(realPos);
                    Image curPieceGfx = this.pieceGfx.getSprite(type.ordinal(), color.ordinal()).getScaledCopy(0.5f);
                    float    dx       = curPieceGfx.getWidth()/2;
                    float    dy       = curPieceGfx.getHeight();
                    g.drawImage(curPieceGfx, this.refX+this.offX+persPos.x-dx, this.refY+this.offY+this.offBoardY+persPos.y-dy);
                }
                catch(ChessException cex)
                {
                    
                }
            }
        }
        
        // Display player info 
        int nbW    = this.board.getNbRemainingPieces(ChessColor.CLR_WHITE);
        int nbK    = this.board.getNbRemainingPieces(ChessColor.CLR_BLACK);
        boolean isPlayingW = false;
        boolean isPlayingK = false;
        if(this.state != STATE_END)
        {
            isPlayingW = this.currentColor==ChessColor.CLR_WHITE;
            isPlayingK = this.currentColor==ChessColor.CLR_BLACK;
        }
        long timeW = this.board.getPlayerDuration(ChessColor.CLR_WHITE, isPlayingW);
        long timeK = this.board.getPlayerDuration(ChessColor.CLR_BLACK, isPlayingK);
        displayPlayerInfo(g, ChessColor.CLR_WHITE, "WHITE", nbW, this.kingStateW, timeW, this.refX-650, this.refY+(int)((IChess.BOARD_HEIGHT-4)*this.offCellY), new Color(213,207,174) );
        displayPlayerInfo(g, ChessColor.CLR_BLACK, "BLACK", nbK, this.kingStateB, timeK, this.refX-650, this.refY+(int)(0*this.offCellY), new Color(134,85,85));
        
        // Prepare button positions
        Vector2f realPos = new Vector2f(0,0);
        Vector2f persPos = null;
        // Display undo button
        realPos = new Vector2f(0.5f*this.offCellX, (IChess.BOARD_HEIGHT+0.5f)*this.offCellY );
        persPos = this.getPerspectivePosition(realPos);
        g.drawImage( this.undoGfx, this.refX+persPos.x-this.undoGfx.getWidth()/2, this.refY+persPos.y-this.undoGfx.getHeight()/11 );
        // Display restart button
        realPos = new Vector2f(1.5f*this.offCellX, (IChess.BOARD_HEIGHT+0.5f)*this.offCellY );
        persPos = this.getPerspectivePosition(realPos);
        g.drawImage( this.restartGfx, this.refX+persPos.x-this.undoGfx.getWidth()/2, this.refY+persPos.y-this.undoGfx.getHeight()/11 );
        // Display quit button
        realPos = new Vector2f(2.5f*this.offCellX, (IChess.BOARD_HEIGHT+0.5f)*this.offCellY );
        persPos = this.getPerspectivePosition(realPos);
        g.drawImage( this.quitGfx, this.refX+persPos.x-this.undoGfx.getWidth()/2, this.refY+persPos.y-this.undoGfx.getHeight()/11 );
        
        // Display version
        g.drawString(this.version, 10, MainLauncher.HEIGHT-100);
    }

    
    //------------------------------------------------
    // INTERFACE UPDATE METHOD
    //------------------------------------------------
    
    /**
     * This method is called for each frame, and is used to update all the data needed in our application.
     * @param container the game container containing a lot of system information.
     * @param game StateBasedGame object. Not used for our chess application. 
     * @param delta number of milliseconds from the last time this method was called (duration between two consecutive calls).
     * @throws SlickException Exception from the Slick2D library.
     */
    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
    {
        // Clean grahpics
        this.container.getGraphics().clear();
    
        // Chess game state management
        this.handleStateGames();
    }
    
    
    //------------------------------------------------
    // INTERFACE KEYBOARD METHODS
    //------------------------------------------------
    
    /**
     * Method called every time a key is pressed on the keyboard.
     * @param key key value.
     * @param c character value.
     */
    @Override
    public void keyPressed(int key, char c)
    {   
        // Check for application exit
        switch(key)
        {
            case Input.KEY_ESCAPE:
                // Go to pause screen
                this.container.exit();
            case Input.KEY_ENTER:
                //if(this.state == STATE_END)
                {
                    this.state = STATE_REINIT;
                }
                break;
            case Input.KEY_BACK:
                // Undo last move
                this.isUndoRequest = true;
                break;
                
            default:
                break;
        }
    }    
    
    
    //------------------------------------------------
    // INTERFACE STATE ID METHOD
    //------------------------------------------------
    
    /**
     * Method called to retrieve the execution controller ID. Not used by our application here.
     * @return the integer value of the current game controller ID.
     */
    @Override
    public int getID()
    {
          return 42; // When you don't know the answer, try this value ;) 
    }
    
    
    //------------------------------------------------
    // END OF CLASS
    //------------------------------------------------
}