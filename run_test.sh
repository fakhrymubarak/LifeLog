#!/bin/bash

# Configuration variables
APP_ID="com.fakhry.lifelog"
APK_PATH="./app/build/outputs/apk/debug/app-debug.apk"  # Adjust this path as needed
TEST_FILE="./scenario/onboarding/onboarding_test.yaml"              # Adjust this path as needed

# Text colors
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
RED='\033[0;31m'
NC='\033[0m' # No Color

# Check if maestro is installed
if ! command -v maestro &> /dev/null; then
    echo -e "${RED}Maestro is not installed. Please install it first.${NC}"
    echo "Run: curl -Ls \"https://get.maestro.mobile.dev\" | bash"
    exit 1
fi

# Check if ADB is installed
if ! command -v adb &> /dev/null; then
    echo -e "${RED}ADB is not installed. Please install the Android SDK Platform Tools.${NC}"
    exit 1
fi

# Check if any device is connected
if [ -z "$(adb devices | grep -v 'List' | grep device)" ]; then
    echo -e "${RED}No device connected. Please connect a device or start an emulator.${NC}"
    exit 1
fi

# Check if APK exists
if [ ! -f "$APK_PATH" ]; then
    echo -e "${YELLOW}APK not found at $APK_PATH. Building now...${NC}"
    ./gradlew assembleDebug
    if [ $? -ne 0 ]; then
        echo -e "${RED}Build failed.${NC}"
        exit 1
    fi
    echo -e "${GREEN}APK built successfully.${NC}"
fi

# Check if test file exists
if [ ! -f "$TEST_FILE" ]; then
    echo -e "${RED}Test file not found at $TEST_FILE${NC}"
    echo "Please specify the correct test file path:"
    read -r TEST_FILE
    if [ ! -f "$TEST_FILE" ]; then
        echo -e "${RED}Test file not found at $TEST_FILE. Exiting.${NC}"
        exit 1
    fi
fi

# Uninstall the app if it exists
echo -e "${YELLOW}Uninstalling previous version of the app...${NC}"
adb uninstall "$APP_ID" || true

# Install the app
echo -e "${YELLOW}Installing fresh APK...${NC}"
adb install "$APK_PATH"
if [ $? -ne 0 ]; then
    echo -e "${RED}Failed to install the APK.${NC}"
    exit 1
fi
echo -e "${GREEN}APK installed successfully.${NC}"

# Run the Maestro test
echo -e "${YELLOW}Running Maestro test...${NC}"
maestro test "$TEST_FILE"

# Check test result
if [ $? -eq 0 ]; then
    echo -e "${GREEN}Onboarding test completed successfully!${NC}"
else
    echo -e "${RED}Onboarding test failed.${NC}"
    exit 1
fi