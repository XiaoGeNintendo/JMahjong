package top.hellholestudios.xgn.jmj.scoring.classic;

import top.hellholestudios.xgn.jmj.Mentsu;
import top.hellholestudios.xgn.jmj.RonWrapper;
import top.hellholestudios.xgn.jmj.Tiles;
import top.hellholestudios.xgn.jmj.scoring.Yaku;
import top.hellholestudios.xgn.jmj.util.TileConstants;

public class Chantaiyao extends Yaku {
    public Chantaiyao() {
        super("hqdyj", "混全帯幺九");
    }


    private boolean check(Mentsu m) {
        for (Tiles t : TileConstants.TerminalTile) {
            if (m.contains(t.ordinal())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int check(RonWrapper ron) {
        for (Mentsu m : ron.sorted.mentsus) {
            if (m!=null && !check(m)) {
                return 0;
            }
        }

        boolean ok=false;
        for(Tiles t: TileConstants.TerminalTile){
            if(t.ordinal()==ron.sorted.head.tile){
                ok=true;
                break;
            }
        }

        if(!ok){
            return 0;
        }

        for (Mentsu m : ron.ankans) {
            if (!check(m)) {
                return 0;
            }
        }

        for (Mentsu m : ron.fuuro) {
            if (!check(m)) {
                return 0;
            }
        }

        if (ron.isMenchin()) {
            return 2;
        } else {
            return 1;
        }
    }
}
