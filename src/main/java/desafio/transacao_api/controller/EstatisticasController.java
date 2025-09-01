package desafio.transacao_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import desafio.transacao_api.business.services.EstatisticasService;
import desafio.transacao_api.controller.dtos.EstatisticasResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/estatistica")
@RequiredArgsConstructor
public class EstatisticasController {

    private final EstatisticasService estatisticasservice;

    @GetMapping
    @Operation(description = "Responsável por buscar estatísticas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Busca efetuada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Erro na busca de estatística"),
        @ApiResponse(responseCode = "500", description = "Erro do servidor")
    })

    public ResponseEntity<EstatisticasResponseDTO> buscartEstatisticas(@RequestParam(value = "intervaloBusca", required = false, defaultValue = "60") Integer intervalo) {

        EstatisticasResponseDTO response = estatisticasservice.calcularEstatisticas(intervalo);
        return ResponseEntity.ok(response);
    }

}
