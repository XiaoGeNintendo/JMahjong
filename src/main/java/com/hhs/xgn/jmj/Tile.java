package com.hhs.xgn.jmj;

/**
 * A tile in Mahjong
 *
 * @author XGN
 */
public class Tile {
    /**
     * The id of the tile. Please see [TileID]
     */
    public int id;
    /**
     * is red dora
     */
    public boolean red;

    /**
     * Mark this tile as red dora and return this
     * @return this tile after making it dora
     */
    public Tile markRed(){
        this.red=true;
        return this;
    }

    @Override
    public String toString() {
        return Tiles.values()[id].utf;
    }

    public Tile(int id) {
        this.id = id;
    }

    public Tile(int id, boolean red) {
        this.id = id;
        this.red = red;
    }
}
