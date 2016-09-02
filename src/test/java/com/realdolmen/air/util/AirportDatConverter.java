package com.realdolmen.air.util;


import javax.xml.stream.*;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AirportDatConverter {

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("src/test/java/com/realdolmen/air/util/airports.dat");

        Map<String, Integer> regionsMapping = new HashMap<>();

        try (Stream<String> lines = Files.lines(path)) {
            XMLOutputFactory output = XMLOutputFactory.newInstance();
            XMLStreamWriter writer = output.createXMLStreamWriter(System.out);

            List<String> regions = lines.map(string -> string.split(",")[11]
                                                              .split("/")[0]
                                                            .replace('"', ' ')
                                                                .trim())
                    .distinct()
                    .collect(Collectors.toList());

            int i = 0;
            for (String region : regions) {
                writer.writeStartElement("region");

                writer.writeAttribute("id", ""+i);
                writer.writeAttribute("name", region);
                writer.writeEndElement();
                System.out.println();

                regionsMapping.put(region, i);

                i++;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }



        try (Stream<String> lines = Files.lines(path)) {
            XMLOutputFactory output = XMLOutputFactory.newInstance();
            XMLStreamWriter writer = output.createXMLStreamWriter(System.out);


            // <airport id="100" code="EBGB" name="Grimbergen Airfield" version="1"/>
            lines.forEach(s -> {
                try {
                    String[] exploded = s.split(",");

                    String region = exploded[11].replace('"',' ').split("/")[0].trim();


                    int id = Integer.parseInt(exploded[0]);

                    if(id % 100 == 0 && ! exploded[4].replace('"', ' ').trim().isEmpty()) {
                        if (!(region.equals("\\N") || region.equals("U") || region.equals("E"))) {
                            Integer regionId = regionsMapping.get(region);

                            writer.writeStartElement("airport");

                            writer.writeAttribute("id", exploded[0]);
                            writer.writeAttribute("name", exploded[1].replace('"', ' ').trim());
                            writer.writeAttribute("country", exploded[3].replace('"', ' ').trim());
                            writer.writeAttribute("code", exploded[4].replace('"', ' ').trim());
                            writer.writeAttribute("version", "1");
                            writer.writeAttribute("region_id", "" + regionId);
                            writer.writeAttribute("available", "1");
                            writer.writeEndElement();

                            System.out.println();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });


        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }
}
