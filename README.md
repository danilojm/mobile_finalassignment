
# Mobile and Pervasive Computing – Assignment 2

### Contributors:
- **Anderson Victoral** - Student ID: 200558597  
- **Carolina Arce** - Student ID: 200556714  
- **Danilo Mendes** - Student ID: 200549002  
- **Regina Fruet** - Student ID: 200552682  

**Professor:** Youssef Rizk  
**Date:** December 2024  

---

## Overview of the Application
This application delivers an interactive experience focused on seamless user authentication and customizable activity difficulty levels. Users can sign in through various methods and navigate through an intuitive interface to select their desired level of interaction.

### Features:
1. **Authentication**  
   - **Google Sign-In**: Quick and secure access using Google accounts.  
   - **Facebook Login**: An alternative login method using Facebook accounts.  
   - **Email/Password**: A traditional login option for convenience.  

2. **Difficulty Levels**  
   - Four levels of activity: **Easy**, **Medium**, **Intermediate**, and **Advanced**.  
   - Dynamic content tailored to the selected difficulty level for a personalized experience.

3. **Smooth Navigation**  
   - Guided progression from login to difficulty selection, and eventually to content delivery.  
   - User-friendly design ensures a seamless and engaging user journey.

---

## Technical Details

### Authentication
The application utilizes **Firebase Authentication** for secure and efficient user management:
- Google, Facebook, and Email/Password authentication methods are triggered using SDK calls.
- Firebase ensures smooth operation and robust validation during the login process.

### Navigation
The app’s navigation flow is designed to maintain a logical progression:
- **Login Screen** → **Difficulty Selection Screen** → **Content Screen**.  
- OnClick listeners and post-authentication validation manage transitions effortlessly.

### Difficulty Level
- Users can select one of four difficulty levels on the **Difficulties Screen**.
- Content dynamically adjusts based on the selected level, minimizing code duplication and enhancing scalability.

---

## Advantages of the Structure

1. **Scalability**  
   - Adding new difficulty levels requires only updating corresponding resources (text, images) without modifying the core logic.

2. **Efficiency**  
   - A single activity handles all levels, reducing code redundancy and simplifying maintenance.

3. **Enhanced User Experience**  
   - Consistent and dynamic interface ensures smooth transitions and a satisfying user journey.

---

## How to Set Up and Run

### Set Up Firebase
1. Configure the Firebase project to enable:
   - **Google Sign-In**
   - **Facebook Login**
   - **Email/Password authentication**

### Run the Application
1. Clone the project repository to your local development environment:
   ```bash
   git clone <repository-url>
   cd <repository-folder>
   ```
2. Launch the app on your emulator or device to explore its features.

---

## Acknowledgements
Special thanks to **Professor Youssef Rizk** for guidance throughout the development process.

---

**Submitted by:**  
Anderson Victoral, Carolina Arce, Danilo Mendes, Regina Fruet  
