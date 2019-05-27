# The Date and Time API

1. All java.time objects are immutable.
2. An Instant is a point on the time line (similar to a Data).
3. In Java time, each day has exactly 86,400 seconds (that is, no leap seconds).
4. A Duration is the difference between two instants. Period represents the number of elapsed
years, months, or days.
5. LocalDateTime has no time zone information.
6. TemporalAdjuster methods handle common calendar computations, such as finding the first Tuesday of a month.
7. ZonedDateTime is a point in time in a given time zone (similar to GregorianCalendar).
8. Use a Period, not a Duration, when advancing zoned time, in order to account for
daylight savings time changes.
9. Use DateTimeFormatter to format and parse dates and times.

## Local date/time classes (No specific instant in time)

- java.time.LocalDate
- java.time.LocalTime
- java.time.LocalDateTime
- java.time.Period

## Instant classes (Specific instant in time)

- java.time.Instant
- java.time.Duration
- java.time.ZonedDateTime
