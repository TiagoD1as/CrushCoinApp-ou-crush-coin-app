package br.com.crushcoin.repository;

import br.com.crushcoin.entity.DespesaEntity;
import br.com.crushcoin.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DespesaRepository extends JpaRepository<DespesaEntity, Long> {
    
    @Query("SELECT d FROM DespesaEntity d JOIN FETCH d.usuario")
    List<DespesaEntity> findAllWithUsuario();
    
    List<DespesaEntity> findByUsuario(UsuarioEntity usuario);
    
    List<DespesaEntity> findByUsuarioId(Long usuarioId);
    
    List<DespesaEntity> findByUsuarioIdAndDataGastoBetween(Long usuarioId, LocalDate dataInicio, LocalDate dataFim);
}

