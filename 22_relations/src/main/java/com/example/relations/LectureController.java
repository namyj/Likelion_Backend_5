package com.example.relations;

import com.example.relations.entity.Instructor;
import com.example.relations.entity.Lecture;
import com.example.relations.repo.InstructorRepository;
import com.example.relations.repo.LectureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("lectures")
@RequiredArgsConstructor
public class LectureController {
    private final LectureRepository lectureRepository;
    private final InstructorRepository instructorRepository;

    // 강의에 강사 매핑
    @PutMapping("{id}/instructor/{instructorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // response body가 없다는 것을 명시
    public void updateLectureInstructor(
           @PathVariable("id") Long id,
           @PathVariable("instructorId") Long instructorId
    ) {
        Optional<Lecture> optionalLecture = lectureRepository.findById(id);
        if (optionalLecture.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        Optional<Instructor> optionalInstructor = instructorRepository.findById(instructorId);
        if (optionalInstructor.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        Lecture lecture = optionalLecture.get();
        Instructor instructor = optionalInstructor.get();

        lecture.setInstructor(instructor);
        lectureRepository.save(lecture);
    }

    // id 강의의 강사를 반환하는 엔드포인트
    @GetMapping("{id}/instructor")
    public void readlectureInstructor(Long id) {
        Optional<Lecture> optionalLecture
                = lectureRepository.findById(id);
        if (optionalLecture.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        log.info(optionalLecture.get().getInstructor().toString());
    }
}
