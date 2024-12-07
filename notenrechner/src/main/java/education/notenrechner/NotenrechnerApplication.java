package education.notenrechner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import education.notenrechner.model.Grade;
import education.notenrechner.service.GradeService;

@SpringBootApplication
public class NotenrechnerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotenrechnerApplication.class, args);
	}

	// CommandLineRunner wird ausgeführt, wenn die Anwendung startet
    @Bean
    CommandLineRunner init(GradeService gradeService) {
        return args -> {
            // Beispielnoten hinzufügen
            gradeService.addGrade(new Grade("Mathematik", 1.3, 5));
            gradeService.addGrade(new Grade("Englisch", 2.0, 3));
            gradeService.addGrade(new Grade("Informatik", 1.7, 4));

            // Noten anzeigen
            System.out.println("Alle Noten:");
            gradeService.getAllGrades().forEach(grade -> {
                System.out.printf("Fach: %s, Note: %.1f, Credit Points: %d%n",
                        grade.getSubject(), grade.getGrade(), grade.getCreditPoints());
            });

            // Durchschnitt berechnen und ausgeben
            System.out.printf("Notendurchschnitt: %.2f%n", gradeService.calculateAverage());

            // Eine Note löschen
            gradeService.deleteGrade("Englisch");
            System.out.println("Nach dem Löschen von Englisch:");
            gradeService.getAllGrades().forEach(grade -> {
                System.out.printf("Fach: %s, Note: %.1f, Credit Points: %d%n",
                        grade.getSubject(), grade.getGrade(), grade.getCreditPoints());
            });

            // Alle Noten zurücksetzen
            gradeService.resetGrades();
            System.out.println("Alle Noten zurückgesetzt.");
            System.out.printf("Aktuelle Anzahl der Noten: %d%n", gradeService.getAllGrades().size());
        };
    }

}
