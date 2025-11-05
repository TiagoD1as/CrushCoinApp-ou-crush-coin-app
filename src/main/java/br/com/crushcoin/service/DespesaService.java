package br.com.crushcoin.service;

import br.com.crushcoin.entity.DespesaEntity;
import br.com.crushcoin.entity.UsuarioEntity;
import br.com.crushcoin.repository.DespesaRepository;
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
public class DespesaService {

    @Autowired
    private DespesaRepository despesaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public DespesaEntity criar(Long usuarioId, DespesaEntity despesa) {
        UsuarioEntity usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + usuarioId));
        
        if (despesa.getValor() == null || despesa.getValor().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Valor da despesa deve ser maior que zero");
        }
        
        if (despesa.getDataGasto() == null) {
            throw new RuntimeException("Data do gasto é obrigatória");
        }
        
        if (despesa.getDataGasto().isAfter(LocalDate.now())) {
            throw new RuntimeException("Data do gasto não pode ser futura");
        }
        
        despesa.setUsuario(usuario);
        return despesaRepository.save(despesa);
    }

    public List<DespesaEntity> listarTodas() {
        return despesaRepository.findAllWithUsuario();
    }

    public Optional<DespesaEntity> buscarPorId(Long id) {
        return despesaRepository.findById(id);
    }

    public List<DespesaEntity> buscarPorUsuario(Long usuarioId) {
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new RuntimeException("Usuário não encontrado com ID: " + usuarioId);
        }
        return despesaRepository.findByUsuarioId(usuarioId);
    }

    public List<DespesaEntity> buscarPorPeriodo(Long usuarioId, LocalDate dataInicio, LocalDate dataFim) {
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new RuntimeException("Usuário não encontrado com ID: " + usuarioId);
        }
        
        if (dataInicio.isAfter(dataFim)) {
            throw new RuntimeException("Data de início deve ser anterior à data de fim");
        }
        
        return despesaRepository.findByUsuarioIdAndDataGastoBetween(usuarioId, dataInicio, dataFim);
    }

    public DespesaEntity atualizar(Long id, DespesaEntity despesaAtualizada) {
        return despesaRepository.findById(id)
                .map(despesa -> {
                    if (despesaAtualizada.getValor() != null) {
                        if (despesaAtualizada.getValor().compareTo(BigDecimal.ZERO) <= 0) {
                            throw new RuntimeException("Valor da despesa deve ser maior que zero");
                        }
                        despesa.setValor(despesaAtualizada.getValor());
                    }
                    
                    if (despesaAtualizada.getDataGasto() != null) {
                        if (despesaAtualizada.getDataGasto().isAfter(LocalDate.now())) {
                            throw new RuntimeException("Data do gasto não pode ser futura");
                        }
                        despesa.setDataGasto(despesaAtualizada.getDataGasto());
                    }
                    
                    return despesaRepository.save(despesa);
                })
                .orElseThrow(() -> new RuntimeException("Despesa não encontrada com ID: " + id));
    }

    public void deletar(Long id) {
        if (!despesaRepository.existsById(id)) {
            throw new RuntimeException("Despesa não encontrada com ID: " + id);
        }
        despesaRepository.deleteById(id);
    }
}

