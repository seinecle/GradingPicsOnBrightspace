/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IO;

import Model.Assignment;
import Model.Student;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import javafx.scene.image.Image;

/**
 *
 * @author LEVALLOIS
 */
public class PictureLoader {

    public List<Assignment> loadAssignmentsInMemory(String path) throws IOException {

        List<Assignment> assignments = new ArrayList();

        Path directoryAssignments = Paths.get(path);
        Files.walk(directoryAssignments).forEach(folder -> {
            String folderName = folder.getFileName().toString();
            //checking for a series of petty conditions
            if (!folderName.equals("index.html") & !folderName.equals(directoryAssignments.getFileName().toString()) & folder.toFile().isDirectory()) {
//                System.out.println("directoryAssignments.getFileName():\"" + directoryAssignments.getFileName() + "\"");
//                System.out.println("folderName: " + "\"" + folderName + "\"");
                int lastIndexHyphen = folderName.lastIndexOf("-");
                int firstLetterInName = 0;
                Pattern p = Pattern.compile("\\p{L}");
                Matcher m = p.matcher(folderName);
                if (m.find()) {
                    firstLetterInName = m.start();
                }
                String nameStudent = folderName.substring(firstLetterInName, lastIndexHyphen).trim();

                String date = folderName.substring(lastIndexHyphen+1).trim();
                date = date.replace("Oct", "oct.");
                if (date.indexOf(",") == 6){
                    date = "oct. 0"+date.substring(5);
                }
//                System.out.println("date: " + date);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy HH_mm").withLocale(Locale.FRENCH);
                LocalDateTime dateTime = LocalDateTime.parse(date, formatter);

                Student student = new Student();
                student.setFullName(nameStudent);
                Assignment assignment = new Assignment();
                assignment.setStudent(student);
                assignment.setDatetime(dateTime);

                for (File file : folder.toFile().listFiles()) {
                    Path pathNIO = Paths.get(file.toURI());
                    try {
                        //checks if the file is actually a picture.
                        String probeContentType = Files.probeContentType(pathNIO);
                        if (probeContentType.toLowerCase().contains("image")) {
                            Image image = new Image(file.toURI().toString());
                            assignment.addImage(image);
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(PictureLoader.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                //if a student already submitted an assignment, take into account the latest version only
                if (assignments.contains(assignment)) {
                    int index = assignments.indexOf(assignment);
                    Assignment prev = assignments.get(index);
                    if (prev.getDatetime().isBefore(dateTime)) {
                        assignments.remove(prev);
                        assignments.add(assignment);
                    }

                } else {
                    assignments.add(assignment);
                }
            }
        });
        return assignments;

    }

}
