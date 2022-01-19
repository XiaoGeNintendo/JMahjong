package top.hellholestudios.xgn.jmj;

import top.hellholestudios.xgn.jmj.util.JavaUtil;

public class HandValidator {

    /**
     * Validate the given hand to check for possible error-prone data
     * @param hand the hand to check
     * @param ignoreMinor whether to ignore minor errors like illegal red dora
     * @return the result in Enum ValidatorResult
     * @throws NullPointerException when your hand is completely a mess
     */
    public static ValidatorResult validate(Hand hand,boolean ignoreMinor){
        boolean[] reded=new boolean[Tiles.values().length];

        for(Tile x:hand.getTiles()){
            if(!ignoreMinor && x.red){
                if(!JavaUtil.contain(new int[]{Tiles.FiveSou.ordinal(),Tiles.FivePin.ordinal(),Tiles.FiveMan.ordinal()},x.id)){
                    return ValidatorResult.IllegalRedDora;
                }
                if(reded[x.id]){
                    return ValidatorResult.IllegalRedDora;
                }
                reded[x.id]=true;
            }
        }

        var arr=hand.toCountArray();
        for(int x:arr){
            if(x>4){
                return ValidatorResult.IllegalTileCountArray;
            }
        }

        return ValidatorResult.AllOK;
    }

    /**
     * Validate the given condition to prevent error-prone data
     * @param hand the hand to check
     * @param lastTile last tile
     * @param ankan list of ankans
     * @param fuuro list of fuuros
     * @param ignoreMinor whether to ignore minor mistakes
     * @return the result
     */
    public static ValidatorResult validate(Hand hand, Tile lastTile, Mentsu[] ankan, Mentsu[] fuuro,boolean ignoreMinor){
        var res=validate(hand,ignoreMinor);
        if(res!=ValidatorResult.AllOK){
            return res;
        }

        boolean[] reded=new boolean[Tiles.values().length];
        int[] arr=hand.toCountArray();

        for(Tile t:hand.getTiles()){
            if(t.red){
                if(!ignoreMinor && reded[t.id]){
                    return ValidatorResult.IllegalRedDora;
                }
                reded[t.id]=true;
            }
        }

        if(!ignoreMinor && lastTile.red) {
            if (reded[lastTile.id]){
                return ValidatorResult.IllegalRedDora;
            }
            reded[lastTile.id]=true;
        }
        arr[lastTile.id]++;

        int c=hand.getTiles().length;

        for(Mentsu m:ankan){
            if(m.type!=Mentsu.Kantsu){
                if(!ignoreMinor) {
                    return ValidatorResult.IllegalAnkan;
                }
            }
            arr[m.tile]+=4;
            c+=3;
        }

        for(Mentsu m:fuuro){

            c+=3;
            for(Tile t:m.toTiles()){
                arr[t.id]++;
                if(!ignoreMinor && t.red){
                    if(reded[t.id]){
                        return ValidatorResult.IllegalRedDora;
                    }
                    reded[t.id]=true;
                }
            }
        }

        if(c>14){
            return ValidatorResult.FuuroCountOverflow;
        }
        if(c<13){
            return ValidatorResult.FuuroCountUnderflow;
        }
        return ValidatorResult.AllOK;
    }
}
