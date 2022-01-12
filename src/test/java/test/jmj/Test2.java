package test.jmj;

import com.hhs.xgn.jmj.*;
import com.hhs.xgn.jmj.scoring.AgariInfo;
import com.hhs.xgn.jmj.scoring.HandDescriber;
import com.hhs.xgn.jmj.scoring.classic.DefaultRuleset;
import com.hhs.xgn.jmj.util.HandUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Test2 {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("输入手牌：");
                String notation = s.nextLine();
                Hand hd = HandUtil.fromNotation(notation);

                System.out.println("输入最后一张：");
                int t = Tiles.from(s.nextLine()).ordinal();


                System.out.println("输入副露情况。输入End结束！s=顺子 k=刻子 K=杠子 dK=暗杠");
                ArrayList<Mentsu> fuuro = new ArrayList<>();
                ArrayList<Mentsu> dks = new ArrayList<>();
                while (true) {
                    String id = s.next();
                    if ("End".equalsIgnoreCase(id)) {
                        s.nextLine();
                        break;
                    }
                    String type = s.next();
                    s.nextLine();

                    if ("s".equals(type)) {
                        fuuro.add(new Mentsu(Tiles.from(id).ordinal(), Mentsu.Shuntsu));
                    } else if ("k".equals(type)) {
                        fuuro.add(new Mentsu(Tiles.from(id).ordinal(), Mentsu.Kotsu));
                    } else if ("K".equals(type)) {
                        fuuro.add(new Mentsu(Tiles.from(id).ordinal(), Mentsu.Kantsu));
                    } else if ("dK".equals(type)) {
                        dks.add(new Mentsu(Tiles.from(id).ordinal(), Mentsu.Kantsu));
                    }else{
                        System.out.println("?");
                    }
                }

                System.out.println(hd + "<==自摸==" + Tiles.from(t));
                System.out.println("暗刻:"+dks+" 副露："+fuuro);
                AgariInfo info = new AgariInfo(AgariInfo.AgariType.Tsumo, AgariInfo.TileSource.Tsumo, false, Wind.East, Wind.South, new int[0]);

                DefaultRuleset ruleset = new DefaultRuleset();
                HandDescriber desc = ruleset.describe(hd, t, dks.toArray(new Mentsu[0]), fuuro.toArray(new Mentsu[0]), new Tile[0], info);

                System.out.println(desc.fu);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
