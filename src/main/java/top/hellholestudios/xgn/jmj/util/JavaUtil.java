package top.hellholestudios.xgn.jmj.util;

public class JavaUtil {
    public static boolean debug = false;

    /**
     * Returns true if the array contains the given element
     * @param x the array
     * @param y the element
     * @return true if it contains
     */
    public static boolean contain(int[] x,int y){
        for(int z: x){
            if(z==y){
                return true;
            }
        }
        return false;
    }
}
