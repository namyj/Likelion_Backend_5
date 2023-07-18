package com.example.relations;

import com.example.relations.entity.Instructor;
import com.example.relations.entity.Lecture;
import com.example.relations.repo.InstructorRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("instructor")
@RequiredArgsConstructor
public class InstructorController {

    private final InstructorRepository instructorRepository;

    @GetMapping("{id}/lectures")
    @Transactional
    public void readInstructorLectures(
            @PathVariable("id") Long id
    ) {
        Optional<Instructor> optionalInstructor = instructorRepository.findById(id);
        if (optionalInstructor.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        Instructor instructor = optionalInstructor.get();

        if (instructor.getLectures() == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        for (Lecture lecture: instructor.getLectures()) {
            log.info(lecture.getCourseName());
        }

    }
}
