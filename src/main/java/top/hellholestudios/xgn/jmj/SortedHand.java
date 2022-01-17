package top.hellholestudios.xgn.jmj;

import java.util.Arrays;

/**
 * A sorted hand is a way to represent a hand.
 * What makes it different from a normal hand is the loss of ability to be broken down to different
 * shapes
 */
public class SortedHand {
    public Mentsu[] mentsus = new Mentsu[4];
    public Toitsu head;
    /**
     * Indicate the last tile's location in mentsus. <br>
     * 0-3 will be traditional mentsus <br>
     * 4 will be in the head <br>
     * -1 means the last tile is not given or the position can't be decided
     */
    public int lastTileIndicator = -1;

    @Override
    public String toString() {
        return "SortedHand{" +
                "mentsus=" + Arrays.toString(mentsus) +
                ", head=" + head +
                ", indicating " + lastTileIndicator +
                '}';
    }

    public SortedHand cpy() {
        SortedHand sh = new SortedHand();
        for (int i = 0; i < 4; i++) {
            if (this.mentsus[i] == null) {
                sh.mentsus[i] = null;
            } else {
                sh.mentsus[i] = this.mentsus[i].cpy();
            }
        }
        sh.head = this.head.cpy();

        return sh;
    }
}
