package org.example;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.xml.catalog.Catalog;


public class Main{

    public static Map<Integer, Carte> citire() {
        try {
            File file = new File("src/main/resources/carti.json");
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(file, new TypeReference<Map<Integer, Carte>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void scriere(Map<Integer, Carte> carti)
    {
        try{
            ObjectMapper mapper = new ObjectMapper();
            File file = new File ("src/main/resources/carti.json");
            mapper.writeValue(file,carti);
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        Map<Integer, Carte> map = new HashMap<Integer,Carte>();
        map = citire();
        var entryset = map.entrySet();
        var it = entryset.iterator();

        while (it.hasNext())
        {
            var m = it.next();
            Integer key = m.getKey();
            Carte value = m.getValue();
            System.out.println("Cheie: " + key + " Valoare: " + value.toString());

            if (key == 2)
                it.remove();



        }

        map.putIfAbsent(7, new Carte("1984", "George Orwell", 1934));
        System.out.println(map);
        scriere(map);

        Set <Carte> valueSet = new HashSet<Carte>();
        valueSet = map.values()
                        .stream()
                .filter(a -> a.autorul().contains("Harari"))
                .collect(Collectors.toSet());
        valueSet.forEach(value-> System.out.println(value));


    }

}
