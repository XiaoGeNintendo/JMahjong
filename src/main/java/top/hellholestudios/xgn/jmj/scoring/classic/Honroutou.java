package top.hellholestudios.xgn.jmj.scoring.classic;

import top.hellholestudios.xgn.jmj.Mentsu;
import top.hellholestudios.xgn.jmj.RonWrapper;
import top.hellholestudios.xgn.jmj.Tile;
import top.hellholestudios.xgn.jmj.Tiles;
import top.hellholestudios.xgn.jmj.scoring.Yaku;

public class Honroutou extends Yaku {
    public Honroutou() {
        super("hlt", "混老頭");
    }


    @Override
    public boolean isNormalOnly() {
        return false;
    }

    @Override
    public String[] ignore() {
        return new String[]{"hqdyj","cqdyj"};
    }

    @Override
    public int check(RonWrapper ron) {

        if(ron.sorted==null){
            //must be chiitoitsu
            if(new Chiitoitsu().check(ron)==0){
                return 0;
            }
        }

        for(Tile t:ron.raw.getTiles()){
            if(!Tiles.from(t.id).isTerminal()){
                return 0;
            }
        }

        if(!Tiles.from(ron.lastTile.id).isTerminal()){
            return 0;
        }

        for(Mentsu m:ron.ankans){
            for(Tile t:m.toTiles()){
                if(!Tiles.from(t.id).isTerminal()){
                    return 0;
                }
            }
        }
        for(Mentsu m:ron.fuuro){
            for(Tile t:m.toTiles()){
                if(!Tiles.from(t.id).isTerminal()){
                    return 0;
                }
            }
        }

        return 2;
    }
}
