package top.hellholestudios.xgn.jmj.scoring.classic;

import top.hellholestudios.xgn.jmj.RonWrapper;
import top.hellholestudios.xgn.jmj.scoring.Yaku;

public class Poutou extends Yaku {
    public Poutou() {
        super("jlbd", "九連宝燈");
    }


    @Override
    public String[] ignore() {
        return new String[]{"qys"};
    }

    @Override
    public boolean isYakuman() {
        return true;
    }

    @Override
    public int check(RonWrapper ron) {
        int[] cnt = ron.raw.toCountArray();
        cnt[ron.lastTile.id]++;

        for (int i = 0; i < 27; i += 9) {
            boolean used = false;
            boolean ok = true;

            for (int j = 0; j < 9; j++) {
                int id = i + j;
                int shouldBe;
                if (j == 0 || j == 8) {
                    shouldBe = 3;
                } else {
                    shouldBe = 1;
                }

                if (cnt[id] == shouldBe + 1) {
                    if (used) {
                        ok = false;
                        break;
                    }
                    used = true;
                } else if (cnt[id] != shouldBe) {
                    ok = false;
                    break;
                }
            }

            if (ok) {
                return 13;
            }
        }

        return 0;
    }
}
