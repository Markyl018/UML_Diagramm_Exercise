package MedicalCare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Hospital {
    // Основные коллекции (агрегация из диаграммы)
    private List<Patient> patients;
    private List<Person> staff;

    // Взаимосвязи (пациент → врач, пациент → медсёстры)
    private Map<Patient, Doctor> assignedDoctors;
    private Map<Patient, List<Nurse>> assignedNurses;

    // Уникальность ID
    private Set<String> usedIds;

    public Hospital() {
        this.patients = new ArrayList<>();
        this.staff = new ArrayList<>();
        this.assignedDoctors = new HashMap<>();
        this.assignedNurses = new HashMap<>();
        this.usedIds = new HashSet<>();
    }

    //Регистрация пациента
    public void RegisterPatient(Patient p) {
        if (p == null) {
            throw new IllegalArgumentException("Пациент не может быть null");
        }
        if (!usedIds.add(p.GetId())) {
            throw new IllegalArgumentException("ID уже занят: " + p.GetId());
        }
        patients.add(p);
        System.out.println("Зарегистрирован пациент: " + p.getName()
                + " (ID: " + p.GetId() + ")");
    }

    //Приём сотрудника в штат
    public void HireStaff(Person person) {
        if (person == null) {
            throw new IllegalArgumentException("Сотрудник не может быть null");
        }
        if (person instanceof IIdentifiable identifiable) {
            if (!usedIds.add(identifiable.GetId())) {
                throw new IllegalArgumentException("ID уже занят: "
                        + identifiable.GetId());
            }
        }
        staff.add(person);
        System.out.println("Принят в штат: " + person.getName());
    }

    //Назначение врача пациенту
    public void AssignDoctor(Patient p, Doctor d) {
        if (p == null || d == null) {
            throw new IllegalArgumentException("Пациент и врач не могут быть null");
        }
        if (!patients.contains(p)) {
            System.out.println("Пациент не зарегистрирован в больнице!");
            return;
        }
        if (!staff.contains(d)) {
            System.out.println("Доктор не в штате больницы!");
            return;
        }

        assignedDoctors.put(p, d);
        d.addPatient(p);

        System.out.println("Пациенту " + p.getName()
                + " назначен доктор " + d.getName());
    }

    //Назначение медсестры пациенту
    public void AssignNurse(Patient p, Nurse n) {
        if (p == null || n == null) {
            throw new IllegalArgumentException("Пациент и медсестра не могут быть null");
        }
        if (!patients.contains(p)) {
            System.out.println("Пациент не зарегистрирован в больнице!");
            return;
        }
        if (!staff.contains(n)) {
            System.out.println("Медсестра не в штате больницы!");
            return;
        }

        assignedNurses.computeIfAbsent(p, k -> new ArrayList<>()).add(n);
        System.out.println("Пациенту " + p.getName()
                + " назначена медсестра " + n.getName());
    }

    //Получение связей
    public Doctor getDoctorFor(Patient p) {
        return assignedDoctors.get(p);
    }

    public List<Nurse> getNursesFor(Patient p) {
        return assignedNurses.getOrDefault(p, new ArrayList<>());
    }

    //Геттеры
    public List<Patient> getPatients() {
        return patients;
    }

    public List<Person> getStaff() {
        return staff;
    }

    //Удобные выводы
    public void printAllPatients() {
        System.out.println("\n=== Все пациенты больницы ===");
        if (patients.isEmpty()) {
            System.out.println("(нет пациентов)");
            return;
        }
        for (Patient p : patients) {
            p.DisplayInfo();
            Doctor doc = getDoctorFor(p);
            if (doc != null) {
                System.out.println("  Лечащий врач: " + doc.getName());
            }
            List<Nurse> nurses = getNursesFor(p);
            if (!nurses.isEmpty()) {
                System.out.print("  Медсёстры: ");
                for (Nurse n : nurses) {
                    System.out.print(n.getName() + " ");
                }
                System.out.println();
            }
            System.out.println("---");
        }
    }

    public void printAllStaff() {
        System.out.println("\n=== Весь персонал ===");
        for (Person person : staff) {
            person.DisplayInfo();
        }
    }

    //Список пациентов конкретного врача
    public void printPatientsOf(Doctor d) {
        System.out.println("\n=== Пациенты доктора " + d.getName() + " ===");
        if (d.getMyPatients().isEmpty()) {
            System.out.println("(нет пациентов)");
            return;
        }
        for (Patient p : d.getMyPatients()) {
            p.DisplayInfo();
            System.out.println("---");
        }
    }
}