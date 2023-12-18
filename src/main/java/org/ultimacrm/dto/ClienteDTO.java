package org.ultimacrm.dto;

import lombok.*;
import org.ultimacrm.dto.validations.ValidCpf;
import org.ultimacrm.models.Cliente;

import javax.validation.constraints.*;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {


    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @ValidCpf //Validação Personalizada
    @NotBlank(message = "O CPF é obrigatório")
    private String cpf;

    @NotBlank(message = "A data de nascimento é obrigatória")
    private String dataDeNascimento;

    @Min(value = 0, message = "A idade não pode ser negativa")
    private int idadeAtual;

    @NotNull(message = "O gênero não pode ser nulo")
    private char genero;

    private List<ContatoDTO> contatos;

    private List<EnderecoDTO> enderecos;

    public ClienteDTO(Cliente cliente) {

        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
        this.dataDeNascimento = cliente.getDataDeNascimento();
        this.idadeAtual = cliente.getIdadeAtual();
        this.genero = cliente.getGenero();

        // Populando contatos (se existirem)
        if (cliente.getContatos() != null) {
            this.contatos = cliente.getContatos().stream()
                    .map(ContatoDTO::new)
                    .collect(Collectors.toList());
        }

        // Populando endereços (se existirem)
        if (cliente.getEnderecos() != null) {
            this.enderecos = cliente.getEnderecos().stream()
                    .map(EnderecoDTO::new)
                    .collect(Collectors.toList());
        }
    }

}
