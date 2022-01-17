package top.hellholestudios.xgn.jmj.scoring.classic;

import top.hellholestudios.xgn.jmj.RonWrapper;
import top.hellholestudios.xgn.jmj.Tile;
import top.hellholestudios.xgn.jmj.Tiles;
import top.hellholestudios.xgn.jmj.scoring.Yaku;

public class Chiitoitsu extends Yaku {
    public Chiitoitsu() {
        super("qdz", "七対子");
    }

    @Override
    public boolean isNormalOnly() {
        return false;
    }

    @Override
    public int check(RonWrapper ron) {
        if(!ron.isMenchin() || ron.ankans.length>0 || ron.sorted!=null){
            return 0;
        }

        int[] count=new int[Tiles.values().length];

        for(Tile t: ron.raw.getTiles()){
            count[t.id]++;
        }
        count[ron.lastTile.id]++;

        for(int i=0;i<Tiles.values().length;i++){
            if(count[i]!=0 && count[i]!=2){
                return 0;
            }
        }
        return 2;
    }
}
