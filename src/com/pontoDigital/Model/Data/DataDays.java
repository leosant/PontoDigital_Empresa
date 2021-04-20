package com.pontoDigital.Model.Data;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "data_days")
public class DataDays {

	@Id
	private int id;
	private Integer day;
	private Date hours;
}
