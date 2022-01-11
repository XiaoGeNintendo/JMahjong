package com.hhs.xgn.jmj.scoring;

import java.util.HashMap;
import java.util.Map;

public class HandDescriber {
    public int fu;
    public HashMap<String,Integer> yakus=new HashMap<>();

    public HashMap<String,Integer> yakumans=new HashMap<>();

    public boolean hasYakuman(){
        return !yakumans.isEmpty();
    }

    /**
     * 13 han = 1 yakuman han <br>
     * 26 han = 2 yakuman han etc..
     * @return Yakuman Han of this describer
     */
    public long getYakumanHan(){
        long x=0;
        for(Map.Entry<String,Integer> e:yakumans.entrySet()){
            x+=e.getValue();
        }
        return x/13;
    }

    public long getHan(){
        long x=0;
        for(Map.Entry<String,Integer> e:yakus.entrySet()){
            x+=e.getValue();
        }
        return x;
    }
}
