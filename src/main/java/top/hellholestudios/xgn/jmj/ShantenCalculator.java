package top.hellholestudios.xgn.jmj;

import top.hellholestudios.xgn.jmj.scoring.Ruleset;
import top.hellholestudios.xgn.jmj.scoring.Yaku;

import java.util.Map;

/**
 * This is a utility class to calculate the Shanten of the given hand
 */
public class ShantenCalculator {

    private static int s = 15, cMax = 0, count = 0;

    public static int getShanten(int[] countArr, Ruleset yakus){
        int x=getNormalShanten(countArr);
        for(Map.Entry<String, Yaku> e:yakus.yakus.entrySet()){
            int y=e.getValue().getCustomShanten(countArr);
            if(y!=-2){
                x=Math.min(x,y);
            }
        }
        return x;
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
        while(i<Tiles.values().length && cnt[i]==0){
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
        if (g + gb > (count-2)/3) {
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

        while (i<Tiles.values().length && cnt[i] == 0) {
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
