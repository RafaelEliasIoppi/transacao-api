package desafio.transacao_api.controller.dtos;

import java.time.OffsetDateTime;

public record TransacaoResponseDTO(
    Long id,
    Double valor,
    OffsetDateTime dataHora
) {
}
