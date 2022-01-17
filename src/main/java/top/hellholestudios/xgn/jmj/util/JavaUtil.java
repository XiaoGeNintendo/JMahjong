package top.hellholestudios.xgn.jmj.util;

public class JavaUtil {
    public static boolean debug = false;

    /**
     * Return the sum of the int array
     * @param x the int array to sum with
     * @return the sum
     */
    public static int sum(int[] x){
        int y=0;
        for(int z:x){
            y+=z;
        }
        return y;
    }

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
