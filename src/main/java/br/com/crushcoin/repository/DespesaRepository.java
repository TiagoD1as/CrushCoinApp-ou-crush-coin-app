package br.com.crushcoin.repository;

import br.com.crushcoin.entity.DespesaEntity;
import br.com.crushcoin.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DespesaRepository extends JpaRepository<DespesaEntity, Long> {
    
    @Query("SELECT d FROM DespesaEntity d JOIN FETCH d.usuario")
    List<DespesaEntity> findAllWithUsuario();
    
    @Query("SELECT d FROM DespesaEntity d JOIN FETCH d.usuario WHERE d.id = :id")
    Optional<DespesaEntity> findByIdWithUsuario(@Param("id") Long id);
    
    List<DespesaEntity> findByUsuario(UsuarioEntity usuario);
    
    @Query("SELECT d FROM DespesaEntity d JOIN FETCH d.usuario WHERE d.usuario.id = :usuarioId")
    List<DespesaEntity> findByUsuarioId(@Param("usuarioId") Long usuarioId);
    
    @Query("SELECT d FROM DespesaEntity d JOIN FETCH d.usuario WHERE d.usuario.id = :usuarioId AND d.dataGasto BETWEEN :dataInicio AND :dataFim")
    List<DespesaEntity> findByUsuarioIdAndDataGastoBetween(@Param("usuarioId") Long usuarioId, @Param("dataInicio") LocalDate dataInicio, @Param("dataFim") LocalDate dataFim);
}

