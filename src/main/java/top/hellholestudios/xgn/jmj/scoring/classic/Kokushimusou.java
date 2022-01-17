package top.hellholestudios.xgn.jmj.scoring.classic;

import top.hellholestudios.xgn.jmj.RonWrapper;
import top.hellholestudios.xgn.jmj.Tiles;
import top.hellholestudios.xgn.jmj.scoring.Yaku;
import top.hellholestudios.xgn.jmj.util.TileConstants;

public class Kokushimusou extends Yaku {
    public Kokushimusou() {
        super("gsws", "国士無双");
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
        if (!ron.isMenchin() || ron.ankans.length != 0) {
            return 0;
        }

        int[] cnt = ron.raw.toCountArray();
        cnt[ron.lastTile.id]++;

        boolean two = false;

        for (Tiles t : TileConstants.TerminalTile) {
            if (cnt[t.ordinal()] == 2) {
                if (two) {
                    return 0;
                }
                two = true;
            } else if (cnt[t.ordinal()] != 1) {
                return 0;
            }
        }

        if (two) {
            return 13;
        } else {
            return 0;
        }
    }
}
