/*
 * AsymmetricKeyRSACipherTest
 */
package com.simonmittag.cryptoutils.asymmetric;

import com.simonmittag.cryptoutils.SimpleCipher;
import com.sun.xml.internal.fastinfoset.sax.SystemIdResolver;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import sun.nio.cs.StandardCharsets;

import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.Random;

import static com.sun.xml.internal.ws.dump.LoggingDumpTube.Position.Before;
import static org.junit.Assert.assertEquals;

/**
 * @author simonmittag
 * @since <version>
 */
public class AsymmetricKeyRSACipherTest {
    protected String publicKey;
    protected String privateKey;
    protected SimpleCipher cipher;

    @Before
    public void setup() {
        publicKey = readClassPathResourceAsString("public_key");
        privateKey = readClassPathResourceAsString("private_key");
        cipher = new AsymmetricKeyRSACipher(publicKey, privateKey);
    }

    @Test
    public void testEncryptDecrypt() {
        String encrypted = cipher.encrypt("Hello World");
        String decrypted = cipher.decrypt(encrypted);
        assertEquals(decrypted, "Hello World");
    }

    @Test
    public void testEncryptionPerformance() throws UnsupportedEncodingException {
        int base=1;
        do {
            printEncryptionPerformance(base*=2);
        } while (base<128);
    }

    public void printEncryptionPerformance(int lengthInBytes) throws UnsupportedEncodingException {
        String message = "";
        for(int i=0;i<lengthInBytes;i++) { message += "a"; }
        long before = System.nanoTime();
        String encrypted = cipher.encrypt(message);
        long after = System.nanoTime();
        long elapsedNanos = after-before;
        System.out.printf("\nthe encryption of %s bytes took %s nanoseconds (%s microseconds, %s milliseconds)",
                message.length(), elapsedNanos, elapsedNanos/1000, elapsedNanos/1000000);
    }

    protected String readClassPathResourceAsString(String relativePath) {
        try {
            String content = new String(Files.readAllBytes(
                    Paths.get(ClassLoader.getSystemResource(relativePath).toURI())
            ));
            return content;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
