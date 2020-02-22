package ru.javavision.model;

//import lombok.*;

import java.io.Serializable;


/**
 * 
 * Created : 26/11/2017.
 */
//@Data
//@ToString
//@EqualsAndHashCode
//@NoArgsConstructor
//@AllArgsConstructor
public class Engine  implements Serializable {
	private static final long serialVersionUID = 1L;

	public Engine() {
	}

    private String model;

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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Engine other = (Engine) obj;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (power != other.power)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Engine [model=" + model + ", power=" + power + "]";
	}
    
    
}
