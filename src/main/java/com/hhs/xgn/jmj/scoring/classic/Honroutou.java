package com.hhs.xgn.jmj.scoring.classic;

import com.hhs.xgn.jmj.Mentsu;
import com.hhs.xgn.jmj.RonWrapper;
import com.hhs.xgn.jmj.Tile;
import com.hhs.xgn.jmj.Tiles;
import com.hhs.xgn.jmj.scoring.Yaku;
import com.hhs.xgn.jmj.util.TileConstant;

public class Honroutou extends Yaku {
    public Honroutou() {
        super("hlt", "混老頭");
    }


    @Override
    public boolean isNormalOnly() {
        return false;
    }



    @Override
    public int check(RonWrapper ron) {
        for(Tile t:ron.raw.getTiles()){
            if(!Tiles.from(t.id).isTerminal()){
                return 0;
            }
        }
        for(Mentsu m:ron.ankans){
            for(Tile t:m.toTiles()){
                if(!Tiles.from(t.id).isTerminal()){
                    return 0;
                }
            }
        }
        for(Mentsu m:ron.fuuro){
            for(Tile t:m.toTiles()){
                if(!Tiles.from(t.id).isTerminal()){
                    return 0;
                }
            }
        }

        return 2;
    }
}