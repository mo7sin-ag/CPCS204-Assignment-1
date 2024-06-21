//Name: Abdulmohsin Mustafa Ageel
//ID: 2036815
//Section: ZA
//E-mail: a-mohsin@live.com
package StudentsAttendance;

import java.util.*;
import java.io.*;

public class MainProgram {

    public static void main(String[] args) throws FileNotFoundException {
        
        //Writing to output file initialization.
        File OutputFile = new File("output.txt");
        PrintWriter Output_File = new PrintWriter(OutputFile);

        //Imports files.
        File intialInformationAssign1 = new File("intialInformationAssign1.txt");
        File commandsAssign1 = new File("commandsAssign1.txt");

        //Checks if the files exist.
        if (!intialInformationAssign1.exists()) {
            System.out.println("The file (intialInformationAssign1) was not found, the program will terminate.");
            System.exit(0);
        }
        if (!commandsAssign1.exists()) {
            System.out.println("The file (commandsAssign1) was not found, the program will terminate.");
            System.exit(0);
        }

        //Reads from files.
        Scanner FILEintialInformationAssign1 = new Scanner(intialInformationAssign1);
        Scanner FILEcommandsAssign1 = new Scanner(commandsAssign1);

        Output_File.println("	Welcome to the MOE Safe Attendance Management System for Elementary Students ");
        Output_File.println();
        Output_File.println("       ---------------------------------------------------------");

        //Creats arrays to store students and teachers, sizes are read from the file.
        Student[] RegisteredStudents = new Student[FILEintialInformationAssign1.nextInt()];
        Teacher[] RegisteredTeachers = new Teacher[FILEintialInformationAssign1.nextInt()];

        //To prevent reading error.
        FILEintialInformationAssign1.nextLine();

        //Stores Calsses' names.
        String[] ClassesNames = FILEintialInformationAssign1.nextLine().split(" ");

        Output_File.println("The classes are:");
        for (int i = 0; i < ClassesNames.length; i++) {
            Output_File.println("	" + ClassesNames[i]);
        }
        Output_File.println("===================================================================================================");

        String Line;
        String[] SplitedLine;

        //Start reading from intialInformationAssign1 file.
        while (FILEintialInformationAssign1.hasNextLine()) {
            //Adds students from file to array.
            for (int StudentPosition = 0; StudentPosition < RegisteredStudents.length; StudentPosition++) {
                Line = FILEintialInformationAssign1.nextLine();
                SplitedLine = Line.split(" ");
                Student S = new Student(Integer.parseInt(SplitedLine[0]), SplitedLine[1], SplitedLine[2]);
                RegisteredStudents[StudentPosition] = S;
            }
            //Adds teachers from file to array.
            for (int TeacherPosition = 0; TeacherPosition < RegisteredTeachers.length; TeacherPosition++) {
                Line = FILEintialInformationAssign1.nextLine();
                SplitedLine = Line.split(" ");

                //To fix extra space in (3546 Doaa Mohammed  Arabic Pearl) line.
                if (SplitedLine[3].equals("")) {
                    SplitedLine[3] = SplitedLine[4];
                    SplitedLine[4] = SplitedLine[5];
                }
                //(3335 Batiol Ali Math) doesn't have a class.
                if (SplitedLine.length == 4) {
                    Teacher T = new Teacher(Integer.parseInt(SplitedLine[0]), SplitedLine[1], SplitedLine[2], SplitedLine[3], "");
                    RegisteredTeachers[TeacherPosition] = T;
                    continue;
                }

                Teacher T = new Teacher(Integer.parseInt(SplitedLine[0]), SplitedLine[1], SplitedLine[2], SplitedLine[3], SplitedLine[4]);
                RegisteredTeachers[TeacherPosition] = T;
            }
        }

        Class[] ClassesLinkList = new Class[ClassesNames.length];
        TeachingStaff TeacherLinkList = new TeachingStaff();

        //Start reading from FILEcommandsAssign1 file.
        while (FILEcommandsAssign1.hasNextLine()) {
            Line = FILEcommandsAssign1.nextLine();
            SplitedLine = Line.split(" ");
            String Command = SplitedLine[0];

            switch (Command) {
                //STARTUP command ####################################################################################################################################################################
                case "STARTUP":
                    //Creates LinkList for Classes.
                    for (int ClassNumber = 0; ClassNumber < ClassesNames.length; ClassNumber++) {
                        Class C = new Class(ClassesNames[ClassNumber]);
                        ClassesLinkList[ClassNumber] = C;
                    }

                    //Adds studenst in each class.
                    for (int StudentPosition = 0, ClassNumber = 0, Count = 0; StudentPosition < RegisteredStudents.length; StudentPosition++) {
                        ClassesLinkList[ClassNumber].addStudent(RegisteredStudents[StudentPosition]);

                        //After every 5 student, the class will change to add the next 5 student to a diffrent class.
                        Count++;
                        if (Count == 5) {
                            Count = 0;
                            ClassNumber++;
                        }
                    }

                    //Adds teachers in the TeachingStaff LinkedList
                    for (int TeacherPosition = 0; TeacherPosition < RegisteredTeachers.length; TeacherPosition++) {
                        TeacherLinkList.AddTeacher(RegisteredTeachers[TeacherPosition]);
                    }
                    break;

                //STARTUP command ####################################################################################################################################################################
                case "DISPLAY_ALL_CLASSES":
                    Output_File.println();
                    Output_File.println("The first distribution for students among the classes ");
                    Output_File.println("===================================================================================================");
                    Output_File.println();

                    for (int ClassPosition = 0; ClassPosition < ClassesLinkList.length; ClassPosition++) {
                        Output_File.print(ClassesNames[ClassPosition] + "                      ");
                    }
                    Output_File.println();
                    Output_File.println();
                    Output_File.println("-------------------------------------------------------------------------------------------------------------------------------------");

                    //Will print the first student from each class, then the second student from each class and so on. StudentNumber detrmines the row of students while ClassNumber determiens the class.
                    //Inner loop changes the row of students, outer loop changes the class.
                    for (int StudentNumber = 0; StudentNumber < ClassesLinkList.length; StudentNumber++) {
                        for (int ClassNumber = 0; ClassNumber < ClassesLinkList.length; ClassNumber++) {
                            Student S = ClassesLinkList[ClassNumber].searchByIndex(StudentNumber);
                            Output_File.print(S.getStdID() + " " + S.getFname() + " " + S.getLname() + "         ");
                        }
                        Output_File.println();
                    }
                    Output_File.println();
                    break;

                //NUM_OF_STUDENTS_IN_CLASS command ####################################################################################################################################################################
                case "NUM_OF_STUDENTS_IN_CLASS":
                    Output_File.println("===================================================================================================");
                    Output_File.println();

                    for (int ClassNumber = 0; ClassNumber < ClassesLinkList.length; ClassNumber++) {
                        if (ClassesLinkList[ClassNumber].getClassName().equals(SplitedLine[1])) {
                            Output_File.println("Number of students in " + SplitedLine[1] + ": " + ClassesLinkList[ClassNumber].CountNumberOfStudents());
                            break;
                        } //This will be printed if the class wasn't found.
                        else if (ClassNumber + 1 == ClassesLinkList.length) {
                            Output_File.println("Not found any class name " + SplitedLine[1]);
                        }
                    }
                    break;

                //DISPLAY_STUDENTS_IN_CLASS command ####################################################################################################################################################################
                case "DISPLAY_STUDENTS_IN_CLASS":
                    Output_File.println("===================================================================================================");
                    Output_File.println();

                    //This loop searches for the class.
                    for (int ClassNumber = 0; ClassNumber < ClassesLinkList.length; ClassNumber++) {
                        if (ClassesLinkList[ClassNumber].getClassName().equals(SplitedLine[1])) {
                            Output_File.println();
                            Output_File.println("The students of class " + SplitedLine[1]);
                            Output_File.println("------------------------------");
                            Output_File.println();
                            Output_File.println("       " + SplitedLine[1] + "			");
                            Output_File.println("--------------------------------------------------------------------------------------------------");

                            //This loop print all students in the class.
                            //(ClassesLinkList[ClassNumber].CountNumberOfStudents()) is used to find the number of students in the class.
                            for (int StudentNumber = 0; StudentNumber < ClassesLinkList[ClassNumber].CountNumberOfStudents(); StudentNumber++) {
                                Student S = ClassesLinkList[ClassNumber].searchByIndex(StudentNumber);
                                Output_File.println(S.getStdID() + ", " + S.getFname() + " " + S.getLname() + ", " + S.getStatus());
                            }
                            break;
                        } //This will be printed if the class wasn't found.
                        else if (ClassNumber + 1 == ClassesLinkList.length) {
                            Output_File.print("Not found any class name " + SplitedLine[1]);
                        }
                    }
                    Output_File.println();
                    break;

                //DISPLAY_BASED_ON_STATUS command ####################################################################################################################################################################
                case "DISPLAY_BASED_ON_STATUS":
                    Output_File.println("===================================================================================================");
                    Output_File.println();

                    //This loop searches for the class.
                    for (int ClassNumber = 0; ClassNumber < ClassesLinkList.length; ClassNumber++) {

                        if (ClassesLinkList[ClassNumber].getClassName().equals(SplitedLine[1])) {
                            //If a student with the desired status was found, this header will only be printed once.
                            boolean PrintHeader = true;
                            //If a student with the desired status was found, the "Not found any student of the status" wont't be printed.
                            boolean FoundNoStudent = true;

                            //This loop will search all students in the class and print the ones with the desired status.
                            //(ClassesLinkList[ClassNumber].CountNumberOfStudents()) is used to find the number of students in the class.
                            for (int StudentNumber = 0; StudentNumber < ClassesLinkList[ClassNumber].CountNumberOfStudents(); StudentNumber++) {

                                Student S = ClassesLinkList[ClassNumber].searchByIndex(StudentNumber);
                                if (S.getStatus().equals(SplitedLine[2])) {

                                    if (PrintHeader) {
                                        Output_File.println();
                                        Output_File.println("The students of class " + SplitedLine[1] + " with status " + SplitedLine[2]);
                                        Output_File.println("---------------------------------------------------");
                                        Output_File.println();
                                        Output_File.println("       " + SplitedLine[1] + " 			");
                                        Output_File.println("--------------------------------------------------------------------------------------------------");
                                        PrintHeader = false;
                                        FoundNoStudent = false;
                                    }

                                    Output_File.println(S.getStdID() + ", " + S.getFname() + " " + S.getLname() + ", " + S.getStatus());
                                }
                            }
                            if (FoundNoStudent) {
                                Output_File.println("Not found any student of the status " + SplitedLine[2] + " in class " + SplitedLine[1]);
                            }
                            break;
                        } //This will be printed if the class wasn't found.
                        else if (ClassNumber + 1 == ClassesLinkList.length) {
                            Output_File.println("Not found any class name " + SplitedLine[1]);
                        }
                    }
                    Output_File.println();
                    break;

                //CHANGE_STUDENT_STATUS command ####################################################################################################################################################################
                case "CHANGE_STUDENT_STATUS":
                    Output_File.println("===================================================================================================");
                    Output_File.println();

                    //This loop searches for the class.
                    for (int ClassNumber = 0; ClassNumber < ClassesLinkList.length; ClassNumber++) {

                        if (ClassesLinkList[ClassNumber].getClassName().equals(SplitedLine[2])) {
                            //If a student with the desired ID was found, the "Not found any student with id" wont't be printed.
                            boolean FoundNoStudent = true;

                            //This loop will search all students in the class and select the one with the desired ID.
                            //(ClassesLinkList[ClassNumber].CountNumberOfStudents()) is used to find the number of students in the class.
                            for (int StudentNumber = 0; StudentNumber < ClassesLinkList[ClassNumber].CountNumberOfStudents(); StudentNumber++) {

                                Student S = ClassesLinkList[ClassNumber].searchByIndex(StudentNumber);
                                if (S.getStdID() == Integer.parseInt(SplitedLine[1])) {
                                    Output_File.print("The student " + S.getFname() + " " + S.getLname() + " (ID#" + S.getStdID() + ") from class " + S.getSClass() + " changed her status from " + S.getStatus() + " to ");
                                    S.setStatus(SplitedLine[3]);
                                    Output_File.println(S.getStatus());
                                    FoundNoStudent = false;
                                }

                            }
                            if (FoundNoStudent) {
                                Output_File.println("Not found any student with id " + SplitedLine[1] + " in class " + SplitedLine[2]);
                            }
                            break;
                        } //This will be printed if the class wasn't found.
                        else if (ClassNumber + 1 == ClassesLinkList.length) {
                            Output_File.println("Not found any class name " + SplitedLine[2]);
                        }
                    }
                    break;

                //DISPLAY_ALL_TEACHERS command ####################################################################################################################################################################
                case "DISPLAY_ALL_TEACHERS":
                    Output_File.println("===================================================================================================");
                    Output_File.println();
                    Output_File.println("The teachers are:");
                    Output_File.println();

                    for (int TeacherNumber = 0; TeacherNumber < RegisteredTeachers.length; TeacherNumber++) {
                        Teacher T = TeacherLinkList.searchByIndex(TeacherNumber);
                        Output_File.println("ID:" + T.getTeachID() + ", Name: " + T.getFname() + " " + T.getLname() + ",  Subject: " + T.getSubject() + " " + T.getTClass());
                    }
                    Output_File.println();
                    break;

                //MOVE_STUDENTS command ####################################################################################################################################################################
                case "MOVE_STUDENTS":
                    Output_File.println("===================================================================================================");
                    Output_File.println();

                    //This loop searches for the class.
                    for (int OldClass = 0; OldClass < ClassesLinkList.length; OldClass++) {

                        if (ClassesLinkList[OldClass].getClassName().equals(SplitedLine[2])) {
                            //If a student with the desired ID was found, the "Not found any student with id" wont't be printed.
                            boolean FoundNoStudent = true;

                            //This loop will search all students in the class and select the one with the desired ID.
                            //(ClassesLinkList[ClassNumber].CountNumberOfStudents()) is used to find the number of students in the class.
                            for (int StudentNumber = 0; StudentNumber < ClassesLinkList[OldClass].CountNumberOfStudents(); StudentNumber++) {

                                Student S = ClassesLinkList[OldClass].searchByIndex(StudentNumber);
                                if (S.getStdID() == Integer.parseInt(SplitedLine[1])) {

                                    for (int NewClass = 0; NewClass < ClassesLinkList.length; NewClass++) {
                                        if (ClassesLinkList[NewClass].getClassName().equals(SplitedLine[3])) {
                                            Output_File.print("The student " + S.getFname() + " " + S.getLname() + " (ID#" + S.getStdID() + ") moved from class " + S.getSClass() + " to class ");
                                            ClassesLinkList[OldClass].deleteStudent(S.getStdID());
                                            ClassesLinkList[NewClass].AddStudentAtBeginning(S);
                                            S.setClass(SplitedLine[3]);
                                            Output_File.println(S.getSClass());
                                            FoundNoStudent = false;
                                            break;
                                        } else if (NewClass + 1 == ClassesLinkList.length) {
                                            Output_File.println("Not found any class name " + SplitedLine[3]);
                                        }
                                    }
                                    break;
                                }
                            }
                            if (FoundNoStudent) {
                                Output_File.println("Not found any student with id " + SplitedLine[1] + " in class " + SplitedLine[2]);
                            }
                            break;
                        } //This will be printed if the class wasn't found.
                        else if (OldClass + 1 == ClassesLinkList.length) {
                            Output_File.println("Not found any class name " + SplitedLine[2]);
                        }
                    }
                    break;

                //QUIT command ####################################################################################################################################################################
                case "QUIT":
                    Output_File.println();
                    Output_File.println();
                    Output_File.println();
                    Output_File.println("	-----------------------------------------");
                    Output_File.println("	  Best wishes for your first assignment!           ");
                    Output_File.println("         -----------------------------------------                         ");
                    break;
            }
        }
        Output_File.close();
    }
}
