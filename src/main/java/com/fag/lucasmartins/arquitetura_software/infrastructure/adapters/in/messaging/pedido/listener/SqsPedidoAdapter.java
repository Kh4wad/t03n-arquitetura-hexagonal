package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.pedido.listener;

import com.fag.lucasmartins.arquitetura_software.application.ports.in.service.PedidoServicePort;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PedidoBO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.entradaestoque.exceptions.ConsumerSQSException;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.pedido.dto.PedidoEventDTO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.pedido.mapper.PedidoEventDTOMapper;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "aws.sqs.enabled", havingValue = "true")
public class SqsPedidoAdapter {

    private static final Logger log = LoggerFactory.getLogger(SqsPedidoAdapter.class);

    private final PedidoServicePort pedidoServicePort;

    public SqsPedidoAdapter(PedidoServicePort pedidoServicePort) {
        this.pedidoServicePort = pedidoServicePort;
    }

    @SqsListener(value = "${aws.sqs.queue.pedido}")
    public void listen(PedidoEventDTO evento) {
        try {
            log.info("Mensagem recebida: {}", evento.getCustomerId());

            final PedidoBO pedidoBO = PedidoEventDTOMapper.toBo(evento);
            final PedidoBO pedidoSalvo = pedidoServicePort.criarPedido(pedidoBO);

            log.info("Mensagem consumida com sucesso.");
        } catch (Exception e) {
            log.error("Erro ao processar evento de pedido do cliente {}",
                    evento != null ? evento.getCustomerId() : null, e);
            throw new ConsumerSQSException("erro ao processar o evento de pedido", e);
        }
    }
}
