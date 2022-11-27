package com.example.finalproject;

import static org.junit.Assert.*;

import org.junit.Test;

public class ClassTest {

    @Test
    public void setCourseDays() {
        String a=null;
        Class curr = new Class("TestClassCode","TestClassName");
        curr.setCourseDays(a);
        assertSame("Null as expected",curr.getCourseDays(),null);
        //assertSame("Null as expected",curr.getCourseDays(),"");
    }
    @Test
    public void setCourseDays2() {
        String a=null;
        Class curr = new Class("TestClassCode","TestClassName");
        curr.setCourseDays(a);
        assertSame("double quotes will not be returned so error",curr.getCourseDays(),"");
    }


    @Test
    public void setCourseHours() {
        String a=null;
        Class curr = new Class("TestClassCode","TestClassName");
        curr.setCourseHours(a);
        assertSame("double quotes will not be returned so will be an error",curr.getCourseHours(),"");
    }
    @Test
    public void setCourseHoursB() {
        String a=null;
        Class curr = new Class("TestClassCode","TestClassName");
        curr.setCourseHours(a);
        assertSame("Null as expected so should pass",curr.getCourseHours(),null);
    }
    @Test
    public void setCourseHours2() {
        String a="18:00-20:00";
        Class curr = new Class("TestClassCode","TestClassName");
        curr.setCourseHours(a);
        assertSame("18:00-20:00 as expected so should fail",curr.getCourseHours(),null);
    }

    @Test
    public void setCapacity() {
        String a=null;
        Class curr = new Class("TestClassCode","TestClassName");
        curr.setCapacity(a);
        assertSame("Null is not valid should fail",curr.getCourseHours(),null);
    }
    @Test
    public void setCapacity2() {
        String a="0";
        Class curr = new Class("TestClassCode","TestClassName");
        curr.setCapacity(a);
        assertSame("zero should not be null so should fail",curr.getCourseHours(),null);
    }
    @Test
    public void setCapacity3() {
        String a="0p";
        Class curr = new Class("TestClassCode","TestClassName");
        curr.setCapacity(a);
        assertSame("error handling must be in method that calls this method so should fail",curr.getCourseHours(),null);
    }



}