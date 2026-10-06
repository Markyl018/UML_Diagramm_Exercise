package MedicalCare;

public class Nurse extends Person implements IIdentifiable {
    private String id;
    private String shift;

    public Nurse() {
        super();
    }

    public Nurse(int age, String name, String id, String shift) {
        super(age, name);
        setId(id);
        this.shift = shift;
    }

    //IIdentifiable
    @Override
    public String GetId() {
        return id;
    }

    public void setId(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID не может быть пустым");
        }
        this.id = id.trim();
    }

    //Геттеры/сеттеры
    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        if (shift == null || shift.isBlank()) {
            throw new IllegalArgumentException("Смена не может быть пустой");
        }
        this.shift = shift;
    }

    //Ассистирование
    public void AssistDoctor(Doctor d, Patient p) {
        if (d == null || p == null) {
            throw new IllegalArgumentException("Врач и пациент не могут быть null");
        }
        System.out.println("Медсестра " + getName()
                + " ассистирует доктору " + d.getName()
                + " для пациента " + p.getName());
    }

    //DisplayInfo
    @Override
    public void DisplayInfo() {
        System.out.println("Медсестра: " + getName()
                + ", возраст: " + getAge()
                + ", ID: " + id
                + ", смена: " + shift);
    }
}