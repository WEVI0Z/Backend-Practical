package com.employee.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
@Slf4j
public class SequenceRepository extends FileRepository {

    private static final String SEQUENCE_FILE_PATH = "data/sequence.txt";

    public Long getNextId() {
        String content = getContent(SEQUENCE_FILE_PATH);
        Long id = Objects.nonNull(content) ? Long.parseLong(content) + 1 : 1L;
        overwriteData(SEQUENCE_FILE_PATH, String.valueOf(id));
        return id;
    }

}
