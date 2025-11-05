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

    public InvestimentoEntity criar(Long usuarioId, InvestimentoEntity investimento) {
        UsuarioEntity usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + usuarioId));
        
        if (investimento.getValor() == null || investimento.getValor().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Valor do investimento deve ser maior que zero");
        }
        
        if (investimento.getData() == null) {
            throw new RuntimeException("Data do investimento é obrigatória");
        }
        
        BigDecimal valorMinimo = new BigDecimal("100.00");
        if (investimento.getValor().compareTo(valorMinimo) < 0) {
            throw new RuntimeException("Valor mínimo de investimento é R$ 100,00");
        }
        
        investimento.setUsuario(usuario);
        InvestimentoEntity investimentoSalvo = investimentoRepository.save(investimento);
        return investimentoRepository.findByIdWithUsuario(investimentoSalvo.getId())
                .orElse(investimentoSalvo);
    }

    public List<InvestimentoEntity> listarTodos() {
        return investimentoRepository.findAllWithUsuario();
    }

    public Optional<InvestimentoEntity> buscarPorId(Long id) {
        return investimentoRepository.findByIdWithUsuario(id);
    }

    public List<InvestimentoEntity> buscarPorUsuario(Long usuarioId) {
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new RuntimeException("Usuário não encontrado com ID: " + usuarioId);
        }
        return investimentoRepository.findByUsuarioId(usuarioId);
    }

    public List<InvestimentoEntity> buscarPorPeriodo(Long usuarioId, LocalDate dataInicio, LocalDate dataFim) {
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new RuntimeException("Usuário não encontrado com ID: " + usuarioId);
        }
        
        if (dataInicio.isAfter(dataFim)) {
            throw new RuntimeException("Data de início deve ser anterior à data de fim");
        }
        
        return investimentoRepository.findByUsuarioIdAndDataBetween(usuarioId, dataInicio, dataFim);
    }

    public InvestimentoEntity atualizar(Long id, InvestimentoEntity investimentoAtualizado) {
        return investimentoRepository.findByIdWithUsuario(id)
                .map(investimento -> {
                    if (investimentoAtualizado.getValor() != null) {
                        if (investimentoAtualizado.getValor().compareTo(BigDecimal.ZERO) <= 0) {
                            throw new RuntimeException("Valor do investimento deve ser maior que zero");
                        }
                        
                        BigDecimal valorMinimo = new BigDecimal("100.00");
                        if (investimentoAtualizado.getValor().compareTo(valorMinimo) < 0) {
                            throw new RuntimeException("Valor mínimo de investimento é R$ 100,00");
                        }
                        
                        investimento.setValor(investimentoAtualizado.getValor());
                    }
                    
                    if (investimentoAtualizado.getData() != null) {
                        investimento.setData(investimentoAtualizado.getData());
                    }
                    
                    InvestimentoEntity investimentoSalvo = investimentoRepository.save(investimento);
                    return investimentoRepository.findByIdWithUsuario(investimentoSalvo.getId())
                            .orElse(investimentoSalvo);
                })
                .orElseThrow(() -> new RuntimeException("Investimento não encontrado com ID: " + id));
    }

    public void deletar(Long id) {
        if (!investimentoRepository.existsById(id)) {
            throw new RuntimeException("Investimento não encontrado com ID: " + id);
        }
        investimentoRepository.deleteById(id);
    }

    public BigDecimal calcularTotalInvestimentos(Long usuarioId) {
        List<InvestimentoEntity> investimentos = buscarPorUsuario(usuarioId);
        return investimentos.stream()
                .map(InvestimentoEntity::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

