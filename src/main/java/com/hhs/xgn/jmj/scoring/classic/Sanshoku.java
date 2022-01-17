package com.hhs.xgn.jmj.scoring.classic;

import com.hhs.xgn.jmj.Mentsu;
import com.hhs.xgn.jmj.RonWrapper;
import com.hhs.xgn.jmj.Tiles;
import com.hhs.xgn.jmj.scoring.Yaku;

public class Sanshoku extends Yaku {
    public Sanshoku() {
        super("ssts", "三色同順");
    }


    @Override
    public int check(RonWrapper ron) {
        int[] start = new int[Tiles.values().length];
        for (Mentsu m : ron.sorted.mentsus) {
            if (m!=null && m.type == Mentsu.Shuntsu) {
                start[m.tile]++;
            }
        }
        for (Mentsu m : ron.ankans) {
            if (m.type == Mentsu.Shuntsu) {
                start[m.tile]++;
            }
        }
        for (Mentsu m : ron.fuuro) {
            if (m.type == Mentsu.Shuntsu) {
                start[m.tile]++;
            }
        }

        boolean ok = false;
        for (int i = 0; i < 9; i++) {
            if (start[i] > 0 && start[i + 9] > 0 && start[i + 18] > 0) {
                ok = true;
                break;
            }
        }

        if (ok) {
            if (ron.isMenchin()) {
                return 2;
            } else {
                return 1;
            }
        } else {
            return 0;
        }
    }
}
