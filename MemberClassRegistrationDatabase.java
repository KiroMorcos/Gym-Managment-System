import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

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
                record.setRegistrationStatus("Active");
                return true;
            }
            return false;
        }
    }

}
