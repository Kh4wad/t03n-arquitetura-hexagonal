package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.pedido.mapper;

import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PedidoBO;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PedidoProdutoBO;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PessoaBO;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.ProdutoBO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.pedido.dto.PedidoEventDTO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.pedido.dto.PedidoEventItemDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PedidoEventDTOMapper {

    private PedidoEventDTOMapper() {
    }

    public static PedidoBO toBo(PedidoEventDTO dto) {
        final PedidoBO pedidoBO = new PedidoBO();
        final PessoaBO pessoaBO = new PessoaBO();
        pessoaBO.setId(dto.getCustomerId());

        pedidoBO.setPessoa(pessoaBO);
        pedidoBO.setCep(dto.getZipCode());

        final List<PedidoEventItemDTO> orderItems = dto.getOrderItems() != null ? dto.getOrderItems()
                : Collections.emptyList();
        final List<PedidoProdutoBO> itens = new ArrayList<>();

        for (PedidoEventItemDTO itemDTO : orderItems) {
            final ProdutoBO produtoBO = new ProdutoBO();
            produtoBO.setId(itemDTO.getSku());
            
            final PedidoProdutoBO itemBO = new PedidoProdutoBO();
            itemBO.setProduto(produtoBO);
            itemBO.setQuantidade(itemDTO.getAmount());
            itens.add(itemBO);
        }

        pedidoBO.setItens(itens);
        return pedidoBO;
    }
}
