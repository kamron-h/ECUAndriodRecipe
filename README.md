# Simple Recipe Collector App

![Pic0](https://imagedelivery.net/5IPp1ww3GQHMKaYL6DWf-g/b91cbf59-a2a3-4d61-2bcf-ae0e7edcba00/computer)
![Pic1](https://imagedelivery.net/5IPp1ww3GQHMKaYL6DWf-g/a942689a-5c42-49f1-9364-149442078600/computer)
![Pic2](https://imagedelivery.net/5IPp1ww3GQHMKaYL6DWf-g/376e3810-9c4f-4e8b-cbd7-f03894aac200/computer)

## Objective

The Simple Recipe Collector App is an Android application designed to allow users to add and view recipes. Users can enter a recipe title and detailed description, which are stored in Firebase Storage. The app also provides the functionality to retrieve and display recipe details when a user selects a title.

## Features

### Recipe Addition
- Users can enter a recipe title and detailed description using separate text boxes.
- A "Add" button is provided to save the recipe details to Firebase Storage.

### Data Handling
- When the "Add" button is clicked, the app combines the title and details into a single text file.
- The text file is then uploaded to Firebase Storage for storage.

### Data Retrieval and Display
- The app allows users to retrieve and display the full content of a recipe (title and details) when they select a title from a list.

## Requirements

To run the Simple Recipe Collector App, you will need:

- Android Studio
- Android SDK

## Installation

1. Clone the repository or download the ZIP file.

   https://github.com/kamron-h/ECUAndriodRecipe.git

3. Open Android Studio.

4. Wait for Android Studio to build and sync the project.

## Usage

1. Launch the app on your Android device or emulator.

2. To add a recipe:
- Enter a recipe title and detailed description in the provided text boxes.
- Click the "Add" button to save the recipe to Firebase Storage.

3. To view a recipe:
- You will see a list of recipe titles.
- Select a title to retrieve and display the full recipe details.

4. Enjoy using the Simple Recipe Collector App!

## Troubleshooting

- If you encounter any issues or have questions, please check the Firebase documentation for storage and Android setup.
- Ensure that your Firebase project configuration is correctly set up in the `google-services.json` file.

