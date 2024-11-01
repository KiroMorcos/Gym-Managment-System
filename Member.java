public class Member extends Human{
    private String  membershipType, status;

    public Member(String memberID, String name, String membershipType, String mail, String phoneNumber, String status) {
        super(memberID, name, mail, phoneNumber);
        this.membershipType = membershipType;
        this.status = status;
    }

    @Override
    public String lineRepresentation(){
        return (id + "," + name + "," + membershipType + "," + mail + "," + phoneNumber + "," + status);
    }

    @Override
    public String getSearchKey(){
        return id;
    }
}
