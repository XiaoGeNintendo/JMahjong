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


    private boolean wanted(int x,RonWrapper wrapper){
        Tiles t=Tiles.from(x);
        return t.isWind(wrapper.agariInfo.prevalantWind) || t.isWind((wrapper.agariInfo.seatWind))
                || t.isDragon();
    }

    @Override
    public int check(RonWrapper ron) {
        int s=0;
        for (int i = 0; i < 4; i++) {
            if (ron.sorted.mentsus[i] != null &&
                    wanted(ron.sorted.mentsus[i].tile,ron)){
                s++;
            }
        }

        for(Mentsu m: ron.ankans){
            if(wanted(m.tile,ron)){
                s++;
            }
        }
        for(Mentsu m:ron.fuuro){
            if(wanted(m.tile,ron)){
                s++;
            }
        }

        return s;
    }
}
