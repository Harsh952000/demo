package com.lcwd.todo.Todo_Application.helper;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Helper {
    public static Date parse(LocalDateTime localDateTime) throws ParseException
    {
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        return date;
    }
}
