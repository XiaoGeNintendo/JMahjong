package top.hellholestudios.xgn.jmj.scoring.classic;

import top.hellholestudios.xgn.jmj.RonWrapper;
import top.hellholestudios.xgn.jmj.scoring.AgariInfo;
import top.hellholestudios.xgn.jmj.scoring.Yaku;

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
