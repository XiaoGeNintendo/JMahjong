package com.hhs.xgn.jmj.scoring.classic;

import com.hhs.xgn.jmj.RonWrapper;
import com.hhs.xgn.jmj.scoring.Yaku;

public class Chiihou extends Yaku {
    public Chiihou() {
        super("dh", "地和");
    }

    @Override
    public boolean isNormalOnly() {
        return false;
    }

    @Override
    public boolean isYakuman() {
        return true;
    }

    @Override
    public int check(RonWrapper ron) {
        if(ron.sorted==null){
            //must be chiitoitsu or musou
            if(new Chiitoitsu().check(ron)==0 && new Kokushimusou().check(ron)==0 && new Kokushimusou13().check(ron)==0){
                return 0;
            }
        }
        if(ron.sorted==null){
            //must be chiitoitsu or musou
            if(new Chiitoitsu().check(ron)==0 && new Kokushimusou().check(ron)==0 && new Kokushimusou13().check(ron)==0){
                return 0;
            }
        }

        if(!ron.agariInfo.isDealer()) {
            return 13;
        }else{
            return 0;
        }
    }
}
