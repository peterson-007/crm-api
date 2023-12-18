package org.ultimacrm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ultimacrm.models.Endereco;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDTO {

    @NotBlank(message = "O nome da rua é obrigatório")
    private String rua;
    @NotBlank(message = "O número é obrigatório")
    private String numero;
    private String complemento;
    @NotBlank(message = "O bairro é obrigatório")
    private String bairro;
    @NotBlank(message = "A cidade é obrigatório")
    private String cidade;
    @NotBlank(message = "O CEP é obrigatório")
    private String cep;

    private int idCliente;

    public EnderecoDTO(Endereco endereco) {
        this.rua = endereco.getRua();
        this.numero = endereco.getNumero();
        this.complemento = endereco.getComplemento();
        this.bairro = endereco.getBairro();
        this.cidade = endereco.getCidade();
        this.cep = endereco.getCep();
        // Populando o id do cliente
        if (endereco.getCliente() != null) {
            this.idCliente = Math.toIntExact(endereco.getCliente().getId());
        }
    }
}