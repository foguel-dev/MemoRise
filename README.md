# Memorise App

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

## Description

Memorise is a note-taking app designed for efficient note management. It provides a simple and elegant interface for creating, editing, and deleting notes.

## Table of Contents

- [Installation](#installation)
- [Dependencies](#dependencies)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Installation

To install the necessary dependencies, add the following lines to your `build.gradle` file:

// lifeCycle
implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2"
implementation "androidx.lifecycle:lifecycle-runtime-ktx:2.6.2"
implementation "androidx.lifecycle:lifecycle-livedata-ktx:2.6.2"

// Room
implementation "androidx.room:room-runtime:2.6.0"
kapt "androidx.room:room-compiler:2.6.0"
implementation "androidx.room:room-ktx:2.6.0"

// Navigation Components
implementation "androidx.navigation:navigation-fragment-ktx:$navVersion"
implementation "androidx.navigation:navigation-ui-ktx:$navVersion"

id "androidx.navigation.safeargs.kotlin" version "2.5.1" apply false

