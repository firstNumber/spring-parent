package commain.fatorypackage;

import sun.applet.Main;

/**
 * @author liyongzhen
 * @create 2018-08-29 9:21
 **/
public class StaticNoodlesFatory {
    public  static  <T extends INoodles> T createNoodles(Class<T> clz)  {
        T result = null;
        try {
            result = (T)Class.forName(clz.getName()).newInstance();
        } catch (Exception  e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        Class clz = LzNoodles.class;
        System.out.println(clz);
        System.out.println(clz.getName());

        INoodles lz = StaticNoodlesFatory.createNoodles(LzNoodles.class);
        lz.desc();
    }

}
