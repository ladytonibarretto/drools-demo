package com.drools.service;

import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.ObjectFilter;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drools.facts.Course;
import com.drools.facts.CourseList;
import com.drools.facts.Subject;

@Service
public class SubjectService {

    private final KieContainer kieContainer;
    
    @Autowired
    public SubjectService(KieContainer kieContainer){
        this.kieContainer = kieContainer;
    }
    
    public List<Course> getSuggestedCourse(List<Subject> subjects){
        KieSession kieSession = kieContainer.newKieSession("SubjectSession");
        for (Subject subject : subjects){
            kieSession.insert(subject);
        }
        kieSession.fireAllRules();
        List<Course> courseList = getCoursesFromFactHandles(kieSession);
        kieSession.dispose();

        return courseList;
    }
    
    private List<Course> getCoursesFromFactHandles(KieSession kieSession){

        // Find all Course facts
        ObjectFilter courseFilter = new ObjectFilter() {
            @Override
            public boolean accept(Object object) {
                if (Course.class.equals(object.getClass())) return true;
                if (Course.class.equals(object.getClass().getSuperclass())) return true;
                return false;
            }
        };

        CourseList courses = new CourseList();
        for (FactHandle handle : kieSession.getFactHandles(courseFilter)){
            Course currSubj = (Course)kieSession.getObject(handle);
            courses.addCourse(currSubj);
        }
        
        return courses.getItems();
    }
    
}
