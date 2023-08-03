package com.ricardo.crudspring.exception;

public class RecordNotFoundExcption extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RecordNotFoundExcption(Long id) {
        super("Registro não encontrado com o id " + id);
    }
}
