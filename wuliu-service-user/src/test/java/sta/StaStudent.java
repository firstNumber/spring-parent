package sta;

public class StaStudent {
	public static void main(String[] args) {
		System.out.println("1322");
		StaClass[] a = new StaClass[10];
		a[0].name = "1";
		a[0].next.name = "2";
		StaClass bb = new StaClass(null, new StaClass());
		System.out.println();
	}
}
