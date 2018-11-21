package com.epam.training.sportsbetting.service;

import com.epam.training.sportsbetting.domain.sportevent.SportEvent;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JSONWriterReaderTest {
    private static final String SPORT_EVENTS_FILEPATH = "src/main/resources/sport_events_test.json";

    @Test
    void shouldReturnValidClass() throws Exception {
        List<SportEvent> sportEventList = TestUtils.getTestSportEventList();

        JSONWriterReader writerReader = new JSONWriterReader();

        assertEquals(sportEventList, writerReader.read(SPORT_EVENTS_FILEPATH, SportEvent[].class));
    }
}