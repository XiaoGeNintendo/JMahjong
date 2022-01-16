package com.hhs.xgn.jmj.scoring.classic;

import com.hhs.xgn.jmj.Mentsu;
import com.hhs.xgn.jmj.RonWrapper;
import com.hhs.xgn.jmj.Tiles;
import com.hhs.xgn.jmj.scoring.Yaku;
import com.hhs.xgn.jmj.util.TileConstant;

public class Junchantaiyao extends Yaku {
    public Junchantaiyao() {
        super("cqdyj", "纯全帯幺九");
    }


    @Override
    public String[] ignore() {
        return new String[]{"hqdyj"};
    }

    private boolean check(Mentsu m) {
        for (Tiles t : TileConstant.OneNineTile) {
            if (m.contains(t.ordinal())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int check(RonWrapper ron) {
        for (Mentsu m : ron.sorted.mentsus) {
            if (m!=null && !check(m)) {
                return 0;
            }
        }

        boolean ok=false;
        for(Tiles t:TileConstant.OneNineTile){
            if(t.ordinal()==ron.sorted.head.tile){
                ok=true;
                break;
            }
        }

        if(!ok){
            return 0;
        }

        for (Mentsu m : ron.ankans) {
            if (!check(m)) {
                return 0;
            }
        }

        for (Mentsu m : ron.fuuro) {
            if (!check(m)) {
                return 0;
            }
        }

        if (ron.isMenchin()) {
            return 3;
        } else {
            return 2;
        }
    }
}
