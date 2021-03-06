package top.hellholestudios.xgn.jmj.scoring.classic;

import top.hellholestudios.xgn.jmj.Mentsu;
import top.hellholestudios.xgn.jmj.RonWrapper;
import top.hellholestudios.xgn.jmj.Tile;
import top.hellholestudios.xgn.jmj.Tiles;
import top.hellholestudios.xgn.jmj.scoring.Yaku;
import top.hellholestudios.xgn.jmj.util.TileConstants;

public class Tanyao extends Yaku {
    public Tanyao() {
        super("dyj", "断幺九");
    }


    @Override
    public boolean isNormalOnly() {
        return false;
    }

    @Override
    public int check(RonWrapper ron) {
        if (ron.ruleset instanceof DefaultRuleset && ((DefaultRuleset) ron.ruleset).isTanyaoMenchin && !ron.isMenchin()) {
            return 0;
        }

        for(Tile t:ron.raw.getTiles()){
            if(Tiles.from(t.id).isTerminal()){
                return 0;
            }
        }

        if(Tiles.from(ron.lastTile.id).isTerminal()){
            return 0;
        }

        for(Mentsu m:ron.fuuro){
            for(Tiles t: TileConstants.TerminalTile) {
                if (m.contains(t.ordinal())){
                    return 0;
                }
            }
        }
        for(Mentsu m:ron.ankans){
            for(Tiles t: TileConstants.TerminalTile) {
                if (m.contains(t.ordinal())){
                    return 0;
                }
            }
        }
        return 1;
    }
}
