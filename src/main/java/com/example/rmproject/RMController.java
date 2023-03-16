package com.example.rmproject;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class RMController {

    // Service dependency injection
    private final RMService service;


    // Get-Request um alle Charaktere der Seite 1 auszugeben
    @GetMapping("characters")
    public CharacterResponse getAllCharacters() {
        return service.getAllCharacters();
    }

    // Get-Request um einen zufälligen Charakter auszugeben
    @GetMapping("characters/random")
    public RMCharacter getRandomCharacter() {
        return service.getRandomCharacter();
    }
}
