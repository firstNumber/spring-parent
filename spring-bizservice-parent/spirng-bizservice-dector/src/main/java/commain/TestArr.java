package commain;

import java.util.ArrayList;

/**
 * @author liyongzhen
 * @create 2018-08-28 10:13
 **/
public class TestArr<T> extends ArrayList<T> {

    public TestArr<T> filter(CallBack callBack){
		TestArr<T> newTestArr = new TestArr<T>();
		for(T element:this){
			Boolean return1 = callBack.isReturn(element);
			if(return1){
				newTestArr.add(element);
			}
		}
		return newTestArr;
	}

    public static void main(String[] args) {
        TestArr<Integer> testArr = new TestArr();
		testArr.add(1);
		testArr.add(2);
		testArr.add(3);
		testArr.add(4);
		testArr.add(5);
		testArr.add(6);
		TestArr<Integer> newArr = testArr.filter(new CallBack<Integer>() {
			@Override
			public boolean isReturn(Integer element) {
				return !(element%2==0);
			}
		});

		for(Integer element :newArr){
			System.out.println(element);
		}
    }
}
