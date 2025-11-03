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

    // CREATE
    public DespesaEntity criar(Long usuarioId, DespesaEntity despesa) {
        // Regra de negócio: validar se usuário existe
        UsuarioEntity usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + usuarioId));
        
        // Regra de negócio: validar valor positivo
        if (despesa.getValor() == null || despesa.getValor().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Valor da despesa deve ser maior que zero");
        }
        
        // Regra de negócio: validar data não nula
        if (despesa.getDataGasto() == null) {
            throw new RuntimeException("Data do gasto é obrigatória");
        }
        
        // Regra de negócio: data não pode ser futura
        if (despesa.getDataGasto().isAfter(LocalDate.now())) {
            throw new RuntimeException("Data do gasto não pode ser futura");
        }
        
        despesa.setUsuario(usuario);
        return despesaRepository.save(despesa);
    }

    // READ - Buscar todos
    public List<DespesaEntity> listarTodas() {
        return despesaRepository.findAll();
    }

    // READ - Buscar por ID
    public Optional<DespesaEntity> buscarPorId(Long id) {
        return despesaRepository.findById(id);
    }

    // READ - Buscar por usuário
    public List<DespesaEntity> buscarPorUsuario(Long usuarioId) {
        // Regra de negócio: validar se usuário existe
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new RuntimeException("Usuário não encontrado com ID: " + usuarioId);
        }
        return despesaRepository.findByUsuarioId(usuarioId);
    }

    // READ - Buscar por período
    public List<DespesaEntity> buscarPorPeriodo(Long usuarioId, LocalDate dataInicio, LocalDate dataFim) {
        // Regra de negócio: validar se usuário existe
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new RuntimeException("Usuário não encontrado com ID: " + usuarioId);
        }
        
        // Regra de negócio: validar período
        if (dataInicio.isAfter(dataFim)) {
            throw new RuntimeException("Data de início deve ser anterior à data de fim");
        }
        
        return despesaRepository.findByUsuarioIdAndDataGastoBetween(usuarioId, dataInicio, dataFim);
    }

    // UPDATE
    public DespesaEntity atualizar(Long id, DespesaEntity despesaAtualizada) {
        return despesaRepository.findById(id)
                .map(despesa -> {
                    // Regra de negócio: validar valor positivo
                    if (despesaAtualizada.getValor() != null) {
                        if (despesaAtualizada.getValor().compareTo(BigDecimal.ZERO) <= 0) {
                            throw new RuntimeException("Valor da despesa deve ser maior que zero");
                        }
                        despesa.setValor(despesaAtualizada.getValor());
                    }
                    
                    // Regra de negócio: validar data
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

    // DELETE
    public void deletar(Long id) {
        if (!despesaRepository.existsById(id)) {
            throw new RuntimeException("Despesa não encontrada com ID: " + id);
        }
        despesaRepository.deleteById(id);
    }
}

