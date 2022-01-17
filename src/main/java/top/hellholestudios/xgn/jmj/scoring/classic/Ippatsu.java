package top.hellholestudios.xgn.jmj.scoring.classic;

import top.hellholestudios.xgn.jmj.RonWrapper;
import top.hellholestudios.xgn.jmj.scoring.Yaku;

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
