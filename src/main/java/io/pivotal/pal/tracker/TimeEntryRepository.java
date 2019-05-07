package io.pivotal.pal.tracker;

import java.util.List;

public interface TimeEntryRepository {

    TimeEntry create(TimeEntry any);

    TimeEntry find(long timeEntryId);

    List<TimeEntry> list();

    void delete(long timeEntryId);

    TimeEntry update(long eq, TimeEntry any);
}
