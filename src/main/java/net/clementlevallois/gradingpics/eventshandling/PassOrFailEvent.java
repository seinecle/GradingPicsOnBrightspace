/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.clementlevallois.gradingpics.eventshandling;

import net.clementlevallois.gradingpics.io.Excel;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventTarget;

/**
 *
 * @author LEVALLOIS
 */
public class PassOrFailEvent implements EventHandler {

    String name;
    String grade;

    public PassOrFailEvent(String studentName, String grade) {
        this.name = studentName;
        this.grade = grade;
    }

    @Override
    public void handle(Event event) {
//        System.out.println("event.getEventType().getName(): "+ event.getEventType().getName());
        if (event.getEventType().getName().equalsIgnoreCase("Action")) {
            try {
                Excel excel = new Excel();
                excel.writeFGradeForOneStudent(name, grade);
            } catch (IOException ex) {
                Logger.getLogger(PassOrFailEvent.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
