package com.pontoDigital.Model.Data;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "data_days")
public class DataDays {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private Integer day;
	
	private Date inicioHours;
	
	private Date entradaAlm;
	
	private Date saidaAlm;
	
	private Date finalHours;
	
	private Date resultHours;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "data_month_id")
	private DataMonth dataMonth;
}
