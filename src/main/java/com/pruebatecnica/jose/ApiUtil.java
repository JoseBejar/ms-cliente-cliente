package com.pruebatecnica.jose;

import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.http.MediaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ApiUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void setExampleResponse(NativeWebRequest request, String mediaType, String example) {
        // Puedes implementar lógica para establecer el ejemplo en la respuesta
        // Por ejemplo, si estás usando MockMvc para pruebas, podrías configurar el ejemplo allí
        // Aquí, simplemente imprimimos el ejemplo
        System.out.println("Setting example response: " + example);
    }
}