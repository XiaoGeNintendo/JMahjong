package com.hhs.xgn.jmj;

import com.hhs.xgn.jmj.exception.HandTileCountException;

import java.util.Arrays;

/**
 * Hand should contain 3n+2 tiles or 3n+1 tiles <br/>
 *
 * @author XGN
 */
public class Hand {
    private Tile[] tiles;

    /**
     * @throws com.hhs.xgn.jmj.exception.HandTileCountException When tile count is incorrect
     */
    public Hand(Tile[] tiles){
        setTiles(tiles);
    }

    @Override
    public String toString() {
        return "Hand{" +
                Arrays.toString(tiles) +
                '}';
    }

    /**
     *
     * @return the count array of this hand used for other utilities
     */
    public int[] toCountArray(){
        int mx=Tiles.values().length;

        int[] res=new int[mx];

        for(Tile x:tiles){
            res[x.id]++;
        }

        return res;
    }

    public Tile[] getTiles(){
        return tiles;
    }

    /**
     * @return true if tiles.length%3=2
     */
    public boolean is3NPlus2(){
        return tiles.length%3==2;
    }

    public void setTiles(Tile[] tiles){
        if(tiles.length%3==0 || tiles.length>14){
            throw new HandTileCountException("Illegal Tile Length:"+tiles.length);
        }
        this.tiles=tiles;
    }
}
