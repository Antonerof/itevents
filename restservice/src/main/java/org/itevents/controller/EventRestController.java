package org.itevents.controller;

import io.swagger.annotations.Api;
import org.itevents.model.Event;
import org.itevents.service.EventService;
import org.itevents.wrapper.EventWrapper;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
@Api("Events")
public class EventRestController {

    private final int DEFAULT_ITEMS_PER_PAGE = 10;
    @Inject
    private EventService eventService;

    @RequestMapping(method = RequestMethod.GET, value = "/events/{id}")
    public ResponseEntity<Event> getEvent(@PathVariable("id") int id) {
        Event event = eventService.getEvent(id);
        if (event == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Event>(event, HttpStatus.OK);
    }


    private List<Event> getPaginatedEvents(int page, int itemsPerPage, List<Event> events) {

        int pages = events.size()/itemsPerPage;
        if (events.size() % itemsPerPage != 0) {
            pages++;
        }
        PagedListHolder<Event> paginatedEvents = new PagedListHolder<Event>(events);
        paginatedEvents.setPageSize(itemsPerPage);
        if (page <= 0) {
            return paginatedEvents.getPageList();
        }
        if (page > pages - 1) {
            paginatedEvents.setPage(pages - 1);
            return paginatedEvents.getPageList();
        }
        paginatedEvents.setPage(page);
        return paginatedEvents.getPageList();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/events")
    public List<Event> getFilteredEvents(@RequestParam(required = false, value = "page") Integer page,
                                         @RequestParam(required = false, value = "itemsPerPage") Integer itemsPerPage,
                                         @ModelAttribute EventWrapper wrapper) {
        itemsPerPage = getItemsPerPage(itemsPerPage);
        List<Event> filteredEvents = eventService.getFilteredEvents(wrapper);
        return getPaginatedEvents(page, itemsPerPage, filteredEvents);
    }

    private int getItemsPerPage(Integer itemsPerPage) {
        if (itemsPerPage == null || itemsPerPage <= 0) {
            itemsPerPage = DEFAULT_ITEMS_PER_PAGE;
        }
        return itemsPerPage;
    }
}
