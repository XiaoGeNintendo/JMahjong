package top.hellholestudios.xgn.jmj.scoring;

import top.hellholestudios.xgn.jmj.Hand;
import top.hellholestudios.xgn.jmj.Mentsu;
import top.hellholestudios.xgn.jmj.Tile;

import java.util.HashMap;

/**
 * Define a rule set for calculating score.
 * Implement <code>getScore()</code>
 */
public abstract class Ruleset {
    public HashMap<String,Yaku> yakus=new HashMap<String, Yaku>();

    public void registerYaku(Yaku yaku){
        if(yakus.containsKey(yaku.name)){
            throw new IllegalStateException("Yaku already registered:"+yaku.name);
        }
        yakus.put(yaku.name,yaku);
    }

    public abstract long score(HandDescriber describer, AgariInfo agariInfo);

    /**
     * Describe a hand with scoring information
     * @param hand A 3n+1 hand
     * @param lastTile the last tile
     * @param ankan List of ankans
     * @param fuuro List of fuuros
     * @param other Other tiles
     * @param agariInfo Agari Information
     * @return The hand describer
     */
    public abstract HandDescriber describe(Hand hand, Tile lastTile, Mentsu[] ankan, Mentsu[] fuuro, Tile[] other, AgariInfo agariInfo);
}
