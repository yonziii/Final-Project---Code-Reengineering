# Refactoring & Code Quality Improvement: Chess Engine Case Study

This repository contains the source code and documentation for our group project on analyzing and refactoring a legacy Chess Engine. The project focuses on identifying **Code Smells**, applying **Refactoring Techniques**, and adhering to **SOLID Principles** to pay off technical debt.

---

## 🧐 The Problem
The initial codebase was functional but suffered from significant **Technical Debt**. It was rigid, fragile, and difficult to extend due to poor design choices.

### Key Code Smells Identified:
1.  **OO Abusers:**
    * **Switch Statements:** Massive switch-cases in `Piece.java` determining movement logic.
    * **Primitive Obsession:** Passing coordinates as raw integers (`int x, int y`) instead of objects.
2.  **The Bloaters:**
    * **Lazy Class:** Unnecessary classes like `LeftRook`, `RightRook` that added no value.
    * **Large Class:** `ChessBoard` acted as a "God Class" with too many responsibilities.
3.  **The Couplers:**
    * **Feature Envy:** `ChessBoard` accessing internal data of `Pawn` to calculate moves.
    * **Inappropriate Intimacy:** Public fields exposing internal state to other classes.

---

## 🛠 The Solution (Refactoring Strategy)
We applied a series of refactoring techniques to transform the code into a modular and maintainable system.

| Refactoring Technique | Problem Solved | SOLID Principle Enforced |
| :--- | :--- | :--- |
| **Replace Conditional with Polymorphism** | Removed huge switch statements in `Piece.java`. | **Open/Closed Principle (OCP)** |
| **Collapse Hierarchy** | Merged redundant classes (`LeftRook`, `RightRook`) into parent (`Rook`). | **KISS (Keep It Simple, Stupid)** |
| **Move Method** | Moved validation logic from `ChessBoard` to `Pawn`. | **Single Responsibility (SRP)** |
| **Introduce Parameter Object** | Replaced `(int r, int c)` with `Position` object. | **Encapsulation** |
| **Inline Class** | Removed `Move` class and integrated its data. | **Simplicity** |

---

## 📊 Results: Before vs After

### Structural Improvement
We successfully reduced the file count in the `pieces` package from **23 to 16 files** (~30% reduction), significantly lowering the cognitive load required to navigate the project.

| Metric | Before Refactoring | After Refactoring |
| :--- | :--- | :--- |
| **File Count (Pieces)** | 23 Files (Bloated) | 7 Files (Core Logic) |
| **Movement Logic** | Rigid `switch-case` | Modular `polymorphism` |
| **Coupling** | High (God Class) | Low (Decoupled) |

> *Refer to the `Report` or `PPT` folder for visual screenshots of the directory structure comparison.*

---

## 🚀 How to Run

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/yonziii/Final-Project---Code-Reengineering.git](https://github.com/yonziii/Final-Project---Code-Reengineering.git)
    ```
2.  **Open in IDE:**
    * Open the project in **Eclipse** or **IntelliJ IDEA**.
3.  **Run the Game:**
    * Navigate to `src/com/directi/training/codesmells/refactored/Main.java`.
    * Run the `Main` class.

---

## 📂 Repository Structure
* `/src` - Contains the source code (Original "smelly" code and "Refactored" code).
* `/docs` - Contains the Final Report (PDF) and Presentation Slides.
* `README.md` - This documentation.

---

### Disclaimer
This project is for educational purposes as part of the Code Reengineering course assessment. The original "smelly" code was provided as a case study for analysis.
