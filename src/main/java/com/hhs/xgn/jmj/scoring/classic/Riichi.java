package com.hhs.xgn.jmj.scoring.classic;

import com.hhs.xgn.jmj.RonWrapper;
import com.hhs.xgn.jmj.scoring.AgariInfo;
import com.hhs.xgn.jmj.scoring.Yaku;

public class Riichi extends Yaku {
    public Riichi() {
        super("lz", "Riichi");
    }

    @Override
    public boolean isNormalOnly() {
        return false;
    }

    @Override
    public int check(RonWrapper ron) {
        if (ron.agariInfo.riichi == AgariInfo.Riichi.Riichi) {
            return 1;
        } else {
            return 0;
        }
    }
}
