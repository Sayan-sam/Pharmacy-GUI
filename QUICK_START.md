# Quick Start Guide - Pharmacy Management System

## 🎯 Quick Steps to Release

Since you already have a JAR file in the releases folder, here's how to prepare for GitHub release:

### 1. Prepare Your Repository

```bash
cd /home/monica/codes/github-repos/Pharmacy-GUI

# Make the launch script executable
chmod +x run.sh

# Copy launch scripts to releases folder
cp run.sh releases/
cp run.bat releases/
```

### 2. Commit All Changes

```bash
# Stage all new files
git add README.md LICENSE RELEASE_GUIDE.md .gitignore pom.xml
git add run.sh run.bat releases/README.md

# Commit the changes
git commit -m "Prepare for v1.0.0 release - Add documentation and build configuration"

# Create a version tag
git tag -a v1.0.0 -m "Release version 1.0.0"
```

### 3. Push to GitHub

```bash
# Push the commits
git push origin main

# Push the tag
git push origin v1.0.0
```

### 4. Create GitHub Release

1. Go to your repository on GitHub
2. Click "Releases" → "Draft a new release"
3. Select tag: `v1.0.0`
4. Title: `Pharmacy Management System v1.0.0`
5. Upload these files:
   - `releases/Pharmacy-GUI.jar` (rename to `Pharmacy-Management-System-1.0.0.jar`)
   - `releases/run.sh`
   - `releases/run.bat`
6. Copy the release description from RELEASE_GUIDE.md
7. Click "Publish release"

## 🔧 If You Need to Build from Source

### Install Maven (One-time setup)

**Ubuntu/Debian:**
```bash
sudo apt update
sudo apt install maven
```

**Fedora/RHEL:**
```bash
sudo dnf install maven
```

**macOS:**
```bash
brew install maven
```

**Verify installation:**
```bash
mvn -version
```

### Build the Project

```bash
cd /home/monica/codes/github-repos/Pharmacy-GUI

# Clean and build
mvn clean package

# The JAR will be created in: target/Pharmacy-Management-System-1.0.0.jar

# Copy to releases
cp target/Pharmacy-Management-System-1.0.0.jar releases/
```

## 📦 What You've Got Now

Your repository now has:

✅ **README.md** - Comprehensive documentation with:
   - Features overview
   - Installation instructions
   - Usage guide
   - Building from source
   - Contributing guidelines

✅ **pom.xml** - Updated with:
   - Executable JAR configuration
   - All dependencies bundled
   - Main class specified
   - Version set to 1.0.0

✅ **LICENSE** - MIT License

✅ **RELEASE_GUIDE.md** - Complete guide for:
   - Creating releases
   - Version management
   - GitHub release templates
   - Troubleshooting

✅ **Launch Scripts**:
   - `run.sh` for Linux/Mac
   - `run.bat` for Windows

✅ **.gitignore** - Properly configured to:
   - Ignore build artifacts
   - Keep source files
   - Exclude IDE files

## 🚀 Next Steps

### Immediate Actions:

1. **Update GitHub Repository Info**
   - Edit README.md and replace:
     - `yourusername` with your GitHub username
     - `your.email@example.com` with your email
   - Add repository description
   - Add topics/tags: java, swing, pharmacy, inventory-management, billing

2. **Test Locally**
   ```bash
   # Test the existing JAR
   java -jar releases/Pharmacy-GUI.jar
   
   # Or use the launch script
   ./run.sh
   ```

3. **Commit and Push**
   ```bash
   git add .
   git commit -m "Add comprehensive documentation and release configuration"
   git push origin main
   ```

4. **Create GitHub Release** (see step 4 above)

### Optional Enhancements:

5. **Add Screenshots**
   - Take screenshots of your application
   - Add them to a `docs/screenshots/` folder
   - Update README.md with actual images

6. **Create Contributing Guide**
   - Add CONTRIBUTING.md with development guidelines

7. **Set Up GitHub Actions** (automated builds)
   - Create `.github/workflows/build.yml`
   - Automate JAR creation on releases

8. **Add Changelog**
   - Create CHANGELOG.md
   - Track version history

## 📝 Customization Checklist

Before releasing, update these in README.md:

- [ ] Replace `yourusername` with your GitHub username
- [ ] Add your email address
- [ ] Update repository URL
- [ ] Add actual screenshots
- [ ] Update default credentials section with actual info
- [ ] Add any project-specific notes

## 🎉 You're Ready!

Your project is now ready for:
- ✅ Public release on GitHub
- ✅ Easy distribution to users
- ✅ Professional documentation
- ✅ Version control and management

Users can now:
- Download the JAR from releases
- Run it with a double-click (if Java is installed)
- Use the launch scripts for easy starting
- Read documentation to understand features
- Contribute to the project

## 💡 Tips

**For Users Without Java:**
- Point them to https://adoptium.net/ for easy Java installation
- Include Java installation in your documentation

**For Distribution:**
- Consider creating installers with JPackage (Java 14+)
- Package Java with your app for true standalone distribution
- Create platform-specific installers (exe, deb, dmg)

**For Marketing:**
- Add badges to README (build status, license, version)
- Share on Reddit (r/java, r/opensource)
- Post on LinkedIn
- Add to GitHub Awesome lists if applicable

---

Need help with any step? Check RELEASE_GUIDE.md for detailed instructions!
