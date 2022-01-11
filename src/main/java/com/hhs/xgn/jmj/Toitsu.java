package com.hhs.xgn.jmj;


/**
 * Base class for heads in hand <br>
 */
public class Toitsu {
    /**
     * The first tile ID of this mentsu
     */
    public int tile;

    public Toitsu cpy(){
        return new Toitsu(tile);
    }

    public Toitsu(int tile){
        this.tile=tile;
    }

    public Toitsu(Tiles tile){
        this.tile=tile.ordinal();
    }

    @Override
    public String toString() {
        String s=Tiles.from(tile).utf;
        return "["+s+s+"]";
    }
}
