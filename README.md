# Potato Disease Classification

A project to detect and classify potato plant diseases (healthy, late blight, or early blight) using a Convolutional Neural Network (CNN) deployed on Google Cloud Platform, with an accompanying Android application for image capture and disease prediction.

---

## Table of Contents
- [Installation](#installation)
- [Usage](#usage)
  - [Android App](#android-app)
  - [API Usage](#api-usage)
- [Model Architecture](#model-architecture)
- [Dataset](#dataset)
- [Deployment](#deployment)
- [Contributing](#contributing)

---

## Installation

**1. Clone the Repository**
```bash
git clone https://github.com/Ahmad-Reza/Potato-Guard.git
```

**2. Set Up Environment**
- **Python:** Ensure you have Python 3.x installed.
- **Required Libraries:** Install the necessary libraries using pip:
```bash
conda install matplotlib numpy notebook tensorflow=2.12.0
```
```bash
pip install tensorflow-addons tensorflow-model-optimization
```
**2. Configure Google Cloud Platform**
- **Create a Project:** Set up a Google Cloud Platform (GCP) project and enable the necessary APIs.
- **Storage Bucket:** Create a Cloud Storage bucket to store your model and configure API endpoints.
- **Android Integration:** Update your Android app to interact with the deployed GCP API.

---

## Usage
### Android App
**1. Download and Install:**
- Obtain the APK file from the repository's Releases section or [Download the APK](https://github.com/Ahmad-Reza/Potato-Guard/releases/download/v1.0.0/app-debug.apk).

**2. Launch the App: Open the app on your Android device.**
- Capture an image of the potato plant using the camera.

  <img src="https://raw.githubusercontent.com/Ahmad-Reza/Potato-Guard/refs/heads/main/extras/first.png" style="padding:15px" height="400" width="200">

**3. Capture or Upload:**
- **Capture:** Use the app's camera to take a photo of the potato plant leaf.
- **Upload:** Select an existing image from your device's gallery.

  <img src="https://raw.githubusercontent.com/Ahmad-Reza/Potato-Guard/refs/heads/main/extras/processing.png" style="padding:15px" height="400" width="200">

**4. Get Results:** The app processes the image and displays the predicted disease category (healthy, late blight, or early blight) and confidence.

  <img src="https://raw.githubusercontent.com/Ahmad-Reza/Potato-Guard/refs/heads/main/extras/result.png" style="padding:15px" height="400" width="200">  

### API Usage
**1. Allow permission:** Add allUsers permission with cloud functions invoker value to public use of API.

**2. Send Image Request:**
- Use an HTTP POST request to send image file data to the API endpoint.

**3. Receive Prediction:**
- The API responds with a JSON payload containing the predicted disease category and model predict confident.

---

## Model Architecture
- **Convolutional Neural Network (CNN):**
  - The CNN consists of several convolutional, pooling, and fully connected layers optimized for image classification.
- **Training Process:**
  - **Preprocessing:** Resizing, normalizing, and augmenting the dataset.
  - **Optimization:** Used techniques like the Adam optimizer and early stopping to improve accuracy.

---

## Dataset
- **Source:**
  - The dataset consists of images of potato plant leaves in various health conditions.
- **Data Preprocessing:**
  - Images were resized, normalized, and augmented to enhance the dataset's quality and improve model performance.

---

## Deployment
- **Model Deployment:**
  - The trained model was deployed to Google Cloud Platform using TensorFlow Serving.
- **API Endpoint:**
  - A RESTful API endpoint was created to expose the model's prediction capabilities to clients.

---

## Contributing
**1. Fork the Repository:** Create a fork of this repository.

**2. Create a Branch:**
```bash
git checkout -b feature-or-bugfix-branch-name
```
**3. Make Changes:** Add your feature or fix issues in the project.

**4. Push to GitHub:**
```bash
git push origin feature-or-bugfix-branch-name
```
**5 Submit a Pull Request:** Open a pull request to the main repository.

---

Thank you for visiting✨ Happy coding!🚀
