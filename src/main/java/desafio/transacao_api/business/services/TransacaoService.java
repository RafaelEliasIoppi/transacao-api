package desafio.transacao_api.business.services;


import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import desafio.transacao_api.controller.dtos.TransacaoRequestDTO;
import desafio.transacao_api.infrastructure.exceptions.UnprocessableEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@RequiredArgsConstructor
@Slf4j
public class TransacaoService {

    private final List<TransacaoRequestDTO> listaTransacoes = new ArrayList<>();
    

    public void adicionarTransacoes(TransacaoRequestDTO dto) {

        log.info("Adicionando transação: {}", dto);
        
        if (dto.dataHora().isAfter(OffsetDateTime.now())) {

            log.error("Tentativa de adicionar transação com data futura: {}", dto);
            throw new UnprocessableEntity("Transação com data futura não é permitida"
            );
        }
    }

}
