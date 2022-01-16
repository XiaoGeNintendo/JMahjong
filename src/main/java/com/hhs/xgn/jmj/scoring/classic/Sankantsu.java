package com.hhs.xgn.jmj.scoring.classic;

import com.hhs.xgn.jmj.Mentsu;
import com.hhs.xgn.jmj.RonWrapper;
import com.hhs.xgn.jmj.scoring.Yaku;

public class Sankantsu extends Yaku {
    public Sankantsu() {
        super("sgz", "三槓子");
    }


    @Override
    public int check(RonWrapper ron) {
        int c=ron.ankans.length;

        for (Mentsu m : ron.fuuro) {
            if (m.type == Mentsu.Kantsu) {
                c++;
            }
        }

        if(c==3){
            return 2;
        }else{
            return 0;
        }
    }
}
