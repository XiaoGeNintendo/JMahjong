package top.hellholestudios.xgn.jmj;


/**
 * Base class for all triples in hand <br>
 * Depend on the type, it can be Shuntsu or Kotsu
 */
public class Mentsu {

    public static final int Kotsu = 0;
    public static final int Shuntsu = 1;
    public static final int Kantsu = 2;

    /**
     * The first tile ID of this mentsu
     */
    public int tile;
    public int type;

    /**
     * Whether each tile is a red dora. Shuntsus are sorted from small to big
     */
    public boolean[] redDora=new boolean[4];

    public Mentsu cpy() {
        return new Mentsu(tile, type);
    }

    public Mentsu(int tile, int type) {
        if (type == Shuntsu && !Tiles.from(tile).canFormShuntsu()) {
            throw new IllegalArgumentException("Cannot start a shuntsu with given tile:" + Tiles.from(tile).utf);
        }
        this.tile = tile;
        this.type = type;
    }

    public Mentsu(Tiles tile, int type) {
        new Mentsu(tile.ordinal(), type);
    }

    /**
     * @return all the tiles in this mentsu
     */
    public Tile[] toTiles(){
        Tiles ts=Tiles.from(tile);
        Tile t0=new Tile(ts.ordinal(),redDora[0]);
        if(type==Shuntsu){
            Tile t1=new Tile(ts.getNextTile().ordinal(),redDora[1]);
            Tile t2=new Tile(ts.getNextTile().getNextTile().ordinal(),redDora[2]);
            return new Tile[]{t0,t1,t2};
        }else if(type==Kotsu){
            Tile t1=new Tile(ts.ordinal(),redDora[1]);
            Tile t2=new Tile(ts.ordinal(),redDora[2]);

            return new Tile[]{t0,t1,t2};
        }else if(type==Kantsu){
            Tile t1=new Tile(ts.ordinal(),redDora[1]);
            Tile t2=new Tile(ts.ordinal(),redDora[2]);
            Tile t3=new Tile(ts.ordinal(),redDora[3]);

            return new Tile[]{t0,t1,t2,t3};
        }else{
            throw new IllegalStateException("Unknown Mentsu type:"+type);
        }
    }

    @Override
    public String toString() {
        if (type == Shuntsu) {
            return "[" + Tiles.from(tile).utf + Tiles.from(tile).getNextTile().utf + Tiles.from(tile).getNextTile().getNextTile().utf + "]";
        } else {
            String s = Tiles.from(tile).utf;
            if (type == Kantsu) {
                return "[" + s + s + s + s + "]";
            } else {
                return "[" + s + s + s + "]";
            }
        }
    }

    /**
     * Check whether given tile is in this mentsu
     * @param t the id of the tile
     * @return true if it is in the mentsu
     */
    public boolean contains(int t) {
        if (type == Kotsu || type==Kantsu) {
            return t == tile;
        } else {
            return t == tile ||
                    t == Tiles.from(tile).getNextTile().ordinal() ||
                    t == Tiles.from(tile).getNextTile().getNextTile().ordinal();
        }
    }
}
