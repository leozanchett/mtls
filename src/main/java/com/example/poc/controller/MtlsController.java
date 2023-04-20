package com.example.poc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;


@RestController()
@RequestMapping("/mtls")
public class MtlsController {

    @GetMapping
    public String mTLS(@RequestHeader("access-signature") String acessSignature) throws IllegalAccessException {
        System.out.println("access-signature: " + acessSignature);
        try {
            byte[] xBytes = Base64.getUrlDecoder().decode("AbFCmkni9pvGC1o9uAOhPGfgveJrk9Y4NgtNHLyb6FtgwgMHXSMZPoFLsA03neFa1i2-36GnT_zTydSbyMZMS9Py".getBytes());
            byte[] yBytes = Base64.getUrlDecoder().decode("AXR3qFe3LBJvY7pXrBw3bQdUP6ozsHZqa7VIFUVGnY5a7iakifA6QeGmtNbcGI9y32nJzHorDcYKLQ38BUbWd9JM".getBytes());
            String curveName = "secp521r1";

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Hello World!";
    }
}