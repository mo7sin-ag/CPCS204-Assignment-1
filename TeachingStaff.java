//Name: Abdulmohsin Mustafa Ageel
//ID: 2036815
//Section: ZA
//E-mail: a-mohsin@live.com
package StudentsAttendance;

class TeachingStaff {
//Variables

    private String Name = "TeachingStaff";
    private Teacher head;

//Constructors
    public TeachingStaff() {
    }

    public TeachingStaff(Teacher head) {
        this.head = head;
    }

//Methods
    public void AddTeacher(Teacher T) {
        if (head == null) {
            head = T;
        } else {
            Teacher helpPtr = head;
            while (helpPtr.getNext() != null) {
                helpPtr = helpPtr.getNext();
            }
            helpPtr.setNext(T);
        }
    }

    //Method added by me to print by index.
    public Teacher searchByIndex(int Index) {
        if (Index == 0) {
            return head;

        } else {
            Teacher helpPtr = head;

            for (int TeacherPosition = 0; TeacherPosition < Index; TeacherPosition++) {
                helpPtr = helpPtr.getNext();
            }
            return helpPtr;
        }
    }

    public Teacher searchByID(int ID) {
        Teacher helpPtr = head;

        while (helpPtr != null) {
            if (helpPtr.getTeachID() == ID) {
                return helpPtr;
            }
            helpPtr = helpPtr.getNext();
        }
        return helpPtr;
    }

//Setters
    public void setName(String Name) {
        this.Name = Name;
    }

    public void setHead(Teacher head) {
        this.head = head;
    }

//Getters
    public String getName() {
        return Name;
    }

    public Teacher getHead() {
        return head;
    }
}
