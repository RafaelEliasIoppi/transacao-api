package desafio.transacao_api.business.services;

import java.util.DoubleSummaryStatistics;
import java.util.List;

import org.springframework.stereotype.Service;

import desafio.transacao_api.controller.dtos.EstatisticasResponseDTO;
import desafio.transacao_api.controller.dtos.TransacaoRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@RequiredArgsConstructor
@Slf4j
public class EstatisticasService {

    public final TransacaoService transacaoService;

    public EstatisticasResponseDTO calcularEstatisticas(Integer intervaloBusca) {

        log.info("Iniciada a busca de transações pelo periodo {}", intervaloBusca);
        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoes(intervaloBusca);

       if(transacoes.isEmpty()) {
           log.warn("Nenhuma transação encontrada para o período {}", intervaloBusca);
           return new EstatisticasResponseDTO(0.0, 0.0, 0.0, 0.0, 0);
       }

       DoubleSummaryStatistics estatisticasTranscoes = transacoes.stream()
                .mapToDouble(TransacaoRequestDTO::valor)
                .summaryStatistics();
        log.info("Estaticas retornadas");
        return new EstatisticasResponseDTO(
                estatisticasTranscoes.getSum(),
                estatisticasTranscoes.getAverage(),
                estatisticasTranscoes.getMax(),
                estatisticasTranscoes.getMin(),
                estatisticasTranscoes.getCount()
        );
    }


}
