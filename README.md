# Student and Course Management Application

## 1. Entity Relationship Design

The application manages two primary entities: **Student** and **Course**. 
The relationship between these entities is **Many-to-One**. Many students can enroll in a single course.

*   **Course Entity:** Contains an `id` (Primary Key), a `name` (e.g., Java Programming), and a unique `code` (e.g., CS101).
*   **Student Entity:** Contains an `id` (Primary Key), a `name`, a unique `email`, and a `Course` reference. 
*   **JPA Mapping:** The relationship is defined in the `Student` entity using the `@ManyToOne` annotation, mapped via a foreign key column named `course_id`.

---

## 2. Implementation Details for Each Operation

### A. Populate Database
To populate the database upon startup, a `DataLoader` component was implemented using Spring Boot's `CommandLineRunner` interface. 
*   Created 10 sample courses.
*   Created 10 sample students linked to the sample courses.

### B. Create Operation (Add Student)
The Create operation allows users to add a new student through a JSP form.
*   **Exception Handling:** If a user attempts to add a student with an email that already exists, the database throws an integrity constraint violation. The controller catches `DataIntegrityViolationException` and binds a user-friendly error message to the model without crashing the application.

### C. Read Operation (Display Entities)
The Read operation fetches data from the database and binds it to the `list.jsp` view.
*   **Custom JPQL Query:** To demonstrate an inner join, a custom query was implemented in the `StudentRepository` to fetch students along with their associated course data.

### D. Update Operation (Edit Student)
The Update operation fetches an existing student by ID and populates a form for editing. The controller uses Spring Data JPA's `save()` method, which intelligently performs an `UPDATE` rather than an `INSERT` because the student object already possesses a primary key ID.

---

## 3. Challenges Faced and Solutions

1.  **Challenge:** Serving JSP files reliably from an embedded Tomcat server during IDE execution.
    *   **Solution:** Spring Boot can sometimes fail to map `/WEB-INF/` directories correctly when run directly from modern IDEs (like VS Code or Eclipse). To overcome this, `<packaging>war</packaging>` was added to the `pom.xml`, and the JSP views were relocated to `src/main/resources/META-INF/resources/WEB-INF/views`. The Servlet spec guarantees that `META-INF/resources` is treated as the root of the web application, ensuring 100% reliable view resolution across all environments.
2.  **Challenge:** Handling duplicate email addresses gracefully.
    *   **Solution:** Initially, submitting a duplicate email resulted in a generic Whitelabel 500 Error page. This was resolved by implementing a `try-catch` block in the controller specifically targeting `DataIntegrityViolationException`, allowing the application to reload the form with a styled, informative error banner.

---

## Running the Application Locally
1. Clone this repository.
2. Open terminal and run `mvn spring-boot:run`.
3. Open a web browser and go to `http://localhost:8080/students`.
