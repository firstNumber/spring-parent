package third;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author liyongzhen
 * @create 2018-07-09 15:40
 **/
public class FunctionThird {
    public static void main(String[] args) {
        Set<String> tim = new LinkedHashSet<>();
		tim.add("123");
		Set<String> timeSet =tim;

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("KEY","杨幂");

        for(String key : map.keySet()){
            System.out.println(map.get(key));
        }

        Thread t = new Thread();


        Map<Integer,String> mapI = new HashMap<>();
        for(int i =0;i<10;i++){
            mapI.putIfAbsent(i,"val_"+i);
        }
    }

}
