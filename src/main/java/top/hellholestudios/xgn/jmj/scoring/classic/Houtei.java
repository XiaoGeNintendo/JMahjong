package top.hellholestudios.xgn.jmj.scoring.classic;

import top.hellholestudios.xgn.jmj.RonWrapper;
import top.hellholestudios.xgn.jmj.scoring.AgariInfo;
import top.hellholestudios.xgn.jmj.scoring.Yaku;

public class Houtei extends Yaku {
    public Houtei() {
        super("hdlyu", "河底撈魚");
    }


    @Override
    public boolean isNormalOnly() {
        return false;
    }
    @Override
    public int check(RonWrapper ron) {
        if(ron.agariInfo.source== AgariInfo.TileSource.Houtei){
            return 1;
        }else{
            return 0;
        }
    }
}
