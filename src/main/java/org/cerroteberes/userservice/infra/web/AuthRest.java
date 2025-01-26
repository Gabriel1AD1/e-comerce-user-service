package org.cerroteberes.userservice.infra.web;

import lombok.AllArgsConstructor;
import org.cerroteberes.userservice.app.port.input.use_case.InGetUserPrincipalForEmail;
import org.cerroteberes.userservice.app.port.input.use_case.InGetUserPrincipalForUserId;
import org.cerroteberes.userservice.domain.dto.response.ResponseUserPrincipalDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.cerroteberes.userservice.infra.web.ApiVersion.v1;

@RestController
@RequestMapping(v1+"user-principal")
@PreAuthorize("hasRole('ADMIN') or hasRole('TOTAL_ACCESS')")
@AllArgsConstructor
public class AuthRest {
    private final InGetUserPrincipalForEmail inGetUserPrincipalForEmail;
    private final InGetUserPrincipalForUserId inGetUserPrincipalForUserId;
    @GetMapping("/")
    public ResponseEntity<ResponseUserPrincipalDTO> getUserPrincipalByImei(@RequestParam("email") String email){
        return ResponseEntity.ok(inGetUserPrincipalForEmail.executeGetUserPrincipalForEmail(email));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseUserPrincipalDTO> getUserPrincipalById(@PathVariable("id")Long userId){
        return ResponseEntity.ok(inGetUserPrincipalForUserId.executeGetUserPrincipalForUserId(userId));
    }
}
