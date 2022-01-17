package com.hhs.xgn.jmj.scoring.classic;

import com.hhs.xgn.jmj.RonWrapper;
import com.hhs.xgn.jmj.scoring.Yaku;

public class CPoutou extends Yaku {
    public CPoutou() {
        super("czjlbd", "純正九蓮宝燈");
    }


    @Override
    public String[] ignore() {
        return new String[]{"jlbd","qys"};
    }

    @Override
    public boolean isYakuman() {
        return true;
    }

    @Override
    public int check(RonWrapper ron) {
        int[] cnt = ron.raw.toCountArray();

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

                if (cnt[id] != shouldBe) {
                    ok = false;
                    break;
                }
            }

            if (ok) {
                return 26;
            }
        }

        return 0;
    }
}
