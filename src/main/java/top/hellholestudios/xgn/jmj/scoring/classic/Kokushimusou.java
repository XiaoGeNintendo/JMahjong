package top.hellholestudios.xgn.jmj.scoring.classic;

import top.hellholestudios.xgn.jmj.RonWrapper;
import top.hellholestudios.xgn.jmj.Tiles;
import top.hellholestudios.xgn.jmj.scoring.Yaku;
import top.hellholestudios.xgn.jmj.util.JavaUtil;

public class Kokushimusou extends Yaku {
    public Kokushimusou() {
        super("gsws", "国士無双");
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
    public int getCustomShanten(int[] cntArray) {
        int on=0;
        boolean two=false;
        for(int i=0;i<Tiles.values().length;i++){
            if(Tiles.from(i).isTerminal()){
                if(cntArray[i]>=2){
                    if(two){
                        on+=cntArray[i]-1;
                    }else{
                        on+=cntArray[i]-2;
                        two=true;
                    }
                }
            }else{
                on+=cntArray[i];
            }
        }
        return on-1+14-JavaUtil.sum(cntArray);
    }

    public boolean check(int[] cnt){
        return getCustomShanten(cnt)==-1;
    }

    @Override
    public int check(RonWrapper ron) {
        if (!ron.isMenchin() || ron.ankans.length != 0) {
            return 0;
        }

        int[] cnt = ron.raw.toCountArray();
        cnt[ron.lastTile.id]++;

        if(check(cnt)){
            return 13;
        }else{
            return 0;
        }
    }
}
