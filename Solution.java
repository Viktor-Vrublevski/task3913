package com.javarush.task.task39.task3913;

import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Date;

public class Solution {
    public static void main(String[] args) throws IOException, ParseException {
        LogParser logParser = new LogParser(Paths.get("C:/logers"));
        Date after = logParser.format.parse("30.01.2014 12:56:22");
        Date before = logParser.format.parse("11.12.2013 10:11:12");

    }
}
