package com.qikserve.tagContentExtractor;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {


    @Test
    public void testExampleFromHackerRank() {
        String input = "4\n" +
                "<h1>Nayeem loves counseling</h1>\n" +
                "<h1><h1>Sanjay has no watch</h1></h1><par>So wait for a while</par>\n" +
                "<Amee>safat codes like a ninja</amee>\n" +
                "<SA premium>Imtiaz has a secret crush</SA premium>\n";

        String expectedOutput = "Nayeem loves counseling\n" +
                "Sanjay has no watch\n" +
                "So wait for a while\n" +
                "None\n" +
                "Imtiaz has a secret crush";

        String actualOutput = runTest(input);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testValidTags() {
        String input = "2\n" +
                "<h1>Nayeem loves counseling</h1>\n" +
                "<SA premium>Imtiaz has a secret crush</SA premium>\n";

        String expectedOutput = "Nayeem loves counseling\n" +
                "Imtiaz has a secret crush";

        String actualOutput = runTest(input);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testInvalidTags() {
        String input = "1\n" +
                "<h1>Nayeem loves counseling</h2>\n";

        String expectedOutput = "None";

        String actualOutput = runTest(input);
        assertEquals(expectedOutput, actualOutput);
    }

    private String runTest(String input) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Solution.main(null);

        return outputStream.toString().trim().replace("\r\n", "\n");
    }

}
