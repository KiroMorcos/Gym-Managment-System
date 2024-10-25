import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MemberDatabase {
    private ArrayList<Member> records;
    private String filename;

    public MemberDatabase(String filename) {
        this.filename = filename + ".txt";
        this.records = new ArrayList<>();
    }

    public void readFromFile(){
        try{
            File file = new File(filename);
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()){
                String memberData = reader.nextLine();
                Member member = createRecordFrom(memberData);
                records.add(member);
            }
            reader.close();
        }catch (FileNotFoundException e){
            System.out.println("ERROR: File Not Found");
            e.printStackTrace();
        }
    }

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

    public ArrayList<Member> returnAllRecords(){
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

    public Member getRecord(String key){
        int recordIndex = getRecordIndex(key);
        if(recordIndex == -1)
            return null;
        return records.get(recordIndex);
    }

    public void insertRecord(Member record){
        if (getRecordIndex(record.getSearchKey()) == -1)
        {
            records.add(record);
            return;
        }
        System.out.println("Member Already Exists.");
    }

    public void deleteRecord(String key){
        int recordIndex = getRecordIndex(key);
        if (recordIndex != -1)
        {
            records.remove(recordIndex);
            return;
        }
        System.out.printf("Member Doesn't Exist");
    }

    public void saveToFile(){
        try {
            FileWriter writer = new FileWriter(filename);

            int recordsSize = records.size();
            for (int i = 0; i < recordsSize; i++)
            {
                Member member = records.get(i);
                writer.write(member.lineRepresentation() + "\n");
            }
            writer.close();
        }catch (IOException e){
            System.out.printf("ERROR: Cannot Write to File");
            e.printStackTrace();
        }
    }
}
