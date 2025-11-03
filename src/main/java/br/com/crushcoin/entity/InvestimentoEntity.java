package br.com.crushcoin.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "INVESTIMENTOS")
public class InvestimentoEntity {

	@Id
	@SequenceGenerator(name = "SEQ_INVESTIMENTOS", sequenceName = "SEQ_INVESTIMENTOS", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_INVESTIMENTOS")
	@Column(name = "INVESTMENT_ID")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "USER_ID", nullable = false)
	private UsuarioEntity usuario;

	@Column(name = "DATA", nullable = false)
	private LocalDate data;

	@Column(name = "VALOR", nullable = false, precision = 19, scale = 2)
	private BigDecimal valor;

	public InvestimentoEntity() {}

	public InvestimentoEntity(UsuarioEntity usuario, LocalDate data, BigDecimal valor) {
		this.usuario = usuario;
		this.data = data;
		this.valor = valor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UsuarioEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
}


