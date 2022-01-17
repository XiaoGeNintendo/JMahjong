package com.hhs.xgn.jmj.scoring.classic;

import com.hhs.xgn.jmj.Mentsu;
import com.hhs.xgn.jmj.RonWrapper;
import com.hhs.xgn.jmj.Tiles;
import com.hhs.xgn.jmj.scoring.Yaku;

public class Daisuushii extends Yaku {
    public Daisuushii() {
        super("dsx", "大四喜");
    }


    @Override
    public String[] ignore() {
        return new String[]{"xsx"};
    }

    @Override
    public boolean isYakuman() {
        return true;
    }

    private int wanted(int x, RonWrapper wrapper){
        Tiles t=Tiles.from(x);
        if(t.isWind()){
            return 1;
        }
        return 0;
    }

    @Override
    public int check(RonWrapper ron) {
        int s=0;
        for (int i = 0; i < 4; i++) {
            if (ron.sorted.mentsus[i] != null){
                s+=wanted(ron.sorted.mentsus[i].tile,ron);
            }
        }

        for(Mentsu m: ron.ankans){
            s+=wanted(m.tile,ron);
        }
        for(Mentsu m:ron.fuuro){
            s+=wanted(m.tile,ron); //ensured that terminal tiles cannot form shuntsu
        }

        if(s==4){
            return 26;
        }
        return 0;
    }
}
