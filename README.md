# NoteApplication
![GitHub last commit](https://img.shields.io/github/last-commit/braveheart3208/NoteApplication)
![Maintenance](https://img.shields.io/maintenance/no/2030)

Note Application is an Android Application written in Kotlin. In this application, Clean Architecture and MVVM design pattern are applied.

**MVVM design pattern and benefits**

MVVM is an alternative design pattern to traditional design patterns such as MVP, MVC in Android development.
Unlike MVP or MVC, MVVM allows 1 to many relationships between the View and ViewModel.
This paradigm in conjunction with built-in support for Architecture components and HILT allows us to
implement observable patterns utilizing Flow to streamline the development process.
It further reduces the coupling between the View and its companion in Android development (in this case ViewModel)

**Clean architecture and benefits**

The Clean Architecture allows us to decouple business logic from Android framework. The use of domain usecases (in this case) means each business logic is isolated from view.

## Installation

Download the GitHub Project [NoteApp](https://github.com/braveheart3208/NoteApplication). And open from the latest Android Studio version (which can be downloaded [here](https://developer.android.com/studio))

## Usage
Run the application from Android Studio in order to use the app.

## Library Used
- Jetpack Compose
- Dagger-Hilt Dependencies Injection
- Room Local Database
- Coroutines

## Contributing
Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.

Please make sure to update tests as appropriate.

## Acknowledgement
Much appreciate Billy Hlaing for encouraging me to build portfolio applications, and to dive deep into learning.
And thanks to Phillip Lackner, who has a great walk through of the process and explanation.

## License
None