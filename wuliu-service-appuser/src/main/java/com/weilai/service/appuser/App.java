package com.weilai.service.appuser;

public class App {
	public static void main(String[] args) {
		// System.out.println("Hello World!");
		// List<String> orders = new ArrayList<>();
		// orders.add("a");
		// orders.add("b");
		// List<String> oneBatchNumList = new ArrayList<String>();
		// Iterator<String> it = orders.iterator();
		// while (it.hasNext()) {
		// if (it.next().equals("a")) {
		// it.remove();
		// }
		// }
		// for (String cc : orders) {
		// System.out.println(cc);
		// }
		System.out.println(TmsType.CUSTOMER.name().equals("CUSTOMER"));

	}

	public enum TmsType {
		CUSTOMER("C"), STORAGE("W"), STORE("S"), ORDER("G");
		private String value;

		TmsType(String value) {
			this.value = value;
		}

		public String getCode() {
			return this.value;
		}
	}
}
