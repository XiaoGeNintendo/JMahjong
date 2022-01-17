package test.jmj;

import top.hellholestudios.xgn.jmj.*;
import top.hellholestudios.xgn.jmj.scoring.AgariInfo;
import top.hellholestudios.xgn.jmj.scoring.HandDescriber;
import top.hellholestudios.xgn.jmj.scoring.classic.DefaultRuleset;
import top.hellholestudios.xgn.jmj.util.HandUtil;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Test2 {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("输入手牌。最后一张牌是荣和牌。");
                String notation = s.nextLine();
                Tile[] t = HandUtil.fromNotationRaw(notation);
                Tile[] tx = new Tile[t.length - 1];
                System.arraycopy(t, 0, tx, 0, t.length - 1);
                Hand hd = new Hand(tx);
                Tile last=t[t.length-1];

                System.out.println("输入副露和暗杠情况。用'#'分隔暗杠（先）和副露，用','分隔不同的面子:");
                ArrayList<Mentsu> fuuro = new ArrayList<>();
                ArrayList<Mentsu> dks = new ArrayList<>();

                String st = s.nextLine();
                String[] str = st.split("#");
                String[] str0 = str[0].split(",");
                String[] str1 = str[1].split(",");

                for (String x : str0) {
                    if(!x.trim().isEmpty()) {
                        dks.add(HandUtil.toMentsu(HandUtil.fromNotationRaw(x.trim())));
                    }
                }
                for (String x : str1) {
                    if(!x.trim().isEmpty()) {
                        fuuro.add(HandUtil.toMentsu(HandUtil.fromNotationRaw(x.trim())));
                    }
                }
//                System.out.println("输入副露情况。输入E结束！s=顺子 k=刻子 K=杠子 dK=暗杠");
//                ArrayList<Mentsu> fuuro = new ArrayList<>();
//                ArrayList<Mentsu> dks = new ArrayList<>();
//                while (true) {
//                    String id = s.next();
//                    if ("E".equalsIgnoreCase(id)) {
//                        s.nextLine();
//                        break;
//                    }
//                    String type = s.next();
//                    s.nextLine();
//
//                    if ("s".equals(type)) {
//                        fuuro.add(new Mentsu(Tiles.from(id).ordinal(), Mentsu.Shuntsu));
//                    } else if ("k".equals(type)) {
//                        fuuro.add(new Mentsu(Tiles.from(id).ordinal(), Mentsu.Kotsu));
//                    } else if ("K".equals(type)) {
//                        fuuro.add(new Mentsu(Tiles.from(id).ordinal(), Mentsu.Kantsu));
//                    } else if ("dK".equals(type)) {
//                        dks.add(new Mentsu(Tiles.from(id).ordinal(), Mentsu.Kantsu));
//                    }else{
//                        System.out.println("?");
//                    }
//                }

//                System.out.println("输入宝牌：");
//                Tile[] dora=HandUtil.fromNotationRaw(s.nextLine());

                System.out.println(hd + "<==自摸==" + last);
                System.out.println("暗刻:" + dks + " 副露：" + fuuro);
//                System.out.println("宝牌:"+Arrays.toString(dora));

//                AgariInfo info = new AgariInfo(AgariInfo.AgariType.Tsumo, AgariInfo.TileSource.Tsumo, false, Wind.East, Wind.East, HandUtil.toIntArray(dora));
                AgariInfo info = new AgariInfo(AgariInfo.TileSource.Tsumo, Wind.East, Wind.East, new int[0]);
                DefaultRuleset ruleset = new DefaultRuleset();
                ruleset.isAotenjou = true;
                HandDescriber desc = ruleset.describe(hd, last, dks.toArray(new Mentsu[0]), fuuro.toArray(new Mentsu[0]), new Tile[0], info);

                System.out.println("===========================");
                System.out.println(desc.getHan() + "翻" + desc.fu + "符");
                System.out.println("得点：" + ruleset.score(desc, info));
//                System.out.println(ruleset.yakus);

                for (Map.Entry<String, Integer> e : desc.yakumans.entrySet()) {
                    System.out.println(ruleset.yakus.get(e.getKey()).displayName + " " + e.getValue() / 13 + "倍役满");
                }
                for (Map.Entry<String, Integer> e : desc.yakus.entrySet()) {
                    System.out.println(ruleset.yakus.get(e.getKey()).displayName + " " + e.getValue() + "翻");
                }
                System.out.println("===========================");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
