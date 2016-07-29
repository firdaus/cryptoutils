/*
 * CellBlockCipherWrapper
 */
package com.simonmittag.cryptoutils.symmetric;

import static com.simonmittag.cryptoutils.PropertyHelper.getEnvOrProperty;

/**
 * @author simonmittag
 */
public class PropertyBasedCipherKeyWrapper implements SimpleSymmetricCipher {
    public static final String SYMMETRIC_SECRET_KEY = "SYMMETRIC_SECRET_KEY";
    public static final String INIT_VECTOR = "INIT_VECTOR";

    protected SimpleSymmetricCipher cipher;

    public PropertyBasedCipherKeyWrapper(SimpleSymmetricCipher cipher) {
        this.cipher = cipher;
        this.setKey(getEnvOrProperty(SYMMETRIC_SECRET_KEY));
        this.setInitVector(getEnvOrProperty(INIT_VECTOR));
    }

    public void setKey(String key) {
        this.cipher.setKey(key);
    }

    public void setInitVector(String initVector) {
        this.cipher.setInitVector(initVector);
    }

    public String encrypt(String raw) {
        return cipher.encrypt(raw);
    }

    public String decrypt(String encrypted) {
        return cipher.decrypt(encrypted);
    }
}
