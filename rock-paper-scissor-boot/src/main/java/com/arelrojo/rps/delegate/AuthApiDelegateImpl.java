package com.arelrojo.rps.delegate;

import com.arelrojo.rps.contract.endpoint.api.AuthApiDelegate;
import com.arelrojo.rps.contract.endpoint.model.AuthDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AuthApiDelegateImpl implements AuthApiDelegate {

    public ResponseEntity<AuthDTO> auth(AuthDTO authDTO){
        return ResponseEntity.ok().body(new AuthDTO());
    }
}
