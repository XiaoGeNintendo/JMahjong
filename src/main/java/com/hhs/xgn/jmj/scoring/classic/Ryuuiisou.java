package com.hhs.xgn.jmj.scoring.classic;

import com.hhs.xgn.jmj.Mentsu;
import com.hhs.xgn.jmj.RonWrapper;
import com.hhs.xgn.jmj.Tile;
import com.hhs.xgn.jmj.Tiles;
import com.hhs.xgn.jmj.scoring.Yaku;

public class Ryuuiisou extends Yaku {
    public Ryuuiisou() {
        super("lys", "緑一色");
    }

    @Override
    public boolean isYakuman() {
        return true;
    }

    @Override
    public String[] ignore() {
        return new String[]{"hys"};
    }

    @Override
    public int check(RonWrapper ron) {
        for (Tile t : ron.raw.getTiles()) {
            if (!Tiles.from(t.id).isGreen()) {
                return 0;
            }
        }

        if (!Tiles.from(ron.lastTile.id).isGreen()) {
            return 0;
        }

        for (Mentsu m : ron.ankans) {
            for (Tile t : m.toTiles()) {
                if (!Tiles.from(t.id).isGreen()) {
                    return 0;
                }
            }
        }
        for (Mentsu m : ron.fuuro) {
            for (Tile t : m.toTiles()) {
                if (!Tiles.from(t.id).isGreen()) {
                    return 0;
                }
            }
        }

        return 13;
    }
}
