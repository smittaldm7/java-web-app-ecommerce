package model;


public class Supplier {
	static int idCounter=0;;
	int id;
	String name;
	String warehouseAddress;
	
	public Supplier(String name, String address)
	{
		id = ++idCounter;
		this.name=name;
		this.warehouseAddress=address;
	}

	@Override
	public String toString() {
		return "Supplier [id=" + id + ", name=" + name + ", warehouseAddress="
				+ warehouseAddress + "]";
	}

	public String getName() {
		return name;
	}

	public String getWarehouseAddress() {
		return warehouseAddress;
	}


}
