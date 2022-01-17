package top.hellholestudios.xgn.jmj.scoring.classic;

import top.hellholestudios.xgn.jmj.Mentsu;
import top.hellholestudios.xgn.jmj.RonWrapper;
import top.hellholestudios.xgn.jmj.Tiles;
import top.hellholestudios.xgn.jmj.scoring.Yaku;

public class SanshokuDouKou extends Yaku {
    public SanshokuDouKou() {
        super("sstk", "三色同刻");
    }


    @Override
    public int check(RonWrapper ron) {
        int[] start = new int[Tiles.values().length];
        for (Mentsu m : ron.sorted.mentsus) {
            if (m!=null && m.type != Mentsu.Shuntsu) {
                start[m.tile]++;
            }
        }
        for (Mentsu m : ron.ankans) {
            if (m.type != Mentsu.Shuntsu) {
                start[m.tile]++;
            }
        }
        for (Mentsu m : ron.fuuro) {
            if (m.type != Mentsu.Shuntsu) {
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
            return 2;
        } else {
            return 0;
        }
    }
}
