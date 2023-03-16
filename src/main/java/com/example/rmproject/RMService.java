package com.example.rmproject;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

@Service
public class RMService {

    // Erstellen eines WebClients der uns die Funktionen zur verfügung stellt HTTP-Request zu senden.
    // Erstellt für die URL https://rickandmortyapi.com/api
    private final WebClient client = WebClient.create("https://rickandmortyapi.com/api");


    // Methode um alle Charaktere der Seite 1 auszugeben
    // Gibts eine CharacterResponse zurück, sodass wir auf das Attribut "results" zugreifen können,
    // welches die tatsächliche Liste an characteren enthält
    public CharacterResponse getAllCharacters() {
        // Erstellt eine ResponseEntity vom Typ CharacterResponse welche dann HTTP-Header und Body enthält
        ResponseEntity<CharacterResponse> responseEntity = client
                // Wir definieren, dass wir eine GET-Request senden möchten
                .get()
                // .uri fügt dann "/character" an den link hinzu auf dem der WebClient arbeitet,
                // also https://rickandmortyapi.com/api/character
                .uri("/character")
                // retrieve führt nun die Request aus und signalisiert, dass wir die Daten holen möchten
                .retrieve()
                // Wandelt das JSON, dass wir zurückbekommen in eine CharacterResponse (Java-Objekt) um
                .toEntity(CharacterResponse.class)
                // Wartet bis der Server der externen API antwortet
                .block();
        // Object.requireNonNull stellt sicher, dass die Antwort nicht leer ist
        // mit getBody() signalisieren wir,
        // dass wir nur an dem body und nicht an dem HTTP-Header (mit status code etc.) interessiert sind
        return Objects.requireNonNull(responseEntity).getBody();
    }


    // Hier benötigen wir nur einen einzelnen Charakter also Rückgabetyp RMCharacter
    public RMCharacter getRandomCharacter() {
        // Wir generieren eine Zufallszahl zwischen 1 und 826 (Anzahl aller Charaktere)
        // Math.random() liefert eine Kommazahl zwischen 0 und 1
        // wir multiplizieren diese mit der Anzahl der Charaktere
        // dann wandeln wir das Ergebniss (double) zu einem int um
        int randomId = (int) (Math.random() * 826);
        ResponseEntity<RMCharacter> responseEntity = client
                .get()
                // Anstatt auf https://rickandmortyapi.com/api/character zuzugreifen
                // greifen wir nun auf https://rickandmortyapi.com/api/<randomId> zu
                // Beispiel:
                //      Zufallszahl "randomId" liefert und 345 zurück
                //      wir senden also die GET-Request an https://rickandmortyapi.com/api/character/345
                .uri("/character/" + randomId)
                .retrieve()
                .toEntity(RMCharacter.class)
                .block();
        return Objects.requireNonNull(responseEntity).getBody();
    }

}
