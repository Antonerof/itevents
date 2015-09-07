package org.itevents.service;

import org.itevents.model.Event;
import org.itevents.model.Location;
import org.itevents.model.Filter;

import java.util.Date;
import java.util.List;

public interface EventService {

    void addEvent(Event event);

    Event getEvent(int id);

    List<Event> getAllEvents();

    List<Event> getEventsInRadius(Location location, int radius);

    Event removeEvent(Event event);

    List<Event> getFilteredEvents(Filter params);

    List<Event> getFutureFilteredEvents(FilteredEventsParameter params, Date date);
}
