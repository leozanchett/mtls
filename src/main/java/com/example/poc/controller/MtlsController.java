package com.example.poc.controller;

import com.example.poc.entities.ValidarAssinaturaDigital;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController()
@RequestMapping("/mtls")
public class MtlsController {

    @GetMapping
    public Map<String, String> mTLS(@RequestHeader("access-signature") String accessSignature, @RequestHeader("mensagem") String mensagem) throws  NoSuchAlgorithmException, SignatureException, InvalidKeyException, InvalidKeySpecException, UnsupportedEncodingException {
        byte[] assinaturaBase64 = Base64.getDecoder().decode(accessSignature);
        ValidarAssinaturaDigital validarAssinaturaDigital = new ValidarAssinaturaDigital(mensagem);

        boolean valida = validarAssinaturaDigital.verify(assinaturaBase64);

        Map<String, String> response = new HashMap<>();
        response.put("Assinatura valida", String.valueOf(valida));
        return response;
    }
}


