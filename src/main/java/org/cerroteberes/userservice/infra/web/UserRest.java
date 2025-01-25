package org.cerroteberes.userservice.infra.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.cerroteberes.userservice.app.port.input.use_case.InCreateUser;
import org.cerroteberes.userservice.app.port.input.use_case.InDeleteUser;
import org.cerroteberes.userservice.app.port.input.use_case.InListUser;
import org.cerroteberes.userservice.app.port.input.use_case.InUpdateUser;
import org.cerroteberes.userservice.domain.dto.request.RequestCreateUserDTO;
import org.cerroteberes.userservice.domain.dto.request.RequestUpdateUserDTO;
import org.cerroteberes.userservice.domain.dto.response.ReadUserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static org.cerroteberes.userservice.infra.web.ApiVersion.v1;

@RestController
@RequestMapping(v1+"user")
@AllArgsConstructor
public class UserRest {
    private final InCreateUser inCreateUser;
    private final InDeleteUser inDeleteUser;
    private final InListUser inListUser;
    private final InUpdateUser inUpdateUser;

    @PostMapping
    public ResponseEntity<Void> createResource(@Valid@RequestBody RequestCreateUserDTO dto){
        Long idGenerate = inCreateUser.execute(dto).getId();
        return ResponseEntity.created(URI.create("/api/v1/user/".concat(idGenerate.toString()))).build();
    }
    @GetMapping
    public ResponseEntity<List<ReadUserDTO>> listResources(){
        return ResponseEntity.ok(inListUser.execute());
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateResource(
            @Valid@RequestBody RequestUpdateUserDTO dto,
            @PathVariable Long id){
        inUpdateUser.execute(dto,id);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMapping(@PathVariable Long id){
        inDeleteUser.execute(id);
        return ResponseEntity.noContent().build();
    }
}
