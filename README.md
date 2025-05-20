# Chatbot App – Setup Instructions
This project is made up of two parts:

- Backend: A Flask API (run with Python & Ollama for the Llama 2 model)
- Frontend: An Android app (chat interface)

### Set up and run the backend (Flask + Ollama)
##### 1. Install & Start Ollama
Download and install Ollama if you haven’t already.

Open a terminal (Command Prompt/Terminal window).

Start the Ollama server by running:

```nginx
ollama serve
```
Make sure your model (e.g. llama2) is available. You can pull it with:

```nginx
ollama pull llama2
```
(You only need to pull a model once, then just run ollama serve next time.)




##### 2. Set Up and Run the Flask Backend
Open another terminal window.

Go to your backend folder:

```bash
cd path/to/your/flask-backend
```
(Recommended) Create and activate a virtual environment:

On Windows:

```bash
python -m venv venv
venv\Scripts\activate
```
On Mac/Linux:

```bash
python3 -m venv venv
source venv/bin/activate
```

Install the required packages (only needed the first time):

```nginx
pip install flask requests
```
Start the Flask server with:

```css
python main-ollama.py
```
The server should say something like:

```nginx
App running on port 5000
```
Make sure it’s running with:
http://<your-ip>:5000/ (see below for how to check your IP)

##### 3. Find Your Computer's Local IP Address
On Windows, open Command Prompt and type:
```nginx
ipconfig
```
Look for the "IPv4 Address" under your active Wi-Fi or Ethernet adapter (it’ll look like 192.168.x.x).

On Mac/Linux:
```nginx
ifconfig
```
You’ll need this IP for the Android app!



### Android App Setup
##### 1. Open the Project in Android Studio
Open the android-app folder (or wherever your Android Studio project is).

Open RetrofitClient.java (usually in com.example.llamachatbotapp).

##### 2. Set the Base URL
Find this line:

```java
// TODO: Replace with your backend server's IP address and port
  .baseUrl("http://YOUR_SERVER_IP:5000/")
```
Replace "YOUR_SERVER_IP" with your computer’s actual IP address from above (e.g. 192.168.0.22).

##### 3. Build and Run on Your Device
Make sure your Android phone and your computer are connected to the same Wi-Fi network.

Plug in your phone with USB and enable "USB Debugging" (or use the emulator if you prefer, with .baseUrl("http://10.0.2.2:5000/")).

Build and run the app.

Log in and start chatting!



### Troubleshooting
If you get a network error, double-check:

- The IP address in RetrofitClient.java is correct.
- Both phone and computer are on the same network.
- The Flask backend and Ollama server are both running.
- If running on a real device, Windows Firewall isn’t blocking Python/Flask (allow access if prompted).

If you change your network or restart your PC, your IP might change—double-check and update in the Android code if needed.

Extra Notes
For faster replies, you can lower the num_predict value in the backend code.

Don’t share your private IP on public repos—use a placeholder in the code before uploading.
