package top.hellholestudios.xgn.jmj.scoring;

import top.hellholestudios.xgn.jmj.RonWrapper;

public abstract class Yaku {
    public String name;
    public String displayName;

    public Yaku(String name, String displayName) {
        this.name = name;
        this.displayName = displayName;
    }


    /**
     * When this yaku is applied, ignore everything in this list <br>
     * It is recommended to remove same variants with the less value <br>
     * For example, ignoring riichi in W-Riichi.
     * @return the ignored yaku
     */
    public String[] ignore(){
        return new String[0];
    }

    /**
     * Mark this yaku as normal only. When a winning hand is not special, check function will not be called to prevent NPE.
     */
    public boolean isNormalOnly(){
        return true;
    }

    public boolean isYakuman(){
        return false;
    }

    /**
     * Return a custom shanten for this yaku.
     * @param cntArray the count array of the given 3n+2 hand
     * @return the custom shanten or -2 for not applied. 0 = tenpai, -1 = ron
     */
    public int getCustomShanten(int[] cntArray){
        return -2;
    }

    /**
     * Return the number of "飜"(han) given the sorted hand or the raw hand. <br>
     * It is recommended to use the sorted hand as the first option.<br>
     * It is ensured that the sorted hand is a valid representation of the raw hand <br>
     * sorted can be null in case the user entered a non-traditional hand
     * @param ron the information
     * @return the number of han. Return 0 for not applied.
     */
    public abstract int check(RonWrapper ron);
}
