package MedicalCare;

import java.util.ArrayList;
import java.util.List;

public class Patient extends Person implements IIdentifiable, ITreatable, IBillable {
    private String id;
    private String diagnosis;

    // Назначенное лечение (врач назначает)
    private List<String> prescribedTreatments;
    // Полученное лечение (пациент реально прошёл)
    private List<String> receivedTreatments;

    // Конструктор по умолчанию
    public Patient() {
        super();
        this.prescribedTreatments = new ArrayList<>();
        this.receivedTreatments = new ArrayList<>();
    }

    // Параметризованный конструктор
    public Patient(int age, String name, String id, String diagnosis) {
        super(age, name);
        setId(id);
        setDiagnosis(diagnosis);
        this.prescribedTreatments = new ArrayList<>();
        this.receivedTreatments = new ArrayList<>();
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

    //Геттеры/сеттер
    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        if (diagnosis == null || diagnosis.isBlank()) {
            throw new IllegalArgumentException("Диагноз не может быть пустым");
        }
        this.diagnosis = diagnosis.trim();
    }

    public List<String> getPrescribedTreatments() {
        return prescribedTreatments;
    }

    public List<String> getReceivedTreatments() {
        return receivedTreatments;
    }

    //ITreatable
    @Override
    public void ReceiveTreatment(String treatment) {
        if (treatment == null || treatment.isBlank()) {
            throw new IllegalArgumentException("Название лечения не может быть пустым");
        }
        receivedTreatments.add(treatment.trim());
        System.out.println("Пациент " + getName()
                + " получил лечение: " + treatment);
    }

    // Назначение лечения (вызывает врач)
    public void PrescribeTreatment(String treatment) {
        if (treatment == null || treatment.isBlank()) {
            throw new IllegalArgumentException("Название лечения не может быть пустым");
        }
        prescribedTreatments.add(treatment.trim());
        System.out.println("Пациенту " + getName()
                + " назначено лечение: " + treatment);
    }

    //IBillable
    @Override
    public double CalculateBill() {
        // Считаем только фактически полученное лечение
        return receivedTreatments.size() * 100.0;
    }

    //DisplayInfo
    @Override
    public void DisplayInfo() {
        System.out.println("Пациент: " + getName()
                + ", возраст: " + getAge()
                + ", ID: " + id
                + ", диагноз: " + diagnosis);
        System.out.println("  Назначено: " + prescribedTreatments);
        System.out.println("  Получено:  " + receivedTreatments);
        System.out.println("  Счёт:      " + CalculateBill() + " руб.");
    }
}