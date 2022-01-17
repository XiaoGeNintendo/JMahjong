package com.hhs.xgn.jmj.scoring.classic;

import com.hhs.xgn.jmj.Mentsu;
import com.hhs.xgn.jmj.RonWrapper;
import com.hhs.xgn.jmj.Tile;
import com.hhs.xgn.jmj.scoring.Yaku;

public class Tsuuiisou extends Yaku {
    public Tsuuiisou() {
        super("zys", "字一色");
    }

    @Override
    public boolean isYakuman() {
        return true;
    }

    @Override
    public int check(RonWrapper ron) {
        int[] x=new int[10];
        for(Tile t:ron.raw.getTiles()){
            x[t.id/9]=1;
        }

        for(Mentsu m:ron.ankans){
            for(Tile t:m.toTiles()){
                x[t.id/9]=1;
            }
        }

        for(Mentsu m:ron.fuuro){
            for(Tile t:m.toTiles()){
                x[t.id/9]=1;
            }
        }

        if(x[0]+x[1]+x[2]==0 && x[3]>0){
            return 13;
        }

        return 0;
    }
}
