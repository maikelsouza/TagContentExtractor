package com.qikserve.tagContentExtractor;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {

    public static void main( String[] args ) {

        Scanner in = new Scanner(System.in);
        int numberOfSentences = Integer.parseInt(in.nextLine());

        if(numberOfSentences > 0){
            while (numberOfSentences > 0) {
                String line = in.nextLine();

                List<String> openingTags = findOpeningTags(line);
                List<String> closingTags = findClosingTags(line);

                if (openingTags.size() != closingTags.size() || openingTags.isEmpty() && closingTags.isEmpty()){
                    System.out.println("None");
                }else{
                    for (int i = 0; i< openingTags.size(); i++){
                        String openingTag = openingTags.remove(i);
                        String closingTag = closingTags.remove(i);

                        if (openingTag.equals(closingTag.replace("/",""))){
                            String extractTextBetweenTags = extractTextBetweenTags(line,openingTag,closingTag);
                            System.out.println(extractTextBetweenTags);
                        }else {
                            System.out.println("None");
                        }
                    }
                }
                numberOfSentences--;
            }
            in.close();
        }
    }

    private static List<String> findOpeningTags(String args) {
        return findTags(args, "<([a-zA-Z0-9]+)[^>]*>");
    }

    private static List<String> findClosingTags(String args) {
        return findTags(args, "</([a-zA-Z0-9]+)[^>]*>");
    }

    private static List<String> findTags(String args, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(args);

        List<String> tags = new ArrayList<>();
        if (matcher.find()) {
            tags.add(matcher.group());
        }

        return tags;
    }

    private static String extractTextBetweenTags(String input, String openingTag, String closingTag) {

        String regex = Pattern.quote(openingTag) + "(.*?)" + Pattern.quote(closingTag);
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            String result = matcher.group(1).replaceAll("<.*?>", "");
            return result.isEmpty() ? "None" : result;
        } else {
            return "Nome";
        }
    }
}
