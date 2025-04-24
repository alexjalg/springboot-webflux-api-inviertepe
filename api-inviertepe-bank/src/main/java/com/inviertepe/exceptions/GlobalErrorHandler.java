package com.inviertepe.exceptions;

//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ResponseStatusException;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Component
//@Order(-1)
public class GlobalErrorHandler {
//	implements ErrorWebExceptionHandler {
//}
//
//    private final ObjectMapper objectMapper;
//
//    public GlobalErrorHandler(ObjectMapper objectMapper) {
//        this.objectMapper = objectMapper;
//    }
//
//    @Override
//    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
//        HttpStatus status;
//        Map<String, Object> errorAttributes = new HashMap<>();
//
//        // Determina el estado en función de la excepción o define un estado por defecto
//        if (ex instanceof ResponseStatusException) {
//            // Si es una ResponseStatusException, obtenemos el código de estado de la excepción
//            status = (HttpStatus) ((ResponseStatusException) ex).getStatusCode();
//        } else {
//            // En caso contrario, se define como error interno
//            status = HttpStatus.INTERNAL_SERVER_ERROR;
//        }
//        
//        // Personalización del mensaje y detalles según el código de estado
//        switch (status) {
//            case NOT_FOUND -> {
//                errorAttributes.put("error", "Resource Not Found");
//                errorAttributes.put("message", "The requested resource was not found.");
//            }
//            case UNAUTHORIZED -> {
//                errorAttributes.put("error", "Unauthorized");
//                errorAttributes.put("message", "Access to this resource is unauthorized.");
//            }
//            case BAD_REQUEST -> {
//                errorAttributes.put("error", "Bad Request");
//                errorAttributes.put("message", "The request is invalid or cannot be processed.");
//            }
//            default -> {
//                errorAttributes.put("error", "Internal Server Error");
//                errorAttributes.put("message", "An unexpected error occurred. Please try again later.");
//            }
//        }
//
//        // Información adicional en la respuesta de error
//        errorAttributes.put("status", status.value());
//        errorAttributes.put("timestamp", System.currentTimeMillis());
//        errorAttributes.put("path", exchange.getRequest().getPath().value());
//
//        exchange.getResponse().setStatusCode(status);
//        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
//
//        try {
//            byte[] bytes = objectMapper.writeValueAsBytes(errorAttributes);
//            return exchange.getResponse().writeWith(Mono.just(exchange.getResponse().bufferFactory().wrap(bytes)));
//        } catch (JsonProcessingException e) {
//            return Mono.error(e);
//        }
//    }
}
