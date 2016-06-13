package model;


public class Supplier {
	//static int idCounter=0;;
	int id;
	String name;
	String warehouseAddress;
	
	public Supplier(String name, String address)
	{
		//id = ++idCounter;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime
				* result
				+ ((warehouseAddress == null) ? 0 : warehouseAddress.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Supplier other = (Supplier) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (warehouseAddress == null) {
			if (other.warehouseAddress != null)
				return false;
		} else if (!warehouseAddress.equals(other.warehouseAddress))
			return false;
		return true;
	}


}
