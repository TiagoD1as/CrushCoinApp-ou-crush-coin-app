package br.com.crushcoin.repository;

import br.com.crushcoin.entity.InvestimentoEntity;
import br.com.crushcoin.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface InvestimentoRepository extends JpaRepository<InvestimentoEntity, Long> {
    
    @Query("SELECT i FROM InvestimentoEntity i JOIN FETCH i.usuario")
    List<InvestimentoEntity> findAllWithUsuario();
    
    @Query("SELECT i FROM InvestimentoEntity i JOIN FETCH i.usuario WHERE i.id = :id")
    Optional<InvestimentoEntity> findByIdWithUsuario(@Param("id") Long id);
    
    List<InvestimentoEntity> findByUsuario(UsuarioEntity usuario);
    
    @Query("SELECT i FROM InvestimentoEntity i JOIN FETCH i.usuario WHERE i.usuario.id = :usuarioId")
    List<InvestimentoEntity> findByUsuarioId(@Param("usuarioId") Long usuarioId);
    
    @Query("SELECT i FROM InvestimentoEntity i JOIN FETCH i.usuario WHERE i.usuario.id = :usuarioId AND i.data BETWEEN :dataInicio AND :dataFim")
    List<InvestimentoEntity> findByUsuarioIdAndDataBetween(@Param("usuarioId") Long usuarioId, @Param("dataInicio") LocalDate dataInicio, @Param("dataFim") LocalDate dataFim);
}

