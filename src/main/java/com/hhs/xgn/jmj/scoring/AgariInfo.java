package com.hhs.xgn.jmj.scoring;

import com.hhs.xgn.jmj.Tiles;
import com.hhs.xgn.jmj.Wind;

public class AgariInfo {

    public enum AgariType {
        Tsumo,
        Ron;
    }

    public enum TileSource {
        /**
         * Tile from kan or pei
         */
        Rinshan,
        /**
         * Normal Tsumo
         */
        Tsumo,
        /**
         * Normal tile from others
         */
        Ron,
        /**
         * Last tile tsumo
         */
        Haitei,
        /**
         * Last tile discarded from others
         */
        Houtei,
        /**
         * From others' kantsus
         */
        Kan,
        /**
         * Tsumo but in first turn
         */
        FirstTsumo
    }

    public enum Riichi {
        NoRiichi,
        Riichi,
        WRiichi
    }

    public AgariType agariType;
    public TileSource source;

    public Riichi riichi = Riichi.NoRiichi;
    public boolean ippatsu;
    public Wind prevalantWind;
    public Wind seatWind;
    public int[] doras;

    public AgariInfo(AgariType agariType, TileSource source, Wind prevalentWind, Wind seatWind, int[] doras) {
        this.agariType = agariType;
        this.source = source;
        this.prevalantWind = prevalentWind;
        this.seatWind = seatWind;
        this.doras = doras;
    }

    public boolean isDealer(){
        return seatWind==Wind.East;
    }

    public AgariInfo markRiichi(){
        riichi=Riichi.Riichi;
        return this;
    }

    public AgariInfo markWRiichi(){
        riichi=Riichi.WRiichi;
        return this;
    }

    public AgariInfo markIppatsu(){
        ippatsu=true;
        return this;
    }


}
