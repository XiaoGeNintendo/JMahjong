package com.hhs.xgn.jmj;

import com.hhs.xgn.jmj.scoring.AgariInfo;
import com.hhs.xgn.jmj.scoring.Ruleset;

/**
 * Ron wrapper contains the information needed to determine a ron <br/>
 * Note that the hand contains a 3n+1 format one but the sorted hand contains a 3n+2 format
 */
public class RonWrapper {
    /**
     * Sorted hand of 3n+2 tiles with last tile
     */
    public SortedHand sorted;
    /**
     * Raw hand of 3n+1 tiles without last tile
     */
    public Hand raw;
    public int lastTile;
    public Mentsu[] ankans;
    public Mentsu[] fuuro;
    public Tile[] other;
    public AgariInfo agariInfo;
    public Ruleset ruleset;

    public RonWrapper(SortedHand sorted, Hand raw, int lastTile, Mentsu[] ankans, Mentsu[] fuuro, Tile[] other, AgariInfo agariInfo, Ruleset ruleset) {
        this.sorted = sorted;
        this.raw = raw;
        this.lastTile = lastTile;
        this.ankans = ankans;
        this.fuuro = fuuro;
        this.other = other;
        this.agariInfo = agariInfo;
        this.ruleset = ruleset;
    }

    public Tiles last(){
        return Tiles.from(lastTile);
    }

    public boolean isMenchin(){
        return fuuro.length==0;
    }
}
