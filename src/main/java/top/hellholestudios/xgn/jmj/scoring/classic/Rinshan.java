package top.hellholestudios.xgn.jmj.scoring.classic;

import top.hellholestudios.xgn.jmj.RonWrapper;
import top.hellholestudios.xgn.jmj.scoring.AgariInfo;
import top.hellholestudios.xgn.jmj.scoring.Yaku;

public class Rinshan extends Yaku {
    public Rinshan() {
        super("lskh", "嶺上開花");
    }

    @Override
    public boolean isNormalOnly() {
        return false;
    }

    @Override
    public int check(RonWrapper ron) {
        if(ron.agariInfo.source== AgariInfo.TileSource.Rinshan){
            return 1;
        }else{
            return 0;
        }
    }
}
