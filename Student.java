//Name: Abdulmohsin Mustafa Ageel
//ID: 2036815
//Section: ZA
//E-mail: a-mohsin@live.com
package StudentsAttendance;

class Student {
//Variables

    private int stdID;
    private String Fname, Lname, Status, Class;
    private Student next;
//Constructors

    public Student() {
    }

    public Student(int stdID, String Fname, String Lname) {
        this.stdID = stdID;
        this.Fname = Fname;
        this.Lname = Lname;
        Status = "in-person";
    }
//Methods

    public boolean updateStatus(int ID, String NewStatus) {
        if (stdID == ID) {
            setStatus(NewStatus);
            return true;
        } else {

            Student helpPtr = getNext();
            while (helpPtr != null) {
                if (helpPtr.getStdID() == ID) {
                    helpPtr.setStatus(Status);
                    return true;
                }
                helpPtr = helpPtr.getNext();
            }
        }
        return false;
    }

    public String printStudent(Student S) {
        return "Student{" + "stdID=" + stdID + ", Fname=" + Fname + ", Lname=" + Lname + ", Status=" + Status + ", Class=" + Class + '}';
    }

//Setters
    public void setStdID(int stdID) {
        this.stdID = stdID;
    }

    public void setFname(String Fname) {
        this.Fname = Fname;
    }

    public void setLname(String Lname) {
        this.Lname = Lname;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public void setClass(String Class) {
        this.Class = Class;
    }

    public void setNext(Student next) {
        this.next = next;
    }
//Getters

    public int getStdID() {
        return stdID;
    }

    public String getFname() {
        return Fname;
    }

    public String getLname() {
        return Lname;
    }

    public String getStatus() {
        return Status;
    }

    public String getSClass() {
        return Class;
    }

    public Student getNext() {
        return next;
    }
}
