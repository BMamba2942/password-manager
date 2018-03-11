package passwordmanager.controllers;

import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.jasypt.util.text.StrongTextEncryptor;
import passwordmanager.models.Password;

public class EncryptionController {

    private static enum MODES { ENCRPYT, DECRYPT };

    private Password _handlePassword(Password password, String key, MODES mode) {
        StrongTextEncryptor encryptor = new StrongTextEncryptor();
        encryptor.setPassword(key);
        String name = "", pass = "";
        try {
            name = mode == MODES.ENCRPYT ? encryptor.encrypt(password.getName()) : encryptor.decrypt(password.getName());
            pass = mode == MODES.ENCRPYT ? encryptor.encrypt(password.getValue()) : encryptor.decrypt(password.getValue());
        } catch (EncryptionOperationNotPossibleException e) {
            throw e;
        }

        return new Password(name, pass);
    }

    public Password encryptPassword(Password password, String key) {
        return _handlePassword(password, key, MODES.ENCRPYT);
    }

    public Password decryptPassword(Password password, String key) {
        return _handlePassword(password, key, MODES.DECRYPT);
    }
}
