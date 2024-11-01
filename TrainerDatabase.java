public class TrainerDatabase extends Database<Trainer>{

    public TrainerDatabase(String filename) {
        super(filename);
    }

    @Override
    public Trainer createRecordFrom(String line){
        String[] tokens = line.split(",");

        String trainerID = tokens[0];
        String name = tokens[1];
        String mail = tokens[2];
        String speciality = tokens[3];
        String phoneNumber = tokens[4];

        Trainer trainer = new Trainer(trainerID, name, mail, speciality, phoneNumber);
        return trainer;
    }
}
