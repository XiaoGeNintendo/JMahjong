package top.hellholestudios.xgn.jmj.scoring.classic;

import top.hellholestudios.xgn.jmj.Mentsu;
import top.hellholestudios.xgn.jmj.RonWrapper;
import top.hellholestudios.xgn.jmj.scoring.Yaku;

public class Toitoi extends Yaku {
    public Toitoi() {
        super("ddh", "対々");
    }


    @Override
    public int check(RonWrapper ron) {
        int c=0;
        for (Mentsu m : ron.sorted.mentsus) {
            if (m!=null && m.type != Mentsu.Shuntsu) {
                c++;
            }
        }
        for (Mentsu m : ron.ankans) {
            if (m.type != Mentsu.Shuntsu) {
                c++;
            }
        }
        for (Mentsu m : ron.fuuro) {
            if (m.type != Mentsu.Shuntsu) {
                c++;
            }
        }

        if(c==4){
            return 2;
        }else{
            return 0;
        }
    }
}
