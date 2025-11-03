package br.com.crushcoin.repository;

import br.com.crushcoin.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    
    Optional<UsuarioEntity> findByEmail(String email);
    
    boolean existsByEmail(String email);
}

