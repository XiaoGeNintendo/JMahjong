package top.hellholestudios.xgn.jmj.scoring.classic;

import top.hellholestudios.xgn.jmj.RonWrapper;
import top.hellholestudios.xgn.jmj.scoring.AgariInfo;
import top.hellholestudios.xgn.jmj.scoring.Yaku;

public class MenchinTsumo extends Yaku {
    public MenchinTsumo() {
        super("mqzm", "門前清自摸和");
    }

    @Override
    public boolean isNormalOnly() {
        return false;
    }

    @Override
    public int check(RonWrapper ron) {
        boolean ok=ron.isMenchin() && ron.agariInfo.agariType == AgariInfo.AgariType.Tsumo;
        if(ok){
            return 1;
        }else{
            return 0;
        }
    }
}
