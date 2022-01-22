package com.luccas.producer.model.vo;

public class Anime {

	private String name;
	private String tipo;

	public Anime() {
	}

	public Anime(final String name, final String tipo) {
		this.name = name;
		this.tipo = tipo;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(final String tipo) {
		this.tipo = tipo;
	}
}
