package top.hellholestudios.xgn.jmj;

import top.hellholestudios.xgn.jmj.exception.HandTileCountException;
import top.hellholestudios.xgn.jmj.scoring.Ruleset;
import top.hellholestudios.xgn.jmj.scoring.Yaku;
import top.hellholestudios.xgn.jmj.util.JavaUtil;

import java.util.ArrayList;
import java.util.Map;

/**
 * This is a utility class to calculate the Shanten of the given hand <br>
 * The algorithm used is provided by <a href="https://zhuanlan.zhihu.com/p/31000381">九条可怜</a>
 */
public class ShantenCalculator {

    private static int s = 15, cMax = 0, count = 0;

    /**
     * <b>According to several tests, when the count array is 3n+1 style, this algorithm is not guaranteed to output the wanted result. It is strictly recommended to avoid using 3n+1 hands in this function</b>
     *
     * @param countArr the count array of the given hand
     * @param yakus    the yakus to check special shanten to
     * @return the shanten to the hand
     */
    public static int getShanten(int[] countArr, Ruleset yakus) {
        int x = getNormalShanten(countArr);
        for (Map.Entry<String, Yaku> e : yakus.yakus.entrySet()) {
            int y = e.getValue().getCustomShanten(countArr);
            if (y != -2) {
                x = Math.min(x, y);
            }
        }
        return x;
    }

    /**
     * Suggest throwing according to tile efficiency. <br>
     * If the count array is 3n+1 style, return an array with a single Suggestion whose throw ID is -1. <br>
     * <b>According to several tests, when the count array is 3n+1 style, this algorithm is not guaranteed to output the wanted result. It is strictly recommended to avoid using 3n+1 hands in this function</b>
     * If the count array is 3n+2 style, return an array with suggestions sorted by tile efficiency. <br>
     *
     * @param countArr   the count array representing the hand
     * @param yakus      all the yakus
     * @param otherTiles a count array indicating other tiles known. This will be used to provide better results.
     * @return list of suggestions or waits
     */
    public static Suggestion[] suggest(int[] countArr, Ruleset yakus, int[] otherTiles) {

        int count = JavaUtil.sum(countArr);
        int shanten = getShanten(countArr, yakus);

        ArrayList<Suggestion> suggestions = new ArrayList<>();
        if (count % 3 == 1) {
            Suggestion s = new Suggestion();
            s.throwID = -1;
            ArrayList<Integer> tmp = new ArrayList<>();
            for (int i = 0; i < Tiles.values().length; i++) {
                countArr[i]++;
                int newShanten = getShanten(countArr, yakus);
                if (newShanten < shanten) {
                    s.efficiency += Math.max(0, 4 - countArr[i] - otherTiles[i] + 1);
                    tmp.add(i);
                }
                countArr[i]--;
            }

            s.waitID = new int[tmp.size()];
            for (int i = 0; i < tmp.size(); i++) {
                s.waitID[i] = tmp.get(i);
            }

            suggestions.add(s);
        } else if (count % 3 == 2) {
            for (int i = 0; i < Tiles.values().length; i++) {
                if (countArr[i] > 0) {
                    Suggestion s = new Suggestion();
                    s.throwID = i;
                    countArr[i]--;
                    ArrayList<Integer> tmp = new ArrayList<>();
                    for (int j = 0; j < Tiles.values().length; j++) {
                        countArr[j]++;
                        int nw = getShanten(countArr, yakus);
                        if (nw < shanten) {
                            s.efficiency += Math.max(0, 4 - countArr[j] - otherTiles[i] + 1);
                            tmp.add(j);
                        }
                        countArr[j]--;
                    }

                    if (tmp.size() != 0) {
                        s.waitID = new int[tmp.size()];
                        for (int j = 0; j < tmp.size(); j++) {
                            s.waitID[j] = tmp.get(j);
                        }

                        suggestions.add(s);
                    }

                    countArr[i]++;
                }
            }
        } else {
            throw new HandTileCountException("Invalid tile count:" + count);
        }

        suggestions.sort((o1, o2) -> -Integer.compare(o1.efficiency, o2.efficiency));
        return suggestions.toArray(new Suggestion[0]);
    }

    public static int getNormalShanten(int[] countArr) {
        s = 15;
        cMax = 0;
        count = 0;

        for (int i = 0; i < Tiles.values().length; i++) {
            count += countArr[i];
        }
        cutP(countArr, count, (count - 2) / 3);
        return s;
    }

    private static void cutP(int[] cnt, int cRem, int k) {
        for (int i = 0; i < Tiles.values().length; i++) {
            if (cnt[i] >= 2) {
                cnt[i] -= 2;
                cut3(cnt, 0, cRem - 2, k, 1, 0);
                cnt[i] += 2;
            }
        }

        cut3(cnt, 0, cRem, k, 0, 0);
    }

    private static void cut3(int[] cnt, int i, int cRem, int k, int p, int g) {
        while (i < Tiles.values().length && cnt[i] == 0) {
            i++;
        }

        if (i == Tiles.values().length) {
            cut2(cnt, 0, cRem, k, p, g, 0);
            return;
        }

        if (cnt[i] >= 3) {
            cnt[i] -= 3;
            cut3(cnt, i, cRem - 3, k, p, g + 1);
            cnt[i] += 3;
        }
        if (Tiles.from(i).canFormShuntsu()) {
            if (cnt[i + 1] > 0 && cnt[i + 2] > 0) {
                cnt[i]--;
                cnt[i + 1]--;
                cnt[i + 2]--;
                cut3(cnt, i, cRem - 3, k, p, g + 1);
                cnt[i]++;
                cnt[i + 1]++;
                cnt[i + 2]++;
            }
        }

        cut3(cnt, i + 1, cRem, k, p, g);
    }

    private static void cut2(int[] cnt, int i, int cRem, int k, int p, int g, int gb) {
        if (s == -1) {
            return;
        }
        if (g + gb > (count - 2) / 3) {
            return;
        }
        int c = 3 * g + 2 * gb + 2 * p;
        if (cRem < cMax - c) {
            return;
        }
        if (cRem == 0) {
            s = Math.min(s, 2 * (k - g) - gb - p);
            cMax = Math.max(cMax, c);
            return;
        }

        while (i < Tiles.values().length && cnt[i] == 0) {
            i++;
        }

        if (cnt[i] >= 2) {
            cnt[i] -= 2;
            cut2(cnt, i, cRem - 2, k, p, g, gb + 1);
            cnt[i] += 2;
        }

        if (i < Tiles.East.ordinal()) {
            if (i % 9 <= 7 && cnt[i + 1] > 0) {
                cnt[i]--;
                cnt[i + 1]--;
                cut2(cnt, i, cRem - 2, k, p, g, gb + 1);
                cnt[i]++;
                cnt[i + 1]++;
            }
            if (i % 9 <= 6 && cnt[i + 2] > 0) {
                cnt[i]--;
                cnt[i + 2]--;
                cut2(cnt, i, cRem - 2, k, p, g, gb + 1);
                cnt[i]++;
                cnt[i + 2]++;
            }
        }

        int x = cnt[i];
        cnt[i] = 0;
        cut2(cnt, i + 1, cRem - x, k, p, g, gb);
        cnt[i] = x;
    }
}
