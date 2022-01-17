package top.hellholestudios.xgn.jmj.scoring.classic;

import top.hellholestudios.xgn.jmj.Mentsu;
import top.hellholestudios.xgn.jmj.RonWrapper;
import top.hellholestudios.xgn.jmj.Tile;
import top.hellholestudios.xgn.jmj.scoring.Yaku;

public class Dora extends Yaku {
    public Dora() {
        super("bp", "ドラ");
    }

    @Override
    public boolean isNormalOnly() {
        return false;
    }

    @Override
    public int check(RonWrapper ron) {
        int s=0;
        for(int dora:ron.agariInfo.doras){
            for(Tile t:ron.raw.getTiles()){
                if(t.id==dora){
                    s++;
                }
            }

            if(dora==ron.lastTile.id){
                s++;
            }

            for(Mentsu m:ron.ankans){
                for(Tile t:m.toTiles()){
                    if(t.id==dora){
                        s++;
                    }
                }
            }

            for(Mentsu m:ron.fuuro){
                for(Tile t:m.toTiles()){
                    if(t.id==dora){
                        s++;
                    }
                }
            }
        }

        return s;
    }
}
