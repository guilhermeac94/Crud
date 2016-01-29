package com.finch.crud.controller;

import com.finch.crud.exception.BadRequestException;
import com.finch.crud.exception.NotFoundException;
import com.finch.crud.model.Cliente;
import com.finch.crud.service.ClienteService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller responsável por receber as requisições.
 *
 * @author guilherme.carvalho
 */
@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    List<Cliente> getClientes() {
        return clienteService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    Cliente create(@RequestBody @Valid Cliente cliente) {
        if (clienteService.findById(cliente.getId()) == null) {
            return clienteService.create(cliente);
        } else {
            throw new BadRequestException();
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    Cliente update(@RequestBody @Valid Cliente cliente, @PathVariable("id") Long id) {
        if (clienteService.findById(id) != null) {
            cliente.setId(id);
            return clienteService.update(cliente);
        } else {
            throw new NotFoundException();
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    void delete(@PathVariable("id") Long id) {
        if (clienteService.findById(id) != null) {
            clienteService.remove(id);
        } else {
            throw new NotFoundException();
        }
    }
    
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    Cliente findById(@PathVariable("id") Long id) {
        return clienteService.findById(id);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    String handleBadRequest(BadRequestException ex) {
        return "Id já existente";
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody
    String handleNotFound(NotFoundException ex) {
        return "Id não existente";
    }

}
