package two;

/**
 * @author liyongzhen
 * @create 2018-05-15 16:30
 **/
public class Ball implements FunctionTwo {
    public  String name;
    public Ball(String name) {
        this.name= name;
    }

    void test(){
        Ball ball =new Ball("小花");
        System.out.println(ball.name);
    }
}
