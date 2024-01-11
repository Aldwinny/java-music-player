# <img src="https://drive.google.com/uc?id=1HioQItuqx9IEasGNdwUvbAKpHDskZ7yp" height="25" /> Alds' WAV Player

> This was a java application made to help me practice my skills in Java.

<img src="https://drive.google.com/uc?id=16a0HX15Wl8ORsJk8QGWyHEP796fV7wIh" alt="Application Showcase" height="250" />

## ✏️ Author

I'm Aldwin Dennis Reyes, an aspiring mobile developer. Check me out on my website [here!](https://aldwinny.github.io/)

## 💻 Technologies

Built with Java and powered by Gradle. It uses Swing and AWT to power its GUI.

## 🔎 Scope

The goal of this small project is to create a simple working music player with a GUI. I made it to practice Java programming and to apply what I know. It has:

- A working GUI
- An option to change the theme of the application
- A way to select the folder where the music is to be taken from
- Ability to play WAV files

## 🔨 Building and Running

After building with <code>gradlew fatJar</code>, it can be ran using <code>java -jar -Xdebug -agentlib:jdwp="transport=dt_socket,server=y,suspend=n,address=5000" music-player-all.jar</code> where music-player-all.jar is the output JAR file. It will attempt to find disc.gif and icon.png in the resources folder in the same root directory. Due to my lack of knowledge during the time, only a JAR file can be built. It also doesn't immediately run when clicked.
