/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.clementlevallois.gradingpics.model;

/**
 *
 * @author LEVALLOIS
 */
public class Grade {

    Float assignment1;
    Float assignment2;
    Float assignment3;
    Float total;

    public float getAssignment1() {
        return assignment1;
    }

    public void setAssignment1(float assignment1) {
        this.assignment1 = assignment1;
    }

    public float getAssignment2() {
        return assignment2;
    }

    public void setAssignment2(float assignment2) {
        this.assignment2 = assignment2;
    }

    public float getAssignment3() {
        return assignment3;
    }

    public void setAssignment3(float assignment3) {
        this.assignment3 = assignment3;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float computeTotal() {
        total = (assignment1 == null) ? 0f : assignment1;
        total = (assignment2 == null) ? total: assignment2;
        total = (assignment3 == null) ? total: assignment3;
        
        return total;
    }

}
