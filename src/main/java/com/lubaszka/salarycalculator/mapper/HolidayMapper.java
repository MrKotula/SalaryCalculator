package com.lubaszka.salarycalculator.mapper;

import com.lubaszka.salarycalculator.config.MapperConfig;
import com.lubaszka.salarycalculator.model.Holiday;
import com.lubaszka.salarycalculator.model.dto.response.HolidayResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Interface for mapping Holiday entities to HolidayResponse DTOs.
 * Utilizes MapStruct for defining mappings between the Holiday model and HolidayResponse DTO.
 * Configured using MapperConfig and includes custom mappings for ID generation and day-of-week determination.
 *
 * <p>HolidayMapper provides a method for converting a Holiday entity into a HolidayResponse
 * DTO with additional attributes, such as a unique identifier (generated sequentially) and
 * a day-of-week field derived from the date string.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 *     Holiday holiday = new Holiday("2024-01-01", "New Year's Day", "New Year's Day", "US", false, true, null, List.of("Public"), null);
 *     HolidayResponse response = holidayMapper.mapToHolidayResponse(holiday);
 * </pre>
 *
 * @see com.lubaszka.salarycalculator.model.Holiday
 * @see com.lubaszka.salarycalculator.model.dto.response.HolidayResponse
 */
@Mapper(config = MapperConfig.class)
public interface HolidayMapper {

    DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Counter for generating unique identifiers for HolidayResponse objects.
     */
    AtomicInteger COUNTER = new AtomicInteger(1);

    /**
     * Maps a {@link Holiday} entity to a {@link HolidayResponse} DTO.
     *
     * <p>This mapping includes the following transformations:</p>
     * <ul>
     *     <li>Generates a unique ID for the DTO using an atomic counter.</li>
     *     <li>Maps the name and date directly from the Holiday entity.</li>
     *     <li>Determines the day of the week from the holiday's date string using the
     *     {@link #mapDayOfWeek(String)} method.</li>
     * </ul>
     *
     * @param holiday the Holiday entity to map
     * @return the mapped HolidayResponse DTO
     */
    @Mapping(target = "id", expression = "java(COUNTER.getAndIncrement())")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "date", source = "date")
    @Mapping(target = "dayOfWeek", source = "date", qualifiedByName = "mapDayOfWeek")
    HolidayResponse mapToHolidayResponse(Holiday holiday);

    /**
     * Determines the day of the week from a date string in "yyyy-MM-dd" format.
     *
     * @param date the date string to convert
     * @return the day of the week as a string
     * @throws java.time.format.DateTimeParseException if the date string is not in the expected format
     */
    @Named("mapDayOfWeek")
    default String mapDayOfWeek(String date) {
        LocalDate localDate = LocalDate.parse(date, DATE_TIME_FORMATTER);

        return localDate.getDayOfWeek().toString();
    }
}

