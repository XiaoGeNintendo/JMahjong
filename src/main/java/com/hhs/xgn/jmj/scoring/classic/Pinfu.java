package com.hhs.xgn.jmj.scoring.classic;

import com.hhs.xgn.jmj.Mentsu;
import com.hhs.xgn.jmj.RonWrapper;
import com.hhs.xgn.jmj.Tiles;
import com.hhs.xgn.jmj.scoring.Yaku;

public class Pinfu extends Yaku {
    public Pinfu() {
        super("ph", "平和");
    }


    @Override
    public int check(RonWrapper ron) {
        if (!ron.isMenchin()) {
            return 0;
        }

        if(ron.ankans.length!=0){
            return 0;
        }

        for (int i = 0; i < 4; i++) {
            if (ron.sorted.mentsus[i].type != Mentsu.Shuntsu) {
//                System.out.println("A1");
                return 0;
            }
        }

        Tiles t = Tiles.from(ron.sorted.head.tile);
        if (t.isWind(ron.agariInfo.seatWind) || t.isWind(ron.agariInfo.prevalantWind) || t.isDragon()) {
            return 0;
        }

        Mentsu m = ron.sorted.mentsus[ron.sorted.lastTileIndicator];
        if (m.type == Mentsu.Shuntsu) {
            Tiles ti = Tiles.from(m.tile);
            if (ti.getNextTile().ordinal() == ron.lastTile.id) {
//                System.out.println("A2");
                return 0;
            } else if (ti.ordinal() % 9 == 0 && ti.getNextTile().getNextTile().ordinal() == ron.lastTile.id ||
                    ti.ordinal() % 9 == 6 && ti.ordinal() == ron.lastTile.id) {
//                System.out.println("A3");
                return 0;
            }
        }

        return 1;
    }
}
