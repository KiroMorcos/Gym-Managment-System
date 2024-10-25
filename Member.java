public class Member {
    String memberID, name, membershipType, mail, phoneNumber, status;

    public Member(String memberID, String name, String membershipType, String mail, String phoneNumber, String status) {
        this.memberID = memberID;
        this.name = name;
        this.membershipType = membershipType;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
        this.status = status;
    }

    public String lineRepresentation(){
        return (memberID + "," + name + "," + membershipType + "," + mail + "," + phoneNumber + "," + status);
    }

    public String getSearchKey(){
        return memberID;
    }
}
