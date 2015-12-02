package org.itevents.service.sendmail;

import org.itevents.dao.UserDao;
import org.itevents.model.Event;
import org.itevents.model.Filter;
import org.itevents.model.User;
import org.itevents.service.FilterService;
import org.itevents.service.MailFilterService;
import org.itevents.test_utils.BuilderUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

import java.text.ParseException;
import java.util.List;

import static org.mockito.Mockito.*;
/**
 * Created by ramax on 11/6/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class MailNotificationServiceTest {

    @InjectMocks
    @Inject
    private MailNotificationService mailNotificationEventService;

    @Mock
    private MailService mailService;

    @Mock
    private UserDao userDao;

    @Mock
    private MailFilterService mailFilterService;

    @Mock
    private FilterService filterService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void performNotifyTest() throws ParseException {
        List<User> users = BuilderUtil.buildAllUser();
        users.forEach(a->a.setSubscribed(true));
        when(userDao.getAllUsers()).thenReturn(users);

        List<Event> events = BuilderUtil.buildEventsForMailUtilTest();
        when(mailFilterService.getFilteredEventsInDateRangeWithRating(any(Filter.class))).thenReturn(events);

        doNothing().when(mailService).sendMail(anyString(), anyString());

        Filter kievFilter = BuilderUtil.buildKyivFilter();
        when(filterService.getLastFilterByUser(any(User.class))).thenReturn(kievFilter);

        mailNotificationEventService.performNotify();

        verify(userDao).getAllUsers();
        verify(mailService, times(users.size())).sendMail(anyString(), anyString());
        verify(mailFilterService, times(users.size())).getFilteredEventsInDateRangeWithRating(any(Filter.class));
        verify(filterService, times(users.size())).getLastFilterByUser(any(User.class));
    }
}
