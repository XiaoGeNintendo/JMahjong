package top.hellholestudios.xgn.jmj.exception;

/**
 * An exception that occurs when the number of tiles in a hand is not 3n+1 or 3n+2
 */
public class HandTileCountException extends RuntimeException{
    public HandTileCountException(){
        super();
    }

    public HandTileCountException(String s){
        super(s);
    }
}
