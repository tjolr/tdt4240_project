<!-- @format -->

# Pandemic Survival

## Trailer

https://www.youtube.com/watch?v=2jFqLApuKFA

## Initialization: installing, compiling and running

_Pandemic Survival_ is only meant to run on android devices or emulators. Below are the two different tested ways to run the game.

### To play the game on your android device

1. Download _Pandemic Survival for Android.apk_ from the index of this repository to your android device.
2. Install the game, verifying that you are sure if prompted.
3. You can now launch the game on your android device!

### To play the game from Android Studio

1. Make sure you have an updated version of Android Studio.
2. Import the project locally by cloning our GitHub repository.
3. Press the green run-button on the top bar to run the project.
4. Choose whether to run the game on an android emulator or a connected android device.

## The Application
The application is split into two parts, the 'Menu' and the 'Game'. They are described below.

### Main menu

From the main menu, it is possible to navigate to the 'Settings' page and change the theme of the menu between Dark and Light. The 'Play Now'-button allows the user to choose a name, and the user is then lead to an overview of active games. From there, it is possible to join other games or host your own. If you are the host, you can at any moment choose to start the game. If you are joining someone elses lobby, you will have to wait for them to start it.

### Playing the game

Movement is handled with the left joystick, while shooting is handled with the right joystick. Shooting zombies causes them to disappear and your score to go up. If the zombies get to you, they will start doing damage to you. Once your health reaches zero you are sent to the score screen. An overview of the health and score of the other players in the lobby is displayed while playing, and will also be displayed on the score screen.

Getting the highest score means you are the best!

### Known bugs

- When a game is finished, trying to create a new game without first resetting the application will lead to the application crashing.
- In certain cases, zombies may suddenly appear within the screen, even though they are supposed to spawn outside of it.

## File Structure

In the big green image below you can see the file structure of Pandemic Survival's core folder. On start-up, the main_menu-screen is initialized. The screens/-folder handles the different screens in the application, the ecs/-folder contains the internal logic of the game and the firebase/-folder handles all communication with the back-end. Assets are of course contained in the assets/-folder.

![logical_view_overview](https://user-images.githubusercontent.com/43404631/116101627-45c52380-a6ae-11eb-9bbd-b0d77f820096.png)
