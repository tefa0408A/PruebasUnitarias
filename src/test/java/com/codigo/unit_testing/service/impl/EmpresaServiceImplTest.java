package com.codigo.unit_testing.service.impl;

import com.codigo.unit_testing.aggregates.constants.Constants;
import com.codigo.unit_testing.aggregates.request.EmpresaRequest;
import com.codigo.unit_testing.aggregates.response.BaseResponse;
import com.codigo.unit_testing.dao.EmpresaRepository;
import com.codigo.unit_testing.entity.Empresa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class EmpresaServiceImplTest {

    EmpresaRequest empresaRequest;

    @Mock
    private EmpresaRepository empresaRepository;

    @InjectMocks
    private EmpresaServiceImpl empresaService;

    //nos ayuda a ejecutar e inicializar los mocks e injectMocks
    @BeforeEach
    void setUp(){
        empresaRequest = new EmpresaRequest();
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testCrearEmpresaExiste() {
        //arrange : preparar todo
        //parametro
        //EmpresaRequest empresaRequest = new EmpresaRequest();
        //todo atributo se tiene que definir siempre
        empresaRequest.setNumeroDocumento("123456789");

            //Configurar el comportamiento del mock
            when(empresaRepository.existsByNumeroDocumento(anyString())).
                    thenReturn(true);

        //act
        ResponseEntity<BaseResponse<Empresa>> response = empresaService.crear(empresaRequest);

        //assert
        assertEquals(Constants.CODE_EXIST,response.getBody().getCode());
        assertEquals(Constants.MSJ_EXIST, response.getBody().getMessage());
        assertTrue(response.getBody().getObjeto().isEmpty());

    }

    @Test
    void testCrearEmpresaNoExiste(){
        //arrange : preparar todo
        //parametro
        //EmpresaRequest empresaRequest = new EmpresaRequest();
        //todo atributo se tiene que definir siempre
        empresaRequest.setNumeroDocumento("123456789");
        Empresa empresaEsperada = new Empresa();

        //Configurar el comportamiento del mock
        when(empresaRepository.existsByNumeroDocumento(anyString())).
                thenReturn(false);

        when(empresaRepository.save(any())). thenReturn(empresaEsperada);

        //act
        ResponseEntity<BaseResponse<Empresa>> response = empresaService.crear(empresaRequest);

        //assert
        assertEquals(Constants.CODE_OK,response.getBody().getCode());
        assertEquals(Constants.MSJ_OK, response.getBody().getMessage());
        assertFalse(response.getBody().getObjeto().isEmpty());
        assertSame(empresaEsperada,response.getBody().getObjeto().get());

    }

    @Test
    void testActualizarEmpresaExiste() {
        //arrange
        EmpresaRequest empresaRequest = new EmpresaRequest();
        Long id = 1L;
        Empresa empresa = new Empresa();

        //configurar el mock
        when(empresaRepository.existsById(any())).thenReturn(true);
        //me retorna un objeto opcional por eso uso el Optional.of
        when(empresaRepository.findById(id)).thenReturn(Optional.of(empresa));
        when(empresaRepository.save(any())).thenReturn(empresa);

        //act
        ResponseEntity<BaseResponse<Empresa>> response = empresaService.actualizar(id,empresaRequest);

        //assert
        assertEquals(Constants.CODE_OK,response.getBody().getCode());
        assertEquals(Constants.MSJ_OK, response.getBody().getMessage());
        assertNotNull(response);
        assertFalse(response.getBody().getObjeto().isEmpty());
        //assertTrue(response.getBody().getObjeto().isPresent());
        assertSame(empresa,response.getBody().getObjeto().get());
    }

    @Test
    void testActualizarEmpresaNoExiste(){
        //EmpresaRequest empresaRequest = new EmpresaRequest();
        Long id = 1L;

        //Configurar el comportamiento del mock
        when(empresaRepository.existsById(id)).
                thenReturn(false);

        //act
        ResponseEntity<BaseResponse<Empresa>> response = empresaService.actualizar(id,empresaRequest);

        //assert
        assertNotNull(response);
        assertEquals(Constants.CODE_EMPRESA_NO_EXIST,response.getBody().getCode());
        assertEquals(Constants.MSJ_EMPRESA_NO_EXIST, response.getBody().getMessage());
        assertTrue(response.getBody().getObjeto().isEmpty());
    }

    @Test
    void obtenerEmpresa() {
    }

    @Test
    void obtenerTodos() {
    }



    @Test
    void delete() {
    }

    @Test
    void obtenerEmpresaXNumDoc() {
    }
}