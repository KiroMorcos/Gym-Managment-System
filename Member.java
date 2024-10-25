public class Member {
    String memberID, name, membershipType, email, phoneNumber, status;

    public Member(String memberID, String name, String membershipType, String email, String phoneNumber, String status) {
        this.memberID = memberID;
        this.name = name;
        this.membershipType = membershipType;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.status = status;
    }

    public String lineRepresentation(){
        return (memberID + "," + name + "," + membershipType + "," + email + "," + phoneNumber + "," + status);
    }

    public String getSearchKey(){
        return memberID;
    }
}
}
