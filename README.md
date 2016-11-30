# Grading Brightspace assignments with a desktop app.

###I create this desktop app for the following use case:  
- large number of students in the session (in my session: 500+ students)
- the assignment is submitted on dropbox
- students must upload pictures, and only pictures
- the grading is "pass or fail": I just need to visualize the pic, see if it meets the standards, and grade accordingly.

###Grading from the Brightspace website is too slow because:
- pics take some time to load  
- some pics can be seen directly in preview (jpeg format), but others need to be clicked on to be opened (png format)
This is enough to make grading for a very large number of students, for several assignments, a nightmare.

###Solution:  
- I download all assignments as a zip file
- a desktop app opens the pics one by one, with the name of the student below it, and with 2 buttons: "pass" and "fail".
- clicking on a button writes the grade in an Excel file, and moves to the next picture.
- When done with all the pics, I will just upload the grades of the Excel file on Brightspace.

###Technical aspects of the solution:
- I will take this opportunity to develop a JavaFX app for Windows an Mac.
- I should add a connection to the api of Brightspace to skip the manual download and upload, that's planned.

Feedback and questions? levallois@em-lyon.com

###License:
Copyright 2016 Clément Levallois

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.