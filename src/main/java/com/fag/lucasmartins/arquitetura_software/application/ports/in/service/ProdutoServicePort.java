package com.fag.lucasmartins.arquitetura_software.application.ports.in.service;

import com.fag.lucasmartins.arquitetura_software.core.domain.bo.ProdutoBO;

public interface ProdutoServicePort {

    ProdutoBO salvar(ProdutoBO produtoBO);
}


