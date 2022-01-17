package com.hhs.xgn.jmj.scoring.classic;

import com.hhs.xgn.jmj.Mentsu;
import com.hhs.xgn.jmj.RonWrapper;
import com.hhs.xgn.jmj.scoring.Yaku;

public class Suukantsu extends Yaku {
    public Suukantsu() {
        super("4gz", "四槓子");
    }


    @Override
    public boolean isYakuman() {
        return true;
    }

    @Override
    public int check(RonWrapper ron) {
        int c=ron.ankans.length;

        for (Mentsu m : ron.fuuro) {
            if (m.type == Mentsu.Kantsu) {
                c++;
            }
        }

        if(c==4){
            return 13;
        }else{
            return 0;
        }
    }
}
