package com.hhs.xgn.jmj.scoring.classic;

import com.hhs.xgn.jmj.RonWrapper;
import com.hhs.xgn.jmj.scoring.AgariInfo;
import com.hhs.xgn.jmj.scoring.Yaku;

public class Ippatsu extends Yaku {
    public Ippatsu() {
        super("yf", "一発");
    }

    @Override
    public boolean isNormalOnly() {
        return false;
    }

    @Override
    public int check(RonWrapper ron) {
        if(ron.agariInfo.ippatsu){
            return 1;
        }else{
            return 0;
        }
    }
}
