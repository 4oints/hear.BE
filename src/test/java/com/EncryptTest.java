package com;

import org.assertj.core.api.Assertions;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class EncryptTest {
    //@Test
    public void jasypt_encrypt_decrypt_test() {
        String plainText = "jdbc:postgresql://13.124.197.52:5432/heardb";

        StandardPBEStringEncryptor jasypt = new StandardPBEStringEncryptor();
        jasypt.setProvider(new BouncyCastleProvider());
        jasypt.setPassword("heardot");
        jasypt.setAlgorithm("PBEWithSHA256And128BitAES-CBC-BC");

        String encryptedText = jasypt.encrypt(plainText);
        String decryptedText = jasypt.decrypt(encryptedText);

        System.out.println("encryptedText = " + encryptedText);
        System.out.println("decryptedText = " + decryptedText);

        assertThat(plainText).isEqualTo(decryptedText);
    }
}