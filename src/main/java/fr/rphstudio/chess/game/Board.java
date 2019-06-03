/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.rphstudio.chess.game;
import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.IMove;
import java.util.List;


public class Board {
	private Piece[][] grid;

	public Board()
	{
		this.grid = new Piece[8][8];
		generateGrid();

	}
	public Piece getPiece(IChess.ChessPosition z){
		if( z.x > -1 && z.x < 8 &&  z.y > -1 && z.y < 8 ){
			return this.grid[z.x][z.y];
		}
		else return null;
	}

	public void setPiece(IChess.ChessPosition z, Piece piece) {
		this.grid[z.x][z.y] = piece;
	}


// Faire des méthodes pour placer les pieces a leur place d'origine
        public int getNbRemainingPieces(IChess.ChessColor c){
            int count = 0;
           for (int y = 0; y <= 7 ; y++) {
            for (int x = 0; x <= 7 ; x++) {
                if( this.grid[x][y]!=  null  ){
                    if( grid[x][y].getColor() == c ){
                        count +=1;
                    }
                }
            }
           }
           return count;
        }
        
	private void generateGrid(){

		IChess.ChessColor color = null;
		IChess.ChessType type = null;
		IMove move = null;

		for (int y = 0; y <= 7 ; y++) {
			for (int x = 0; x <= 7 ; x++) {

				color = null;
				type = null;
				move = null;

				if(y == 0 || y == 1 )  color = IChess.ChessColor.CLR_BLACK;
				else if ( y == 6 || y == 7) color = IChess.ChessColor.CLR_WHITE;

				if(y == 0 || y == 7) {

					switch (x) {
						case 0:
						case 7:
							type = IChess.ChessType.TYP_ROOK;
							move = new Rook();

							break;

						case 1:
						case 6:
							type = IChess.ChessType.TYP_KNIGHT;
							move = new Knight();
							break;

						case 2:
						case 5:
							type = IChess.ChessType.TYP_BISHOP;
							move = new Bishop();

							break;

						case 3:
							type = IChess.ChessType.TYP_QUEEN;
							move = new Queen();

							break;

						case 4:
							type = IChess.ChessType.TYP_KING;
							move = new King();

							break;
					}
				} else if (y == 1|| y == 6){
					type = IChess.ChessType.TYP_PAWN;
					move = new Pawn();

				}

				if(color != null && type != null && move != null) this.grid[x][y] = new Piece(color, type, move);
			}
		}
	}
        
    /**
     *
     * @param color
     * @return
     */
    public IChess.ChessKingState getKingThreaten(IChess.ChessColor color){

            for(int x = 0; x < 8; x++){
                for(int y = 0; y < 8; y++){
                    Piece toto = grid[x][y];

                    if(toto != null && toto.getType() == IChess.ChessType.TYP_KING && toto.getColor() == color){

                        IChess.ChessPosition posKing = new IChess.ChessPosition(x,y);

                        for(int x2 = 0; x2 < 8; x2++){
                            for(int y2 = 0; y2 < 8; y2++){

                                Piece tata = grid[x2][y2];

                                if(tata != null && tata.getColor() != color){

                                    List<IChess.ChessPosition> ennemiMouv = tata.getMove(new IChess.ChessPosition(x2,y2), this);

                                    for( IChess.ChessPosition curMouv : ennemiMouv){         
                                        if( posKing.equals(curMouv)) 
                                            return IChess.ChessKingState.KING_THREATEN;

                                    }
                                }
                            }					
                        }
                    }

                }
            }
            return IChess.ChessKingState.KING_SAFE;
        }
    
}
