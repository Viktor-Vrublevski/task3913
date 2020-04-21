package com.javarush.task.task39.task3913.query;

import com.javarush.task.task39.task3913.Event;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Set;

public interface DateQuery {
    Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) throws IOException, ParseException;

    Set<Date> getDatesWhenSomethingFailed(Date after, Date before) throws IOException, ParseException;

    Set<Date> getDatesWhenErrorHappened(Date after, Date before) throws IOException, ParseException;

    Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) throws IOException, ParseException;

    Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) throws IOException, ParseException;

    Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) throws IOException, ParseException;

    Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) throws IOException, ParseException;

    Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) throws IOException, ParseException;
}