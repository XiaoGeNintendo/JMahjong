package com.hhs.xgn.jmj.scoring.classic;

import com.hhs.xgn.jmj.Mentsu;
import com.hhs.xgn.jmj.RonWrapper;
import com.hhs.xgn.jmj.scoring.AgariInfo;
import com.hhs.xgn.jmj.scoring.Yaku;

public class Haitei extends Yaku {
    public Haitei() {
        super("hdly", "海底撈月");
    }


    @Override
    public boolean isNormalOnly() {
        return false;
    }
    @Override
    public int check(RonWrapper ron) {
        if(ron.agariInfo.source== AgariInfo.TileSource.Haitei){
            return 1;
        }else{
            return 0;
        }
    }
}
