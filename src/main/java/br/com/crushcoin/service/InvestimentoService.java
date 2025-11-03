package br.com.crushcoin.service;

import br.com.crushcoin.entity.InvestimentoEntity;
import br.com.crushcoin.entity.UsuarioEntity;
import br.com.crushcoin.repository.InvestimentoRepository;
import br.com.crushcoin.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class InvestimentoService {

    @Autowired
    private InvestimentoRepository investimentoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // CREATE
    public InvestimentoEntity criar(Long usuarioId, InvestimentoEntity investimento) {
        // Regra de negócio: validar se usuário existe
        UsuarioEntity usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + usuarioId));
        
        // Regra de negócio: validar valor positivo
        if (investimento.getValor() == null || investimento.getValor().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Valor do investimento deve ser maior que zero");
        }
        
        // Regra de negócio: validar data não nula
        if (investimento.getData() == null) {
            throw new RuntimeException("Data do investimento é obrigatória");
        }
        
        // Regra de negócio: valor mínimo de investimento
        BigDecimal valorMinimo = new BigDecimal("100.00");
        if (investimento.getValor().compareTo(valorMinimo) < 0) {
            throw new RuntimeException("Valor mínimo de investimento é R$ 100,00");
        }
        
        investimento.setUsuario(usuario);
        return investimentoRepository.save(investimento);
    }

    // READ - Buscar todos
    public List<InvestimentoEntity> listarTodos() {
        return investimentoRepository.findAll();
    }

    // READ - Buscar por ID
    public Optional<InvestimentoEntity> buscarPorId(Long id) {
        return investimentoRepository.findById(id);
    }

    // READ - Buscar por usuário
    public List<InvestimentoEntity> buscarPorUsuario(Long usuarioId) {
        // Regra de negócio: validar se usuário existe
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new RuntimeException("Usuário não encontrado com ID: " + usuarioId);
        }
        return investimentoRepository.findByUsuarioId(usuarioId);
    }

    // READ - Buscar por período
    public List<InvestimentoEntity> buscarPorPeriodo(Long usuarioId, LocalDate dataInicio, LocalDate dataFim) {
        // Regra de negócio: validar se usuário existe
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new RuntimeException("Usuário não encontrado com ID: " + usuarioId);
        }
        
        // Regra de negócio: validar período
        if (dataInicio.isAfter(dataFim)) {
            throw new RuntimeException("Data de início deve ser anterior à data de fim");
        }
        
        return investimentoRepository.findByUsuarioIdAndDataBetween(usuarioId, dataInicio, dataFim);
    }

    // UPDATE
    public InvestimentoEntity atualizar(Long id, InvestimentoEntity investimentoAtualizado) {
        return investimentoRepository.findById(id)
                .map(investimento -> {
                    // Regra de negócio: validar valor positivo
                    if (investimentoAtualizado.getValor() != null) {
                        if (investimentoAtualizado.getValor().compareTo(BigDecimal.ZERO) <= 0) {
                            throw new RuntimeException("Valor do investimento deve ser maior que zero");
                        }
                        
                        // Regra de negócio: valor mínimo
                        BigDecimal valorMinimo = new BigDecimal("100.00");
                        if (investimentoAtualizado.getValor().compareTo(valorMinimo) < 0) {
                            throw new RuntimeException("Valor mínimo de investimento é R$ 100,00");
                        }
                        
                        investimento.setValor(investimentoAtualizado.getValor());
                    }
                    
                    // Regra de negócio: validar data
                    if (investimentoAtualizado.getData() != null) {
                        investimento.setData(investimentoAtualizado.getData());
                    }
                    
                    return investimentoRepository.save(investimento);
                })
                .orElseThrow(() -> new RuntimeException("Investimento não encontrado com ID: " + id));
    }

    // DELETE
    public void deletar(Long id) {
        if (!investimentoRepository.existsById(id)) {
            throw new RuntimeException("Investimento não encontrado com ID: " + id);
        }
        investimentoRepository.deleteById(id);
    }

    // Regra de negócio: calcular total de investimentos de um usuário
    public BigDecimal calcularTotalInvestimentos(Long usuarioId) {
        List<InvestimentoEntity> investimentos = buscarPorUsuario(usuarioId);
        return investimentos.stream()
                .map(InvestimentoEntity::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

