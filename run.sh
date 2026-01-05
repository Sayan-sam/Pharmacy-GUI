#!/bin/bash
# Pharmacy Management System Launcher

# Find Java
if type -p java; then
    JAVA=java
elif [[ -n "$JAVA_HOME" ]] && [[ -x "$JAVA_HOME/bin/java" ]]; then
    JAVA="$JAVA_HOME/bin/java"
else
    echo "Error: Java is not installed. Please install Java 8 or higher."
    echo "Download from: https://adoptium.net/"
    exit 1
fi

# Get Java version
JAVA_VERSION=$("$JAVA" -version 2>&1 | awk -F '"' '/version/ {print $2}')
echo "Found Java version: $JAVA_VERSION"

# Get the directory where the script is located
SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

# Find the JAR file
JAR_FILE=$(find "$SCRIPT_DIR" -name "Pharmacy-Management-System-*.jar" | head -n 1)

if [ -z "$JAR_FILE" ]; then
    echo "Error: Pharmacy-Management-System JAR file not found!"
    echo "Please make sure the JAR file is in the same directory as this script."
    exit 1
fi

echo "Starting Pharmacy Management System..."
echo "JAR: $JAR_FILE"

# Run the application
"$JAVA" -jar "$JAR_FILE"
