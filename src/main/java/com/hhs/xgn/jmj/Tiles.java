package com.hhs.xgn.jmj;

public enum Tiles {

    OneMan("一",true),
    TwoMan("二",true),
    ThreeMan("三",true),
    FourMan("四",true),
    FiveMan("五",true),
    SixMan("六",true),
    SevenMan("七",true),
    EightMan("八",true),
    NineMan("九",false),

    OnePin("①",true),
    TwoPin("②",true),
    ThreePin("③",true),
    FourPin("④",true),
    FivePin("⑤",true),
    SixPin("⑥",true),
    SevenPin("⑦",true),
    EightPin("⑧",true),
    NinePin("⑨",false),

    OneSou("１",true),
    TwoSou("２",true),
    ThreeSou("３",true),
    FourSou("４",true),
    FiveSou("５",true),
    SixSou("６",true),
    SevenSou("７",true),
    EightSou("８",true),
    NineSou("９",false),

    East("東",false),
    South("南",false),
    West("西",false),
    North("北",false),
    White("白",false),
    Green("發",false),
    Red("中",false),

    Other("他",false)
    ;

    public final String utf;
    public final boolean next;

    public Tile toTile(){
        return new Tile(this.ordinal());
    }

    public boolean canFormShuntsu(){
        Tiles next=this.getNextTile();
        return next!=null && next.getNextTile()!=null;
    }

    public static Tiles from(int id) {
        return Tiles.values()[id];
    }

    /**
     *
     * @return the next tile of this tile, or null if it is a terminal tile
     */
    public Tiles getNextTile(){
        if(!next){
            return null;
        }
        return Tiles.values()[this.ordinal()+1];
    }

    Tiles(String utf, boolean next) {
        this.utf = utf;
        this.next = next;
    }
}
