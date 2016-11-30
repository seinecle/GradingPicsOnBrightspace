/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;

/**
 *
 * @author LEVALLOIS
 */
public class Assignment {

    List<Image> images;
    Student student;

    public Assignment() {
        images = new ArrayList();
    }

    public void addImage(String fullPathAndName) {
        Image image = new Image(fullPathAndName);
        images.add(image);
    }

    public void addImage(Image image) {
        images.add(image);
    }

    public List<Image> getImages() {
        return images;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

}
