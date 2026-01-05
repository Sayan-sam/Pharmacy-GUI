# Release Guide for Pharmacy Management System

## Prerequisites
- Maven installed
- Java JDK 8 or higher
- Git configured
- Access to the GitHub repository

## Step-by-Step Release Process

### 1. Prepare the Release

```bash
# Navigate to project directory
cd Pharmacy-GUI

# Ensure you're on the main branch
git checkout main

# Pull latest changes
git pull origin main

# Update version number in pom.xml if needed
# Change <version>1.0.0</version> to your target version
```

### 2. Build the Project

```bash
# Clean and build the project
mvn clean package

# Verify the build was successful
# The JAR file should be in target/Pharmacy-Management-System-x.x.x.jar
```

### 3. Test the Build

```bash
# Test the generated JAR
java -jar target/Pharmacy-Management-System-1.0.0.jar

# Verify all features work:
# - Login functionality
# - Billing page
# - Purchase page
# - Admin features (if applicable)
# - Database connectivity
```

### 4. Copy to Releases Folder

```bash
# Copy the JAR to releases folder
cp target/Pharmacy-Management-System-1.0.0.jar releases/

# Copy launch scripts
cp run.sh releases/
cp run.bat releases/
chmod +x releases/run.sh
```

### 5. Commit Changes

```bash
# Add changes
git add .

# Commit with version tag
git commit -m "Release version 1.0.0"

# Tag the release
git tag -a v1.0.0 -m "Release version 1.0.0"
```

### 6. Push to GitHub

```bash
# Push commits
git push origin main

# Push tags
git push origin v1.0.0
```

### 7. Create GitHub Release

1. Go to your repository on GitHub
2. Click on "Releases" in the right sidebar
3. Click "Draft a new release" or "Create a new release"
4. Fill in the release information:
   - **Tag version**: v1.0.0 (select the tag you just pushed)
   - **Release title**: Pharmacy Management System v1.0.0
   - **Description**: Use the template below

5. Attach files:
   - `Pharmacy-Management-System-1.0.0.jar` from the releases folder
   - `run.sh`
   - `run.bat`
   - Optionally: `INSTALLATION.md` or other documentation

6. Click "Publish release"

## GitHub Release Description Template

```markdown
# 💊 Pharmacy Management System v1.0.0

## 🎉 What's New

This is the initial release of the Pharmacy Management System!

### Features
- ✅ User authentication and management
- ✅ Medicine inventory tracking
- ✅ Customer billing system
- ✅ Purchase management
- ✅ Sales history
- ✅ Admin dashboard with reports
- ✅ Role-based access control

## 📥 Installation

### Quick Start

1. **Download** the `Pharmacy-Management-System-1.0.0.jar` file below
2. **Ensure Java 8+** is installed: `java -version`
3. **Run** the application:
   - **Windows**: Double-click the JAR or use `run.bat`
   - **Linux/Mac**: Use `./run.sh` or `java -jar Pharmacy-Management-System-1.0.0.jar`

### What's Included
- `Pharmacy-Management-System-1.0.0.jar` - Main application (all dependencies included)
- `run.sh` - Launch script for Linux/Mac
- `run.bat` - Launch script for Windows

## 📋 System Requirements

- **Java Runtime Environment (JRE) 8 or higher**
- **Operating System**: Windows, Linux, or macOS
- **Memory**: 512 MB RAM minimum
- **Disk Space**: 100 MB

## 🚀 Getting Started

1. Launch the application
2. Login with your credentials
3. Explore the features:
   - **BUY MEDS**: Purchase medicines
   - **BILLING**: Process customer sales
   - **ACCOUNTS**: Manage account information
   - **Settings** (Admin only): Access admin features

## 📖 Documentation

Full documentation is available in the [README.md](../README.md)

## 🐛 Known Issues

- Image paths may need adjustment based on installation directory
- First-time users need to contact admin for credentials

## 🔄 Upgrading

If upgrading from a previous version:
1. Backup your `pharmacy.db` database
2. Replace the old JAR with this new version
3. Run the application

## 🤝 Contributing

We welcome contributions! Please see [CONTRIBUTING.md](../CONTRIBUTING.md)

## 📞 Support

- **Issues**: Report bugs on [GitHub Issues](../../issues)
- **Discussions**: Join [GitHub Discussions](../../discussions)

---

**Full Changelog**: https://github.com/yourusername/Pharmacy-GUI/compare/v0.9.0...v1.0.0
```

## Versioning Guide

We follow [Semantic Versioning](https://semver.org/):

- **MAJOR** version (1.0.0): Incompatible API changes
- **MINOR** version (1.1.0): New features, backward compatible
- **PATCH** version (1.0.1): Bug fixes, backward compatible

### Version Update Checklist

When releasing a new version:

- [ ] Update version in `pom.xml`
- [ ] Update version in README.md if mentioned
- [ ] Update CHANGELOG.md (create one if it doesn't exist)
- [ ] Run full test suite
- [ ] Build and test the JAR
- [ ] Create git tag with version number
- [ ] Create GitHub release with release notes
- [ ] Update download links in README.md

## Hotfix Process

For urgent bug fixes:

```bash
# Create hotfix branch from the tag
git checkout -b hotfix/1.0.1 v1.0.0

# Make fixes
# Update version to 1.0.1 in pom.xml

# Commit and build
git commit -am "Hotfix: Fix critical bug"
mvn clean package

# Merge back to main
git checkout main
git merge hotfix/1.0.1

# Tag and push
git tag -a v1.0.1 -m "Hotfix release 1.0.1"
git push origin main --tags

# Delete hotfix branch
git branch -d hotfix/1.0.1
```

## Automation (Future)

Consider setting up:
- GitHub Actions for automated builds
- Automated testing on push
- Automatic release creation from tags
- Version bump automation

## Troubleshooting

### Build Fails
- Ensure Maven is installed: `mvn -version`
- Check JDK version: `java -version` (should be 8+)
- Clear Maven cache: `mvn clean`
- Delete `.m2/repository` cache if needed

### JAR Doesn't Run
- Verify JAR was built: `ls -lh target/`
- Check manifest: `jar xf target/Pharmacy-Management-System-1.0.0.jar META-INF/MANIFEST.MF`
- Ensure Main-Class is set to `com.sayansam.App`

### Resources Not Found
- Verify resources are included in JAR: `jar tf target/Pharmacy-Management-System-1.0.0.jar | grep resources`
- Check resource paths in code

## Distribution

### Option 1: GitHub Releases (Recommended)
- Users download from releases page
- Automatic notifications to watchers
- Version history maintained

### Option 2: Direct Download
- Host JAR on a web server
- Provide direct download link

### Option 3: Package Managers (Advanced)
- Create installers for Windows (NSIS, WiX)
- Create .deb packages for Debian/Ubuntu
- Create .rpm packages for RedHat/Fedora
- Use jpackage (Java 14+)

## Maintenance

After release:
- Monitor issue reports
- Plan next version features
- Maintain changelog
- Update documentation as needed
