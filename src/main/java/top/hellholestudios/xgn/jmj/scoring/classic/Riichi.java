package top.hellholestudios.xgn.jmj.scoring.classic;

import top.hellholestudios.xgn.jmj.RonWrapper;
import top.hellholestudios.xgn.jmj.scoring.AgariInfo;
import top.hellholestudios.xgn.jmj.scoring.Yaku;

public class Riichi extends Yaku {
    public Riichi() {
        super("lz", "立直");
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
