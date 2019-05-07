package io.pivotal.pal.tracker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private Map<Long, TimeEntry> map = new HashMap<>();
    private Long id = 0L;

    public TimeEntry create(TimeEntry timeEntry) {
        if (map.containsKey(timeEntry.getId())) {
            return timeEntry;
        }
        id++;
        timeEntry.setId(id);
        map.put(id, timeEntry);
        return map.get(id);
    }

    public TimeEntry find(long id) {
        return map.get(id);
    }

    public List<TimeEntry> list() {
        return new ArrayList<TimeEntry>(map.values());
    }

    public TimeEntry update(long id, TimeEntry timeEntry) {
        TimeEntry te = find(id);
        if (te != null) {
            timeEntry.setId(id);
            map.replace(timeEntry.getId(), timeEntry);
            return timeEntry;
        }
        return te;
    }

    public void delete(long id) {
        map.remove(id);
    }
}
