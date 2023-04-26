package com.example.poc.entities;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class ValidarAssinaturaDigital {

        private String mensagem;

        public  ValidarAssinaturaDigital(String mensagem) {
            this.mensagem = mensagem;
        }

        public boolean verify(byte[] assinatura) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, InvalidKeySpecException, UnsupportedEncodingException {
            System.out.println("Mensagem: " + this.mensagem);
            Signature sig = Signature.getInstance("SHA512withECDSA");

            //Inicializando Obj Signature com a Chave Pública
            sig.initVerify(this.getPubKey());

            //Verificando assinatura
            sig.update(this.mensagem.getBytes());
            boolean valida = sig.verify(assinatura);
            System.out.println("Assinatura é válida? " + valida);
            return valida;
        }

        private PublicKey getPubKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
            final  String pubKeyBase64 = "MIGbMBAGByqGSM49AgEGBSuBBAAjA4GGAAQBpnyr74amlFTyNVRhCfWK+3BBQB+DrEwxagd91IYCBaneHLuqGEOooCYgUMaM65NcfJf52auSk5kSjRuBjbbR5ZsANQ2Bj7aEqLPSUmzlHvPnrVX+gCtqyazKnEwwGsJpsGYj/+JXvHFhzPNRnEiM0Al4H2aQGz+kPjPfRUwlf8gPwY8=";
            final  byte[] pubKeyBytes = Base64.getDecoder().decode(pubKeyBase64);
            X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(pubKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("EC");
            PublicKey pubKey = keyFactory.generatePublic(pubKeySpec);
            return pubKey;
        }

}
