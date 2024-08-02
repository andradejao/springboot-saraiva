package br.com.projeto.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Fotos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idfotos;
	
	@Column
	private String foto1;
	
	@Column
	private String foto2;
	
	@Column
	private String foto3;
	
	@Column
	private String foto4;

	public Fotos() {
	}

	public Fotos(Integer idfotos, String foto1, String foto2, String foto3, String foto4) {
		this.idfotos = idfotos;
		this.foto1 = foto1;
		this.foto2 = foto2;
		this.foto3 = foto3;
		this.foto4 = foto4;
	}

	public Integer getIdfotos() {
		return idfotos;
	}

	public void setIdfotos(Integer idfotos) {
		this.idfotos = idfotos;
	}

	public String getFoto1() {
		return foto1;
	}

	public void setFoto1(String foto1) {
		this.foto1 = foto1;
	}

	public String getFoto2() {
		return foto2;
	}

	public void setFoto2(String foto2) {
		this.foto2 = foto2;
	}

	public String getFoto3() {
		return foto3;
	}

	public void setFoto3(String foto3) {
		this.foto3 = foto3;
	}

	public String getFoto4() {
		return foto4;
	}

	public void setFoto4(String foto4) {
		this.foto4 = foto4;
	}
	
	
}
