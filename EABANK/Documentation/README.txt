# PROJECT OVERVIEW

MKA EABANK SYSTEM
The MKA EABANK SYSTEM is a mobile-based digital wallet application developed in Java Swing framework. This system simulates real-world e-wallet functionalities such as account registration, login with OTP verification, balance checking, money transfers, cash in/out operations, and currency conversion.

The project is structured using Object-Oriented Programming (OOP) principles, including encapsulation, inheritance, polymorphism, and the use of abstract classes and interfaces to promote modularity and reusability. A design pattern has been implemented such as the Strategy Method Design Pattern to manage transaction types flexibly and extend system features with minimal code duplication.

The system handles simulated transactions and provides visually engaging feedback through styled graphical components and digital receipts. It ensures an intuitive and user-friendly experience while also demonstrating robust backend logic for managing users and transactions.

# Project Specification included:
1. User Login/Registration with OTP Verification
2. Secure PIN Handling with simple hash-based storage
3. Cash In with currency conversion and credit/debit card details
4. Cash Out with outlet selection (e.g., 7-Eleven, Palawan Pawnshop)
5. Transfer Funds between registered users
6. Check Balance with receipt
7. Transaction History in a scrollable dialog
8. Currency Exchange from PHP to USD or JPY

# System Design Overview
1. Classes
- EABANKSYSTEM: Main class handling GUI flow and user interaction
- User: Stores user details, balance, and transaction history
- Transaction: Stores transaction details such as type, amount, reference number, timestamp
- TransactionAction & CashInTransaction: Handles polymorphic transaction behaviors

2. Panels & Layouts
- Custom BackgroundPanel class with background image (e.g bg3.jpg, logout.jpg, etc.)
- Swing components dynamically replaced using frame.setContentPane(...)
- Layouts managed with absolute positioning for precise GUI design

3. Transaction Types
- Cash In, Cash Out, Transfer, Check Balance, and Currency Exchange
- All generate styled receipts with "receipt.jpg" as the background

4. Security
- PIN is stored using a hash (hashCode → toHexString)
- OTP verification required on registration

# How to Run the Project
1. Prerequisites
Java Development Kit (JDK) 8 or later
Java-compatible IDE (e.g., VS Code with Java Extension Pack)

2. Project Structure
This shows you how your project files are organized, and where each important file should be placed so the Java program runs properly with all its visual elements.

PROJECT-folder/
├── EABANKSYSTEM.java          # Your main Java code (the entire system is in this file)
├── bg3.jpg                    # Background image shown on every screen
├── login.jpg                  # Icon for the Login button
├── register.jpg               # Icon for the Register button
├── cashin.jpg, cashout.jpG    # More icons used for button visuals
├── logout.jpg, transfer.jpg 
├── balance.jpg, exit.jpg 
├── receipt.jpg, balance.jpg             

# Compile & Run
Use Command Line:
-javac EABANKSYSTEM.java
-java EABANKSYSTEM

Or, using an IDE:
1. Create a new Java project.
2. Add the EABANKSYSTEM.java file to src.
3. Place the required image files in the project root or classpath.
4. Run EABANKSYSTEM.main().

# Step-by-Step Usage
1. Run the app.
2. Click Register and fill out the form.
3. Enter the OTP shown in the popup.
3. After successful registration, go back and click Login.
5. Use specification like Cash In, Cash Out, Transfer, and Money Exchanger.
6. Use Transaction History or Check Balance to review your account.