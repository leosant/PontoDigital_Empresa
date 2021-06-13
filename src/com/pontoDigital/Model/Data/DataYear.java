package com.pontoDigital.Model.Data;

import java.time.LocalTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.pontoDigital.Model.Employer.Funcionario;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Data
@Table(name = "data_year")
public class DataYear {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private Integer year;
	
	private Integer month;
	
	private Integer days;
	
	private LocalTime earlyHours;
	
	private LocalTime entranceLunch;
	
	private LocalTime exitLunch;
	
	private LocalTime finalHours;
	
	private Integer resultHours;
	
	@ManyToOne
	@JoinColumn(name = "funcionario_fk")
	private Funcionario funcionarios;	
}
