package com.lab4.controller;

import com.lab4.dto.OwnerDto;
import com.lab4.service.OwnerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/owners")
@Tag(name = "Owner Controller")
@SecurityRequirement(name = "basicAuth")
public class OwnerController {

    private final OwnerService owners;

    public OwnerController(OwnerService owners) {
        this.owners = owners;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get owner by id")
    public OwnerDto get(@PathVariable Long id) {
        return owners.findById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @accessCheck.isOwner(#id)")
    @Operation(summary = "Update owner")
    public ResponseEntity<OwnerDto> update(@PathVariable Long id,
                                           @RequestBody OwnerDto dto) {
        return ResponseEntity.ok(owners.updateOwner(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @accessCheck.isOwner(#id)")
    @Operation(summary = "Delete owner")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        owners.deleteOwner(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    @Operation(summary = "Create owner")
    public ResponseEntity<OwnerDto> create(@RequestBody OwnerDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(owners.createOwner(dto));
    }
}
