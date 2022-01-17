package com.hhs.xgn.jmj.scoring.classic;

import com.hhs.xgn.jmj.Mentsu;
import com.hhs.xgn.jmj.RonWrapper;
import com.hhs.xgn.jmj.Tiles;
import com.hhs.xgn.jmj.scoring.Yaku;

public class Shousangen extends Yaku {
    public Shousangen() {
        super("xsy", "小三元");
    }


    private int wanted(int x){
        Tiles t=Tiles.from(x);
        if(t.isDragon()){
            return 1;
        }
        return 0;
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

        if(s==2 && Tiles.from(ron.sorted.head.tile).isDragon()){
            return 2;
        }
        return 0;
    }
}
