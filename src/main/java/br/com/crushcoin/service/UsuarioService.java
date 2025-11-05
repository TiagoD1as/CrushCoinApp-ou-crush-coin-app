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

    public UsuarioEntity criar(UsuarioEntity usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("Email já cadastrado: " + usuario.getEmail());
        }
        
        if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {
            throw new RuntimeException("Email é obrigatório");
        }
        
        if (usuario.getSenhaHash() == null || usuario.getSenhaHash().trim().isEmpty()) {
            throw new RuntimeException("Senha é obrigatória");
        }
        
        return usuarioRepository.save(usuario);
    }

    public List<UsuarioEntity> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<UsuarioEntity> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Optional<UsuarioEntity> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public UsuarioEntity atualizar(Long id, UsuarioEntity usuarioAtualizado) {
        return usuarioRepository.findById(id)
                .map(usuario -> {
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

    public void deletar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado com ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    public boolean existePorId(Long id) {
        return usuarioRepository.existsById(id);
    }
}

