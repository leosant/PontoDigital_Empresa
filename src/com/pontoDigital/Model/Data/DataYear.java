package com.pontoDigital.Model.Data;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.pontoDigital.Model.Employer.Funcionario;

import lombok.Data;

@Entity
@Data
@Table(name = "data_year")
public class DataYear {
	
	@Id
	private int id;
	
	private Integer year;
	
	@OneToMany(mappedBy = "dataYear")
	private List<DataMonth> dataMonth;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "funcionario_fk")
	private Funcionario funcionarios;
}
