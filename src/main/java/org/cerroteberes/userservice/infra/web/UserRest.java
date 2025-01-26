package org.cerroteberes.userservice.infra.web;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.cerroteberes.userservice.app.port.input.use_case.*;
import org.cerroteberes.userservice.domain.dto.request.RequestCreateUserDTO;
import org.cerroteberes.userservice.domain.dto.request.RequestUpdateUserDTO;
import org.cerroteberes.userservice.domain.dto.response.ReadUserDTO;
import org.cerroteberes.userservice.domain.entity.enums.TypeUserSignup;
import org.cerroteberes.userservice.infra.security.models.MCSVPrincipalSecurity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static org.cerroteberes.userservice.infra.web.ApiVersion.v1;

@RestController
@RequestMapping(v1+"user")
@PreAuthorize("hasRole('ADMIN') or hasRole('TOTAL_ACCESS')")
@AllArgsConstructor
public class UserRest {
    private final InCreateUser inCreateUser;
    private final InDeleteUser inDeleteUser;
    private final InListUser inListUser;
    private final InUpdateUser inUpdateUser;
    @PostMapping("/{type-user}")
    public ResponseEntity<Void> createResource(@Valid@RequestBody RequestCreateUserDTO dto,@PathVariable("type-user") TypeUserSignup typeUserSignup){
        Long idGenerate = inCreateUser.executeCreateUser(dto, typeUserSignup).getId();
        return ResponseEntity.created(URI.create("/api/v1/user/".concat(idGenerate.toString()))).build();
    }
    @GetMapping
    public ResponseEntity<List<ReadUserDTO>> listResources(){
        return ResponseEntity.ok(inListUser.executeListUser());
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateResource(@Valid@RequestBody RequestUpdateUserDTO dto, @NotNull @PathVariable Long id){
        inUpdateUser.execute(dto,id);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMapping(@PathVariable Long id){
        inDeleteUser.executeDeleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/test")
    public String ok() {
        // Obtiene el usuario autenticado desde el contexto de seguridad
        MCSVPrincipalSecurity userPrincipalSecurity =
                (MCSVPrincipalSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "Usuario autenticado: " + userPrincipalSecurity.getName();
    }
}
