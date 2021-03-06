package top.hellholestudios.xgn.jmj;

public enum ValidatorResult {
    AllOK(ErrorLevel.OK),
    /**
     * Contains more than 1 red dora of each kind or a red dora besides 0p0m0s
     */
    IllegalRedDora(ErrorLevel.MINOR),
    /**
     * Contains a tile which repeated more than 4 times
     */
    IllegalTileCountArray(ErrorLevel.MAJOR),
    /**
     * Contains an ankan that's not a Kantsu
     */
    IllegalAnkan(ErrorLevel.MINOR),
    /**
     * The total tile count in hand, fuuro and ankan exceeds 14
     */
    FuuroCountOverflow(ErrorLevel.MAJOR),
    /**
     * The total tile count in hand, fuuro and ankan &lt;13
     */
    FuuroCountUnderflow(ErrorLevel.MAJOR),
    /**
     * You can utilize this error to represent unexpected cases
     */
    FailMajor(ErrorLevel.MAJOR),
    /**
     * You can utilize this error to represent unexpected cases
     */
    FailMinor(ErrorLevel.MINOR)
    ;

    enum ErrorLevel{
        OK,
        MINOR,
        MAJOR
    }

    public final ErrorLevel level;
    ValidatorResult(ErrorLevel level){
        this.level=level;
    }
}
