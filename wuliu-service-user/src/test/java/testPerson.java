import java.util.ArrayList;
import java.util.List;

public class testPerson {
	public static void main(String[] ages) {
		List<String> list = new ArrayList<String>() {
			{
				add("123123");
				add("aaaa");
			}
		};
		System.out.println(list.toString());
	}
}
