package br.com.crushcoin.controller;

import br.com.crushcoin.entity.InvestimentoEntity;
import br.com.crushcoin.service.InvestimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/investimentos")
public class InvestimentoController {

    @Autowired
    private InvestimentoService investimentoService;

    // GET - Listar todos os investimentos
    @GetMapping
    public ResponseEntity<List<InvestimentoEntity>> listarTodos() {
        try {
            List<InvestimentoEntity> investimentos = investimentoService.listarTodos();
            return ResponseEntity.status(HttpStatus.OK).body(investimentos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // GET - Buscar investimento por ID
    @GetMapping("/{id}")
    public ResponseEntity<InvestimentoEntity> buscarPorId(@PathVariable Long id) {
        try {
            return investimentoService.buscarPorId(id)
                    .map(investimento -> ResponseEntity.status(HttpStatus.OK).body(investimento))
                    .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // GET - Buscar investimentos por usuário
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> buscarPorUsuario(@PathVariable Long usuarioId) {
        try {
            List<InvestimentoEntity> investimentos = investimentoService.buscarPorUsuario(usuarioId);
            return ResponseEntity.status(HttpStatus.OK).body(investimentos);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // GET - Buscar investimentos por período
    @GetMapping("/usuario/{usuarioId}/periodo")
    public ResponseEntity<?> buscarPorPeriodo(
            @PathVariable Long usuarioId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
        try {
            List<InvestimentoEntity> investimentos = investimentoService.buscarPorPeriodo(usuarioId, dataInicio, dataFim);
            return ResponseEntity.status(HttpStatus.OK).body(investimentos);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // GET - Calcular total de investimentos do usuário
    @GetMapping("/usuario/{usuarioId}/total")
    public ResponseEntity<?> calcularTotal(@PathVariable Long usuarioId) {
        try {
            BigDecimal total = investimentoService.calcularTotalInvestimentos(usuarioId);
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("total", total));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // POST - Criar novo investimento
    @PostMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> criar(@PathVariable Long usuarioId, @RequestBody InvestimentoEntity investimento) {
        try {
            InvestimentoEntity investimentoCriado = investimentoService.criar(usuarioId, investimento);
            return ResponseEntity.status(HttpStatus.CREATED).body(investimentoCriado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
        }
    }

    // PUT - Atualizar investimento
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody InvestimentoEntity investimento) {
        try {
            InvestimentoEntity investimentoAtualizado = investimentoService.atualizar(id, investimento);
            return ResponseEntity.status(HttpStatus.OK).body(investimentoAtualizado);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("não encontrado")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: " + e.getMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
        }
    }

    // DELETE - Deletar investimento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            investimentoService.deletar(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

