public class MemberDatabase extends Database<Member>{
    public MemberDatabase(String filename) {
        super(filename);
    }

    @Override
    public Member createRecordFrom(String line){
        String[] tokens = line.split(",");

        String memberID = tokens[0];
        String name = tokens[1];
        String memberShipType = tokens[2];
        String mail = tokens[3];
        String phoneNumber = tokens[4];
        String status = tokens[5];

        Member member = new Member(memberID, name, memberShipType, mail, phoneNumber, status);

        return member;
    }
}
