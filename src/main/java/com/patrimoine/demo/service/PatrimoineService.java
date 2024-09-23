package com.patrimoine.demo.service;

import com.patrimoine.demo.model.Patrimoine;
import com.patrimoine.demo.repository.FileRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PatrimoineService {
    private final FileRepository<Patrimoine> fileRepository;

    @SneakyThrows
    public Patrimoine crUpdate(Patrimoine patrimoine, String id) {
        patrimoine.setId(id);
        return update(patrimoine);
    }

    @SneakyThrows
    public Patrimoine getById(String id) {
        return fileRepository.findById(id).orElseThrow(ClassNotFoundException::new);
    }

    @SneakyThrows
    public Patrimoine update(Patrimoine patrimoine) {
        Patrimoine toUpdate = fileRepository
                .findById(patrimoine.getId())
                .orElse(new Patrimoine(patrimoine.getId(), patrimoine.getPossesseur(), patrimoine.getDerniereModification()));

        toUpdate.setPossesseur(patrimoine.getPossesseur());
        toUpdate.setDerniereModification(patrimoine.getDerniereModification());
        return fileRepository.save(toUpdate);
    }
}
