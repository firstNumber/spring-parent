package commain.fatorypackage;

/**
 * @author liyongzhen
 * @create 2018-08-29 9:45
 **/
public class MulWayNoodlesFactory {
    public static INoodles createPm(){
        return new PaoNoodles();
    }
    public static  INoodles createLz(){
        return new LzNoodles();
    }

    public static void main(String[] args) {
        INoodles lz = MulWayNoodlesFactory.createPm();
        lz.desc();
    }
}
