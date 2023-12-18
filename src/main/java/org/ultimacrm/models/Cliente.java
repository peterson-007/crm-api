package org.ultimacrm.models;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "cpf")
	private String cpf;

	@Column(name = "data_nascimento")
	private String dataDeNascimento;

	@Column(name = "idade_atual")
	private int idadeAtual;

	@Column(name = "genero")
	private char genero;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Contato> contatos;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Endereco> enderecos;
}
