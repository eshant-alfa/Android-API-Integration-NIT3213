# Android API Integration - NIT3213 Final Project

## 📱 Project Overview

A comprehensive Android application demonstrating modern Android development practices, API integration, and clean architecture patterns. This project showcases the implementation of a complete mobile application with user authentication, data management, and dynamic content display.

## ✨ Key Features

- **🔐 Secure Authentication**: JWT-based login system with API integration
- **📊 Dynamic Dashboard**: Real-time entity management with search and filtering
- **🎨 Modern UI/UX**: Material Design 3 components with dark/light theme support
- **🏗️ Clean Architecture**: MVVM pattern with proper separation of concerns
- **💉 Dependency Injection**: Hilt integration for better testability and maintainability
- **🌐 API Integration**: RESTful API communication using Retrofit and OkHttp
- **📱 Responsive Design**: Optimized for various screen sizes and orientations
- **🧪 Comprehensive Testing**: Unit tests for ViewModels and Repository layer

## 🛠️ Technology Stack

| Component | Technology |
|-----------|------------|
| **Language** | Kotlin |
| **Architecture** | MVVM (Model-View-ViewModel) |
| **UI Framework** | Android XML Layouts with Material Design 3 |
| **Networking** | Retrofit 2.x + OkHttp |
| **Dependency Injection** | Hilt |
| **Image Loading** | Coil |
| **Build System** | Gradle with Kotlin DSL |
| **Testing** | JUnit 4 + Mockito |
| **Minimum SDK** | API 24 (Android 7.0) |
| **Target SDK** | API 34 (Android 14) |

## 📁 Project Structure

```
Android-API-Integration-NIT3213/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/assignmentlast/
│   │   │   │   ├── data/
│   │   │   │   │   ├── api/
│   │   │   │   │   │   └── ApiService.kt              # API service interfaces
│   │   │   │   │   ├── models/
│   │   │   │   │   │   ├── DashboardResponse.kt       # Dashboard API response model
│   │   │   │   │   │   ├── Entity.kt                  # Entity data model
│   │   │   │   │   │   ├── LoginRequest.kt            # Login request model
│   │   │   │   │   │   └── LoginResponse.kt           # Login response model
│   │   │   │   │   └── repository/
│   │   │   │   │       └── AppRepository.kt           # Repository interface
│   │   │   │   ├── di/
│   │   │   │   │   └── AppModule.kt                   # Hilt dependency injection module
│   │   │   │   ├── ui/
│   │   │   │   │   ├── dashboard/
│   │   │   │   │   │   ├── DashboardActivity.kt       # Dashboard screen activity
│   │   │   │   │   │   ├── DashboardViewModel.kt      # Dashboard business logic
│   │   │   │   │   │   └── EntityAdapter.kt           # RecyclerView adapter
│   │   │   │   │   ├── details/
│   │   │   │   │   │   └── DetailsActivity.kt         # Entity details screen
│   │   │   │   │   └── login/
│   │   │   │   │       ├── LoginActivity.kt           # Login screen activity
│   │   │   │   │       └── LoginViewModel.kt          # Login business logic
│   │   │   │   ├── MainActivity.kt                    # Main application entry point
│   │   │   │   └── MyApplication.kt                   # Application class with Hilt
│   │   │   ├── res/
│   │   │   │   ├── drawable/                          # Vector drawables and backgrounds
│   │   │   │   ├── layout/                            # XML layout files
│   │   │   │   ├── mipmap/                            # App icons
│   │   │   │   ├── values/                            # Colors, strings, themes
│   │   │   │   └── xml/                               # Backup and data extraction rules
│   │   │   └── AndroidManifest.xml                    # App manifest
│   │   └── test/
│   │       └── java/com/example/assignmentlast/
│   │           ├── AppRepositoryImplTest.kt           # Repository unit tests
│   │           ├── DashboardViewModelTest.kt          # Dashboard ViewModel tests
│   │           └── LoginViewModelTest.kt              # Login ViewModel tests
│   ├── build.gradle                                   # App-level build configuration
│   └── proguard-rules.pro                             # ProGuard rules
├── gradle/
│   └── libs.versions.toml                             # Dependency version catalog
├── build.gradle                                       # Project-level build configuration
├── gradle.properties                                  # Gradle properties
├── settings.gradle                                    # Project settings
├── gradlew                                            # Gradle wrapper script (Unix)
├── gradlew.bat                                        # Gradle wrapper script (Windows)
└── README.md                                          # This file
```

## 🚀 Getting Started

### Prerequisites

Before running this project, ensure you have the following installed:

- **Android Studio Hedgehog (2023.1.1)** or later
- **Android SDK 34** or later
- **Java 17** or later
- **Git** for version control

### Installation & Setup

1. **Clone the Repository**
   ```bash
   git clone https://github.com/eshant-alfa/Android-API-Integration-NIT3213.git
   cd Android-API-Integration-NIT3213
   ```

2. **Open in Android Studio**
   - Launch Android Studio
   - Select "Open an existing Android Studio project"
   - Navigate to the cloned directory and select it

3. **Sync Project**
   - Wait for Android Studio to sync Gradle files
   - If prompted, update Gradle version if needed
   - Ensure all dependencies are downloaded

4. **Configure API Endpoints**
   - Open `app/src/main/java/com/example/assignmentlast/data/api/ApiService.kt`
   - Update the `BASE_URL` constant with your API endpoint
   ```kotlin
   companion object {
       const val BASE_URL = "https://your-api-endpoint.com/"
   }
   ```

5. **Build and Run**
   - Connect an Android device or start an emulator
   - Click the "Run" button (▶️) in Android Studio
   - Select your target device and wait for the app to install

## 📖 Usage Guide

### Authentication Flow

1. **Launch the Application**
   - The app starts with the login screen
   - Enter your credentials in the provided fields

2. **Login Process**
   - Enter username and password
   - Tap "Login" button
   - The app will authenticate with the API
   - Upon successful authentication, you'll be redirected to the dashboard

### Dashboard Features

1. **Entity Management**
   - View all entities in a scrollable list
   - Each entity displays key information in a card format
   - Tap on any entity to view detailed information

2. **Search and Filter**
   - Use the search functionality to find specific entities
   - Filter entities based on different criteria
   - Real-time search results as you type

3. **Entity Details**
   - Tap on any entity card to view comprehensive details
   - Navigate back to dashboard using the back button
   - View all entity properties and metadata

### Navigation

- **Login Screen** → **Dashboard** (automatic after successful login)
- **Dashboard** → **Entity Details** (tap on entity card)
- **Entity Details** → **Dashboard** (back button)

## 🧪 Testing

### Running Tests

1. **Unit Tests**
   ```bash
   ./gradlew test
   ```

2. **Instrumented Tests** (if available)
   ```bash
   ./gradlew connectedAndroidTest
   ```

3. **All Tests**
   ```bash
   ./gradlew check
   ```

### Test Coverage

The project includes comprehensive unit tests for:
- **Repository Layer**: API integration and data handling
- **ViewModel Layer**: Business logic and state management
- **Data Models**: Model validation and serialization

## 🔧 Configuration

### API Configuration

Update the API configuration in `ApiService.kt`:

```kotlin
interface ApiService {
    companion object {
        const val BASE_URL = "https://your-api-base-url.com/"
        const val TIMEOUT_SECONDS = 30L
    }
    
    @POST("login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
    
    @GET("dashboard")
    suspend fun getDashboard(): Response<DashboardResponse>
}
```

### Build Configuration

Key build configurations in `app/build.gradle`:

```gradle
android {
    compileSdk 34
    defaultConfig {
        minSdk 24
        targetSdk 34
        versionCode 1
        versionName "1.0"
    }
}
```

## 📱 Screenshots

*[Screenshots would be added here showing the login screen, dashboard, and entity details]*

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is created for educational purposes as part of the NIT3213 Mobile Application Development course.

## 👨‍💻 Author

**Eshant Alfa** - NIT3213 Student
- GitHub: [@eshant-alfa](https://github.com/eshant-alfa)

## 🙏 Acknowledgments

- Course instructor for guidance and support
- Android development community for best practices
- Material Design team for UI components
- Open source contributors for libraries used in this project

---

**Note**: This project demonstrates advanced Android development concepts and should be used as a learning resource for mobile application development. 