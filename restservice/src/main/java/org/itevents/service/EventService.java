package org.itevents.service;

import org.itevents.model.Event;
import org.itevents.model.Location;
import org.itevents.wrapper.EventWrapper;

import java.util.List;

public interface EventService {

    void addEvent(Event event);

    Event getEvent(int id);

    List<Event> getAllEvents();

    List<Event> getEventsInRadius(Location location, int radius);

    Event removeEvent(Event event);

    List<Event> getFilteredEvents(EventWrapper wrapper);

    Event getEventWillGo(int id);
}
