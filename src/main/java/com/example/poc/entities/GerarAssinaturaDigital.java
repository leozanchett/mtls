package com.example.poc.entities;
import java.security.*;
import java.security.spec.ECGenParameterSpec;
public class GerarAssinaturaDigital {

    private PublicKey pubKey;

        public PublicKey getPubKey() {
            return pubKey;
        }

        public void setPubKey(PublicKey pubKey) {
            this.pubKey = pubKey;
        }

        public byte[] geraAssinatura(String mensagem) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, InvalidAlgorithmParameterException {
            Signature sig = Signature.getInstance("SHA512withECDSA");

            //Geração das chaves públicas e privadas
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC");
            ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp521r1");
            kpg.initialize(ecSpec, new SecureRandom());
            KeyPair keyP = kpg.generateKeyPair();
            this.pubKey = keyP.getPublic();
            PrivateKey priKey = keyP.getPrivate();

            //Inicializando Obj Signature com a Chave Privada
            sig.initSign(priKey);

            //Gerar assinatura
            sig.update(mensagem.getBytes());
            byte[] assinatura = sig.sign();

            return assinatura;
        }


}
