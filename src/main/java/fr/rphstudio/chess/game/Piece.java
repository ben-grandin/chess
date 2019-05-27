/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

/**
 *
 * @author achourryad
 */
public class Piece {
    
    private IChess.ChessColor color;
    private IChess.ChessType type;
    
    public Piece(IChess.ChessColor a, IChess.ChessType b){
    color = a;
    type = b;
    }
public IChess.ChessColor getColor(){
    return color;
}
public IChess.ChessType getType() {
    return type;
}
}
