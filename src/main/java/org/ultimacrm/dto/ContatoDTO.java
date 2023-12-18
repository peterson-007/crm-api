package org.ultimacrm.dto;

import lombok.*;
import org.ultimacrm.dto.validations.ValidEmail;
import org.ultimacrm.models.Contato;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContatoDTO {


    @NotBlank(message = "O telefone é obrigatório")
    private String telefone;

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "E-mail inválido")
    @ValidEmail(message = "E-mail já cadastrado")
    private String email;

    private int idCliente;

    public ContatoDTO(Contato contato) {

        this.telefone = contato.getTelefone();
        this.email = contato.getEmail();
        // Populando o id do cliente
        if (contato.getCliente() != null) {
            this.idCliente = Math.toIntExact(contato.getCliente().getId());
        }
    }


}