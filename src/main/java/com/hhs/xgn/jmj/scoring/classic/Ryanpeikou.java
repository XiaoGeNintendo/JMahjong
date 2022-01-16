package com.hhs.xgn.jmj.scoring.classic;

import com.hhs.xgn.jmj.Mentsu;
import com.hhs.xgn.jmj.RonWrapper;
import com.hhs.xgn.jmj.scoring.Yaku;

public class Ryanpeikou extends Yaku {
    public Ryanpeikou() {
        super("ebk", "二盃口");
    }


    @Override
    public String[] ignore() {
        return new String[]{"ybk", "qdz"};
    }

    @Override
    public int check(RonWrapper ron) {
        if (!ron.isMenchin()) {
            return 0;
        }

        for (int i = 0; i < 4; i++) {
            if (ron.sorted.mentsus[i].type != Mentsu.Shuntsu) {
                return 0;
            }
        }

        if (ron.sorted.mentsus[0].tile == ron.sorted.mentsus[1].tile &&
                ron.sorted.mentsus[2].tile == ron.sorted.mentsus[3].tile) {
            return 3;
        } else {
            return 0;
        }

    }
}
