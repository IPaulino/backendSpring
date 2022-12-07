package com.alunoonline.api.backend.secretaria.models;

import lombok.AllArgsConstructor; // Criação de construtores com argumentos.
import lombok.Data; // Criação todos os Get, Set.
import lombok.NoArgsConstructor; // Criação de construtores sem argumentos.

import javax.persistence.Entity; // Entity transforma a classe Aluno em entidade (uma tabela).
import javax.persistence.GeneratedValue; // Geração de dados de identidade.
import javax.persistence.GenerationType;
import javax.persistence.Id; // Informacao que o atributo é a Chave da Tabela, a primary key.
import java.io.Serializable; //

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Aluno implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private String curso;

}
