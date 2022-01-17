package test.jmj;

import top.hellholestudios.xgn.jmj.*;
import top.hellholestudios.xgn.jmj.scoring.AgariInfo;
import top.hellholestudios.xgn.jmj.scoring.HandDescriber;
import top.hellholestudios.xgn.jmj.scoring.classic.DefaultRuleset;
import top.hellholestudios.xgn.jmj.util.HandUtil;

import java.util.Map;

public class Test3 {
    public static void main(String[] args) {
        Hand hd = HandUtil.fromNotation("1z");

        Tile lastTile = new Tile(Tiles.East.ordinal());

        Mentsu[] ankans = new Mentsu[]{
                new Mentsu(Tiles.North.ordinal(), Mentsu.Kantsu),
                new Mentsu(Tiles.South.ordinal(), Mentsu.Kantsu),
                new Mentsu(Tiles.West.ordinal(), Mentsu.Kantsu)
        };

        Mentsu[] fuuro = new Mentsu[]{
                new Mentsu(Tiles.White.ordinal(), Mentsu.Kotsu)
        };

        int[] doraIDs = new int[]{Tiles.East.ordinal()};

        AgariInfo info = new AgariInfo(AgariInfo.TileSource.Ron, Wind.East, Wind.East, doraIDs);

        DefaultRuleset ruleset = new DefaultRuleset();
        HandDescriber desc = ruleset.describe(hd, lastTile, ankans, fuuro, new Tile[0], info);

        System.out.println("===========================");
        System.out.println(desc.getHan()+" Han "+desc.fu+" Fu");
        System.out.println("Score："+ruleset.score(desc,info));

        for(Map.Entry<String, Integer> e:desc.yakumans.entrySet()){
            System.out.println(ruleset.yakus.get(e.getKey()).displayName+" "+e.getValue()+"Han");
        }
        for(Map.Entry<String, Integer> e:desc.yakus.entrySet()){
            System.out.println(ruleset.yakus.get(e.getKey()).displayName+" "+e.getValue()+"Han");
        }
        System.out.println("===========================");
    }
}
