package com.fag.lucasmartins.arquitetura_software.core.domain.bo;

import java.time.LocalDate;

import com.fag.lucasmartins.arquitetura_software.core.domain.exceptions.DomainException;

public class PessoaBO {
    private Integer id;

    private String nomeCompleto;

    private String cpf;

    private LocalDate dataNascimento;

    private String email;

    private String telefone;

    public PessoaBO(String nomeCompleto, String cpf, LocalDate dataNascimento, String email, String telefone) {
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.telefone = telefone;

        validarIdade();
        validarCPF();
        validarTelefone();
        validarEmail();
    }

    public PessoaBO() {
    }

    public void validarIdade() {
        if (this.dataNascimento != null && !this.dataNascimento.isAfter(LocalDate.now().minusYears(18))) {
            System.out.println("A idade é válida.");
        } else {
            throw new DomainException("Erro: Idade mínima de 18 anos não atendida.");
        }
    }

    public void validarCPF() {
        if (!cpf.isEmpty()) {
            if (this.cpf.length() == 11) {
                System.out.println("O CPF é válido.");
            } else {
                throw new DomainException("Erro: CPF deve conter 11 dígitos.");
            }
        } else {
            throw new DomainException("Erro: O CPF é obrigatório.");
        }
    }

    public void validarTelefone() {
        boolean soNumeros = telefone.chars().allMatch(Character::isDigit);
        if (!telefone.isEmpty()) {
            if (this.telefone.length() == 11 && soNumeros) {
                System.out.println("O telefone é válido.");
            } else {
                throw new DomainException("Erro: Telefone deve conter 11 dígitos.");
            }
        } else {
            throw new DomainException("Erro: Informe seu numero de telefone.");
        }
    }

    public void validarEmail() {
        if (!email.isEmpty()) {
            if (this.email.contains("@") && this.email.contains(".")) {
                System.out.println("O email é válido.");
            } else {
                throw new DomainException("Erro: E-mail inválido");
            }
        } else {
            throw new DomainException("Erro: O Email é obrigatorio.");
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

}
