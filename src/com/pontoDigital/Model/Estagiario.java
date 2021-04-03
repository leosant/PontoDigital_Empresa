package com.pontoDigital.Model;

import java.util.Date;

import javax.persistence.*;
@Entity
public class Estagiario extends Funcionario{
	
	private Date hoursWork;
	private Date hoursExtras;
}
