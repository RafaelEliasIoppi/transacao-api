package desafio.transacao_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import desafio.transacao_api.business.services.TransacaoService;
import desafio.transacao_api.controller.dtos.TransacaoRequestDTO;
import desafio.transacao_api.controller.dtos.TransacaoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/transacao")
public class TransacaoController {

    private final TransacaoService transacaoService;

    @PostMapping
    @Operation(summary = "Adicionar transações", description = "Responsável por adicionar transações")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Transação criada com sucesso"),
        @ApiResponse(responseCode = "422", description = "Erro de validação na transação"),
        @ApiResponse(responseCode = "400", description = "Erro de requisição"),
        @ApiResponse(responseCode = "500", description = "Erro do servidor")
    })
    public ResponseEntity<Void> adicionarTransacao(@Valid @RequestBody TransacaoRequestDTO dto) {
        transacaoService.adicionarTransacoes(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    @Operation(summary = "Deletar transações", description = "Responsável por deletar todas as transações")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Transações deletadas com sucesso"),
        @ApiResponse(responseCode = "400", description = "Erro de requisição"),
        @ApiResponse(responseCode = "500", description = "Erro do servidor")
    })
    public ResponseEntity<Void> limparTransacoes() {
        transacaoService.limparTransacoes();
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "Listar transações", description = "Retorna todas as transações registradas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro do servidor")
    })
    public ResponseEntity<List<TransacaoResponseDTO>> listarTransacoes() {

        List<TransacaoResponseDTO> transacoes = transacaoService.listarTransacoes();
        return ResponseEntity.ok(transacoes);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar transação por ID", description = "Retorna uma transação específica")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Transação encontrada"),
        @ApiResponse(responseCode = "404", description = "Transação não encontrada"),
        @ApiResponse(responseCode = "500", description = "Erro do servidor")
    })
    public ResponseEntity<TransacaoResponseDTO> buscarPorId(@PathVariable Long id) {
        TransacaoResponseDTO transacao = transacaoService.buscarPorId(id);
        return ResponseEntity.ok(transacao);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar transação", description = "Atualiza os dados de uma transação existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Transação atualizada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Erro de requisição"),
        @ApiResponse(responseCode = "404", description = "Transação não encontrada"),
        @ApiResponse(responseCode = "500", description = "Erro do servidor")
    })
    public ResponseEntity<Void> atualizarTransacao(@PathVariable Long id, @Valid @RequestBody TransacaoRequestDTO dto) {
        transacaoService.atualizarTransacao(id, dto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/health")
    @Operation(summary = "Health check", description = "Verifica se a API está saudável")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "API está saudável")
    })
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("API está saudável");
    }
}
