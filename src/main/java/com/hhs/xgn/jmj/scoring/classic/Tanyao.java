package com.hhs.xgn.jmj.scoring.classic;

import com.hhs.xgn.jmj.Mentsu;
import com.hhs.xgn.jmj.RonWrapper;
import com.hhs.xgn.jmj.Tile;
import com.hhs.xgn.jmj.Tiles;
import com.hhs.xgn.jmj.scoring.Yaku;
import com.hhs.xgn.jmj.util.TileConstant;

public class Tanyao extends Yaku {
    public Tanyao() {
        super("dyj", "断幺九");
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

        for(Mentsu m:ron.fuuro){
            for(Tiles t: TileConstant.TerminalTile) {
                if (m.contains(t.ordinal())){
                    return 0;
                }
            }
        }
        for(Mentsu m:ron.ankans){
            for(Tiles t: TileConstant.TerminalTile) {
                if (m.contains(t.ordinal())){
                    return 0;
                }
            }
        }
        return 1;
    }
}
