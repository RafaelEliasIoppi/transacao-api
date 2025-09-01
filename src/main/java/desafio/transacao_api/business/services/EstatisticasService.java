package desafio.transacao_api.business.services;

import java.util.DoubleSummaryStatistics;
import java.util.List;

import org.springframework.stereotype.Service;

import desafio.transacao_api.controller.dtos.EstatisticasResponseDTO;
import desafio.transacao_api.controller.dtos.TransacaoRequestDTO;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class EstatisticasService {

    public final TransacaoService transacaoService;

    public EstatisticasResponseDTO calcularEstatisticas(Integer intervaloBusca) {

        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoes(intervaloBusca);

       DoubleSummaryStatistics estatisticasTranscoes = transacoes.stream()
                .mapToDouble(TransacaoRequestDTO::valor)
                .summaryStatistics();

        return new EstatisticasResponseDTO(
                estatisticasTranscoes.getSum(),
                estatisticasTranscoes.getAverage(),
                estatisticasTranscoes.getMax(),
                estatisticasTranscoes.getMin(),
                estatisticasTranscoes.getCount()
        );
    }


}
