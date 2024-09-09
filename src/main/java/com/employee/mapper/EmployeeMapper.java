package com.employee.mapper;

import com.employee.dto.EmployeeDto;
import com.employee.entity.EmployeeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "firstName", source = "firstName"),
            @Mapping(target = "lastName", source = "lastName"),
            @Mapping(target = "dateOfBirth", source = "dateOfBirth"),
            @Mapping(target = "salary", source = "salary", qualifiedByName = "longToString"),
            @Mapping(target = "joinDate", source = "joinDate"),
            @Mapping(target = "departement", source = "departement")
    })
    EmployeeDto mapToEmployeeDto(EmployeeEntity employeeEntity);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "firstName", source = "firstName"),
            @Mapping(target = "lastName", source = "lastName"),
            @Mapping(target = "dateOfBirth", source = "dateOfBirth"),
            @Mapping(target = "salary", source = "salary", qualifiedByName = "stringToLong"),
            @Mapping(target = "joinDate", source = "joinDate"),
            @Mapping(target = "departement", source = "departement")
    })
    EmployeeEntity mapToEmployeeEntity(EmployeeDto employeeDto);

    @Named("longToString")
    default String longToString(Long value) {
        return String.valueOf(value);
    }

    @Named("stringToLong")
    default Long stringToLong(String value) {
        return Long.parseLong(value);
    }
}
