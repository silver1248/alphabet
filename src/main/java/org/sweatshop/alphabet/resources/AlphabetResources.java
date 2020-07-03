package org.sweatshop.alphabet.resources;

import com.codahale.metrics.annotation.Timed;

import io.vavr.collection.HashMap;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import io.vavr.collection.Seq;
import lombok.Value;
import lombok.experimental.NonFinal;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@javax.ws.rs.Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Value
public class AlphabetResources {

    static List<String> alphabet = List.of("Alfa", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf", "Hotel", "India", "Juliet", "Kilo", "Lima",
            "Mike", "November", "Oscar", "Papa", "Quebec", "Romeo", "Sierra", "Tango", "Uniform", "Victor", "Whiskey", "X-ray", "Yankee", "Zulu");
//    @NonFinal static List<String> alphabetToChange = List.of("Alfa", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf", "Hotel", "India", "Juliet", "Kilo", "Lima",
//            "Mike", "November", "Oscar", "Papa", "Quebec", "Romeo", "Sierra", "Tango", "Uniform", "Victor", "Whiskey", "X-ray", "Yankee", "Zulu");

    @NonFinal static Map<Character, String> current = createCurrent();

    private static Map<Character, String> createCurrent() {
        Map<Character, String> map = HashMap.empty();
        for (String s : alphabet) {
            map = map.put(s.charAt(0), s);
        }
        return map;
    }

    @javax.ws.rs.Path("phonetic")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Timed
    public List<String> phonetic(@QueryParam("word") String word) {

        if (word != null && !word.equals("")) {
            int whereIsWord = alphabet.indexOf(word.substring(0, 1).toUpperCase() + word.substring(1));
            if (whereIsWord == -1) {
                return alphabet;
            } else {
                return alphabet.take(whereIsWord + 1);
            }
        } else {
            return alphabet;
        }
    }

    @javax.ws.rs.Path("alphabet/{letter}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Timed
    public String getLetter(@PathParam("letter") Character in) {
        return current.get(Character.toUpperCase(in)).get();
    }

    @javax.ws.rs.Path("/alphabet/")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Timed
    public Seq<String> getAlphabet() {
        return current.values();
    }

    @javax.ws.rs.Path("/alphabet/{letter}")
    @Produces(MediaType.APPLICATION_JSON)
    @PUT
    @Timed
    public String changeLetter(@PathParam("letter") Character in, @QueryParam ("word") String word) {
        word = normalizeCases(word);
        in = Character.toUpperCase(in);
        if (in.equals(word.charAt(0))) {
            current = current.put(in, word);
        }
        return current.get(in).get();
    }

    @javax.ws.rs.Path("alphabet/{letter}")
    @Produces(MediaType.APPLICATION_JSON)
    @DELETE
    @Timed
    public String resetLetter(@PathParam("letter") Character in) {
        int charVal = Character.toUpperCase(in) - 'A';
        in = Character.toUpperCase(in);
        current = current.put(in, alphabet.get(charVal));
        return current.get(in).get();
    }

    @javax.ws.rs.Path("/alphabet/")
    @Produces(MediaType.APPLICATION_JSON)
    @DELETE
    @Timed
    public Seq<String> resetAlphabet() {
        current = createCurrent();
        return current.values();
    }

    public static String normalizeCases(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
    }
}
