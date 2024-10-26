import java.util.ArrayList;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class TrainerRole {
    private MemberDatabase memberDatabase;
    private ClassDatabase classDatabase;
    private MemberClassRegistrationDatabase registrationDatabase;

    public TrainerRole() {
        this.memberDatabase = new MemberDatabase("Members");
        this.classDatabase = new ClassDatabase("Class");
        this.registrationDatabase = new MemberClassRegistrationDatabase("Registration");
    }

    public void addMember(String memberID, String name, String membershipType, String mail, String phoneNumber, String status) {
        Member member = new Member(memberID, name, membershipType, mail, phoneNumber, status);
        memberDatabase.insertRecord(member);
    }

    public ArrayList<Member> getListOfMembers() {
        return memberDatabase.returnAllRecords();
    }

    public void addClass(String classID, String className, String trainerID, int duration, int maxParticipants) {
        Class theClass = new Class(classID, className, trainerID, duration, maxParticipants);
        classDatabase.insertRecord(theClass);
    }

    public ArrayList<Class> getListOfClasses() {
        return classDatabase.returnAllRecords();
    }

    public boolean registerMemberForClass(String memberID, String classID, LocalDate registrationDate) {
        if (classDatabase.contains(classID)) {
            Class theClass = classDatabase.getRecord(classID);
            int availableSeats = theClass.getAvailableSeats();
            if (availableSeats > 0) {
                MemberClassRegistration memberClassRegistration = new MemberClassRegistration(memberID, classID, "active", registrationDate);
                registrationDatabase.insertRecord(memberClassRegistration);
                theClass.setAvailableSeats(availableSeats - 1);
                return true;
            } else
                System.out.println("No Available Seats in this Class");
        } else
            System.out.println("Class Doesn't Exist");
        return false;
    }

    public boolean cancelRegistration(String memberID, String classID) {
        if (registrationDatabase.contains(memberID + classID)) {
            MemberClassRegistration memberClassRegistration = registrationDatabase.getRecord(memberID + classID);
            String status = memberClassRegistration.lineRepresentation().split(",")[3];

            if (status.equalsIgnoreCase("cancelled"))
                System.out.println("Already Cancelled");
            else {
                LocalDate currentDate = LocalDate.now();
                LocalDate registrationDate = memberClassRegistration.getRegistrationDate();

                if (registrationDate.isBefore(currentDate)){
                    System.out.println("Class Registration on a late date");
                }
                else {
                    long daysBetween = diffBetweenDays(registrationDate, currentDate);
                    if (daysBetween <= 3)
                        System.out.println("Your Fees are Refundable.");
                    else
                        System.out.println("Your Fees are not Refundable");

                    memberClassRegistration.setStatus("cancelled");
                    int availableSeats = classDatabase.getRecord(classID).getAvailableSeats();
                    classDatabase.getRecord(classID).setAvailableSeats(availableSeats + 1);
                    return true;
                }
            }
        } else
            System.out.println("No Registration with this ID");
        return false;
    }

    public ArrayList<MemberClassRegistration> getListOfRegistrations(){
        return registrationDatabase.returnAllRecords();
    }

    public void logout(){
        memberDatabase.saveToFile();
        classDatabase.saveToFile();
        registrationDatabase.saveToFile();
    }

    private long diffBetweenDays(LocalDate d1, LocalDate d2) {
        long daysBetween = d1.until(d2, ChronoUnit.DAYS);
        return Math.abs(daysBetween);
    }

}
