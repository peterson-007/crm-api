package org.ultimacrm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ultimacrm.models.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    boolean existsByCpf(String cpf);

    Cliente findByCpf(String cpf);
    /*
     'existsByCpf' é um método que o Spring Data JPA interpretará AUTOMATICAMENTE
     e implementará a consulta adequada. NÃO é necessário fornecer a implementação do método;
     o Spring Data JPA GERA A CONSULTA baseado NO NOME DO MÉTODO.
    * */
}
