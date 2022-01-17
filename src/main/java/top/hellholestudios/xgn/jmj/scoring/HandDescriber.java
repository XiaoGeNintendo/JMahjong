package top.hellholestudios.xgn.jmj.scoring;

import java.util.HashMap;
import java.util.Map;

public class HandDescriber {
    public int fu;
    public HashMap<String, Integer> yakus = new HashMap<>();

    public HashMap<String, Integer> yakumans = new HashMap<>();

    @Override
    public String toString() {
        return "HandDescriber{" +
                "fu=" + fu +
                ", totalHan=" + (getHan()) +
                ", yakus=" + yakus +
                ", yakumans=" + yakumans +
                '}';
    }

    public boolean hasYakuman() {
        return !yakumans.isEmpty();
    }

    /**
     * 13 han = 1 yakuman han <br>
     * 26 han = 2 yakuman han etc..
     *
     * @return Yakuman Han of this describer
     */
    public long getYakumanHan() {
        long x = 0;
        for (Map.Entry<String, Integer> e : yakumans.entrySet()) {
            x += e.getValue();
        }
        return x / 13;
    }

    /**
     * Does not count Yakuman han
     *
     * @return the han without yakuman han
     */
    public long getNormalHan() {
        long x = 0;
        for (Map.Entry<String, Integer> e : yakus.entrySet()) {
            x += e.getValue();
        }
        return x;
    }

    public long getHan(){
        return getNormalHan()+getYakumanHan()*13;
    }

    /**
     * Return whether such yaku/yakuman exists
     *
     * @param name the ID for the yaku
     * @return true if found
     */
    public boolean hasYaku(String name) {
        return yakus.containsKey(name) || yakumans.containsKey(name);
    }
}
