package com.example.poc.entities;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class ValidarAssinaturaDigital {

        private  boolean validaAssinatura(byte[] assinatura, String mensagem, PublicKey pubKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, InvalidAlgorithmParameterException {
            Signature sig = Signature.getInstance("SHA512withECDSA");

            //Inicializando Obj Signature com a Chave Pública
            sig.initVerify(pubKey);

            //Verificando assinatura
            sig.update(mensagem.getBytes());
            return sig.verify(assinatura);
        }
        public boolean assinar(byte[] assinatura, byte[] pubKeyBytes) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, InvalidAlgorithmParameterException, InvalidKeySpecException {
            String mensagem = "Teste:Mensagem";
            X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(pubKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("EC");
            PublicKey pubKey = keyFactory.generatePublic(pubKeySpec);

            boolean valida = validaAssinatura(assinatura, mensagem, pubKey);
            System.out.println("Assinatura é válida? " + valida);
            return valida;
        }

        public byte[] getPubKey() {
            final  String pubKeyBase64 = "MIGbMBAGByqGSM49AgEGBSuBBAAjA4GGAAQBpnyr74amlFTyNVRhCfWK+3BBQB+DrEwxagd91IYCBaneHLuqGEOooCYgUMaM65NcfJf52auSk5kSjRuBjbbR5ZsANQ2Bj7aEqLPSUmzlHvPnrVX+gCtqyazKnEwwGsJpsGYj/+JXvHFhzPNRnEiM0Al4H2aQGz+kPjPfRUwlf8gPwY8=";
            final  byte[] pubKeyBytes = Base64.getDecoder().decode(pubKeyBase64);
            return pubKeyBytes;
        }

}
