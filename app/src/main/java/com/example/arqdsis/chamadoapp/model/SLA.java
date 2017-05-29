package com.example.arqdsis.chamadoapp.model;

import java.io.Serializable;



public class SLA implements Serializable {

	private static final long serialVersionUID = 1L;


    private Long id;
	private String descricao;
	private Integer SLATempo;
	private Integer prioridade;
	private Integer ativo;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Integer prioridade) {
		this.prioridade = prioridade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getSLATempo() {
		return SLATempo;
	}

	public void setSLATempo(Integer sLATempo) {
		SLATempo = sLATempo;
	}

	public Integer getAtivo() {
		return ativo;
	}

	public void setAtivo(Integer ativo) {
		this.ativo = ativo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SLA other = (SLA) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SLA [id=" + id + ", descricao=" + descricao + ", SLATempo=" + SLATempo + "]";
	}
	
}
