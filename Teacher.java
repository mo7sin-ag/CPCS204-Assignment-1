//Name: Abdulmohsin Mustafa Ageel
//ID: 2036815
//Section: ZA
//E-mail: a-mohsin@live.com
package StudentsAttendance;

class Teacher {
//Variables

    private int teachID;
    private String Fname, Lname, Subject, Class;
    private Teacher next;
//Constructors

    public Teacher() {
    }

    public Teacher(int teachID, String Fname, String Lname, String Subject, String Class) {
        this.teachID = teachID;
        this.Fname = Fname;
        this.Lname = Lname;
        this.Subject = Subject;
        this.Class = Class;
    }

//Methods
    public String printTeacher() {
        return "Teacher{" + "teachID=" + teachID + ", Fname=" + Fname + ", Lname=" + Lname + ", Subject=" + Subject + ", Class=" + Class + '}';
    }

//Setters
    public void setTeachID(int teachID) {
        this.teachID = teachID;
    }

    public void setFname(String Fname) {
        this.Fname = Fname;
    }

    public void setLname(String Lname) {
        this.Lname = Lname;
    }

    public void setSubject(String Subject) {
        this.Subject = Subject;
    }

    public void setClass(String Class) {
        this.Class = Class;
    }

    public void setNext(Teacher next) {
        this.next = next;
    }

//Getters
    public int getTeachID() {
        return teachID;
    }

    public String getFname() {
        return Fname;
    }

    public String getLname() {
        return Lname;
    }

    public String getSubject() {
        return Subject;
    }

    public String getTClass() {
        return Class;
    }

    public Teacher getNext() {
        return next;
    }

}
