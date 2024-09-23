package com.patrimoine.demo.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.patrimoine.demo.model.Patrimoine;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.util.Optional;

@Repository
public class FileRepository<T> {
    private final String FILE_DATA_CENTER = "file_data_center.json";

    public T save(T t) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_DATA_CENTER));
        File file = new File(FILE_DATA_CENTER);
        if (file.exists() && file.length() == 0) {
            writer.write(t.toString());
        }

        writer.write(", " + t.toString());
        writer.close();
        return t;
    }

    public Optional<T> findById(String id) throws IOException {
        File file = new File(FILE_DATA_CENTER);

        if (file.exists() && file.length() > 0) {
            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_DATA_CENTER))) {
                String data = reader.readLine();

                String[] patrimoineArray = data.split(", ");

                for (String patrimoineString : patrimoineArray) {
                    String[] parts = patrimoineString.split(" ");

                    String foundId = parts[1];

                    if (foundId.equals(id)) {
                        String possesseur = parts[3];
                        String derniereModification = parts[5];

                        return Optional.of((T) new Patrimoine(foundId, possesseur, Instant.parse(derniereModification)));
                    }
                }
            }
        }
        return Optional.empty();
    }

}
