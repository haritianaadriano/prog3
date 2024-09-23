package com.patrimoine.demo.controller;

import com.patrimoine.demo.model.Patrimoine;
import com.patrimoine.demo.service.PatrimoineService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PatrimoineController {
    private final PatrimoineService patrimoineService;

    @PutMapping("/patrimoines/{id}")
    public Patrimoine savePatrimoine(@PathVariable(name = "id") String id, @RequestBody Patrimoine patrimoine) {
        return patrimoineService.crUpdate(patrimoine, id);
    }

    @GetMapping("/patrimoines/{id}")
    public Patrimoine getPatrimoine(@PathVariable(name = "id") String id) {
        return patrimoineService.getById(id);
    }

    @GetMapping("/")
    public String getHello() {
        return "Hello, World";
    }
}
