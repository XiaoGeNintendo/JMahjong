package com.hhs.xgn.jmj;

import java.util.Arrays;

/**
 * A sorted hand is a way to represent a hand.
 * What makes it different from a normal hand is the loss of ability to be broken down to different
 * shapes
 */
public class SortedHand {
    public Mentsu[] mentsus = new Mentsu[4];
    public Toitsu head;

    @Override
    public String toString() {
        return "SortedHand{" +
                "mentsus=" + Arrays.toString(mentsus) +
                ", head=" + head +
                '}';
    }

    public SortedHand cpy(){
        SortedHand sh=new SortedHand();
        for(int i=0;i<4;i++){
            if(this.mentsus[i]==null){
                sh.mentsus[i]=null;
            }else{
                sh.mentsus[i]=this.mentsus[i].cpy();
            }
        }
        sh.head=this.head.cpy();

        return sh;
    }
}
