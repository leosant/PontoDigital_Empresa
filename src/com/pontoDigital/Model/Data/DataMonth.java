package com.pontoDigital.Model.Data;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "data_month")
public class DataMonth {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private Integer month;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "data_year_id")
	private DataYear dataYear;
	
	@OneToMany(mappedBy = "dataMonth")
	private List<DataDays> dataDays;
}
