# Lessons Learned

- care when specifying `spring.jpa.hibernate.ddl-auto=update` configuration for JPA in `application.properties` b/c in
  practice for **database administration (DBA)** to review migration scripts when databases change especially when your
  database is a shared dependency across multiple services
- 