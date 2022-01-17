package top.hellholestudios.xgn.jmj.scoring.classic;

import top.hellholestudios.xgn.jmj.Mentsu;
import top.hellholestudios.xgn.jmj.RonWrapper;
import top.hellholestudios.xgn.jmj.Tile;
import top.hellholestudios.xgn.jmj.Tiles;
import top.hellholestudios.xgn.jmj.scoring.Yaku;

public class Chinroutou extends Yaku {
    public Chinroutou() {
        super("qlt", "清老頭");
    }


    @Override
    public boolean isYakuman() {
        return true;
    }

    @Override
    public String[] ignore() {
        return new String[]{"hqdyj","cqdyj","hlt"};
    }

    @Override
    public int check(RonWrapper ron) {
        for(Tile t:ron.raw.getTiles()){
            if(!Tiles.from(t.id).isOneNine()){
                return 0;
            }
        }

        if(!Tiles.from(ron.lastTile.id).isOneNine()){
            return 0;
        }

        for(Mentsu m:ron.ankans){
            for(Tile t:m.toTiles()){
                if(!Tiles.from(t.id).isOneNine()){
                    return 0;
                }
            }
        }
        for(Mentsu m:ron.fuuro){
            for(Tile t:m.toTiles()){
                if(!Tiles.from(t.id).isOneNine()){
                    return 0;
                }
            }
        }

        return 13;
    }
}
