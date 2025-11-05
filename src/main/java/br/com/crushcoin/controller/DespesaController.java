package br.com.crushcoin.controller;

import br.com.crushcoin.entity.DespesaEntity;
import br.com.crushcoin.service.DespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/despesas")
public class DespesaController {

    @Autowired
    private DespesaService despesaService;

    @GetMapping
    public ResponseEntity<List<DespesaEntity>> listarTodas() {
        try {
            List<DespesaEntity> despesas = despesaService.listarTodas();
            return ResponseEntity.status(HttpStatus.OK).body(despesas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DespesaEntity> buscarPorId(@PathVariable Long id) {
        try {
            return despesaService.buscarPorId(id)
                    .map(despesa -> ResponseEntity.status(HttpStatus.OK).body(despesa))
                    .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> buscarPorUsuario(@PathVariable Long usuarioId) {
        try {
            List<DespesaEntity> despesas = despesaService.buscarPorUsuario(usuarioId);
            return ResponseEntity.status(HttpStatus.OK).body(despesas);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/usuario/{usuarioId}/periodo")
    public ResponseEntity<?> buscarPorPeriodo(
            @PathVariable Long usuarioId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
        try {
            List<DespesaEntity> despesas = despesaService.buscarPorPeriodo(usuarioId, dataInicio, dataFim);
            return ResponseEntity.status(HttpStatus.OK).body(despesas);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> criar(@PathVariable Long usuarioId, @RequestBody DespesaEntity despesa) {
        try {
            DespesaEntity despesaCriada = despesaService.criar(usuarioId, despesa);
            return ResponseEntity.status(HttpStatus.CREATED).body(despesaCriada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody DespesaEntity despesa) {
        try {
            DespesaEntity despesaAtualizada = despesaService.atualizar(id, despesa);
            return ResponseEntity.status(HttpStatus.OK).body(despesaAtualizada);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("não encontrada")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: " + e.getMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            despesaService.deletar(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

