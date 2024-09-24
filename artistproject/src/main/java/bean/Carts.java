package bean;

public class Carts {
	private String cartId;
	private String customerId;
	private String paintingId;

	public Carts() {
	}

	public Carts(String customerId, String paintingId) {
		this.customerId = customerId;
		this.paintingId = paintingId;
	}

	public Carts(String cartId, String customerId, String paintingId) {
		super();
		this.cartId = cartId;
		this.customerId = customerId;
		this.paintingId = paintingId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getPaintingId() {
		return paintingId;
	}

	public void setPaintingId(String paintingId) {
		this.paintingId = paintingId;
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

}
