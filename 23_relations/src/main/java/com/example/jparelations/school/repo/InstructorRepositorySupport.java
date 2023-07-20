package com.example.jparelations.school.repo;

import com.example.jparelations.school.entity.Instructor;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.jparelations.school.entity.QInstructor.instructor; // 기본 ??

@Repository
public class InstructorRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public InstructorRepositorySupport(JPAQueryFactory queryFactory) {
        super(Instructor.class); // 어떤 클래스를 다루기 위한 것인지 명시
        this.queryFactory = queryFactory;
    }
    
    // first_name을 기준으로 조회하는 Querydls식 조회
    public List<Instructor> findByFirstName(String name) {
        /*
        SELECT * FROM instructor WHERE first_name = name
         */
        return queryFactory
                .selectFrom(instructor)
                // 조건
                .where(instructor.firstName.eq(name))
                // 결과 가져오기
                .fetch();
    }

    public List<Instructor> findByFirstNameOrLastName(String name) {
                /*
        SELECT * FROM instructor WHERE first_name = name
         */
        return queryFactory
                .selectFrom(instructor)
                // 조건
                .where(instructor.firstName.eq(name).or(instructor.lastName.eq(name)))
                // 결과 가져오기
                .fetch();
    }
}
