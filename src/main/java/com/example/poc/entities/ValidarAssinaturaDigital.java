package com.example.poc.entities;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class ValidarAssinaturaDigital {

        private String mensagem;
        private String appPubKey;

        public  ValidarAssinaturaDigital(String mensagem) {
            this.mensagem = mensagem;
            Dotenv dotenv = Dotenv.load();
            this.appPubKey = dotenv.get("APP_PUB_KEY");
        }

        public boolean verify(byte[] assinatura) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, InvalidKeySpecException, UnsupportedEncodingException {
            System.out.println("Mensagem: " + this.mensagem);
            final Signature sig = Signature.getInstance("SHA256withECDSA");


            //Inicializando Obj Signature com a Chave Pública
            sig.initVerify(this.getPubKey());

            //Verificando assinatura
            sig.update(this.mensagem.getBytes("UTF-8"));
            boolean valida = sig.verify(assinatura);
            System.out.println("Assinatura é válida? " + valida);
            return valida;
        }

        private PublicKey getPubKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
            final byte[] pubKeyBytes = Base64.getDecoder().decode(this.appPubKey);
            final X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(pubKeyBytes);
            final KeyFactory keyFactory = KeyFactory.getInstance("EC");
            final PublicKey pubKey = keyFactory.generatePublic(pubKeySpec);
            return pubKey;
        }

}
