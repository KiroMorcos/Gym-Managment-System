import java.time.LocalDate;

public class MemberClassRegistrationDatabase extends Database<MemberClassRegistration> {

    public MemberClassRegistrationDatabase(String filename) {
        super(filename);
    }

    @Override
    public MemberClassRegistration createRecordFrom(String line){
        String[] tokens = line.split(",");

        String memberID = tokens[0];
        String classID = tokens[1];
        LocalDate registrationDate = LocalDate.parse(tokens[2]);
        String status = tokens[3];

        MemberClassRegistration memberClassRegistration = new MemberClassRegistration(memberID, classID, status, registrationDate);
        return memberClassRegistration;
    }

    @Override
    public boolean insertRecord(MemberClassRegistration record){
        if (getRecordIndex(record.getSearchKey()) == -1)
        {
            records.add(record);
            return true;
        }else {
            String[] tokens = record.lineRepresentation().split(",");
            if (tokens[3].equalsIgnoreCase("cancelled"))
            {
                record.setStatus("Active");
                return true;
            }
            return false;
        }
    }

}
