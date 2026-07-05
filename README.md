# GitHub Automation Framework

> 🚧 **Status:** In Development

Automation framework for UI and API testing of GitHub.

The project is being developed as a portfolio project to demonstrate the skills expected from a **Middle Java SDET**.

---

# Goals

- Build a scalable automation framework
- Demonstrate clean test architecture
- Cover API and UI testing
- Show API + UI integration
- Minimize flaky tests
- Integrate with Allure
- Support parallel execution
- Run in GitHub Actions
- Run tests in Selenoid

---

# Technology Stack

| Technology | Purpose |
|------------|---------|
| Java 21 | Programming language |
| Gradle | Build tool |
| JUnit 5 | Test framework |
| REST Assured | API testing |
| Selenide | UI testing |
| Jackson | JSON serialization |
| Owner | Configuration management |
| Lombok | Boilerplate reduction |
| Faker | Test data generation |
| SLF4J | Logging |
| Allure | Reporting |
| GitHub Actions | CI |
| Selenoid | Browser execution |

---

# Planned Test Distribution

- API Tests — **60%**
- UI Tests — **30%**
- Integration Tests (API + UI) — **10%**

---

# Architecture

```
config/
infrastructure/
model/
testdata/
api/
ui/
steps/
tests/
```

Architecture follows **layer-based separation of responsibilities**.

- `config` — project configuration
- `infrastructure` — technical implementation (HTTP, Browser, Reporting, Logging)
- `model` — domain models
- `testdata` — factories, builders, constants
- `api` — GitHub REST interaction
- `ui` — Page Objects
- `steps` — orchestration layer
- `tests` — test scenarios

---

# Development Roadmap

## Infrastructure

- [ ] Gradle setup
- [ ] Configuration
- [ ] Logging
- [ ] Allure
- [ ] Retry strategy
- [ ] Selenoid

## API

- [ ] Authentication
- [ ] Repository API
- [ ] Issues API
- [ ] Specifications
- [ ] DTO
- [ ] Services

## UI

- [ ] Login
- [ ] Repository
- [ ] Dashboard
- [ ] Common Components

## Tests

- [ ] Smoke
- [ ] Regression
- [ ] API
- [ ] UI
- [ ] Integration

## CI

- [ ] GitHub Actions
- [ ] Allure Report
- [ ] Parallel Execution

---

# Principles

- KISS
- YAGNI
- SOLID
- Page Object
- Clean Architecture
- Reusable Test Infrastructure
- API-first Test Data Preparation

---

# Current Status

Project is under active development.