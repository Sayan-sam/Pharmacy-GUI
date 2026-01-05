@echo off
REM Pharmacy Management System Launcher for Windows

echo Pharmacy Management System Launcher
echo ====================================

REM Check if Java is installed
where java >nul 2>nul
if %errorlevel% neq 0 (
    echo Error: Java is not installed or not in PATH
    echo Please install Java 8 or higher from: https://adoptium.net/
    pause
    exit /b 1
)

REM Display Java version
echo Checking Java version...
java -version

REM Find the JAR file
set "JAR_FILE="
for %%f in (Pharmacy-Management-System-*.jar) do (
    set "JAR_FILE=%%f"
    goto :found
)

:found
if "%JAR_FILE%"=="" (
    echo Error: Pharmacy-Management-System JAR file not found!
    echo Please make sure the JAR file is in the same directory as this script.
    pause
    exit /b 1
)

echo.
echo Starting Pharmacy Management System...
echo JAR: %JAR_FILE%
echo.

REM Run the application
javaw -jar "%JAR_FILE%"

if %errorlevel% neq 0 (
    echo.
    echo Error: Failed to start the application
    pause
    exit /b 1
)
