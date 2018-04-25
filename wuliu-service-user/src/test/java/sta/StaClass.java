package sta;

public class StaClass {
	public String name;
	public StaClass next;

	public StaClass(String name, StaClass next) {
		this.name = name;
		this.next = next;
	}

	public StaClass() {
	}

	public static void main(String[] args) {
		a b = new a();
		b.a();

	}
}

class a {
	public void a() {
		Thread t = new Thread(new Runnable() {
			public void run() {
				System.out.println("1");
			}
		});
	}

}
