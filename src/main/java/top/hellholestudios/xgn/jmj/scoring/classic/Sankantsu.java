package top.hellholestudios.xgn.jmj.scoring.classic;

import top.hellholestudios.xgn.jmj.Mentsu;
import top.hellholestudios.xgn.jmj.RonWrapper;
import top.hellholestudios.xgn.jmj.scoring.Yaku;

public class Sankantsu extends Yaku {
    public Sankantsu() {
        super("3gz", "三槓子");
    }


    @Override
    public int check(RonWrapper ron) {
        int c=ron.ankans.length;

        for (Mentsu m : ron.fuuro) {
            if (m.type == Mentsu.Kantsu) {
                c++;
            }
        }

        if(c==3){
            return 2;
        }else{
            return 0;
        }
    }
}
