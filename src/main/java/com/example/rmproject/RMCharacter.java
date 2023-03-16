package com.example.rmproject;

// Record der nur die gewünschten Attribute eines Charakters enthält
public record RMCharacter(
        int id,
        String name,
        String species
) {
}
