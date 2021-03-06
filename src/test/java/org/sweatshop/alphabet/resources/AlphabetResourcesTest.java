package org.sweatshop.alphabet.resources;

import static org.testng.Assert.assertEquals;

import org.sweatshop.alphabet.resources.AlphabetResources;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.vavr.collection.List;

public class AlphabetResourcesTest {

    List<String> fullAlphabet = List.of("Alfa", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf", "Hotel", "India", "Juliet", "Kilo", "Lima",
            "Mike", "November", "Oscar", "Papa", "Quebec", "Romeo", "Sierra", "Tango", "Uniform", "Victor", "Whiskey", "X-ray", "Yankee", "Zulu");

    @Test(dataProvider="phoneticTestDP")
    public void phoneticTest(String word, List<String> expected) {
        AlphabetResources fsr = new AlphabetResources("Hello, %s!", "Stranger");
        assertEquals(fsr.phonetic(word), expected);
    }

    @DataProvider
    Object[][] phoneticTestDP() {
        return new Object[][] {
            {null, fullAlphabet},
            {"", fullAlphabet},

            {"hello", fullAlphabet},

            {"oscar", List.of("Alfa", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf", "Hotel", "India", "Juliet", "Kilo", "Lima",
                    "Mike", "November", "Oscar")},
            {"Oscar", List.of("Alfa", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf", "Hotel", "India", "Juliet", "Kilo", "Lima",
                    "Mike", "November", "Oscar")},

            {"Alfa", List.of("Alfa")},
            {"Zulu", fullAlphabet},
        };
    }
}
