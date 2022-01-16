package com.hhs.xgn.jmj.scoring.classic;

import com.hhs.xgn.jmj.Mentsu;
import com.hhs.xgn.jmj.RonWrapper;
import com.hhs.xgn.jmj.Tile;
import com.hhs.xgn.jmj.scoring.AgariInfo;
import com.hhs.xgn.jmj.scoring.Yaku;
import com.hhs.xgn.jmj.util.JavaUtil;

public class Dora extends Yaku {
    public Dora() {
        super("bp", "ドラ");
    }

    @Override
    public boolean isNormalOnly() {
        return false;
    }

    @Override
    public int check(RonWrapper ron) {
        int s=0;
        for(int dora:ron.agariInfo.doras){
            for(Tile t:ron.raw.getTiles()){
                if(t.id==dora){
                    s++;
                }
            }

            if(dora==ron.lastTile.id){
                s++;
            }

            for(Mentsu m:ron.ankans){
                for(Tile t:m.toTiles()){
                    if(t.id==dora){
                        s++;
                    }
                }
            }

            for(Mentsu m:ron.fuuro){
                for(Tile t:m.toTiles()){
                    if(t.id==dora){
                        s++;
                    }
                }
            }
        }

        return s;
    }
}
