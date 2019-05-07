package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class TimeEntry {

    private long id;
    private long projectId;
    private long userId;
    private LocalDate date;
    private int hours;

    public TimeEntry() {
    }

    public TimeEntry(long projectId, long userId, LocalDate date, int hours) {
        this.projectId = projectId;
        this.userId = userId;
        this.date = date;
        this.hours = hours;
    }

    public TimeEntry(long id, long projectId, long userId, LocalDate date, int hours) {
        this.id = id;
        this.projectId = projectId;
        this.userId = userId;
        this.date = date;
        this.hours = hours;
    }

    @RestController
    public static class TimeEntryController {

        TimeEntryRepository respository;

        public TimeEntryController(@Autowired TimeEntryRepository respository) {
            this.respository = respository;
        }

        public ResponseEntity<TimeEntry> create(TimeEntry timeEntry) {
            respository.create(timeEntry);
            ResponseEntity<TimeEntry> response = new ResponseEntity<TimeEntry>(HttpStatus.CREATED);
            return response;
        }

        public ResponseEntity<TimeEntry> read(long id) {
            return new ResponseEntity<TimeEntry>(respository.find(id), HttpStatus.OK);
        }

        public ResponseEntity<List<TimeEntry>> list() {
            return new ResponseEntity<List<TimeEntry>>(respository.list(), HttpStatus.OK);
        }

        public ResponseEntity update(long id, TimeEntry timeEntry) {
            return new ResponseEntity<TimeEntry>(respository.update(id, timeEntry), HttpStatus.OK);
        }

        public ResponseEntity<TimeEntry> delete(long id) {
            TimeEntry te = respository.find(id);
            if (te == null) {
                new ResponseEntity<TimeEntry>(HttpStatus.NOT_FOUND);
            }
            respository.delete(id);
            return new ResponseEntity<TimeEntry>(te, HttpStatus.NO_CONTENT);
        }


    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeEntry timeEntry = (TimeEntry) o;
        return projectId == timeEntry.projectId &&
                userId == timeEntry.userId &&
                hours == timeEntry.hours &&
                Objects.equals(date, timeEntry.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, userId, date, hours);
    }
}
