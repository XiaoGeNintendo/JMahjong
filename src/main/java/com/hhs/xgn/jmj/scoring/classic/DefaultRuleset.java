package com.hhs.xgn.jmj.scoring.classic;

import com.hhs.xgn.jmj.*;
import com.hhs.xgn.jmj.scoring.AgariInfo;
import com.hhs.xgn.jmj.scoring.HandDescriber;
import com.hhs.xgn.jmj.scoring.Ruleset;
import com.hhs.xgn.jmj.scoring.Yaku;
import com.hhs.xgn.jmj.util.HandUtil;

import java.util.Map;

public class DefaultRuleset extends Ruleset {

    public boolean isTanyaoMenchin;
    public boolean isAotenjou;

    public DefaultRuleset(){
        registerYaku(new Riichi());
    }

    private long roundUpTo100(long x) {
        if (x % 100 == 0) {
            return x;
        } else {
            return (x / 100 + 1) * 100;
        }
    }

    @Override
    public long score(HandDescriber describer, AgariInfo agariInfo) {

        long a;

        if (isAotenjou) {
            a = describer.fu * (1L << (describer.getHan() + 13 * describer.getYakumanHan() + 2));
        } else {
            if (describer.hasYakuman()) {
                a = 8000L * describer.getYakumanHan();
            } else if (describer.getHan() >= 5) {
                a = new int[]{2000, 3000, 3000, 4000, 4000, 4000, 6000, 6000}[(int) (describer.getHan() - 5)];
            } else {
                a = Math.min(2000, describer.fu * (1L << (describer.getHan() + 13 * describer.getYakumanHan() + 2)));
            }
        }

        if (agariInfo.dealer) {
            return a * 6;
        } else {
            return a * 4;
        }
    }

    @Override
    public HandDescriber describe(Hand hand, int lastTile, Mentsu[] ankan, Mentsu[] fuuro, Tile[] other, AgariInfo agariInfo) {
        int[] cnt = hand.toCountArray();
        cnt[lastTile]++;
        SortedHand[] breakdown = HandUtil.breakdown(cnt);

        HandDescriber best = new HandDescriber();

        //special judge
        {
            RonWrapper wrapper = new RonWrapper(null, hand, lastTile, ankan, fuuro, other, agariInfo, this);
            HandDescriber now = new HandDescriber();
            for (Map.Entry<String, Yaku> yaku : yakus.entrySet()) {
                if(!yaku.getValue().isNormalOnly()) {
                    int score = yaku.getValue().check(wrapper);
                    if (score > 0) {
                        now.yakus.put(yaku.getKey(), score);
                    }
                }
            }

            if (score(now, agariInfo) > score(best, agariInfo)) {
                best = now;
            }
        }

        for (SortedHand sorted : breakdown) {
            RonWrapper wrapper = new RonWrapper(sorted, hand, lastTile, ankan, fuuro, other, agariInfo, this);
            HandDescriber now = new HandDescriber();
            for (Map.Entry<String, Yaku> yaku : yakus.entrySet()) {
                int score = yaku.getValue().check(wrapper);
                if (score > 0) {
                    now.yakus.put(yaku.getKey(), score);
                }
            }

            if (score(now, agariInfo) > score(best, agariInfo)) {
                best = now;
            }
        }

        return best;
    }
}
