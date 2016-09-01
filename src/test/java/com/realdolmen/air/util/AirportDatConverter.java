package com.realdolmen.air.util;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class AirportDatConverter {

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("src/test/java/com/realdolmen/air/util/airports.dat");
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(s -> {
                String[] exploded = s.split(",");
                System.out.println("Name:" + exploded[1]);
                System.out.println("Country:" + exploded[3]);
                System.out.println("Code:" + exploded[4]);
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
