package org.sweatshop.alphabet.resources;

import com.codahale.metrics.annotation.Timed;

import io.vavr.collection.List;
import lombok.Value;
import lombok.experimental.NonFinal;

import java.util.Optional;

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
    @NonFinal static List<String> alphabetToChange = List.of("Alfa", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf", "Hotel", "India", "Juliet", "Kilo", "Lima",
            "Mike", "November", "Oscar", "Papa", "Quebec", "Romeo", "Sierra", "Tango", "Uniform", "Victor", "Whiskey", "X-ray", "Yankee", "Zulu");

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
    public String getLetter(@PathParam("letter") Optional<Character> in) {
        int charVal = Character.toUpperCase(in.get()) - 'A';
        return alphabetToChange.get(charVal);
    }

    @javax.ws.rs.Path("/alphabet/")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Timed
    public List<String> getAlphabet() {
        return alphabetToChange;
    }

    @javax.ws.rs.Path("/alphabet/{letter}")
    @Produces(MediaType.APPLICATION_JSON)
    @PUT
    @Timed
    public List<String> changeLetter(@PathParam("letter") Character in, @QueryParam ("word") String word) {
        int charVal = Character.toUpperCase(in) - 'A';
        if (Character.toUpperCase(word.charAt(0)) - 'A' == charVal) {
            alphabetToChange = alphabetToChange.subSequence(0, charVal).append(word).appendAll(alphabetToChange.takeRight(26 - (charVal + 1)));
        }
        return alphabetToChange;
    }

    @javax.ws.rs.Path("alphabet/{letter}")
    @Produces(MediaType.APPLICATION_JSON)
    @DELETE
    @Timed
    public List<String> resetLetter(@PathParam("letter") Optional<Character> in) {
        int charVal = Character.toUpperCase(in.get()) - 'A';
        alphabetToChange = alphabetToChange.take(charVal).append(alphabet.get(charVal)).appendAll(alphabetToChange.takeRight(26 - (charVal + 1)));
        return alphabetToChange;
    }

    @javax.ws.rs.Path("/alphabet/")
    @Produces(MediaType.APPLICATION_JSON)
    @DELETE
    @Timed
    public List<String> resetAlphabet() {
        alphabetToChange = alphabet;
        return alphabetToChange;
    }
}
