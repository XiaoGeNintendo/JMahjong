package top.hellholestudios.xgn.jmj.scoring.classic;

import top.hellholestudios.xgn.jmj.Mentsu;
import top.hellholestudios.xgn.jmj.RonWrapper;
import top.hellholestudios.xgn.jmj.Tiles;
import top.hellholestudios.xgn.jmj.scoring.Yaku;

public class Ittsu extends Yaku {
    public Ittsu() {
        super("yqtg", "一気通貫");
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
        for (int i = 0; i < 27; i+=9) {
            if (start[i] > 0 && start[i + 3] > 0 && start[i + 6] > 0) {
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
