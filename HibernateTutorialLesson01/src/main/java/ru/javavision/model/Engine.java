package ru.javavision.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 
 * Created : 26/11/2017.
 */
@Entity
@Table(name="Engine", schema="pavel")
public class Engine  implements Serializable {
	private static final long serialVersionUID = 1L;

	public Engine() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", updatable=false, nullable=false)
	private int id;
//	@Column (name = "model")
    private String model;
//	@Column (name = "power")
    private int power;

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public Engine(String model, int power) {
		super();
		this.model = model;
		this.power = power;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + power;
		return result;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Engine [id=").append(id).append(", model=").append(model).append(", power=").append(power)
				.append("]");
		return builder.toString();
	}

 
 
    
    
}
