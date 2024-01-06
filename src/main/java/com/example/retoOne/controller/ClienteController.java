package com.example.retoOne.controller;

import com.example.retoOne.dto.ClienteDto;
import com.example.retoOne.entity.Cliente;
import com.example.retoOne.service.ClienteService;
import com.example.retoOne.utils.CustomResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cliente")
public class ClienteController {

    private static final Logger LOG = Logger.getLogger(ClienteController.class.getName());

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/list")
    public ResponseEntity<?> list() {
        try {
            List<ClienteDto> clienteDtoList = clienteService.mockeados();
            if (clienteDtoList != null && !clienteDtoList.isEmpty()) {
                return CustomResponseHandler.generateResponse("Listado de cliente", HttpStatus.OK, clienteDtoList);
            } else {
                return CustomResponseHandler.generateResponse("No hay datos en la tabla //Cliente//", HttpStatus.NO_CONTENT, clienteDtoList);
            }
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "", e);
            return CustomResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }

    @GetMapping("/database/create")
    public ResponseEntity<?> createDataBase() {
        try {
            clienteService.createDataBase();
            List<Cliente> clienteList = clienteService.findAll();
            if (clienteList != null && !clienteList.isEmpty()) {
                return CustomResponseHandler.generateResponse("Listado de cliente", HttpStatus.OK, clienteList);
            } else {
                return CustomResponseHandler.generateResponse("No hay datos en la tabla //Cliente//", HttpStatus.NO_CONTENT, clienteList);
            }
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "", e);
            return CustomResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }


    @GetMapping("/database/list")
    public ResponseEntity<?> listDataBase() {
        try {
            List<Cliente> clienteList = clienteService.findAll();
            if (clienteList != null && !clienteList.isEmpty()) {
                return CustomResponseHandler.generateResponse("Listado de cliente", HttpStatus.OK, clienteList);
            } else {
                return CustomResponseHandler.generateResponse("No hay datos en la tabla //Cliente//", HttpStatus.NO_CONTENT, clienteList);
            }
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "", e);
            return CustomResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }

    @PostMapping("/database/findBy")
    public ResponseEntity<?> findByDataBase(@RequestBody Cliente object) {
        try {
            Cliente cliente = clienteService.findByDataBase(object);
            if (cliente != null && cliente.getId() != null) {
                return CustomResponseHandler.generateResponse("Data cliente", HttpStatus.OK, cliente);
            } else {
                return CustomResponseHandler.generateResponse("No hay datos en la tabla //Cliente//", HttpStatus.NO_CONTENT, cliente);
            }
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "", e);
            return CustomResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }

    @GetMapping("/database/findById/{id}")
    public ResponseEntity<?> findByIdDataBase(@PathVariable Long id) {
        try {
            Cliente cliente = clienteService.findByIdDataBase(id);
            if (cliente != null && cliente.getId() != null) {
                return CustomResponseHandler.generateResponse("Data cliente", HttpStatus.OK, cliente);
            } else {
                return CustomResponseHandler.generateResponse("No hay datos en la tabla //Cliente//", HttpStatus.NO_CONTENT, cliente);
            }
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "", e);
            return CustomResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }
}
