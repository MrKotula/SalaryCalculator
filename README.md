# Salary Calculator 💰

> **Web application for calculating wages that accounts for night‑shift hours, public holidays and a configurable base rate.**\
> Supports batch calculations in a single request and a multilingual UI (EN / UA / PL / ES).
> Used by Galeria Wypieków (https://lubaszka.pl) employees to calculate the projected salary based on hours worked in different shifts, including holiday and other applicable bonuses.

---

## 💻 Live demo

The project is deployed on **AWS Elastic Beanstalk**. Try it out here:

👉 [**https://salary-calculator-demo.example.com**](http://lubaszkasalarycalculator-env.eba-nr7r32te.eu-north-1.elasticbeanstalk.com/api/lubaszka/salary/calculate)

---

## ⚙️ Tech stack

| Layer     | Technologies                                                                                              |
| --------- | ------------------------------------------------------------------------------------                      |
| Back‑end  | **Java 17**, **Spring Boot 3**, Spring MVC, Spring Data JPA (Hibernate 6), REST API, MapStruct            |
| Database  | PostgreSQL 16 • Testcontainers (integration tests) • H2 (in‑memory tests)                                 |
| DevOps    | Docker, Docker Compose, AWS Elastic Beanstalk, CodeBuild, CodePipeline GitHub Actions CI/CD               |
| Testing   | JUnit 5, Spring MockMvc, Testcontainers, Mockito                                                          |
| Front‑end | Thymeleaf 3, HTML 5, CSS 3                                                                                |
| i18n      | Spring MessageSource, LocaleResolver                                                                      |

---

## ✨ Key features

- **Single & batch calculations** – calculate one payslip or an entire list in one click.
- **Night‑shift & holiday handling** – automatically applies night‑rate multipliers and checks official holidays via a Holiday REST API.
- **Live language switcher** – change UI language without losing form data.
- **Robust validation** – friendly error messages for invalid input.
- **Extensive tests** – unit + integration tests run in isolated containers.

---

## 🐝 Public‑holiday REST API
👉 [**Used Public Holiday API**](https://date.nager.at/Api)


| Method | Endpoint                           | Description                                           |
| ------ | ---------------------------------- | ----------------------------------------------------- |
| `GET`  | `/public-holiday/{year}/{country}` | Fetch official holidays from the external Holiday API |

---

## © License

Released under the **MIT License** – free for personal and commercial use.

