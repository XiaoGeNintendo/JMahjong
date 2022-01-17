package top.hellholestudios.xgn.jmj.scoring.classic;

import top.hellholestudios.xgn.jmj.RonWrapper;
import top.hellholestudios.xgn.jmj.scoring.AgariInfo;
import top.hellholestudios.xgn.jmj.scoring.Yaku;

public class WRiichi extends Yaku {
    public WRiichi() {
        super("wlz", "W立直");
    }

    @Override
    public boolean isNormalOnly() {
        return false;
    }

    @Override
    public int check(RonWrapper ron) {
        if (ron.agariInfo.riichi == AgariInfo.Riichi.WRiichi) {
            return 2;
        } else {
            return 0;
        }
    }
}
