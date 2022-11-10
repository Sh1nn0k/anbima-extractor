package com.anbima.models;

import lombok.Data;

@Data
public class DataModel {
	
	private String nomeFundo;
	private String linkFundo;
	private String cnpjFundo;
	private String classeAnbima;
	private String gestorFundo;
	private String aplicacaoMin;
	private String patrimonioLiquido;
	private String administradorFundo;
	private String caracteristicaInvestidor;
	private String taxaAdministracaoMax;
	private String rentabilidade;

	
	
	public DataModel(String nomeFundo, String linkFundo, String cnpjFundo, String classeAnbima,String gestorFundo, String aplicacaoMin,
			String patrimonioLiquido, String administradorFundo, String caracteristicaInvestidor,
			String taxaAdministracaoMax, String rentabilidade) {
		super();
		this.nomeFundo = nomeFundo;
		this.linkFundo = linkFundo;
		this.cnpjFundo = cnpjFundo;
		this.classeAnbima = classeAnbima;
		this.gestorFundo = gestorFundo;
		this.aplicacaoMin = aplicacaoMin;
		this.patrimonioLiquido = patrimonioLiquido;
		this.administradorFundo = administradorFundo;
		this.caracteristicaInvestidor = caracteristicaInvestidor;
		this.taxaAdministracaoMax = taxaAdministracaoMax;
		this.rentabilidade = rentabilidade;
	}

	public DataModel() {
		super();
	}
	
	
	

}
