package com.ecommerce.main.cartHandler;

import com.ecommerce.main.dao.ProductTable;

public class ProductQuantity {
	
	private int quantity;
	private ProductTable productTable;
	
	public ProductQuantity() {}
	@Override
	public String toString() {
		return "ProductQuantity [quantity=" + quantity + ", productTable=" + productTable + "]";
	}
	public ProductQuantity(int quantity, ProductTable productTable) {
		super();
		this.quantity = quantity;
		this.productTable = productTable;
	}
	
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public ProductTable getProductTable() {
		return productTable;
	}
	public void setProductTable(ProductTable productTable) {
		this.productTable = productTable;
	}
}
