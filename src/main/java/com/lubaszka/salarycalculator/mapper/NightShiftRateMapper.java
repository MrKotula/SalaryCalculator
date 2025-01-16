package com.lubaszka.salarycalculator.mapper;

import com.lubaszka.salarycalculator.config.MapperConfig;
import com.lubaszka.salarycalculator.model.NightShiftRate;
import com.lubaszka.salarycalculator.model.dto.response.NightShiftRateResponse;
import org.mapstruct.Mapper;

/**
 * Interface for mapping NightShiftRate entities to NightShiftRateResponse DTOs.
 * This interface utilizes MapStruct to define the mappings between the NightShiftRate entity
 * and the NightShiftRateResponse Data Transfer Object (DTO). It provides a method to convert
 * a NightShiftRate object into its corresponding NightShiftRateResponse representation.
 *
 *
 * @see com.lubaszka.salarycalculator.model.NightShiftRate
 * @see com.lubaszka.salarycalculator.model.dto.response.NightShiftRateResponse
 */
@Mapper(config = MapperConfig.class)
public interface NightShiftRateMapper {

    /**
     * Maps a {@link NightShiftRate} entity to a {@link NightShiftRateResponse} DTO.
     *
     * @param nightShiftRate the NightShiftRate entity to be mapped
     * @return a corresponding NightShiftRateResponse DTO
     */
    NightShiftRateResponse mapToNightShiftRateResponse(NightShiftRate nightShiftRate);
}
