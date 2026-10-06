import MedicalCare.*;

public class Main {
    public static void main(String[] args) {
        //Создание больницы
        Hospital hospital = new Hospital();

        //Персонал
        Doctor doc1 = new Doctor(45, "Иванов Иван", "D001", "Терапевт");
        Doctor doc2 = new Doctor(50, "Смирнов Олег", "D002", "Хирург");
        Nurse nurse1 = new Nurse(30, "Петрова Анна", "N001", "Дневная");
        Nurse nurse2 = new Nurse(28, "Козлова Мария", "N002", "Ночная");

        hospital.HireStaff(doc1);
        hospital.HireStaff(doc2);
        hospital.HireStaff(nurse1);
        hospital.HireStaff(nurse2);

        //Пациенты
        Patient pat1 = new Patient(25, "Сидоров Пётр", "P001", "Простуда");
        Patient pat2 = new Patient(60, "Кузнецов Илья", "P002", "Перелом руки");

        hospital.RegisterPatient(pat1);
        hospital.RegisterPatient(pat2);

        //Назначение врачей и медсестёр
        hospital.AssignDoctor(pat1, doc1);
        hospital.AssignDoctor(pat2, doc2);

        hospital.AssignNurse(pat1, nurse1);
        hospital.AssignNurse(pat2, nurse2);

        //Назначение и получение лечения
        System.out.println("\n=== Лечение ===");
        doc1.PrescribeTreatment(pat1, "Аспирин");
        doc1.PrescribeTreatment(pat1, "Постельный режим");
        pat1.ReceiveTreatment("Аспирин");
        pat1.ReceiveTreatment("Постельный режим");

        doc2.PrescribeTreatment(pat2, "Гипс");
        pat2.ReceiveTreatment("Гипс");

        //Ассистирование
        System.out.println("\n=== Ассистирование ===");
        nurse1.AssistDoctor(doc1, pat1);
        nurse2.AssistDoctor(doc2, pat2);

        //Выводы
        System.out.println("\n=== Информация о пациентах ===");
        pat1.DisplayInfo();
        System.out.println();
        pat2.DisplayInfo();

        //Списки
        hospital.printAllPatients();
        hospital.printAllStaff();
        hospital.printPatientsOf(doc1);
        hospital.printPatientsOf(doc2);

        //Счета
        System.out.println("\n=== Счета ===");
        System.out.println("Счёт " + pat1.getName() + ": " + pat1.CalculateBill() + " руб.");
        System.out.println("Счёт " + pat2.getName() + ": " + pat2.CalculateBill() + " руб.");

        //Проверка уникальности ID
        System.out.println("\n=== Проверка уникальности ID ===");
        try {
            Patient duplicate = new Patient(30, "Дубликат", "P001", "Тест");
            hospital.RegisterPatient(duplicate);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}