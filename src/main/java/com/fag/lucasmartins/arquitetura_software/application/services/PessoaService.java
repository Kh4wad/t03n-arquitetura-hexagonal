package com.fag.lucasmartins.arquitetura_software.application.services;

import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PessoaBO;
import com.fag.lucasmartins.arquitetura_software.application.ports.out.persistence.h2.PessoaRepositoryPort;
import com.fag.lucasmartins.arquitetura_software.application.ports.in.service.PessoaServicePort;
import org.springframework.stereotype.Service;

@Service
public class PessoaService implements PessoaServicePort {

    private final PessoaRepositoryPort pessoaRepositoryPort;

    public PessoaService(PessoaRepositoryPort pessoaRepositoryPort) {
        this.pessoaRepositoryPort = pessoaRepositoryPort;
    }

    @Override
    public PessoaBO salvar(PessoaBO pessoaBO) {
        pessoaBO.validarCPF();
        pessoaBO.validarEmail();
        pessoaBO.validarIdade();
        pessoaBO.validarTelefone();

        return pessoaRepositoryPort.salvar(pessoaBO);
    }

    @Override
    public PessoaBO buscarPorId(Integer id) {
        return pessoaRepositoryPort.buscarPorId(id);
    }
}
