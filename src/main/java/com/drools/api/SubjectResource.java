package com.drools.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.drools.facts.Course;
import com.drools.facts.SubjectList;
import com.drools.service.SubjectService;

@RestController
public class SubjectResource {

    private final SubjectService subjectService;

    @Autowired
    public SubjectResource(SubjectService subjectService){
        this.subjectService = subjectService;
    }

    @RequestMapping(value = "/subject",
                    method = RequestMethod.POST,
                    produces = "application/json")
    public List<Course> getSubjects(@RequestBody SubjectList subjects){
        List<Course> sub = subjectService.getSuggestedCourse(subjects.getItems());
        return sub;
    }
}
