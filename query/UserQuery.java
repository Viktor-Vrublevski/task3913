package com.javarush.task.task39.task3913.query;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Set;

public interface UserQuery {
    Set<String> getAllUsers() throws IOException;

    int getNumberOfUsers(Date after, Date before) throws IOException, ParseException;

    int getNumberOfUserEvents(String user, Date after, Date before) throws IOException, ParseException;

    Set<String> getUsersForIP(String ip, Date after, Date before) throws IOException, ParseException;

    Set<String> getLoggedUsers(Date after, Date before) throws IOException, ParseException;

    Set<String> getDownloadedPluginUsers(Date after, Date before) throws IOException, ParseException;

    Set<String> getWroteMessageUsers(Date after, Date before) throws IOException, ParseException;

    Set<String> getSolvedTaskUsers(Date after, Date before) throws IOException, ParseException;

    Set<String> getSolvedTaskUsers(Date after, Date before, int task) throws IOException, ParseException;

    Set<String> getDoneTaskUsers(Date after, Date before) throws IOException, ParseException;

    Set<String> getDoneTaskUsers(Date after, Date before, int task) throws IOException, ParseException;
}