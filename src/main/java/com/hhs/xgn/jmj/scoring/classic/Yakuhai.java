package com.hhs.xgn.jmj.scoring.classic;

import com.hhs.xgn.jmj.Mentsu;
import com.hhs.xgn.jmj.RonWrapper;
import com.hhs.xgn.jmj.Tile;
import com.hhs.xgn.jmj.Tiles;
import com.hhs.xgn.jmj.scoring.Yaku;

public class Yakuhai extends Yaku {
    public Yakuhai() {
        super("yp", "役牌");
    }


    private int wanted(int x,RonWrapper wrapper){
        Tiles t=Tiles.from(x);
        int y=0;
        if(t.isWind(wrapper.agariInfo.prevalantWind)){
            y++;
        }
        if(t.isWind((wrapper.agariInfo.seatWind))){
            y++;
        }
        if(t.isDragon()){
            y++;
        }

        return y;
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

        return s;
    }
}
