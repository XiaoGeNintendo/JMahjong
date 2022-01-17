package top.hellholestudios.xgn.jmj.scoring.classic;

import top.hellholestudios.xgn.jmj.Mentsu;
import top.hellholestudios.xgn.jmj.RonWrapper;
import top.hellholestudios.xgn.jmj.scoring.AgariInfo;
import top.hellholestudios.xgn.jmj.scoring.Yaku;

public class Suuankou extends Yaku {
    public Suuankou() {
        super("4ak", "四暗刻");
    }


    @Override
    public String[] ignore() {
        return new String[]{"3ak", "ddh"};
    }

    @Override
    public boolean isYakuman() {
        return true;
    }

    @Override
    public int check(RonWrapper ron) {
        int c = 0;
        for (int i = 0; i < 4; i++) {
            if (ron.sorted.mentsus[i] != null &&
                    ron.sorted.mentsus[i].type == Mentsu.Kotsu &&
                    (ron.agariInfo.getAgariType() == AgariInfo.AgariType.Tsumo || i != ron.sorted.lastTileIndicator)) {
                c++;
            }
        }

        c += ron.ankans.length;

        if (c == 4) {
            return 13;
        } else {
            return 0;
        }
    }
}
