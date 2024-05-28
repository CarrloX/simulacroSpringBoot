package com.riwi.demo.utils.messages;

public class ErrorMessage {
    public static String idNotFound(String entity) {

        final String message = "no hay resgistros en la entidad %s con el id suministrado";

        return String.format(message, entity);
    }
}
