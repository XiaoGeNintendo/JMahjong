package com.hhs.xgn.jmj.scoring.classic;

import com.hhs.xgn.jmj.RonWrapper;
import com.hhs.xgn.jmj.Tiles;
import com.hhs.xgn.jmj.scoring.Yaku;
import com.hhs.xgn.jmj.util.TileConstant;

public class Kokushimusou13 extends Yaku {
    public Kokushimusou13() {
        super("gsws13", "国士無双１３面待ち");
    }

    @Override
    public String[] ignore() {
        return new String[]{"gsws"};
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

        for(Tiles t: TileConstant.TerminalTile) {
            if (cnt[t.ordinal()] != 1) {
                return 0;
            }
        }

        return 26;
    }
}
