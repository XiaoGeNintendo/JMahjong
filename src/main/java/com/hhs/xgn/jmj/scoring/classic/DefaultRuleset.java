package com.hhs.xgn.jmj.scoring.classic;

import com.hhs.xgn.jmj.*;
import com.hhs.xgn.jmj.scoring.AgariInfo;
import com.hhs.xgn.jmj.scoring.HandDescriber;
import com.hhs.xgn.jmj.scoring.Ruleset;
import com.hhs.xgn.jmj.scoring.Yaku;
import com.hhs.xgn.jmj.util.HandUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

public class DefaultRuleset extends Ruleset {

    public boolean isTanyaoMenchin;
    public boolean isAotenjou;
    /**
     * Fu for 7-pairs or 13-orphans or pinfu
     */
    public int specialFu = 25;

    public DefaultRuleset() {
        registerYaku(new Riichi());
        registerYaku(new Chankan());
        registerYaku(new Haitei());
        registerYaku(new Houtei());
        registerYaku(new Iipeikou());
        registerYaku(new Ippatsu());
        registerYaku(new MenchinTsumo());
        registerYaku(new Pinfu());
        registerYaku(new Rinshan());
        registerYaku(new Tanyao());
        registerYaku(new Yakuhai());
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
                a = new int[]{2000, 3000, 3000, 4000, 4000, 4000, 6000, 6000, 8000}[(int) (Math.min(13, describer.getHan()) - 5)];
            } else {
                a = Math.min(2000, describer.fu * (1L << (describer.getHan() + 13 * describer.getYakumanHan() + 2)));
            }
        }

        if (agariInfo.dealer) {
            return roundUpTo100(a * 6);
        } else {
            return roundUpTo100(a * 4);
        }
    }

    private interface Solve {
        HandDescriber solve(SortedHand sortedHand, boolean special);
    }

    private void l(String s) {
        System.out.println(s);
    }

    @Override
    public HandDescriber describe(Hand hand, int lastTile, Mentsu[] ankan, Mentsu[] fuuro, Tile[] other, AgariInfo agariInfo) {

        Solve solver = (SortedHand sortedHand, boolean special) -> {
            //calculate yakus
            RonWrapper wrapper = new RonWrapper(sortedHand, hand, lastTile, ankan, fuuro, other, agariInfo, this);
            HandDescriber now = new HandDescriber();
            for (Map.Entry<String, Yaku> yaku : yakus.entrySet()) {
//                l("Checking yaku:"+yaku.getKey()+" but it is "+yaku.getValue().isNormalOnly());
                if (!special || !yaku.getValue().isNormalOnly()) {
                    int score = yaku.getValue().check(wrapper);
                    if (score > 0) {
                        if (yaku.getValue().isYakuman()) {
                            now.yakumans.put(yaku.getKey(), score);
                        } else {
                            now.yakus.put(yaku.getKey(), score);
                        }
                    }
                }
            }

            //ignore
            ArrayList<String> del = new ArrayList<>();
            for (Map.Entry<String, Yaku> e : yakus.entrySet()) {
                del.addAll(Arrays.asList(yakus.get(e.getKey()).ignore()));
            }
            for (String s : del) {
                yakus.remove(s);
            }

            //calculate fu
            if (special) {
                now.fu = 25;
            } else if (now.yakus.containsKey("ph")) {
                now.fu = 20;
            } else {
                l("cf20");

                int fu = 20;
                for (int i = 0; i < 4; i++) {
                    if (sortedHand.mentsus[i] != null) {
                        Mentsu m = sortedHand.mentsus[i];
                        if (m.type == Mentsu.Kotsu) {
                            if (Tiles.from(m.tile).isTerminal()) {
                                if (sortedHand.lastTileIndicator == i) {
                                    l("4fmk");
                                    fu += 4; //荣和明刻
                                } else {
                                    l("8fak");
                                    fu += 8;
                                }
                            } else {
                                if (sortedHand.lastTileIndicator == i) {
                                    l("2fmk");
                                    fu += 2; //荣和明刻
                                } else {
                                    l("4fak");
                                    fu += 4;
                                }
                            }
                        }
                    }
                }

                //fuuro
                for (Mentsu m : fuuro) {
                    if (m.type == Mentsu.Kotsu) {
                        if (Tiles.from(m.tile).isTerminal()) {
                            l("fr4f");
                            fu += 4;
                        } else {
                            l("fr2f");
                            fu += 2;
                        }
                    } else if (m.type == Mentsu.Kantsu) {
                        if (Tiles.from(m.tile).isTerminal()) {
                            l("fr16f");
                            fu += 16;
                        } else {
                            l("fr8f");
                            fu += 8;
                        }
                    }
                }

                for (Mentsu a : ankan) {
                    if (Tiles.from(a.tile).isTerminal()) {
                        l("ak32f");
                        fu += 32;
                    } else {
                        l("ak16f");
                        fu += 16;
                    }
                }

                //head
                Tiles t = Tiles.from(sortedHand.head.tile);
                if (t.isDragon()) {
                    l("hd2f");
                    fu += 2;
                }
                if (t.isWind(agariInfo.seatWind)) {
                    l("sw2f");
                    fu += 2;
                }
                if (t.isWind(agariInfo.prevalantWind)) {
                    l("pw2f");
                    fu += 2;
                }

                //listening type
                if (sortedHand.lastTileIndicator == 4) {
                    l("dq2f");
                    fu += 2; //单骑
                }

                if (sortedHand.lastTileIndicator < 4) {
                    Mentsu m = sortedHand.mentsus[sortedHand.lastTileIndicator];
                    if (m.type == Mentsu.Shuntsu) {
                        Tiles ti = Tiles.from(m.tile);
                        if (ti.getNextTile().ordinal() == lastTile) {
                            //中张
                            l("zz2f");
                            fu += 2;
                        } else if (ti.ordinal() % 9 == 0 && ti.getNextTile().getNextTile().ordinal() == lastTile ||
                                ti.ordinal() % 9 == 6 && ti.ordinal() == lastTile) {
                            //边张
                            l("bz2f");
                            fu += 2;
                        }
                    }
                }

                //tsumo or ron
                if (agariInfo.agariType == AgariInfo.AgariType.Tsumo) {
                    l("zm2f");
                    fu += 2;
                }

                if (agariInfo.agariType == AgariInfo.AgariType.Ron && wrapper.isMenchin()) {
                    l("rh10f");
                    fu += 10;
                }

                //lastly if 20fu 1fan
                if (!now.hasYakuman() && now.getHan() == 1) {
                    fu = Math.max(fu, 30);
                }

                //round up
                if (fu % 10 != 0) {
                    fu = (fu / 10 + 1) * 10;
                }

                now.fu = fu;
            }

            l("Score of " + sortedHand + " Returned " + now);
            return now;
        };

        int[] cnt = hand.toCountArray();
        cnt[lastTile]++;
        SortedHand[] breakdown = HandUtil.breakdown(cnt);

        HandDescriber best = new HandDescriber();

        //special judge
        {

            HandDescriber now = solver.solve(null, true);

            if (score(now, agariInfo) > score(best, agariInfo)) {
                best = now;
            }
        }


        //break down hand
        for (SortedHand sorted : breakdown) {
            for (int i = 0; i < 4; i++) { //mentsu last tile
                if (sorted.mentsus[i] != null && sorted.mentsus[i].contains(lastTile)) {
                    sorted.lastTileIndicator = i;

                    HandDescriber now = solver.solve(sorted, false);

                    if (score(now, agariInfo) > score(best, agariInfo)) {
                        best = now;
                    }
                }
            }

            if (sorted.head.tile == lastTile) { //hand last tile
                sorted.lastTileIndicator = 4;

                HandDescriber now = solver.solve(sorted, false);

                if (score(now, agariInfo) > score(best, agariInfo)) {
                    best = now;
                }
            }
        }

        return best;
    }
}
