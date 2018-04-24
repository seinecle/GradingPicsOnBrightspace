/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.clementlevallois.gradingpics.io;

import net.clementlevallois.gradingpics.model.Assignment;
import net.clementlevallois.gradingpics.model.Student;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.image.Image;

/**
 *
 * @author LEVALLOIS
 */
public class PictureLoader {

    public List<Assignment> loadAssignmentsInMemory(String path) throws IOException {

        List<Assignment> assignments = new ArrayList();

        Path directoryAssignments = Paths.get(path);
        System.out.println("path: " + path);
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

                String date = folderName.substring(lastIndexHyphen + 1).trim();
                // 16548-9239 - Ménil ABA - Feb 23, 2018 19_13
                String[] segment = date.split(",");
                
                String monthString = segment[0].split(" ")[0].trim();
                
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MMM dd").withLocale(Locale.US);
                
                TemporalAccessor ta = formatter.parse("2017 " + monthString + " 15");
                LocalDate dateTimeParsed = LocalDate.from(ta);
                int month = dateTimeParsed.getMonth().getValue();

                String day = segment[0].trim().split(" ")[1].trim();
                if (day.length() == 1) {
                    day = "0" + day;
                }
                String yearAndTime = segment[1].trim();
                String year = yearAndTime.split(" ")[0].trim();
                String hour = yearAndTime.split(" ")[1].split("_")[0].trim();
                String minute = yearAndTime.split(" ")[1].split("_")[1].trim();

                LocalDateTime dateTime = LocalDateTime.of(Integer.valueOf(year), month, Integer.valueOf(day), Integer.valueOf(hour), Integer.valueOf(minute));

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
                        if (probeContentType == null) {
                            System.out.println("error in file content type: " + pathNIO.toString());
                            continue;
                        }
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
