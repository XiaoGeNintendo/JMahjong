package test.jmj;

import top.hellholestudios.xgn.jmj.Hand;
import top.hellholestudios.xgn.jmj.ShantenCalculator;
import top.hellholestudios.xgn.jmj.scoring.classic.DefaultRuleset;
import top.hellholestudios.xgn.jmj.util.HandUtil;

import java.util.Scanner;

public class Test3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(true) {
            Hand hd = HandUtil.fromNotation(sc.nextLine());

//            System.out.println(Arrays.toString(hd.toCountArray()));
            long now=System.currentTimeMillis();
//            for(int i=0;i<1000;i++) {
            System.out.println(ShantenCalculator.getShanten(hd.toCountArray(),new DefaultRuleset()));
            System.out.println(ShantenCalculator.getNormalShanten(hd.toCountArray()));
//            }
            System.out.println(System.currentTimeMillis()-now+"ms");
        }
    }
}
