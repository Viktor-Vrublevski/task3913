package com.javarush.task.task39.task3913.query;

import com.javarush.task.task39.task3913.Event;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import java.util.Set;

public interface EventQuery {
    int getNumberOfAllEvents(Date after, Date before) throws IOException, ParseException;

    Set<Event> getAllEvents(Date after, Date before) throws IOException, ParseException;

    Set<Event> getEventsForIP(String ip, Date after, Date before) throws IOException, ParseException;

    Set<Event> getEventsForUser(String user, Date after, Date before) throws IOException, ParseException;

    Set<Event> getFailedEvents(Date after, Date before) throws IOException, ParseException;

    Set<Event> getErrorEvents(Date after, Date before) throws IOException, ParseException;

    int getNumberOfAttemptToSolveTask(int task, Date after, Date before) throws IOException, ParseException;

    int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) throws IOException, ParseException;

    Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) throws IOException, ParseException;

    Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) throws IOException, ParseException;
}