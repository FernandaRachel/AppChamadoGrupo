package com.example.arqdsis.chamadoapp.model;


import java.io.Serializable;

public class Fila implements Serializable {

	private Long id;
	private String descricao;
	private Usuario gerente;
	private Integer ativo;
	transient String nomeSolucionador;

	public Fila(Long id, String descricao, Usuario gerente, String nomeSolucionador) {
		this.id = id;
		this.descricao = descricao;
		this.gerente = gerente;
		this.nomeSolucionador = nomeSolucionador;
	}


	public Fila(String descricao) {
		this.descricao = descricao;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Usuario getGerente() {
		return gerente;
	}

	public void setGerente(Usuario gerente) {
		this.gerente = gerente;
	}

	public Integer getAtivo() {
		return ativo;
	}

	public void setAtivo(Integer ativo) {
		this.ativo = ativo;
	}

	public String getNomeSolucionador() { return nomeSolucionador; }

	public void setNomeSolucionador(String nomeSolucionador) { this.nomeSolucionador = nomeSolucionador; }

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
		Fila other = (Fila) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Fila [id=" + id + ", descricao=" + descricao+"]";
	}


}
