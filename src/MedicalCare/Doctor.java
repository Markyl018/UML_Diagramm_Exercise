package MedicalCare;

import java.util.ArrayList;
import java.util.List;

public class Doctor extends Person implements IIdentifiable {
    private String id;
    private String specialization;
    private List<Patient> myPatients;

    public Doctor() {
        super();
        this.myPatients = new ArrayList<>();
    }

    public Doctor(int age, String name, String id, String specialization) {
        super(age, name);
        setId(id);
        this.specialization = specialization;
        this.myPatients = new ArrayList<>();
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
    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        if (specialization == null || specialization.isBlank()) {
            throw new IllegalArgumentException("Специализация не может быть пустой");
        }
        this.specialization = specialization;
    }

    public List<Patient> getMyPatients() {
        return myPatients;
    }

    //Работа с пациентами
    public void addPatient(Patient p) {
        if (p == null) return;
        if (!myPatients.contains(p)) {
            myPatients.add(p);
        }
    }

    //Назначение лечения
    public void PrescribeTreatment(Patient p, String treatment) {
        if (p == null) {
            throw new IllegalArgumentException("Пациент не может быть null");
        }
        p.PrescribeTreatment(treatment);
        addPatient(p);
        System.out.println("Доктор " + getName()
                + " назначил пациенту " + p.getName()
                + ": " + treatment);
    }

    //DisplayInfo
    @Override
    public void DisplayInfo() {
        System.out.println("Доктор: " + getName()
                + ", возраст: " + getAge()
                + ", ID: " + id
                + ", специализация: " + specialization
                + ", пациентов: " + myPatients.size());
    }
}