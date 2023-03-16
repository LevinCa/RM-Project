package com.example.rmproject;

import java.util.List;

/*
Record um auf das result Attribut zuzugreifen
Wenn https://rickandmortyapi.com/api/character aufgerufen wird kommt folgendes Objekt zurück:

{
    "info": {
        ...

    "results": [
        {
            <character 1>
        },
        {
            <character 2>
        },
        ...
    ]
}

"results" enthält also die tatsächliche Liste an Charakteren, nur dieses Attribut interessiert uns also

 */
public record CharacterResponse(
        List<RMCharacter> results
) {
}
