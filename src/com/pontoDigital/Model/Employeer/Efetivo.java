package com.pontoDigital.Model.Employeer;

import java.util.Date;

import javax.persistence.*;
@Entity
public class Efetivo extends Funcionario{
	
	private Date hoursWork;
	private Date hoursExtras;
}
