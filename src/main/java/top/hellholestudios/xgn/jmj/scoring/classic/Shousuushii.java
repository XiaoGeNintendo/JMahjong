package top.hellholestudios.xgn.jmj.scoring.classic;

import top.hellholestudios.xgn.jmj.Mentsu;
import top.hellholestudios.xgn.jmj.RonWrapper;
import top.hellholestudios.xgn.jmj.Tiles;
import top.hellholestudios.xgn.jmj.scoring.Yaku;

public class Shousuushii extends Yaku {
    public Shousuushii() {
        super("xsx", "小四喜");
    }


    private int wanted(int x){
        Tiles t=Tiles.from(x);
        if(t.isWind()){
            return 1;
        }
        return 0;
    }

    @Override
    public boolean isYakuman() {
        return true;
    }

    @Override
    public int check(RonWrapper ron) {
        int s=0;
        for (int i = 0; i < 4; i++) {
            if (ron.sorted.mentsus[i] != null){
                s+=wanted(ron.sorted.mentsus[i].tile);
            }
        }

        for(Mentsu m: ron.ankans){
            s+=wanted(m.tile);
        }
        for(Mentsu m:ron.fuuro){
            s+=wanted(m.tile); //ensured that terminal tiles cannot form shuntsu
        }

        if(s==3 && Tiles.from(ron.sorted.head.tile).isWind()){
            return 13;
        }
        return 0;
    }
}
