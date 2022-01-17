package com.hhs.xgn.jmj.scoring.classic;

import com.hhs.xgn.jmj.RonWrapper;
import com.hhs.xgn.jmj.Tile;
import com.hhs.xgn.jmj.Tiles;
import com.hhs.xgn.jmj.scoring.AgariInfo;
import com.hhs.xgn.jmj.scoring.Yaku;
import com.hhs.xgn.jmj.util.TileConstant;

public class Kokushimusou extends Yaku {
    public Kokushimusou() {
        super("gsws", "国士無双");
    }

    @Override
    public boolean isNormalOnly() {
        return false;
    }

    @Override
    public boolean isYakuman() {
        return true;
    }

    @Override
    public int check(RonWrapper ron) {
        if(!ron.isMenchin() || ron.ankans.length!=0){
            return 0;
        }

        int[] cnt=ron.raw.toCountArray();
        cnt[ron.lastTile.id]++;

        boolean two=false,zero=false;

        for(Tiles t: TileConstant.TerminalTile) {
            if (cnt[t.ordinal()] == 2) {
                if (two) {
                    return 0;
                }
                two = true;
            }
            if (cnt[t.ordinal()] == 0) {
                if (zero) {
                    return 0;
                }
                zero = true;
            }
        }

        if(two && zero){
            return 13;
        }else{
            return 0;
        }
    }
}
