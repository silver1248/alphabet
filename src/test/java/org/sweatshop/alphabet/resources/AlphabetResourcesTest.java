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

    final List<String> alphabet = List.of("Alfa", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf", "Hotel", "India", "Juliet", "Kilo", "Lima", "Mike",
            "November", "Oscar", "Papa", "Quebec", "Romeo", "Sierra", "Tango", "Uniform", "Victor", "Whiskey", "X-ray", "Yankee", "Zulu");

    public AlphabetResources getAr() {
        AlphabetResources ar = new AlphabetResources();
        ar.resetAlphabet();
        return ar;
    }

    @Test(dataProvider="phoneticTestDP")
    public void phoneticTest(String word, List<String> expected) {
        assertEquals(getAr().phonetic(word), expected);
    }

    @DataProvider
    Object[][] phoneticTestDP() {
        return new Object[][] {
            {null, alphabet},
            {"", alphabet},

            {"hello", alphabet},

            {"oscar", List.of("Alfa", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf", "Hotel", "India", "Juliet", "Kilo", "Lima",
                    "Mike", "November", "Oscar")},
            {"Oscar", List.of("Alfa", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf", "Hotel", "India", "Juliet", "Kilo", "Lima",
                    "Mike", "November", "Oscar")},

            {"Alfa", List.of("Alfa")},
            {"Zulu", alphabet},
        };
    }


    @Test(dataProvider = "getLetterDP")
    public void getLetterTest(Optional<Character> in, String expected, Exception expectedE) {
        AlphabetResources ar = getAr();
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
    public void getAlphabetTest() {
        AlphabetResources ar = getAr();
        assertEquals(ar.getAlphabet(), alphabet);
    }

    @Test(dataProvider="changeLetterTestDP")
    public void changeLetterTest(Character letter, String word, String expected) {
        AlphabetResources ar = getAr();
        assertEquals(ar.changeLetter(letter, word), expected);
    }

    @DataProvider
    Object[][] changeLetterTestDP() {
        return new Object[][] {

            {'H', "HeLlO", "Hello"},
            {'l', "letter", "Letter"},
            {'h', "help", "Help"},
            {'z', "zebra", "Zebra"},
            {'s', "slide", "Slide"},
            {'d', "Door", "Door"},
            {'j', "JAR", "Jar"},
            {'f', "FoOD", "Food"},

            {'x', "food", "X-ray"},
        };
    }

    @Test(dataProvider="resetLetterTestDP")
    public void resetLetterTest(Optional<Character> letter, String expected) {
        int charVal = Character.toUpperCase(letter.get()) - 'A';
        AlphabetResources ar = getAr();
        assertEquals(ar.resetLetter(letter), alphabet.get(charVal));
    }

    @DataProvider
    Object[][] resetLetterTestDP() {
        return new Object[][] {

            {Optional.of('H'), "Hotel"},
            {Optional.of('l'), "Lima"},
            {Optional.of('h'), "Hotel"},
            {Optional.of('z'), "Zulu"},
            {Optional.of('s'), "Sierra"},
        };
    }

    @Test
    public void resestAlphabetTest() {
        AlphabetResources ar = getAr();
        assertEquals(ar.resetAlphabet(), alphabet);
    }
}
