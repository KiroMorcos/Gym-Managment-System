public class ClassDatabase extends Database<Class> {

    public ClassDatabase(String filename) {
        super(filename);
    }

    @Override
    public Class createRecordFrom(String line){
        String[] tokens = line.split(",");

        String classID = tokens[0];
        String className = tokens[1];
        String trainerID = tokens[2];
        int duration =Integer.parseInt(tokens[3]);
        int availableSeats = Integer.parseInt(tokens[4]);

        Class theClass = new Class(classID, className, trainerID, duration, availableSeats);
        return theClass;
    }
}
