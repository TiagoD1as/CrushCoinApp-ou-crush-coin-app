package br.com.crushcoin.service;

import br.com.crushcoin.entity.UsuarioEntity;
import br.com.crushcoin.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // CREATE
    public UsuarioEntity criar(UsuarioEntity usuario) {
        // Regra de negócio: validar email único
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("Email já cadastrado: " + usuario.getEmail());
        }
        
        // Regra de negócio: validar email não vazio
        if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {
            throw new RuntimeException("Email é obrigatório");
        }
        
        // Regra de negócio: validar senha não vazia
        if (usuario.getSenhaHash() == null || usuario.getSenhaHash().trim().isEmpty()) {
            throw new RuntimeException("Senha é obrigatória");
        }
        
        return usuarioRepository.save(usuario);
    }

    // READ - Buscar todos
    public List<UsuarioEntity> listarTodos() {
        return usuarioRepository.findAll();
    }

    // READ - Buscar por ID
    public Optional<UsuarioEntity> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    // READ - Buscar por Email
    public Optional<UsuarioEntity> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    // UPDATE
    public UsuarioEntity atualizar(Long id, UsuarioEntity usuarioAtualizado) {
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    // Regra de negócio: se email foi alterado, verificar se não está em uso
                    if (!usuario.getEmail().equals(usuarioAtualizado.getEmail())) {
                        if (usuarioRepository.existsByEmail(usuarioAtualizado.getEmail())) {
                            throw new RuntimeException("Email já cadastrado: " + usuarioAtualizado.getEmail());
                        }
                    }
                    
                    usuario.setEmail(usuarioAtualizado.getEmail());
                    if (usuarioAtualizado.getSenhaHash() != null && !usuarioAtualizado.getSenhaHash().trim().isEmpty()) {
                        usuario.setSenhaHash(usuarioAtualizado.getSenhaHash());
                    }
                    return usuarioRepository.save(usuario);
                })
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + id));
    }

    // DELETE
    public void deletar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado com ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    // Regra de negócio: verificar se usuário existe
    public boolean existePorId(Long id) {
        return usuarioRepository.existsById(id);
    }
}

