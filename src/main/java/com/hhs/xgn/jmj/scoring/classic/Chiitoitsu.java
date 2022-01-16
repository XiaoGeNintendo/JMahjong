package com.hhs.xgn.jmj.scoring.classic;

import com.hhs.xgn.jmj.Mentsu;
import com.hhs.xgn.jmj.RonWrapper;
import com.hhs.xgn.jmj.Tile;
import com.hhs.xgn.jmj.Tiles;
import com.hhs.xgn.jmj.scoring.AgariInfo;
import com.hhs.xgn.jmj.scoring.Yaku;

public class Chiitoitsu extends Yaku {
    public Chiitoitsu() {
        super("qdz", "七対子");
    }

    @Override
    public boolean isNormalOnly() {
        return false;
    }

    @Override
    public int check(RonWrapper ron) {
        if(!ron.isMenchin() || ron.ankans.length>0){
            return 0;
        }

        int[] count=new int[Tiles.values().length];

        for(Tile t: ron.raw.getTiles()){
            count[t.id]++;
        }
        count[ron.lastTile.id]++;

        for(int i=0;i<Tiles.values().length;i++){
            if(count[i]!=0 && count[i]!=2){
                return 0;
            }
        }
        return 2;
    }
}
