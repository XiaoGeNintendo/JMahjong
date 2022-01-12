package com.hhs.xgn.jmj.scoring.classic;

import com.hhs.xgn.jmj.Mentsu;
import com.hhs.xgn.jmj.RonWrapper;
import com.hhs.xgn.jmj.Tiles;
import com.hhs.xgn.jmj.scoring.Yaku;

public class Iipeikou extends Yaku {
    public Iipeikou() {
        super("ybk", "一盃口");
    }


    @Override
    public int check(RonWrapper ron) {
        if(!ron.isMenchin()){
            return 0;
        }

        if(ron.ankans.length!=0){
            return 0;
        }
        for(int i=0;i<4;i++){
            if(ron.sorted.mentsus[i].type!=Mentsu.Shuntsu){
                continue;
            }
            for(int j=i+1;j<4;j++){
                if(ron.sorted.mentsus[j].type!=Mentsu.Shuntsu){
                    continue;
                }
                if(ron.sorted.mentsus[i].type==ron.sorted.mentsus[j].type){
                    return 1;
                }
            }
        }
        return 0;
    }
}
