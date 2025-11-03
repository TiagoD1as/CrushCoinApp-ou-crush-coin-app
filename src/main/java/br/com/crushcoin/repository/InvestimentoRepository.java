package br.com.crushcoin.repository;

import br.com.crushcoin.entity.InvestimentoEntity;
import br.com.crushcoin.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface InvestimentoRepository extends JpaRepository<InvestimentoEntity, Long> {
    
    List<InvestimentoEntity> findByUsuario(UsuarioEntity usuario);
    
    List<InvestimentoEntity> findByUsuarioId(Long usuarioId);
    
    List<InvestimentoEntity> findByUsuarioIdAndDataBetween(Long usuarioId, LocalDate dataInicio, LocalDate dataFim);
}

