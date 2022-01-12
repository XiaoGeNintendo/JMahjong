package com.hhs.xgn.jmj.scoring.classic;

import com.hhs.xgn.jmj.RonWrapper;
import com.hhs.xgn.jmj.scoring.AgariInfo;
import com.hhs.xgn.jmj.scoring.Yaku;

public class Chankan extends Yaku {
    public Chankan() {
        super("qg", "搶槓");
    }

    @Override
    public boolean isNormalOnly() {
        return false;
    }

    @Override
    public int check(RonWrapper ron) {
        if (ron.agariInfo.source == AgariInfo.TileSource.Kan) {
            return 1;
        } else {
            return 0;
        }
    }
}
