import java.util.ArrayList;

public class AdminRole {
    private TrainerDatabase database;

    public AdminRole() {
        database = new TrainerDatabase("Trainer");
    }

    public void addTrainer(String trainerID,String name,String mail,String speciality,String phoneNumber){
        Trainer trainer = new Trainer(trainerID, name, mail, speciality, phoneNumber);
        database.insertRecord(trainer);
    }

    public ArrayList<Trainer> getListOfTrainers(){
        return database.returnAllRecords();
    }

    public void removeTrainer(String key){
        database.deleteRecord(key);
    }

    public void logout(){
        database.saveToFile();
    }
}
