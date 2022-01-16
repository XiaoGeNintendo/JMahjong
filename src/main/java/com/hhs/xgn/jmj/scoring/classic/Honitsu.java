package com.hhs.xgn.jmj.scoring.classic;

import com.hhs.xgn.jmj.Mentsu;
import com.hhs.xgn.jmj.RonWrapper;
import com.hhs.xgn.jmj.Tile;
import com.hhs.xgn.jmj.scoring.AgariInfo;
import com.hhs.xgn.jmj.scoring.Yaku;

public class Honitsu extends Yaku {
    public Honitsu() {
        super("hys", "混一色");
    }

    @Override
    public boolean isNormalOnly() {
        return false;
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

        if(x[0]+x[1]+x[2]>=2){
            return 0;
        }

        if(ron.isMenchin()){
            return 3;
        }else{
            return 2;
        }
    }
}
