package top.hellholestudios.xgn.jmj.scoring.classic;

import top.hellholestudios.xgn.jmj.RonWrapper;
import top.hellholestudios.xgn.jmj.scoring.AgariInfo;
import top.hellholestudios.xgn.jmj.scoring.Yaku;

public class Tenhou extends Yaku {
    public Tenhou() {
        super("th", "天和");
    }

    @Override
    public boolean isNormalOnly() {
        return false;
    }

    @Override
    public boolean isYakuman() {
        return true;
    }

    @Override
    public int check(RonWrapper ron) {
        if (ron.sorted == null) {
            //must be chiitoitsu or musou
            if (new Chiitoitsu().check(ron) == 0 && new Kokushimusou().check(ron) == 0 && new Kokushimusou13().check(ron) == 0) {
                return 0;
            }
        }

        if (ron.agariInfo.isDealer() && ron.agariInfo.source == AgariInfo.TileSource.FirstTsumo) {
            return 13;
        } else {
            return 0;
        }
    }
}
