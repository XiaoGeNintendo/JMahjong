package top.hellholestudios.xgn.jmj.scoring.classic;

import top.hellholestudios.xgn.jmj.RonWrapper;
import top.hellholestudios.xgn.jmj.scoring.Yaku;

public class Chiitoitsu extends Yaku {
    public Chiitoitsu() {
        super("qdz", "七対子");
    }

    @Override
    public boolean isNormalOnly() {
        return false;
    }

    @Override
    public int getCustomShanten(int[] cntArray) {

        int dz=0;
        for(int i:cntArray){
            if(i>=2){
                dz++;
            }
        }

        return 6-dz;
    }

    public boolean check(int[] count){
        return getCustomShanten(count)==-1;
    }

    @Override
    public int check(RonWrapper ron) {
        if(!ron.isMenchin() || ron.ankans.length>0 || ron.sorted!=null){
            return 0;
        }

        int[] count=ron.raw.toCountArray();
        count[ron.lastTile.id]++;

        if(check(count)){
            return 2;
        }else{
            return 0;
        }
    }
}
