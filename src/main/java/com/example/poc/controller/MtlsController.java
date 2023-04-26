package com.example.poc.controller;

import com.example.poc.entities.ValidarAssinaturaDigital;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController()
@RequestMapping("/mtls")
public class MtlsController {

    @GetMapping
    public Map<String, String> mTLS(@RequestHeader("access-signature") String accessSignature, @RequestHeader("mensagem") String mensagem) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, SignatureException, InvalidKeyException, InvalidKeySpecException {
//        GerarAssinaturaDigital assinaturaDigital = new GerarAssinaturaDigital();
//        byte[] assinatura = assinaturaDigital.geraAssinatura(mensagem);
//        byte[] assinatura = Base64.getDecoder().decode(accessSignature);

        byte[] assinaturaBytes = Hex.decode(accessSignature);

        ValidarAssinaturaDigital validarAssinaturaDigital = new ValidarAssinaturaDigital();
//        boolean valida = validarAssinaturaDigital.assinar(assinatura , assinaturaDigital.getPubKey().getEncoded());
        boolean valida = validarAssinaturaDigital.assinar(assinaturaBytes, validarAssinaturaDigital.getPubKey());
        Map<String, String> response = new HashMap<>();
        response.put("Assinatura valida? ", String.valueOf(valida));
        return response;
    }

    public byte[] tratarFormatoAssinatura(String accessSignature) {
        byte[] assinaturaBytes = Hex.decode(accessSignature);
        return assinaturaBytes;
    }
}


