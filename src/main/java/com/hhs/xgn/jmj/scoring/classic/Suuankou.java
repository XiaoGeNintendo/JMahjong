package com.hhs.xgn.jmj.scoring.classic;

import com.hhs.xgn.jmj.Mentsu;
import com.hhs.xgn.jmj.RonWrapper;
import com.hhs.xgn.jmj.scoring.Yaku;

public class Suuankou extends Yaku {
    public Suuankou() {
        super("4ak", "四暗刻");
    }


    @Override
    public String[] ignore() {
        return new String[]{"3ak","ddh"};
    }

    @Override
    public boolean isYakuman() {
        return true;
    }

    @Override
    public int check(RonWrapper ron) {
        int c = 0;
        for (int i = 0; i < 4; i++) {
            if (ron.sorted.mentsus[i]!=null && ron.sorted.mentsus[i].type == Mentsu.Kotsu && i != ron.sorted.lastTileIndicator) {
                c++;
            }
        }
        if (c == 4) {
            return 13;
        } else {
            return 0;
        }
    }
}
