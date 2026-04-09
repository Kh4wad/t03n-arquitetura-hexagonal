package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.rest.controller;

import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.rest.mapper.PessoaDTOMapper;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PessoaBO;
import com.fag.lucasmartins.arquitetura_software.application.ports.in.service.PessoaServicePort;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.rest.dto.PessoaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "/pessoas", "/api/v1/pessoas" })
public class PessoaControllerAdapter {

    private final PessoaServicePort pessoaServicePort;

    public PessoaControllerAdapter(PessoaServicePort pessoaServicePort) {
        this.pessoaServicePort = pessoaServicePort;
    }

    @PostMapping
    public ResponseEntity<PessoaDTO> cadastrarPessoa(@RequestBody PessoaDTO pessoaDTO) {
        PessoaBO pessoaBO = PessoaDTOMapper.toBo(pessoaDTO);

        PessoaBO pessoaCriadaBo = pessoaServicePort.salvar(pessoaBO);

        PessoaDTO pessoaCriadaDTO = PessoaDTOMapper.toDto(pessoaCriadaBo);

        return ResponseEntity.status(201).body(pessoaCriadaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDTO> buscarPessoaPorId(@PathVariable Integer id) {
        PessoaBO pessoaBO = pessoaServicePort.buscarPorId(id);

        if (pessoaBO == null) {
            return ResponseEntity.notFound().build();
        }

        PessoaDTO pessoaDTO = PessoaDTOMapper.toDto(pessoaBO);
        return ResponseEntity.ok(pessoaDTO);
    }
}