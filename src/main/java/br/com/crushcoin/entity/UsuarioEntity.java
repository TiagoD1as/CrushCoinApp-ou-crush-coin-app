package br.com.crushcoin.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USUARIOS")
public class UsuarioEntity {

	@Id
	@SequenceGenerator(name = "SEQ_USUARIOS", sequenceName = "SEQ_USUARIOS", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USUARIOS")
	@Column(name = "USER_ID")
	private Long id;

	@Column(name = "EMAIL", nullable = false, unique = true, length = 150)
	private String email;

	@Column(name = "SENHA_HASH", nullable = false, length = 255)
	private String senhaHash;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonIgnoreProperties("usuario")
	private List<DespesaEntity> despesas = new ArrayList<>();

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonIgnoreProperties("usuario")
	private List<InvestimentoEntity> investimentos = new ArrayList<>();

	public UsuarioEntity() {}

	public UsuarioEntity(String email, String senhaHash) {
		this.email = email;
		this.senhaHash = senhaHash;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenhaHash() {
		return senhaHash;
	}

	public void setSenhaHash(String senhaHash) {
		this.senhaHash = senhaHash;
	}

	public List<DespesaEntity> getDespesas() {
		return despesas;
	}

	public void setDespesas(List<DespesaEntity> despesas) {
		this.despesas = despesas;
	}

	public List<InvestimentoEntity> getInvestimentos() {
		return investimentos;
	}

	public void setInvestimentos(List<InvestimentoEntity> investimentos) {
		this.investimentos = investimentos;
	}
}


