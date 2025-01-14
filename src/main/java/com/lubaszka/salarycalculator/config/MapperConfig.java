package com.lubaszka.salarycalculator.config;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.NullValueCheckStrategy;

/**
 * Configuration class for MapStruct mappers.
 * <p>
 * This class provides global configuration settings for MapStruct mappers used in the application.
 * It customizes the behavior of the mappers with respect to dependency injection, null value
 * handling, and the package where the generated mapper implementations are placed.
 * </p>
 * Configuration settings:
 * <ul>
 * <li>
 * <b>componentModel</b>: Specifies the component model to be used. Set to "spring" to
 * integrate the mappers with the Spring Framework, allowing them to be managed as Spring
 * beans and injected where needed.
 * </li>
 * <li>
 * <b>nullValueCheckStrategy</b>: Defines the strategy for checking null values. Set to
 * {@link NullValueCheckStrategy#ALWAYS} to ensure that null checks are always performed during
 * mapping, which helps to avoid NullPointerExceptions and handle null values appropriately.
 * </li>
 * <li>
 * <b>injectionStrategy</b>: Specifies the strategy for dependency injection. Set to
 * {@link InjectionStrategy#CONSTRUCTOR}, meaning that dependencies will be injected via the
 * constructor of the mapper.
 * </li>
 * <li>
 * <b>implementationPackage</b>: Determines the package where the generated mapper
 * implementations will be placed. This is defined as "<PACKAGE_NAME>.impl".
 * </li>
 * </ul>
 */
@org.mapstruct.MapperConfig(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        implementationPackage = "<PACKAGE_NAME>.impl"
)
public class MapperConfig {
}
