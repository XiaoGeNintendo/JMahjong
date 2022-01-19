package test.jmj;

import top.hellholestudios.xgn.jmj.Hand;
import top.hellholestudios.xgn.jmj.HandValidator;
import top.hellholestudios.xgn.jmj.Mentsu;
import top.hellholestudios.xgn.jmj.Tiles;
import top.hellholestudios.xgn.jmj.util.HandUtil;

public class Test4 {
    private static void l(String s){
        System.out.println(s);
    }

    public static void main(String[] args) {
        Hand hd= HandUtil.fromNotation("11111111111111z");
        l(""+HandValidator.validate(hd,false));

        hd= HandUtil.fromNotation("00000m0000p0000s1z");
        l(""+HandValidator.validate(hd,false));
        l(""+HandValidator.validate(hd,true));

        hd=HandUtil.fromNotation("1111m2222p3333s44z");
        hd.getTiles()[0].markRed();
        l(""+HandValidator.validate(hd,false));
        l(""+HandValidator.validate(hd,true));

        hd=HandUtil.fromNotation("1m");
        var last=HandUtil.fromNotationRaw("1m")[0];
        Mentsu[] ankan=new Mentsu[]{new Mentsu(Tiles.OnePin,Mentsu.Kotsu)};
        Mentsu[] fuuro=new Mentsu[0];
        l(""+HandValidator.validate(hd,last,ankan,fuuro,false));
        l(""+HandValidator.validate(hd,last,ankan,fuuro,true));
    }
}
