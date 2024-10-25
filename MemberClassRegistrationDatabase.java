import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class MemberClassRegistrationDatabase {
    private ArrayList<MemberClassRegistration> records;
    private String filename;

    public MemberClassRegistrationDatabase(String filename) {
        this.filename = filename + ".txt";
        this.records = new ArrayList<>();
    }

    public void readFromFile(){
        try{
            File file = new File(filename);
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()){
                String memberClassRegistrationData = reader.nextLine();
                MemberClassRegistration memberClassRegistration = createRecordFrom(memberClassRegistrationData);
                records.add(memberClassRegistration);
            }
            reader.close();
        }catch (FileNotFoundException e){
            System.out.println("ERROR: File Not Found");
            e.printStackTrace();
        }
    }

    public MemberClassRegistration createRecordFrom(String line){
        String[] tokens = line.split(",");

        String memberID = tokens[0];
        String classID = tokens[1];
        LocalDate registrationDate = LocalDate.parse(tokens[2]);
        String status = tokens[3];

        MemberClassRegistration memberClassRegistration = new MemberClassRegistration(memberID, classID, status, registrationDate);
        return memberClassRegistration;
    }

    public ArrayList<MemberClassRegistration> returnAllRecords(){
        return records;
    }

    public int getRecordIndex(String key){
        int recordsSize = records.size();
        for (int i = 0; i < recordsSize; i++){
            if (records.get(i).getSearchKey().equalsIgnoreCase(key))
                return i;
        }
        return -1;
    }

    public boolean contains(String key){
        return (getRecordIndex(key) != -1);
    }

    public MemberClassRegistration getRecord(String key){
        int recordIndex = getRecordIndex(key);
        if(recordIndex == -1)
            return null;
        return records.get(recordIndex);
    }

    public void insertRecord(MemberClassRegistration record){
        if (getRecordIndex(record.getSearchKey()) == -1)
        {
            records.add(record);
            return;
        }
        System.out.println("MemberClassRegistration Already Exists.");
    }

    public void deleteRecord(String key){
        int recordIndex = getRecordIndex(key);
        if (recordIndex != -1)
        {
            records.remove(recordIndex);
            return;
        }
        System.out.printf("MemberClassRegistration Doesn't Exist");
    }

    public void saveToFile(){
        try {
            FileWriter writer = new FileWriter(filename);

            int recordsSize = records.size();
            for (int i = 0; i < recordsSize; i++)
            {
                MemberClassRegistration memberClassRegistration = records.get(i);
                writer.write(memberClassRegistration.lineRepresentation() + "\n");
            }
            writer.close();
        }catch (IOException e){
            System.out.printf("ERROR: Cannot Write to File");
            e.printStackTrace();
        }
    }
}
