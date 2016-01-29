package com.finch.crud.service;

import com.finch.crud.model.Cliente;
import java.util.List;

/**
 * Interface dos serviços do cliente.
 *
 * @author guilherme.carvalho
 */
public interface ClienteService {
    List<Cliente> findAll();

    Cliente findById(Long id);

    Cliente create(Cliente cliente);
    
    Cliente update(Cliente cliente);

    void remove(Long id);
}

