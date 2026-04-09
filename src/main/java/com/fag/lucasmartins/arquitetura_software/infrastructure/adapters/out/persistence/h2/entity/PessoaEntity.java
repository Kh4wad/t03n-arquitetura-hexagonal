package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.out.persistence.h2.entity;

import java.time.LocalDate;

import javax.persistence.*;

@Entity
@Table(name = "tb_pessoas")
public class PessoaEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)

   private Integer id;

   private String nomeCompleto;

   private String cpf;

   private LocalDate dataNascimento;

   private String email;

   private String telefone;

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
