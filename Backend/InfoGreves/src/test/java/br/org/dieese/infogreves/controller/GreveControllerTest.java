package br.org.dieese.infogreves.controller;

import br.org.dieese.infogreves.domain.Greve;
import br.org.dieese.infogreves.payloads.request.FinalGreveRequest;
import br.org.dieese.infogreves.payloads.request.GreveRequest;
import br.org.dieese.infogreves.service.GreveService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GreveControllerTest {
    @Mock
    private GreveService greveService;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private GreveController greveController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCadastrarEnvioSuccess() {
        GreveRequest greveRequest = new GreveRequest();
        when(bindingResult.hasErrors()).thenReturn(false);
        when(greveService.cadastrarGreve(any(GreveRequest.class))).thenReturn("12345");

        ResponseEntity<?> response = greveController.cadastrarEnvio(greveRequest, bindingResult);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("12345", response.getBody());
        verify(greveService, times(1)).cadastrarGreve(any(GreveRequest.class));
    }

    @Test
    public void testCadastrarEnvioValidationError() {
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getFieldError()).thenReturn(new FieldError("greveRequest", "dataInicio", "A data de início não pode ser nula."));

        ResponseEntity<?> response = greveController.cadastrarEnvio(new GreveRequest(), bindingResult);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("A data de início não pode ser nula.", response.getBody());
    }

    @Test
    public void testBuscarTodosSuccess() {
        List<Greve> greves = new ArrayList<>();
        when(greveService.buscarTodasGreves()).thenReturn(greves);

        ResponseEntity<?> response = greveController.buscarTodos();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(greves, response.getBody());
    }

    @Test
    public void testBuscarGrevePorIdSuccess() {
        Greve greve = new Greve();
        when(greveService.buscarGrevePorId(anyString())).thenReturn(greve);

        ResponseEntity<?> response = greveController.buscarGrevePorId("12345");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(greve, response.getBody());
    }

    @Test
    public void testBuscarGrevePorIdNotFound() {
        when(greveService.buscarGrevePorId(anyString())).thenThrow(NoSuchElementException.class);

        ResponseEntity<?> response = greveController.buscarGrevePorId("12345");

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testAtualizarDadosFinalGreveSuccess() {
        FinalGreveRequest finalGreveRequest = new FinalGreveRequest();
        Greve greve = new Greve();
        when(bindingResult.hasErrors()).thenReturn(false);
        when(greveService.alterarDadosFinalGreve(any(FinalGreveRequest.class), anyString())).thenReturn(greve);

        ResponseEntity<?> response = greveController.atualizarDadosFinalGreve("12345", finalGreveRequest, bindingResult);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(greve, response.getBody());
    }

    @Test
    public void testAtualizarDadosFinalGreveValidationError() {
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getFieldError()).thenReturn(new FieldError("finalGreveRequest", "dataFim", "A data de fim não pode ser nula."));

        ResponseEntity<?> response = greveController.atualizarDadosFinalGreve("12345", new FinalGreveRequest(), bindingResult);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("A data de fim não pode ser nula.", response.getBody());
    }
    @Test
    public void testDeletarGreveSuccess() {
        ResponseEntity<?> response = greveController.deletarGreve("12345");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(greveService, times(1)).deletarGreve(anyString());
    }

    @Test
    public void testDeletarGreveNotFound() {
        doThrow(NoSuchElementException.class).when(greveService).deletarGreve(anyString());

        ResponseEntity<?> response = greveController.deletarGreve("12345");

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

}