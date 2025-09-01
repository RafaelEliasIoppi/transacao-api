package desafio.transacao_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import desafio.transacao_api.business.services.TransacaoService;
import desafio.transacao_api.controller.dtos.TransacaoRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;


@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/transacao")
public class TransacaoController {

    private final TransacaoService transacaoService;

    @PostMapping
    @Operation(description = "Responsável por adicionar transações")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Transação criada com sucesso"),
        @ApiResponse(responseCode = "422", description = "Erro de validação na transação"),
        @ApiResponse(responseCode = "400", description = "Erro de requisiçao"),
        @ApiResponse(responseCode = "500", description = "Erro do servidor")
    })

    public ResponseEntity<Void> adicionarTransacao(@RequestBody TransacaoRequestDTO dto) {

        transacaoService.adicionarTransacoes(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    @Operation(description = "Responsável por deletar transações")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Transação deletada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Erro de requisiçao"),
        @ApiResponse(responseCode = "500", description = "Erro do servidor")
    })
    public ResponseEntity<Void> limparTransacoes() {

        transacaoService.limparTransacoes();
        return ResponseEntity.noContent().build();
    }
}

