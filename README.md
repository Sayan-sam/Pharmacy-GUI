# 💊 Pharmacy Management System

A comprehensive desktop application for managing pharmacy operations including inventory management, billing, sales tracking, and user administration.

![Java](https://img.shields.io/badge/Java-ED8B00?style=flat&logo=java&logoColor=white)
![SQLite](https://img.shields.io/badge/SQLite-07405E?style=flat&logo=sqlite&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=flat&logo=apache-maven&logoColor=white)
![Swing](https://img.shields.io/badge/Swing-GUI-blue)

## 📋 Table of Contents
- [Features](#-features)
- [Screenshots](#-screenshots)
- [Prerequisites](#-prerequisites)
- [Installation](#-installation)
- [Usage](#-usage)
- [Building from Source](#-building-from-source)
- [Project Structure](#-project-structure)
- [Technologies Used](#-technologies-used)
- [Default Credentials](#-default-credentials)
- [Contributing](#-contributing)
- [License](#-license)

## ✨ Features

### 🔐 User Management
- **Authentication System**: Secure login with username and password
- **Role-Based Access**: Two user types (Admin and Regular User)
- **User Administration**: Create, activate/deactivate, and manage user accounts
- **Password Management**: Change password functionality

### 💰 Billing & Sales
- **Customer Management**: Customer registration and login
- **Interactive Billing**: Search medicines with autocomplete
- **Real-time Stock Validation**: Prevents overselling
- **Bill Generation**: Itemized bills with totals
- **Transaction History**: Track all sales transactions

### 📦 Inventory Management
- **Medicine Database**: Comprehensive medicine information
  - Medicine Name
  - Type (Tablet, Syrup, Injection, etc.)
  - Class (Antibiotic, Painkiller, etc.)
  - Composition/Contains
  - Price
  - Stock Level
- **Stock Tracking**: Real-time inventory monitoring
- **Sortable Tables**: Easy data navigation

### 🛒 Purchase Management
- **Purchase Recording**: Track medicine purchases
- **Purchase History**: View detailed purchase records
- **Transaction Details**: Double-click to view transaction details

### 📊 Admin Features
- **Admin Dashboard**: Comprehensive overview
- **Purchase Reports**: Detailed purchase analytics
- **Sales Reports**: Sales performance tracking
- **User Management**: Complete user administration panel

## 📸 Screenshots

> *Add screenshots of your application here*

## 🔧 Prerequisites

Before running this application, ensure you have:

- **Java Runtime Environment (JRE) 8 or higher**
  - Check your Java version: `java -version`
  - Download from [Oracle](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://adoptium.net/)

## 📥 Installation

### Option 1: Download Pre-built Release (Recommended)

1. Go to the [Releases](https://github.com/yourusername/Pharmacy-GUI/releases) page
2. Download the latest `Pharmacy-Management-System-x.x.x.jar` file
3. Double-click the JAR file to run (or use the command below)

### Option 2: Run from Command Line

```bash
java -jar Pharmacy-Management-System-1.0.0.jar
```

### Option 3: Create Desktop Shortcut

#### Windows
1. Right-click on desktop → New → Shortcut
2. Enter: `javaw -jar "C:\path\to\Pharmacy-Management-System-1.0.0.jar"`
3. Name it "Pharmacy Management System"

#### Linux
Create a `.desktop` file:
```bash
nano ~/Desktop/pharmacy.desktop
```

Add:
```
[Desktop Entry]
Type=Application
Name=Pharmacy Management System
Exec=java -jar /path/to/Pharmacy-Management-System-1.0.0.jar
Icon=/path/to/icon.png
Terminal=false
Categories=Office;
```

#### macOS
1. Create a shell script named `pharmacy.command`:
```bash
#!/bin/bash
java -jar /path/to/Pharmacy-Management-System-1.0.0.jar
```
2. Make it executable: `chmod +x pharmacy.command`
3. Double-click to run

## 🚀 Usage

### First Time Setup

1. **Launch the Application**
   - Run the JAR file as described in Installation

2. **Login**
   - Use default credentials (see below) or credentials provided by your administrator

3. **Main Dashboard**
   - **BUY MEDS**: Purchase medicines for inventory
   - **BILLING**: Create customer bills and process sales
   - **ACCOUNTS**: View and manage account information

### Admin Features

If logged in as an admin, you'll have access to:
- **Settings** menu (gear icon)
  - Admin Purchases: View all purchase records
  - Admin Sales: View all sales records
  - User Management: Manage user accounts
  - Change Password: Update your password

### Regular User Features

Regular users can:
- Process customer billing
- Purchase medicines
- View their account information
- Change their password

## 🔨 Building from Source

### Prerequisites for Building
- Java Development Kit (JDK) 8 or higher
- Maven 3.6 or higher

### Build Steps

1. **Clone the Repository**
```bash
git clone https://github.com/yourusername/Pharmacy-GUI.git
cd Pharmacy-GUI
```

2. **Build with Maven**
```bash
mvn clean package
```

3. **Run the Application**
```bash
java -jar target/Pharmacy-Management-System-1.0.0.jar
```

### Create Release

To create a release package:
```bash
mvn clean package
cp target/Pharmacy-Management-System-1.0.0.jar releases/
```

## 📁 Project Structure

```
Pharmacy-GUI/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── sayansam/
│   │               ├── App.java                 # Main entry point
│   │               ├── StartPage.java           # Login screen
│   │               ├── LoggedInPage.java        # Main dashboard
│   │               ├── BillingPage.java         # Billing interface
│   │               ├── BuyingPage.java          # Purchase interface
│   │               ├── Stock.java               # Inventory view
│   │               ├── Accounts.java            # Account management
│   │               ├── Purchase.java            # Purchase history
│   │               ├── Sales.java               # Sales history
│   │               ├── AdminPurchase.java       # Admin purchase reports
│   │               ├── AdminSales.java          # Admin sales reports
│   │               ├── ManageUsers.java         # User administration
│   │               ├── CreateUser.java          # User creation
│   │               ├── ChangePassword.java      # Password management
│   │               ├── CustomerLogin.java       # Customer authentication
│   │               ├── CustomerSignup.java      # Customer registration
│   │               ├── jdbcmethods.java         # Database operations
│   │               └── DateLabelFormatter.java  # Date formatting utility
│   └── test/
│       └── java/
│           └── com/
│               └── sayansam/
│                   └── AppTest.java
├── resources/
│   ├── database/
│   │   └── pharmacy.db                          # SQLite database
│   └── images/                                  # Application icons and images
├── releases/                                    # Release builds
├── pom.xml                                      # Maven configuration
└── README.md                                    # This file
```

## 🛠️ Technologies Used

- **Java 8**: Core programming language
- **Swing**: GUI framework
- **SwingX**: Enhanced Swing components
- **SQLite**: Embedded database
- **JDBC**: Database connectivity
- **Maven**: Build and dependency management
- **JDatePicker**: Date selection component

### Dependencies
- `sqlite-jdbc` (3.42.0.0): SQLite database driver
- `jdatepicker` (1.3.4): Date picker component
- `swingx-all` (1.6.5-1): Extended Swing components
- `junit` (4.11): Testing framework

## 🔑 Default Credentials

> **Important**: Change default passwords after first login!

The default credentials depend on your database setup. Contact your system administrator for initial login credentials.

For development/testing, you may need to:
1. Check the `pharmacy.db` database in `resources/database/`
2. Query the `accounts` table for existing users

## 📊 Database Schema

The application uses SQLite with the following main tables:

- **accounts**: User credentials and profiles
- **medicine**: Medicine inventory
- **transaction**: Sales and purchase records

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

### Development Guidelines
- Follow Java naming conventions
- Comment complex logic
- Test thoroughly before submitting
- Update documentation as needed

## 🐛 Known Issues

- Image paths are hardcoded (may need adjustment based on installation directory)
- SQL injection vulnerabilities in some queries (use prepared statements in production)
- No data backup mechanism currently implemented

## 🔮 Future Enhancements

- [ ] Implement prepared statements for SQL security
- [ ] Add data export functionality (CSV, PDF)
- [ ] Implement automated backup system
- [ ] Add medicine expiry date tracking
- [ ] Generate detailed reports with charts
- [ ] Multi-language support
- [ ] Barcode scanning support
- [ ] Cloud database option

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 👤 Author

**Sayan Sam**
- GitHub: [@yourusername](https://github.com/yourusername)

## 🙏 Acknowledgments

- Java Swing community for excellent documentation
- SQLite team for the reliable embedded database
- All contributors who have helped improve this project

## 📞 Support

For support, please:
- Open an issue on GitHub
- Contact: your.email@example.com

---

⭐ If you find this project useful, please consider giving it a star!

**Made with ❤️ for pharmacy management**
