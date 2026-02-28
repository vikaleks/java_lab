package com.lab4.service;

import com.lab4.dto.OwnerDto;
import com.lab4.mapper.OwnerMapper;
import com.lab4.model.Owner;
import com.lab4.repository.OwnerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class OwnerService {

    private final OwnerRepository repo;
    private final OwnerMapper mapper;

    public OwnerService(OwnerRepository repo, OwnerMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public OwnerDto findById(Long id) {
        Owner owner = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Owner not found"));
        return mapper.toDto(owner);
    }

    public OwnerDto createOwner(OwnerDto dto) {
        return mapper.toDto(repo.save(mapper.toEntity(dto)));
    }

    public OwnerDto updateOwner(Long id, OwnerDto dto) {
        Owner owner = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Owner not found"));
        owner.setName(dto.getName());
        owner.setBirthday(dto.getBirthday());
        return mapper.toDto(repo.save(owner));
    }

    public void deleteOwner(Long id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(NOT_FOUND, "Owner not found");
        }
        repo.deleteById(id);
    }
}
