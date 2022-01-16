package com.hhs.xgn.jmj.scoring.classic;

import com.hhs.xgn.jmj.Mentsu;
import com.hhs.xgn.jmj.RonWrapper;
import com.hhs.xgn.jmj.Tile;
import com.hhs.xgn.jmj.scoring.Yaku;

public class RedDora extends Yaku {
    public RedDora() {
        super("hbp", "赤ドラ");
    }

    @Override
    public boolean isNormalOnly() {
        return false;
    }

    @Override
    public int check(RonWrapper ron) {
        int s=0;

        for(Tile t:ron.raw.getTiles()){
            if(t.red){
                s++;
            }
        }

        if(ron.lastTile.red){
            s++;
        }

        for(Mentsu m:ron.ankans){
            for(Tile t:m.toTiles()){
                if(t.red){
                    s++;
                }
            }
        }

        for(Mentsu m:ron.fuuro){
            for(Tile t:m.toTiles()){
                if(t.red){
                    s++;
                }
            }
        }


        return s;
    }
}
