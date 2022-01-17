package top.hellholestudios.xgn.jmj;


public class Suggestion {
    /**
     * Which ID to throw. Can be -1 if no need to throw anything
     */
    public int throwID=-1;
    /**
     * Which ID to wait for in order to make hand progress.
     */
    public int[] waitID;

    /**
     * Total number of tiles to progress
     */
    public int efficiency;
}
