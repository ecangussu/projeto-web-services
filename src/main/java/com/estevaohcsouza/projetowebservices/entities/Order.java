package com.estevaohcsouza.projetowebservices.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.estevaohcsouza.projetowebservices.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	//Para garantir que o moment seja mostrado no JSON no formato de string do ISO 8601 > formata o JSON
	private Instant moment;
	
	//Usando integer para mostrar ao banco que estará sendo gravado um número inteiro
	//Tratamento interno (classe Order) > para o mundo externo manter o tipo OrderStatus
	private Integer orderStatus;
	
	@ManyToOne
	//Associação entre pedidos (Order) e usuário (User)
	//Muitos pedidos e um usuário (muitos para um > ManyToOne)
	//JPA transforma em chave estrangeira no BD
	@JoinColumn(name = "client_id")
	//Dá o nome do campo na tabela
	private User client;
	
	public Order() {
	}

	public Order(Long id, Instant moment, OrderStatus orderStatus, User client) {
		this.id = id;
		this.moment = moment;
		setOrderStatus(orderStatus);
		this.client = client;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public OrderStatus getOrderStatus() {
		//Convertendo valor inteiro para OrderStatus
		return OrderStatus.valueOf(orderStatus);
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		if(orderStatus != null) {
			//Está recebendo um OrderStatus porém o atributo orderStatus precisa receber um inteiro > usar o getCode
			this.orderStatus = orderStatus.getCode();			
		}
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	} 

}
