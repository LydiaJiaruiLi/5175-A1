# 5175-A1
This is the part 2 of the first Assignment for course CSI 5175
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
The objective of this part of the assignment is to gain experience in the design and implementation of an Android application that interacts with the user. You are allowed to
develop the application using either Kotlin of Java (no additional tools). Your team is tasked with developing a gamming application. The user of your application should be able to:
1. See an opening page that is a welcome page with the University of Ottawa logo and a welcome message: “Welcome to X’s game” where X represents the team members names. The screen will also have an OK button to end the welcome page.

2. Once the user hits ok, he/she is introduced to a new screen with three buttons ( you can replace the screen with a menu if you prefer):
    - Game A: a quiz game ( question and multiple choice game),
    - Game B: A count the sheep randomly appearing on the screen in a minute.
    - Show score ( shows the score of the last game played for Games A and B)
    -Back to Welcome (goes to the first screen with the buttons if you are not using a menu).

3. Implement Game A: Randomly generated 5 multiple choice questions displayed one at a
time (about movies, news, etc) with an Ok button.
  a. The user scores 1 point for each correct question and 0 for wrong answer.
  b. Each new question has a different screen color.
  c. The user sees the answers after the game is over ( with the wrong answers highlighted).

4. Implement Game B: it starts with a screen with a start button, when hit the user sees a randomly appearing then disappearing animal (stays on the screen for a random time between 1-3 sec) on different places of the screen. After 1 minute, a new screen appears to ask the user how many animals did he see and an ok button. The score is calculated as follows: (number of actual animals – number of reported animals) out of number of actual animals. A score screen is displayed after the game.
