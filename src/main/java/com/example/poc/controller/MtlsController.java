package com.example.poc.controller;

import com.example.poc.entities.ValidarAssinaturaDigital;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;


@RestController()
@RequestMapping("/mtls")
public class MtlsController {

    @GetMapping
    public Map<String, String> mTLS(
            @RequestHeader("access-signature") String accessSignature,
            @RequestHeader("message") String mensagem
    ) {

        try {
            byte[] decodedSignature = mehtodsBase64ToByteArray(accessSignature);

            ValidarAssinaturaDigital validarAssinaturaDigital = new ValidarAssinaturaDigital(mensagem);
            boolean valida = validarAssinaturaDigital.verify(decodedSignature);

            Map<String, String> response = new HashMap<>();
            response.put("Assinatura valida", String.valueOf(valida));
            return response;
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", e.getMessage());
            return response;
        }
    }

    public byte[] mehtodsBase64ToByteArray(String signature) {
        byte[] decode = Base64.getDecoder().decode(signature);
        return decode;
    }
}


