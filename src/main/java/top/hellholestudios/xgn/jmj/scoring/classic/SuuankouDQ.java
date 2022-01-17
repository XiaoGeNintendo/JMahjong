package top.hellholestudios.xgn.jmj.scoring.classic;

import top.hellholestudios.xgn.jmj.Mentsu;
import top.hellholestudios.xgn.jmj.RonWrapper;
import top.hellholestudios.xgn.jmj.scoring.Yaku;

public class SuuankouDQ extends Yaku {
    public SuuankouDQ() {
        super("4akdq", "四暗刻単騎");
    }


    @Override
    public String[] ignore() {
        return new String[]{"3ak","4ak","ddh"};
    }

    @Override
    public boolean isYakuman() {
        return true;
    }

    @Override
    public int check(RonWrapper ron) {
        int c = 0;
        for (int i = 0; i < 4; i++) {
            if (ron.sorted.mentsus[i]!=null &&
                    ron.sorted.mentsus[i].type == Mentsu.Kotsu &&
                    i != ron.sorted.lastTileIndicator) {
                c++;
            }
        }

        c+=ron.ankans.length;

        if (c == 4 && ron.sorted.lastTileIndicator==4) {
            return 26;
        } else {
            return 0;
        }
    }
}
