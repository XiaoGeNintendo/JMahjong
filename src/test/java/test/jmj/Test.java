package test.jmj;

import com.hhs.xgn.jmj.Hand;
import com.hhs.xgn.jmj.util.HandUtil;

import java.util.Arrays;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        while (true) {
            try {
                String notation = s.nextLine();
                Hand hd = HandUtil.fromNotation(notation);

                if(hd.is3NPlus2()) {
                    System.out.println(hd);
                    System.out.println(Arrays.toString(hd.toCountArray()));
                    System.out.println(Arrays.toString(HandUtil.breakdown(hd.toCountArray())));
                }else{
                    System.out.println(Arrays.toString(HandUtil.getListen(hd.toCountArray())));
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
