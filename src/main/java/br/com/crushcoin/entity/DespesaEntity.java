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
@Table(name = "DESPESAS")
public class DespesaEntity {

	@Id
	@SequenceGenerator(name = "SEQ_DESPESAS", sequenceName = "SEQ_DESPESAS", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DESPESAS")
	@Column(name = "EXPENSE_ID")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "USER_ID", nullable = false)
	private UsuarioEntity usuario;

	@Column(name = "VALOR", nullable = false, precision = 19, scale = 2)
	private BigDecimal valor;

	@Column(name = "DATA_GASTO", nullable = false)
	private LocalDate dataGasto;

	public DespesaEntity() {}

	public DespesaEntity(UsuarioEntity usuario, BigDecimal valor, LocalDate dataGasto) {
		this.usuario = usuario;
		this.valor = valor;
		this.dataGasto = dataGasto;
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

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public LocalDate getDataGasto() {
		return dataGasto;
	}

	public void setDataGasto(LocalDate dataGasto) {
		this.dataGasto = dataGasto;
	}
}


