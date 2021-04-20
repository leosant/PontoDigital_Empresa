package com.pontoDigital.Model.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "data_month")
public class DataMonth {
	
	@Id
	private int id;
	private Integer month;

}
