package com.estevaohcsouza.projetowebservices.entities.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.estevaohcsouza.projetowebservices.entities.Order;
import com.estevaohcsouza.projetowebservices.entities.Product;

@Embeddable
//Por ser uma classe auxiliar de chave primária composta
public class OrderItemPK implements Serializable{
	//Classe auxiliar de chave primária composta
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	//Relacionamento muitos para um com pedido e produto
	@JoinColumn(name = "order_id")
	//nome da chave estrangeira na tabela do banco de dados relacional
	private Order order;
	//Referencia para o pedido
		
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	//Referencia para o produto
	
	public Order getOrder() {
		return order;
	}
	
	public void setOrder(Order order) {
		this.order = order;
	}
	
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	//Para comparar um item de pedido será necessário comparar tanto o pedido quanto o produto pois os dois juntos identificam o item
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
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
		OrderItemPK other = (OrderItemPK) obj;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}	

}
