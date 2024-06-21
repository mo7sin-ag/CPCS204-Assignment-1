//Name: Abdulmohsin Mustafa Ageel
//ID: 2036815
//Section: ZA
//E-mail: a-mohsin@live.com
package StudentsAttendance;

class Class {
    //Variables

    private String className;
    private Student head;
//Constructors

    public Class() {
    }

    public Class(String className) {
        this.className = className;
        this.head = null;
    }

    public Class(String className, Student head) {
        this.className = className;
        this.head = head;
    }

//Methods
    public void addStudent(Student S) {
        S.setClass(className);

        if (head == null) {
            head = S;
        } else {
            Student helpPtr = head;

            while (helpPtr.getNext() != null) {
                helpPtr = helpPtr.getNext();
            }
            helpPtr.setNext(S);
        }
    }

    public Student searchByID(int ID) {
        Student helpPtr = head;

        while (helpPtr != null) {
            if (helpPtr.getStdID() == ID) {
                return helpPtr;
            }
            helpPtr = helpPtr.getNext();
        }
        return head;
    }

    public Student searchByStatus(String Status) {
        if (head == null) {
            return null;
        } else {
            Student helpPtr = head;

            while (helpPtr != null) {
                if (helpPtr.getStatus().equals(Status)) {
                    break;
                }
                helpPtr = helpPtr.getNext();
            }
            return helpPtr;
        }
    }

    //Method added by me to print by index.
    public Student searchByIndex(int Index) {
        if (Index == 0) {
            return head;

        } else {
            Student helpPtr = head;

            for (int StudentPosition = 0; StudentPosition < Index; StudentPosition++) {
                helpPtr = helpPtr.getNext();
            }
            return helpPtr;
        }
    }

    //Method added by me to count number of students in a class.
    public int CountNumberOfStudents() {
        int NumberOfStudents = 0;
        Student helpPtr = head;

        while (helpPtr != null) {
            NumberOfStudents++;
            helpPtr = helpPtr.getNext();
        }
        return NumberOfStudents;
    }

    public void deleteStudent(int ID) {
        if (head.getStdID() == ID) {
            head = head.getNext();
        } else {
            Student helpPtr = head;

            while (helpPtr.getNext() != null) {
                if (helpPtr.getNext().getStdID() == ID) {
                    helpPtr.getNext().setNext(null);
                    helpPtr.setNext(helpPtr.getNext());
                    break;
                }
                helpPtr = helpPtr.getNext();
            }
        }
    }

    public void AddStudentAtBeginning(Student S) {
        S.setNext(head);
        head = S;
    }

//Setters
    public void setClassName(String className) {
        this.className = className;
    }

    public void setHead(Student head) {
        this.head = head;
    }

//Getters
    public String getClassName() {
        return className;
    }

    public Student getHead() {
        return head;
    }
}
