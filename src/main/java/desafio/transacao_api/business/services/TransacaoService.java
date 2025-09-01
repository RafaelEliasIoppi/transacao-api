package desafio.transacao_api.business.services;


import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import desafio.transacao_api.controller.dtos.TransacaoRequestDTO;
import desafio.transacao_api.infrastructure.exceptions.UnprocessableEntity;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class TransacaoService {

    private final List<TransacaoRequestDTO> listaTransacoes = new ArrayList<>();
    

    public void adicionarTransacoes(TransacaoRequestDTO dto) {

        log.info("Adicionando transação: {}", dto);
        
        if (dto.dataHora().isAfter(OffsetDateTime.now())) {

            log.error("Tentativa de adicionar transação com data futura: {}", dto);
            throw new UnprocessableEntity("Transação com data futura não é permitida");
        }

        if (dto.valor() == null || dto.valor() <= 0) {

            log.error("Tentativa de adicionar transação com valor inválido: {}", dto);
            throw new UnprocessableEntity("Valor da transação deve ser maior que zero");
        }
  
        listaTransacoes.add(dto);
        log.info("Transacoes adicionadas com sucesso");

    }


    public void limparTransacoes() {

            log.info("Inciado processamento para deletar transações");
            listaTransacoes.clear();
            log.info("Transaçoes excluídas com sucesso");
    }

    public List<TransacaoRequestDTO> buscarTransacoes(Integer intervaloBusca) {
          log.info("Inciado busca de transações por tempo");
        OffsetDateTime dataHoraIntervalo = OffsetDateTime.now().minusSeconds(intervaloBusca);

         log.info("Retorno de transações por tempo");
        return listaTransacoes.stream()
                .filter(t -> t.dataHora().isAfter(dataHoraIntervalo))
                .toList();
    }

    

   
}    


    



