package com.hhs.xgn.jmj.scoring.classic;

import com.hhs.xgn.jmj.Mentsu;
import com.hhs.xgn.jmj.RonWrapper;
import com.hhs.xgn.jmj.Tiles;
import com.hhs.xgn.jmj.scoring.Yaku;

public class Daisangen extends Yaku {
    public Daisangen() {
        super("dsy", "大三元");
    }


    @Override
    public String[] ignore() {
        return new String[]{"xsy"};
    }

    @Override
    public boolean isYakuman() {
        return true;
    }

    private int wanted(int x, RonWrapper wrapper){
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
                s+=wanted(ron.sorted.mentsus[i].tile,ron);
            }
        }

        for(Mentsu m: ron.ankans){
            s+=wanted(m.tile,ron);
        }
        for(Mentsu m:ron.fuuro){
            s+=wanted(m.tile,ron); //ensured that terminal tiles cannot form shuntsu
        }

        if(s==3){
            return 13;
        }
        return 0;
    }
}
