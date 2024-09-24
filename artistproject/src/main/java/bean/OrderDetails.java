package bean;

public class OrderDetails {
	
	private String orderNumber;
	private String paintingId;
	private Double price;

	public OrderDetails() {
	}

	public OrderDetails(String orderNumber, String paintingId, Double price) {
		this.orderNumber = orderNumber;
		this.paintingId = paintingId;
		this.price = price;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getPaintingId() {
		return paintingId;
	}

	public void setPaintingId(String paintingId) {
		this.paintingId = paintingId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
