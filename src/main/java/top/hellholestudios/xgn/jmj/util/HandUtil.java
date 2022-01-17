package top.hellholestudios.xgn.jmj.util;

import top.hellholestudios.xgn.jmj.*;
import top.hellholestudios.xgn.jmj.exception.HandTileCountException;
import top.hellholestudios.xgn.jmj.scoring.classic.Chiitoitsu;
import top.hellholestudios.xgn.jmj.scoring.classic.Kokushimusou;

import java.util.ArrayList;
import java.util.Collections;

public class HandUtil {

    private static void dfsbd(int[] cnt, ArrayList<SortedHand> out, int view, int mentsu, boolean head, boolean usedKotsu, SortedHand cur) {
        if (view == Tiles.values().length) {
            if (head) {
                out.add(cur.cpy());
            }

            return;
        }

        if (cnt[view] == 0) {
            dfsbd(cnt, out, view + 1, mentsu, head, false, cur);
        }

        //kotsu
        if (mentsu < 4 && cnt[view] >= 3) {
            cnt[view] -= 3;
            cur.mentsus[mentsu] = new Mentsu(view, Mentsu.Kotsu);

            dfsbd(cnt, out, view, mentsu + 1, head, true, cur);

            cnt[view] += 3;
            cur.mentsus[mentsu] = null;
        }

        //head
        if (cnt[view] >= 2 && !head) {
            cnt[view] -= 2;
            cur.head = new Toitsu(view);

            dfsbd(cnt, out, view, mentsu, true, usedKotsu, cur);

            cnt[view] += 2;
            cur.head = null;
        }

        //shuntsu
        Tiles t = Tiles.from(view);
        if (!usedKotsu && mentsu < 4 && t.canFormShuntsu() &&
                cnt[view] >= 1 &&
                cnt[t.getNextTile().ordinal()] >= 1 &&
                cnt[t.getNextTile().getNextTile().ordinal()] >= 1) {

            cnt[view]--;
            cnt[t.getNextTile().ordinal()]--;
            cnt[t.getNextTile().getNextTile().ordinal()]--;
            cur.mentsus[mentsu] = new Mentsu(view, Mentsu.Shuntsu);

            dfsbd(cnt, out, view, mentsu + 1, head, usedKotsu, cur);

            cnt[view]++;
            cnt[t.getNextTile().ordinal()]++;
            cnt[t.getNextTile().getNextTile().ordinal()]++;
            cur.mentsus[mentsu] = null;
        }
    }

    /**
     * Returns all possible breakdown for the count array.
     * The count array must represent a ron-able 3n+2 hand containing no more than 4 Mentsu and a head. <br>
     *
     * <b>Note: this method does NOT include red dora information as being seen in the parameters!
     * Do not expect generated Mentsu has a redDora field with true values</b>
     *
     * @param countArr count array of the hand
     * @return all breakdown or an empty array if it is invalid
     * @throws HandTileCountException given count array is not 3n+2 style
     */
    public static SortedHand[] breakdown(int[] countArr) {
        int sm = 0;
        for (int x : countArr) {
            sm += x;
        }

        if (sm > 14 || sm % 3 != 2) {
            throw new HandTileCountException("Invalid tile count:" + sm);
        }

        ArrayList<SortedHand> list = new ArrayList<>();
        dfsbd(countArr, list, 0, 0, false, false, new SortedHand());

        return list.toArray(new SortedHand[0]);
    }

    /**
     * Given a count array of a 3n+2 hand.
     * Test if it consists of mentsus and exactly a toitsu.
     * It does not consider 7-pairs or 13-orphans.
     *
     * @param countArr count array of the hand
     * @return true if it consists of mentsus and exactly a toitsu
     */
    public static boolean testRon(int[] countArr) {
        return breakdown(countArr).length != 0;
    }

    /**
     * @param countArr the 3n+2 hand count array
     * @return true if is 7pairs
     */
    public static boolean is7pairs(int[] countArr) {
        return new Chiitoitsu().check(countArr);
    }

    /**
     * @param countArr the 3n+2 hand count array
     * @return true if is kokushimusou
     */
    public static boolean is13orphans(int[] countArr) {
        return new Kokushimusou().check(countArr);
    }

    /**
     * Check if a count array's hand consists of 4 mentsu and a head OR is 7-pairs or is 13-orphans
     *
     * @param countArr the 3n+2 hand's count array
     * @return true if can ron(including 7-pairs and 13-orphans)
     */
    public static boolean testRonForAll(int[] countArr) {
        return testRon(countArr) || is7pairs(countArr) || is13orphans(countArr);
    }

    /**
     * Given a count array of a 3n+1 listening hand (i.e. One tile from winning)
     * Return all possible tiles to achieve a win. <br>
     *
     * <b>Note: This method has a large time complexity and can have a negative impact on the performance. Please do not call it too often</b>
     *
     * @param countArr count array of the hand
     * @return all possible final tile ID
     * @throws HandTileCountException
     */
    public static Integer[] getListen(int[] countArr) {
        int sm = 0;
        for (int x : countArr) {
            sm += x;
        }

        if (sm > 13 || sm % 3 != 1) {
            throw new HandTileCountException("Invalid tile count:" + sm);
        }

        ArrayList<Integer> arr = new ArrayList<>();
        for (Tiles t : Tiles.values()) {
            countArr[t.ordinal()]++;
            if (HandUtil.testRon(countArr)) {
                arr.add(t.ordinal());
            }
            countArr[t.ordinal()]--;
        }
        return arr.toArray(new Integer[0]);
    }

    public static Tile[] fromNotationRaw(String notation) {
        int delta = -1;

        ArrayList<Tile> t = new ArrayList<>();

        for (int i = notation.length() - 1; i >= 0; i--) {
            char c = notation.charAt(i);
            if (c == 'p') {
                delta = Tiles.OnePin.ordinal() - 1;
            } else if (c == 's') {
                delta = Tiles.OneSou.ordinal() - 1;
            } else if (c == 'm') {
                delta = -1;
            } else if (c == 'z') {
                delta = Tiles.East.ordinal() - 1;
            } else if (c == '0') {
                t.add(Tiles.from(5 + delta).toTile().markRed());
            } else {
                t.add(Tiles.from(c - '0' + delta).toTile());
            }
        }

        Collections.reverse(t);

        return t.toArray(new Tile[0]);
    }

    /**
     * Read a hand in format of <a href="https://tenhou.net/2/?">Tenhou</a>
     *
     * @param notation the notation string e.g. 123m406p789s12345z
     * @return The hand from the notation
     */
    public static Hand fromNotation(String notation) {

        return new Hand(fromNotationRaw(notation));
    }

    /**
     * Discard dora information and generate an array of id from the tile array
     */
    public static int[] toIntArray(Tile[] t) {
        int[] it = new int[t.length];
        for (int i = 0; i < t.length; i++) {
            it[i] = t[i].id;
        }
        return it;
    }

    /**
     * Parse a mentsu from the given tiles maintaining dora data
     *
     * @param tiles the given tiles
     * @return the mentsu with proper dora data
     */
    public static Mentsu toMentsu(Tile[] tiles) {

        Tile[] nw = new Tile[tiles.length];
        System.arraycopy(tiles, 0, nw, 0, tiles.length);

        for (int i = 0; i < nw.length; i++) {
            for (int j = i + 1; j < nw.length; j++) {
                if (nw[i].id > nw[j].id) {
                    var tmp = nw[i];
                    nw[i] = nw[j];
                    nw[j] = tmp;
                }
            }
        }

        if (tiles.length != 3 && tiles.length != 4) {
            throw new HandTileCountException("Mentsu size should be 3 or 4 but not " + tiles.length);
        }
        if (tiles.length == 4) {
            if (nw[0].id == nw[3].id) {
                var m = new Mentsu(nw[0].id, Mentsu.Kantsu);
                for (int i = 0; i < 4; i++) {
                    m.redDora[i] = nw[i].red;
                }
                return m;
            } else {
                throw new IllegalStateException("Mentsu cannot form a Kantsu");
            }
        } else {
            if (nw[0].id == nw[2].id) {
                var m = new Mentsu(nw[0].id, Mentsu.Kotsu);
                for (int i = 0; i < 3; i++) {
                    m.redDora[i] = nw[i].red;
                }
                return m;
            } else {
                if (nw[2].id - nw[0].id == 2) {
                    var m = new Mentsu(nw[0].id, Mentsu.Shuntsu);
                    for (int i = 0; i < 3; i++) {
                        m.redDora[i] = nw[i].red;
                    }
                    return m;
                } else {
                    throw new IllegalStateException("Mentsu cannot form a Kotsu or Shuntsu");
                }
            }
        }
    }


}
