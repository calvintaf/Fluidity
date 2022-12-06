package solutions.fluidity.test.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class EventServiceImplTest {
    @InjectMocks
    private EventServiceImpl eventService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void shouldReturnEvent() {
    }
}

//TODO : Add unit tests for all relevant methods and classes