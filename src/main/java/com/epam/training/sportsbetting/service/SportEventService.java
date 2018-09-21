package com.epam.training.sportsbetting.service;

import com.epam.training.sportsbetting.domain.sportevent.SportEvent;

import java.util.List;

public class SportEventService {
    private JSONWriterReader writerReader = new JSONWriterReader();

    public List<SportEvent> getSportEventsFromJSON(String filepath) {
        return writerReader.read(filepath, SportEvent.class);
    }
}
