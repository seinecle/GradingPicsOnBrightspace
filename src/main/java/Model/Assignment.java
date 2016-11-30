/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javafx.scene.image.Image;

/**
 *
 * @author LEVALLOIS
 */
public class Assignment {

    List<Image> images;
    Student student;
    LocalDateTime datetime;

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

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.student);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Assignment other = (Assignment) obj;
        if (!Objects.equals(this.student, other.student)) {
            return false;
        }
        return true;
    }
    
    

}
