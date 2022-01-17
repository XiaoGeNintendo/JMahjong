package test.jmj;

import top.hellholestudios.xgn.jmj.Hand;
import top.hellholestudios.xgn.jmj.ShantenCalculator;
import top.hellholestudios.xgn.jmj.Suggestion;
import top.hellholestudios.xgn.jmj.Tiles;
import top.hellholestudios.xgn.jmj.scoring.Yaku;
import top.hellholestudios.xgn.jmj.scoring.classic.DefaultRuleset;
import top.hellholestudios.xgn.jmj.util.HandUtil;

import java.util.Map;
import java.util.Scanner;

public class Test3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for(Map.Entry<String, Yaku> x:new DefaultRuleset().yakus.entrySet()){
            System.out.println(x.getKey()+" "+x.getValue().displayName);
        }

        while (true) {
            Hand hd = HandUtil.fromNotation(sc.nextLine());

//            System.out.println(Arrays.toString(hd.toCountArray()));
            long now = System.currentTimeMillis();
//            for(int i=0;i<1000;i++) {
            System.out.println("向听数：" + ShantenCalculator.getShanten(hd.toCountArray(), new DefaultRuleset()));
            var x = ShantenCalculator.suggest(hd.toCountArray(), new DefaultRuleset());
            for(Suggestion y:x){
                if(y.throwID==-1) {
                    System.out.print("待" + y.efficiency + "张 - ");
                }else{
                    System.out.print("打" + Tiles.from(y.throwID).utf + " 待" + y.efficiency + "张 - ");
                }

                for (int z : y.waitID) {
                    System.out.print(Tiles.from(z).utf);
                }
                System.out.println();
            }
//            }
            System.out.println(System.currentTimeMillis() - now + "ms");
        }
    }
}
