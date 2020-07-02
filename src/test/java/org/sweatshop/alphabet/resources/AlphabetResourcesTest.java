package org.sweatshop.alphabet.resources;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import java.util.Optional;

import org.sweatshop.alphabet.resources.AlphabetResources;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.vavr.collection.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AlphabetResourcesTest {

    List<String> fullAlphabet = List.of("Alfa", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf", "Hotel", "India", "Juliet", "Kilo", "Lima",
            "Mike", "November", "Oscar", "Papa", "Quebec", "Romeo", "Sierra", "Tango", "Uniform", "Victor", "Whiskey", "X-ray", "Yankee", "Zulu");

    @Test(dataProvider="phoneticTestDP")
    public void phoneticTest(String word, List<String> expected) {
        AlphabetResources fsr = new AlphabetResources();
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

    @Test(dataProvider = "getLetterDP")
    public void getLetterTest(Optional<Character> in, String expected, Exception expectedE) {
        AlphabetResources ar = new AlphabetResources();
        try {
            assertEquals(ar.getLetter(in), expected);
            assertNull(expectedE);
            return;
        } catch (Exception e) {
            log.error("problem ", e);
            assertEquals(e.getClass(), expectedE.getClass());
            assertEquals(e.getMessage(), expectedE.getMessage());
        }
    }

    @DataProvider
    Object[][] getLetterDP() {
        return new Object[][] {

            {Optional.of('G'), "Golf", null},
            {Optional.of('g'), "Golf", null},

            {Optional.of('n'), "November", null},
        };
    }

    @Test
    public void staticPhoneticTest() {
        AlphabetResources fsr = new AlphabetResources();
        assertEquals(fsr.getAlphabet(), fullAlphabet);
    }
}
