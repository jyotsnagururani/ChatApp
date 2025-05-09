# 💬 Java Chat App (Socket Programming + GUI)

A simple client-server chat application built using **Java Socket programming** and **Swing GUI**. This project allows two users (server and client) to chat in real-time on a local network using a clean graphical interface.

---

## 🚀 Features

- 📡 Real-time client-server communication over sockets  
- 🖥️ GUI-based interface using Swing  
- 📥 Incoming messages displayed in real-time  
- 📨 Send messages via button click or Enter key  
- ❌ Graceful handling of connection termination using `exit`  
- 🔒 Auto-closing on disconnect

---

## 📁 File Structure

/ChatApp
├── Server.java # Server application with GUI
├── Client.java # Client application with GUI
├── README.md # This file

---

## 🧰 Requirements

- Java 18 or higher  
- No external libraries required (uses standard Java and Swing)

---

## 🛠️ How to Run

### 1. Clone the Repository
```bash
git clone https://github.com/your-username/java-chat-app.git
cd java-chat-app
2. Compile the Code
bash
Copy
Edit
javac Server.java
javac Client.java
3. Run the Server
bash
Copy
Edit
java Server
4. Run the Client (in a new terminal)
bash
Copy
Edit
java Client


🔐 Note
Both client and server must run on the same machine (localhost) or the same LAN (modify IP in Client.java for remote connection).

Type exit to terminate the connection from either side.

🤝 Contributing
Pull requests are welcome! If you find bugs or want to suggest features, open an issue or PR.

👨‍💻 Author
Your Name – @jyotsnagururani
