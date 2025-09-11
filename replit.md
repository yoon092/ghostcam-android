# GhostCam Android Project

## Overview
This is an imported Android project called "GhostCam" - a virtual camera application for rooted Android devices. The project uses the Xposed framework and requires root access to function.

## Current State
**Project Status**: Incomplete - Missing Source Files
- The project has Android build configuration (Gradle files)
- All Android resources are present (layouts, drawables, strings)
- **Missing**: Java/Kotlin source files for LoginActivity and MainActivity referenced in AndroidManifest.xml
- Cannot be compiled or run without the missing source code

## Project Architecture
- **Type**: Native Android Application (not a web application)
- **Framework**: Android SDK with Xposed module support
- **Build System**: Gradle with Android Gradle Plugin 7.1.0
- **Target SDK**: Android 31 (Android 12)
- **Minimum SDK**: Android 21 (Android 5.0)
- **Package**: com.example.ghostcam

## Dependencies
- Firebase Database (com.google.firebase:firebase-database:20.1.0)
- Xposed Framework API
- OkHttp3 for network requests
- AndroidX Test libraries

## Important Notes
- This is an Android mobile application, NOT a web application
- Cannot be run in Replit as a web server on port 5000
- Requires Android development environment (Android Studio) or CI/CD pipeline for building
- The project includes Google Services configuration (google-services.json)

## Required Actions to Complete
1. Add missing Java/Kotlin source files:
   - LoginActivity.java/kt
   - MainActivity.java/kt
   - Any Xposed hook implementations
2. Fix test configuration (change android.support.test.runner.AndroidJUnitRunner to androidx.test.runner.AndroidJUnitRunner)
3. Set up proper Android build environment or CI/CD pipeline

## Security Considerations
- Uses sensitive permissions (READ_PRIVILEGED_PHONE_STATE)
- Requires root access and Xposed framework
- Firebase configuration is committed (review security rules)

## Last Updated
September 11, 2025 - Initial project analysis