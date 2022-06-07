package com.lti.recast.recastBoTableau.strategizer.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sapbo_tableau_mapping")
public class SapboTableauMapping implements java.io.Serializable {
    
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String sapboComponent;
	private String tableauComponent;
	private String tableauComponentAvailability;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSapboComponent() {
		return sapboComponent;
	}
	public void setSapboComponent(String sapboComponent) {
		this.sapboComponent = sapboComponent;
	}
	public String getTableauComponent() {
		return tableauComponent;
	}
	public void setTableauComponent(String tableauComponent) {
		this.tableauComponent = tableauComponent;
	}
	public String getTableauComponentAvailability() {
		return tableauComponentAvailability;
	}
	public void setTableauComponentAvailability(String tableauComponentAvailability) {
		this.tableauComponentAvailability = tableauComponentAvailability;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "SapboTableauMapping [id=" + id + ", sapboComponent=" + sapboComponent + ", tableauComponent="
				+ tableauComponent + ", tableauComponentAvailability=" + tableauComponentAvailability + "]";
	}
	
	
	
	
}
