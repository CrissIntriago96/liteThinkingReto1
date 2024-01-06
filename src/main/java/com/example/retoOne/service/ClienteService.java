package com.example.retoOne.service;

import com.example.retoOne.dto.ClienteDto;
import com.example.retoOne.entity.Cliente;
import com.example.retoOne.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<ClienteDto> mockeados() {
        List<ClienteDto> result = new ArrayList<>();
        List<String> numDocumentoList = Arrays.asList("12345678", "87654321", "01234567", "76543210");
        String[] tipoDocumentos = {"C", "P"};
        String[] nombres = {"Cristian", "Angel", "Luis", "Jorge", "Xavi", "Jimmy"};
        String[] apellidos = {"Meza", "Angulo", "Suarez", "Choez", "Salinas", "Intriago"};
        String[] telefonos = {"999999999", "555555555", "888888888", "666666666", "77777777", "444444444"};
        String[] direcciones = {"QUITO", "GUAYAQUIL", "IBARRA", "DURAN", "CUENCA", "LOJA"};
        for (String num : numDocumentoList) {
            ClienteDto.ClienteDtoBuilder clienteBuilder = ClienteDto.builder();
            clienteBuilder.tipoDocumento(tipoDocumentos[(int) Math.floor(Math.random() * 2)]);
            clienteBuilder.numDocumento(num);
            clienteBuilder.primerNombre(nombres[(int) Math.floor(Math.random() * 6)]);
            clienteBuilder.segundoNombre(apellidos[(int) Math.floor(Math.random() * 6)]);
            clienteBuilder.primerApellido(nombres[(int) Math.floor(Math.random() * 6)]);
            clienteBuilder.segundoApellido(apellidos[(int) Math.floor(Math.random() * 6)]);
            clienteBuilder.telefono(telefonos[(int) Math.floor(Math.random() * 6)]);
            clienteBuilder.direccion(direcciones[(int) Math.floor(Math.random() * 6)]);
            ClienteDto clienteDto = clienteBuilder.build();
            result.add(clienteDto);
        }
        return result;
    }

    public List<Cliente> findAll() {
        try {
            return clienteRepository.findAll();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }


    public void createDataBase() {
        List<ClienteDto> mockeados = mockeados();
        List<Cliente> list = new ArrayList<>();
        Cliente.ClienteBuilder clienteSave = Cliente.builder();
        for (ClienteDto dto : mockeados) {
            clienteSave.tipoDocumento(dto.getTipoDocumento());
            clienteSave.numDocumento(dto.getNumDocumento());
            clienteSave.primerNombre(dto.getPrimerNombre());
            clienteSave.segundoNombre(dto.getSegundoNombre());
            clienteSave.primerApellido(dto.getPrimerApellido());
            clienteSave.segundoApellido(dto.getSegundoApellido());
            clienteSave.telefono(dto.getTelefono());
            clienteSave.direccion(dto.getDireccion());
            Cliente cliente = clienteSave.build();
            list.add(cliente);
        }
        clienteRepository.saveAll(list);
    }

    public Cliente findByDataBase(Cliente object) {
        try {
            return clienteRepository.findOne(Example.of(object)).orElse(null);
        } catch (Exception e) {
            return null;
        }
    }

    public Cliente findByIdDataBase(Long id) {
        try {
            return clienteRepository.findById(id).get();
        } catch (Exception e) {
            return null;
        }
    }
}
