# Experiment 8: Implement Menus and WebView in an Android Application

## Student Information
* **Name:** Aldrin Jose Antony
* **USN:** 1SG21CS001
* **Course:** Android Application Development
* **Institution:** Department of Computer Science and Engineering

---

## 1. Experiment Overview
The objective of **Experiment 8** is to demonstrate how to implement **Menus** (Options Menu) and **WebView** in an Android application. This experiment builds a fully functional mini-browser application inside Android, enabling users to browse web pages, enter custom URLs or search queries, navigate back and forth, reload pages, adjust zoom levels, and access developer information.

---

## 2. Concepts and Technologies Behind the Experiment

### A. WebView (`android.webkit.WebView`)
* **Definition:** A view that displays web pages inside your application. It uses the WebKit rendering engine to display web content.
* **WebViewClient:** Ensures that web page navigation (clicking links) happens *within* the app's WebView rather than launching an external browser app.
* **WebSettings:** Configured to enable JavaScript (`setJavaScriptEnabled(true)`), DOM storage (`setDomStorageEnabled(true)`), and image loading.

### B. Android Menus (Options Menu)
* **Definition:** The primary collection of menu items for an activity. Placed in the app bar (`MaterialToolbar`), Options Menus provide access to actions such as Home, Reload, Zoom In, Zoom Out, About/Developer Info, and Exit.
* **Implementation:** Defined using an XML menu resource (`main_menu.xml`) and inflated in `onCreateOptionsMenu()`, with event handling in `onOptionsItemSelected()`.

### C. Material Design 3
* Utilizes modern Material Components (`MaterialToolbar`, surface colors, edge-to-edge window insets) to ensure a polished UI.

---

## 3. Scenario Used to Demonstrate the Application
The application acts as a **Mini Android Browser & Utility App**:
1. **Home Screen:** On launch, the app loads `https://www.google.com` by default inside the WebView. The URL input bar at the top displays the active URL.
2. **URL Navigation:** Users can type any URL (e.g., `github.com`, `developer.android.com`) or search query into the address bar and press **Go** on the keyboard or button.
3. **Options Menu Actions:**
   * **Home:** Quickly returns to the default home page.
   * **Reload:** Refreshes the current web page.
   * **Zoom In / Zoom Out:** Adjusts the WebView text/content zoom.
   * **About / Developer:** Displays an alert dialog showing student credentials (**Aldrin Jose Antony**, USN: **1SG21CS001**) and experiment details.
   * **Exit:** Closes the application.
4. **Hardware Back Button Handling:** Pressing the device back button navigates back through the WebView's browsing history before exiting the app.

---

## 4. Project Folder and File Structure

```text
D:/MyApplicationEXP_8/
│
├── .idea/                      # IDE configuration files
├── app/
│   ├── build.gradle.kts        # App-level Gradle build configuration
│   ├── src/
│   │   ├── main/
│   │   │   ├── AndroidManifest.xml # App manifest with INTERNET permission
│   │   │   ├── java/
│   │   │   │   └── com/
│   │   │   │       └── shareplate/
│   │   │   │           └── myapplicationexp_8/
│   │   │   │               └── MainActivity.java # Main Activity controlling WebView & Menus
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   │   └── activity_main.xml # UI layout with Toolbar, EditText, Button, and WebView
│   │   │   │   ├── menu/
│   │   │   │   │   └── main_menu.xml # Options Menu definition XML
│   │   │   │   ├── values/
│   │   │   │   │   ├── colors.xml
│   │   │   │   │   ├── strings.xml # App strings and menu titles
│   │   │   │   │   └── themes.xml # Material 3 theme configurations
│   │   │   │   └── xml/
│   │   │   │       ├── backup_rules.xml
│   │   │   │       └── data_extraction_rules.xml
│   │   │   └── test/           # Unit tests
│   │   └── androidTest/        # Instrumented UI tests
│   ├── build.gradle.kts
│   └── ...
├── build.gradle.kts            # Root Gradle configuration
├── gradle.properties
├── settings.gradle.kts
└── README.md                   # Comprehensive Experiment Documentation
```

---

## 5. Test Cases and Verification

### Test Case 1: App Launch & Default Home WebView Loading
* **Description:** Verify that launching the application successfully initializes the `WebView` with internet permission, displays the `MaterialToolbar`, and loads the default home page (`https://www.google.com`).
* **Expected Output:** The Google home page renders correctly inside the WebView, and the address bar updates with the loaded URL.
* **Status:** Passed ✅

### Test Case 2: Custom URL Input and Options Menu (Zoom/Reload)
* **Description:** Verify that users can type a custom URL or search query in the address bar and tap "Go", and use the Options Menu items (Reload, Zoom In, Zoom Out) to interact with the web content.
* **Expected Output:** The WebView navigates to the requested webpage, and menu selections trigger immediate actions (page reload and zooming).
* **Status:** Passed ✅

### Test Case 3: Developer Information & USN Verification
* **Description:** Tap the **About / Developer** option in the Options Menu to verify student credentials and experiment details.
* **Expected Output:** An alert dialog pops up displaying:
  * **Developer Name:** Aldrin Jose Antony
  * **USN:** 1SG21CS001
  * **Experiment:** Menus and WebView in an Android Application
* **Status:** Passed ✅

---

## 6. Conclusion
Experiment 8 was successfully implemented, demonstrating core Android UI components including `WebView`, custom `WebViewClient`, `WebSettings`, and `OptionsMenu` integration within a modern Material 3 Android architecture.
