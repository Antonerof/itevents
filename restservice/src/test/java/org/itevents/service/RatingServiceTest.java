package org.itevents.service;

import org.itevents.model.Location;
import org.junit.Assert;
import org.itevents.model.Event;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class RatingServiceTest {

    private static final int EXPECTED_LIST_SIZE = 5;
    private static final int TRIM_SIZE = 5;
    private static final int DESCENDING_ORDER = -1;

    static Map<Event, Integer> mapForTest;
    private static SimpleDateFormat formatter;

    private static RatingServiceImpl ratingService;


    @BeforeClass
    public static void setup() throws ParseException {
        mapForTest = new HashMap<>();
        ratingService = new RatingServiceImpl();
        formatter = new SimpleDateFormat("dd.MM.yyyy");
        Date date = formatter.parse("10.08.2015");
        mapForTest.put(new Event(8, "Java", date, null, "http://www.java.com.ua",
                "Beresteyska", new Location(50.458585, 30.742017), "java@gmail.com"), 1);
        mapForTest.put(new Event(9, "Java", date, null, "http://www.java.com.ua",
                "Beresteyska", new Location(50.458585, 30.742017), "java@gmail.com"), 4);
        mapForTest.put(new Event(10, "Java", date, null, "http://www.java.com.ua",
                "Beresteyska", new Location(50.458585, 30.742017), "java@gmail.com"), 3);
        mapForTest.put(new Event(11, "Java", date, null, "http://www.java.com.ua",
                "Beresteyska", new Location(50.458585, 30.742017), "java@gmail.com"), 5);
        mapForTest.put(new Event(12, "Java", date, null, "http://www.java.com.ua",
                "Beresteyska", new Location(50.458585, 30.742017), "java@gmail.com"), 8);
        mapForTest.put(new Event(13, "Java", date, null, "http://www.java.com.ua",
                "Beresteyska", new Location(50.458585, 30.742017), "java@gmail.com"), 6);
    }

    @AfterClass
    public static void tearDown(){
        ratingService = null;
    }

    @Test
    public void testTrimToSizeMap(){
        List<Event> actualList = ratingService.getListEventByRating(TRIM_SIZE, mapForTest);
        int actualSize = actualList.size();
        int expectedListSize = EXPECTED_LIST_SIZE;
        Assert.assertEquals(expectedListSize, actualSize);
    }

    @Test
    public void testSortMapEvents() throws ParseException {
        Map<Event, Integer> resultedMap = ratingService.sortEventsMap(DESCENDING_ORDER, mapForTest);
        List<Integer> listResultedValue = new ArrayList<>(resultedMap.values());
        Integer[] array = {8, 6, 5, 4, 3, 1};
        List<Integer> listExpectedValue = new ArrayList<>(Arrays.asList(array));
        Assert.assertEquals(listExpectedValue, listResultedValue);
    }
}