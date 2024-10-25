import java.time.LocalDate;

public class MemberClassRegistration {
    private String memberID, classID, status;
    private LocalDate registrationDate;

    public MemberClassRegistration(String memberID, String classID, String status, LocalDate registrationDate) {
        this.memberID = memberID;
        this.classID = classID;
        this.status = status;
        this.registrationDate = registrationDate;
    }

    public String getMemberID(){
        return memberID;
    }

    public String getClassID(){
        return classID;
    }

    public LocalDate getRegistrationDate(){
        return registrationDate;
    }

    public String getSearchKey(){
        return (memberID + classID);
    }

    public String lineRepresentation(){
        return (memberID + "," + classID + "," + registrationDate + "," + status);
    }
}
