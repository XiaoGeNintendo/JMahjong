package com.hhs.xgn.jmj.scoring.classic;

import com.hhs.xgn.jmj.Mentsu;
import com.hhs.xgn.jmj.RonWrapper;
import com.hhs.xgn.jmj.scoring.Yaku;

public class Sanankou extends Yaku {
    public Sanankou() {
        super("sak", "三暗刻");
    }


    @Override
    public int check(RonWrapper ron) {
        int c = 0;
        for (int i = 0; i < 4; i++) {
            if (ron.sorted.mentsus[i]!=null && ron.sorted.mentsus[i].type == Mentsu.Kotsu && i != ron.sorted.lastTileIndicator) {
                c++;
            }
        }
        if (c == 3) {
            return 2;
        } else {
            return 0;
        }
    }
}
