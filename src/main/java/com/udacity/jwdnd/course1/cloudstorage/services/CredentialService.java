package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {
    private final CredentialMapper credentialMapper;
    private final  EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    public List<Credential> getAllCredential() {
        List<Credential> credentialList = credentialMapper.getAllCredential();
        for (Credential credential: credentialList) {
            credential.setDecryptPass(encryptionService.decryptValue(credential.getPassword(), credential.getKey()));
        }

        return credentialList;
    }

    public void addNewCredential(Credential credential) {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);

        String encodeKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(credential.getDecryptPass(), encodeKey);

        credential.setPassword(encryptedPassword);
        credential.setKey(encodeKey);

        if (credential.getCredentialid() == null || credentialMapper.getCredential(credential.getCredentialid()) == null) {
            credentialMapper.addNewCredential(credential);
        } else {
            credentialMapper.updateCredential(credential);
        }
    }

    public void deleteCredential(int credentialid) {
        credentialMapper.deleteCredential(credentialid);
    }
}
