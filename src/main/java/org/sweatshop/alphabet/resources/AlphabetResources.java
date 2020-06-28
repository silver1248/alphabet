package org.sweatshop.alphabet.resources;

import com.codahale.metrics.annotation.Timed;

import io.vavr.collection.List;
import lombok.Value;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@javax.ws.rs.Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Value
public class AlphabetResources {
    String template;
    String defaultName;
    List<String> alphabet = List.of("Alfa", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf", "Hotel", "India", "Juliet", "Kilo", "Lima",
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
}
