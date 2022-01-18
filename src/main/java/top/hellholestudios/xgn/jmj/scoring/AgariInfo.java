package top.hellholestudios.xgn.jmj.scoring;

import top.hellholestudios.xgn.jmj.Wind;

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
         * Normal Tsumo <br>
         * <b>Special Note: To check if a player wins by tsumo, please use isTsumoFamily</b>
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
        FirstTsumo;

        /**
         *
         * @return true if it is FirstTsumo, Haitei, Rinshan or Tsumo
         */
        public boolean isTsumoFamily() {
            return this==FirstTsumo || this==Haitei || this==Rinshan || this==Tsumo;
        }
    }

    public enum Riichi {
        NoRiichi,
        Riichi,
        WRiichi
    }

    public TileSource source;

    public Riichi riichi = Riichi.NoRiichi;
    public boolean ippatsu;
    public Wind prevalantWind;
    public Wind seatWind;
    public int[] doras;

    public AgariInfo(TileSource source, Wind prevalentWind, Wind seatWind, int[] doras) {
        this.source = source;
        this.prevalantWind = prevalentWind;
        this.seatWind = seatWind;
        this.doras = doras;
    }

    public AgariType getAgariType(){
        if(source.isTsumoFamily()){
            return AgariType.Tsumo;
        }else{
            return AgariType.Ron;
        }
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
