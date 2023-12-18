package org.ultimacrm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTO {

    @NotBlank(message = "Digite o nome do produto")
    private String nome;
    @NotNull(message = "Digite o preço do produto")
    private double preco;
}
