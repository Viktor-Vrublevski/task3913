package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery {
    private Path logDir;
    Set<String> set;
    SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    public LogParser(Path logDir) {
        this.logDir = logDir;
        set = new HashSet<>();
    }
// ----------------------------------------Interface IPQuery----------------------------------------
    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) throws ParseException, IOException {
        Set<String> set = new HashSet<>();
      for (String str : universal(after, before)) {
          String[] arr = str.split("\t");
            set.add(arr[0]);
       }
        return set.size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) throws ParseException, IOException {
        Set<String> set = new HashSet<>();
        for (String str : universal(after, before)) {
            String[] arr = str.split("\t");
            set.add(arr[0]);
        }
        return set;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) throws ParseException, IOException {
        Set<String> set = new HashSet<>();
        for (String str : universal(after, before)) {
            String[] arr = str.split("\t");
            if (user.equals(arr[1])) {
                set.add(arr[0]);
            }
        }
        return set;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) throws ParseException, IOException {
        Set<String> set = new HashSet<>();
        for (String str : universal(after, before)) {
            String[] arr = str.split("\t");
            String[] arrEv = arr[3].split(" ");
            if (event.toString().equals(arrEv[0])) {
                set.add(arr[0]);
            }
        }
        return set;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) throws ParseException, IOException {
        Set<String> set = new HashSet<>();
        for (String str : universal(after, before)) {
            String[] arr = str.split("\t");
            String[] arrSt = arr[4].split(" ");
            if (status.toString().equals(arrSt[0])) {
                set.add(arr[0]);
            }
        }
        return set;
    }
//  -----------------------------------Interface UserQuery----------------------------------------
    @Override
    public Set<String> getAllUsers() throws IOException {
       Set<String> setName = new HashSet<>();
       add(logDir);
       for (String str : set){
           if (str.length()>0){
          String[] arr = str.split("\t");
          setName.add(arr[1]);
           }
       }
        return setName;
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) throws IOException, ParseException {
        Set<String> setInteger = new HashSet<>();
        for (String str : universal(after,before)){
            String[] arr = str.split("\t");
            setInteger.add(arr[1]);
        }
        return setInteger.size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) throws IOException, ParseException {
        Set<String> setEvent = new HashSet<>();
        for (String str : universal(after,before)){
            String[] arr = str.split("\t");
            if (user.equals(arr[1])){
                setEvent.add(arr[3]);
            }
        }
        return setEvent.size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) throws IOException, ParseException {
        Set<String> setUsers = new HashSet<>();
        for (String str : universal(after,before)){
            String[] arr = str.split("\t");
            if (ip.equals(arr[0])){
                setUsers.add(arr[1]);
            }
        }
        return setUsers;
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) throws IOException, ParseException {
        Set<String> setUsers = new HashSet<>();
        for (String str : universal(after,before)){
            String[] arr = str.split("\t");
            if (arr[3].equals(Event.LOGIN.toString())){
                setUsers.add(arr[1]);
            }
        }
        return setUsers;
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) throws IOException, ParseException {
        Set<String> setUsers = new HashSet<>();
        for (String str : universal(after,before)){
            String[] arr = str.split("\t");
            if (arr[3].equals(Event.DOWNLOAD_PLUGIN.toString())){
                setUsers.add(arr[1]);
            }
        }
        return setUsers;
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) throws IOException, ParseException {
        Set<String> setUsers = new HashSet<>();
        for (String str : universal(after,before)){
            String[] arr = str.split("\t");
            if (arr[3].equals(Event.WRITE_MESSAGE.toString())){
                setUsers.add(arr[1]);
            }
        }
        return setUsers;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) throws IOException, ParseException {
        Set<String> setUsers = new HashSet<>();
        for (String str : universal(after,before)){
            String[] arr = str.split("\t");
            if (arr[3].contains(Event.SOLVE_TASK.toString())){
                setUsers.add(arr[1]);
            }
        }
        return setUsers;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) throws IOException, ParseException {
        Set<String> setUsers = new HashSet<>();
        for (String str : universal(after,before)){
            String[] arr = str.split("\t");
            if (arr[3].contains(Event.SOLVE_TASK.toString()) && Integer.parseInt(arr[3].replaceAll("\\D",""))==task){
                setUsers.add(arr[1]);
            }
        }
        return setUsers;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) throws IOException, ParseException {
        Set<String> setUsers = new HashSet<>();
        for (String str : universal(after,before)){
            String[] arr = str.split("\t");
            if (arr[3].contains(Event.DONE_TASK.toString())){
                setUsers.add(arr[1]);
            }
        }
        return setUsers;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) throws IOException, ParseException {
        Set<String> setUsers = new HashSet<>();
        for (String str : universal(after,before)){
            String[] arr = str.split("\t");
            if (arr[3].contains(Event.DONE_TASK.toString()) && Integer.parseInt(arr[3].replaceAll("\\D",""))==task){
                setUsers.add(arr[1]);
            }
        }
        return setUsers;
    }

//  --------------------------------------Interface DateQuery---------------------------------------------------
    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) throws IOException, ParseException {
        Set<Date> dates = new HashSet<>();
        for (String str :universal(after, before) ){
            String[] arr = str.split("\t");
            if (arr[1].equals(user) && arr[3].contains(event.toString())){
                dates.add(format.parse(arr[2]));
            }
        }
        return dates;
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) throws IOException, ParseException {
        Set<Date> dates = new HashSet<>();
        for (String str :universal(after, before) ){
            String[] arr = str.split("\t");
            if (arr[4].equals(Status.FAILED.toString())){
                dates.add(format.parse(arr[2]));
            }
        }
        return dates;
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) throws IOException, ParseException {
        Set<Date> dates = new HashSet<>();
        for (String str :universal(after, before) ){
            String[] arr = str.split("\t");
            if (arr[4].equals(Status.ERROR.toString())){
                dates.add(format.parse(arr[2]));
            }
        }
        return dates;
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) throws IOException, ParseException {
       List<Date> dates = new ArrayList<>();
        for (String str :universal(after, before) ){
            String[] arr = str.split("\t");
            if (arr[1].equals(user) && arr[3].equals(Event.LOGIN.toString()) && arr[4].contains(Status.OK.toString())){
                dates.add(format.parse(arr[2]));
            }
        }
        Collections.sort(dates);
        if (!dates.isEmpty())return dates.get(0);
        else return null;
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) throws IOException, ParseException {
        List<Date> dates = new ArrayList<>();
        for (String str :universal(after, before) ){
            String[] arr = str.split("\t");
            if (arr[1].equals(user) && arr[3].equals(Event.SOLVE_TASK.toString()+" "+task)){
                dates.add(format.parse(arr[2]));
            }
        }
        Collections.sort(dates);
        if (dates.size()>0)return dates.get(0);
        else return null;
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) throws IOException, ParseException {
        List<Date> dates = new ArrayList<>();
        for (String str :universal(after, before) ){
            String[] arr = str.split("\t");
            if (arr[1].equals(user) && arr[3].equals(Event.DONE_TASK.toString()+" "+task)){
                dates.add(format.parse(arr[2]));
            }
        }
        Collections.sort(dates);
        if (dates.size()>0)return dates.get(0);
        else return null;
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) throws IOException, ParseException {
        Set<Date> dates = new HashSet<>();
        for (String str :universal(after, before) ){
            String[] arr = str.split("\t");
            if (arr[1].equals(user) && arr[3].equals(Event.WRITE_MESSAGE.toString())){
                dates.add(format.parse(arr[2]));
            }
        }
        return dates;
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) throws IOException, ParseException {
        Set<Date> dates = new HashSet<>();
        for (String str :universal(after, before) ){
            String[] arr = str.split("\t");
            if (arr[1].equals(user) && arr[3].equals(Event.DOWNLOAD_PLUGIN.toString())){
                dates.add(format.parse(arr[2]));
            }
        }
        return dates;
    }
//  ---------------------------------Interface EventQuery-----------------------------------------------------
    @Override
    public int getNumberOfAllEvents(Date after, Date before) throws IOException, ParseException {
        Set<Event> even = new HashSet<>();
        for (String str : universal(after, before)){
            String[] arr = str.split("\t");
           Event e = convert(arr[3]);
           even.add(e);
        }
        return even.size();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before) throws IOException, ParseException {
        Set<Event> events = new HashSet<>();
        for (String str : universal(after, before)){
            String[] arr = str.split("\t");
            events.add(convert(arr[3]));
        }
        return events;
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) throws IOException, ParseException {
        Set<Event> events = new HashSet<>();
        for (String str : universal(after, before)){
            String[] arr = str.split("\t");
            if (ip.equals(arr[0])) {
                events.add(convert(arr[3]));
            }
        }
        return events;
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) throws IOException, ParseException {
        Set<Event> events = new HashSet<>();
        for (String str : universal(after, before)){
            String[] arr = str.split("\t");
            if (user.equals(arr[1])) {
                events.add(convert(arr[3]));
            }
        }
        return events;
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before) throws IOException, ParseException {
        Set<Event> events = new HashSet<>();
        for (String str : universal(after, before)){
            String[] arr = str.split("\t");
            if (arr[4].equals(Status.FAILED.toString())) {
                events.add(convert(arr[3]));
            }
        }
        return events;
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before) throws IOException, ParseException {
        Set<Event> events = new HashSet<>();
        for (String str : universal(after, before)){
            String[] arr = str.split("\t");
            if (arr[4].equals(Status.ERROR.toString())) {
                events.add(convert(arr[3]));
            }
        }
        return events;
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) throws IOException, ParseException {
      int count = 0;
      for (String str : universal(after,before)){
         String[] arr = str.split("\t");
         if (convert(arr[3]).equals(Event.SOLVE_TASK)){
             if (task==Integer.parseInt(arr[3].replaceAll("[\\D\\s]",""))){
                count++;
             }
         }
      }
      return count;
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) throws IOException, ParseException {
       int count = 0;
        for (String str : universal(after,before)){
            String[] arr = str.split("\t");
           if (convert(arr[3]).equals(Event.DONE_TASK)){
               if (task==Integer.parseInt(arr[3].replaceAll("[\\D\\s]",""))){
                   count++;
               }
           }
        }
        return count;
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) throws IOException, ParseException {
       Map<Integer,Integer> map = new HashMap<>();
       for (String str : universal(after, before)){
           String[] arr = str.split("\t");
           if (convert(arr[3]).equals(Event.SOLVE_TASK)){
               int task = Integer.parseInt(arr[3].replaceAll("[\\D\\s]",""));
               map.put(task,getNumberOfAttemptToSolveTask(task,after,before));
           }
       }
       return map;
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) throws IOException, ParseException {
        Map<Integer,Integer> map = new HashMap<>();
        for (String str : universal(after, before)){
            String[] arr = str.split("\t");
            if (convert(arr[3]).equals(Event.DONE_TASK)){
                int task = Integer.parseInt(arr[3].replaceAll("[\\D\\s]",""));
                map.put(task,getNumberOfSuccessfulAttemptToSolveTask(task,after,before));
            }
        }
        return map;
    }

 //---------------------------------Interface QLQuery--------------------------------------------------
 @Override
 public Set<Object> execute(String query) throws IOException, ParseException {
     Set<Object> set = new HashSet<>();
        switch (query){
            case "get ip": set = new HashSet<>(getUniqueIPs(null, null));
            break;
            case "get user": set = new HashSet<>(getAllUsers());
            break;
            case "get event": set = new HashSet<>(getAllEvents(null,null));
            break;
            case "get date": set = new HashSet<>(allDates());
            break;
            case "get status": set = new HashSet<>(allStatus());
            break;
        }
  //       get ip for user = Vasya

     return set;
 }
    //----------------------- Method AllDates --------------------------------------------------------
    public Set<Date> allDates() throws IOException, ParseException {
        Set<Date> dates = new HashSet<>();
        for (String str : universal(null,null)){
            String[] arr = str.split("\t");
            dates.add(format.parse(arr[2]));
        }
        return dates;
    }
    //--------------------------- Method AllStatus--------------------------------------------------------
    public Set<Status> allStatus() throws IOException, ParseException {
        Set<Status> status = new HashSet<>();
        for (String str : universal(null,null)){
            String[] arr = str.split("\t");
            if (arr[4]!=null){
                status.add(convertSt(arr[4]));
            }
        }
        return status;
    }
    // *************************************************************************************************
    public void add(Path path) throws IOException {
        if (Files.isRegularFile(path) && path.getFileName().toString().endsWith(".log")) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path.toFile())));
            while (reader.ready()) {
                set.add(reader.readLine());
            }
        }
        if (Files.isDirectory(path)) {
            File[] files = path.toFile().listFiles();
            if (files != null) {
                for (int i = 0; i < files.length; i++) {
                    add(files[i].toPath());
                }
            }
        }
    }

    //------------------------------------------------------------------------------------------------
    public Set<String> universal(Date after, Date before) throws ParseException, IOException {
        Set<String> ip = new HashSet<>();
        add(logDir);
        for (String str : set) {
            if (str.length() > 0) {
                String[] arr = str.split("\t");
                Date date = format.parse(arr[2]);
                if (after != null && before != null) {
                    if (date.getTime() >= after.getTime() && date.getTime() <= before.getTime())
                        ip.add(str);
                }
                if (after == null && before != null) {
                    if (date.getTime() <= before.getTime())
                        ip.add(str);

                }
                if (after != null && before == null) {
                    if (date.getTime() >= after.getTime()) {
                        ip.add(str);
                    }
                }
                if (after == null && before == null) {
                    ip.add(str);
                }
            }
        }
        return ip;
    }
 //--------------------------------------------------------------------------------------------
    public Event convert(String str){
        Event event = null;
        switch (str.replaceAll("\\d","").trim()){
            case "LOGIN": event = Event.LOGIN;
            break;
            case "DOWNLOAD_PLUGIN": event = Event.DOWNLOAD_PLUGIN;
            break;
            case "WRITE_MESSAGE": event = Event.WRITE_MESSAGE;
            break;
            case "SOLVE_TASK": event = Event.SOLVE_TASK;
            break;
            case "DONE_TASK": event = Event.DONE_TASK;
            break;
        }
        return event;
    }
//---------------------------------------------------------------------------------------------------
    public Status convertSt(String str){
        Status status = null;
        switch (str.trim()){
            case "OK": status = Status.OK;
            break;
            case "FAILED": status = Status.FAILED;
            break;
            case "ERROR": status = Status.ERROR;
            break;
        }
        return status;
    }

}
