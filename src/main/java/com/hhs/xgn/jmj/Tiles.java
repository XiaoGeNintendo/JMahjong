package com.hhs.xgn.jmj;

import com.hhs.xgn.jmj.util.TileConstant;

public enum Tiles {

    OneMan("一", true),
    TwoMan("二", true),
    ThreeMan("三", true),
    FourMan("四", true),
    FiveMan("五", true),
    SixMan("六", true),
    SevenMan("七", true),
    EightMan("八", true),
    NineMan("九", false),

    OnePin("①", true),
    TwoPin("②", true),
    ThreePin("③", true),
    FourPin("④", true),
    FivePin("⑤", true),
    SixPin("⑥", true),
    SevenPin("⑦", true),
    EightPin("⑧", true),
    NinePin("⑨", false),

    OneSou("１", true),
    TwoSou("２", true),
    ThreeSou("３", true),
    FourSou("４", true),
    FiveSou("５", true),
    SixSou("６", true),
    SevenSou("７", true),
    EightSou("８", true),
    NineSou("９", false),

    East("東", false),
    South("南", false),
    West("西", false),
    North("北", false),
    White("白", false),
    Green("發", false),
    Red("中", false),

    Other("他", false);

    public final String utf;
    public final boolean next;

    public Tile toTile() {
        return new Tile(this.ordinal());
    }

    @Override
    public String toString() {
        return utf;
    }

    public boolean canFormShuntsu() {
        Tiles next = this.getNextTile();
        return next != null && next.getNextTile() != null;
    }

    public static Tiles from(int id) {
        return Tiles.values()[id];
    }

    public static Tiles from(String notation) {
        int delta = 0;
        switch (notation.charAt(1)) {
            case 'm':
                delta = -1;
                break;
            case 'p':
                delta = Tiles.OnePin.ordinal() - 1;
                break;
            case 's':
                delta = Tiles.OneSou.ordinal() - 1;
                break;
            case 'z':
                delta = Tiles.East.ordinal() - 1;
                break;
            default:
                throw new IllegalArgumentException("Unknown notation:" + notation);
        }
        delta += notation.charAt(0) - '0';
        return Tiles.values()[delta];
    }

    /**
     * @return the next tile of this tile, or null if it is a terminal tile
     */
    public Tiles getNextTile() {
        if (!next) {
            return null;
        }
        return Tiles.values()[this.ordinal() + 1];
    }

    Tiles(String utf, boolean next) {
        this.utf = utf;
        this.next = next;
    }

    /**
     * @return true if it is 19m19p19s or not number tiles
     */
    public boolean isTerminal() {
        return ordinal() >= Tiles.East.ordinal() || ordinal() % 9 == 0 || ordinal() % 9 == 8;
    }

    /**
     * @return true if it is Red White or Green
     */
    public boolean isDragon() {
        return ordinal() >= Tiles.White.ordinal() && ordinal() <= Tiles.Red.ordinal();
    }

    public boolean isWind(Wind w) {
        return ordinal() == w.ordinal() + Tiles.East.ordinal();
    }

    public boolean isWind() {
        return ordinal() >= Tiles.East.ordinal() && ordinal() <= Tiles.North.ordinal();
    }

    public boolean is1or7() {
        return ordinal() % 9 == 0 || ordinal() % 9 == 6;
    }

    public boolean isOneNine() {
        return ordinal() < Tiles.East.ordinal() && (ordinal() % 9 == 0 || ordinal() % 9 == 8);
    }

    public boolean isGreen() {
        for(Tiles t: TileConstant.GreenTiles){
            if(t==this){
                return true;
            }
        }
        return false;
    }
}
