package top.hellholestudios.xgn.jmj.scoring.classic;

import top.hellholestudios.xgn.jmj.Mentsu;
import top.hellholestudios.xgn.jmj.RonWrapper;
import top.hellholestudios.xgn.jmj.Tile;
import top.hellholestudios.xgn.jmj.scoring.Yaku;

public class RedDora extends Yaku {
    public RedDora() {
        super("hbp", "赤ドラ");
    }

    @Override
    public boolean isNormalOnly() {
        return false;
    }

    @Override
    public int check(RonWrapper ron) {
        int s=0;

        for(Tile t:ron.raw.getTiles()){
            if(t.red){
                s++;
            }
        }

        if(ron.lastTile.red){
            s++;
        }

        for(Mentsu m:ron.ankans){
            for(Tile t:m.toTiles()){
                if(t.red){
                    s++;
                }
            }
        }

        for(Mentsu m:ron.fuuro){
            for(Tile t:m.toTiles()){
                if(t.red){
                    s++;
                }
            }
        }


        return s;
    }
}
