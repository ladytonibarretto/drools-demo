package com.drools.facts;

import java.util.List;

import com.google.common.collect.Lists;

public class CourseList {

    private List<Course> items;

    public List<Course> getItems() {
        return items;
    }

    public void setItems(List<Course> items) {
        this.items = items;
    }

    public List<Course> addCourse(Course course){
        List<Course> courses = getItems();

        if(courses == null){
            courses = Lists.newArrayList();
        }
        courses.add(course);

        setItems(courses);

        return courses;
    }
}
