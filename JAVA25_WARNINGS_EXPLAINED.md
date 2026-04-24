## ⚠️ Java 25 Warnings Explained

### Warnings You May See

#### 1. "Picked up JAVA_TOOL_OPTIONS: -Dfile.encoding=IBM437"
**What it means:**
- Your system default character encoding was set to IBM437 (Windows code page)
- This is non-standard for Java development

**Solution:**
- Set `JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF-8` in environment variables
- This ensures consistent UTF-8 encoding across platforms

**How to set permanently (Windows):**
```
setx JAVA_TOOL_OPTIONS "-Dfile.encoding=UTF-8"
```

---

#### 2. "WARNING: A terminally deprecated method in sun.misc.Unsafe"
**What it means:**
- Maven's Guice library (versions 5.1.0 and earlier) uses internal Java APIs
- Java 25 has stricter controls on accessing internal JDK classes
- These internal APIs are being phased out by Oracle

**Root cause:**
- Guice uses `sun.misc.Unsafe::staticFieldBase` for bytecode manipulation
- This creates "hidden classes" for dependency injection
- The method is deprecated and will be removed in future Java versions

**Why it happens:**
- Java 25 introduced Module System warnings
- Zulu JDK 25 is stricter about internal API access
- Maven 3.9.x uses Guice 5.1.0 which hasn't updated for Java 25 yet

**Solution:**
Configure Maven with module access permissions:
```
MAVEN_OPTS="--add-modules jdk.unsupported --add-opens java.base/sun.misc=ALL-UNNAMED"
```

**What these do:**
- `--add-modules jdk.unsupported`: Enables access to unsupported modules
- `--add-opens java.base/sun.misc=ALL-UNNAMED`: Allows deep reflection access to sun.misc package

---

### Your Project Configuration

Your `pom.xml` now includes:
```xml
<properties>
    <java.version>25</java.version>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
</properties>

<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <configuration>
        <compilerArgs>
            <arg>--enable-preview</arg>
            <arg>-XDignore.symbol.file</arg>
        </compilerArgs>
    </configuration>
</plugin>
```

These settings:
- Enable Java 25 preview features (if needed)
- Ignore symbol file warnings during compilation
- Set UTF-8 as project encoding

---

### How to Use the Setup Script

**Run from PowerShell:**
```powershell
.\setup-java25-env.ps1
```

**Or manually set environment variables:**
```powershell
$env:JAVA_HOME = "C:\Program Files\Zulu\zulu-25"
$env:PATH = "C:\Program Files\Zulu\zulu-25\bin;$env:PATH"
$env:MAVEN_OPTS = "--add-modules jdk.unsupported --add-opens java.base/sun.misc=ALL-UNNAMED"
$env:JAVA_TOOL_OPTIONS = "-Dfile.encoding=UTF-8"

mvn clean install
```

---

### Expectations

**Build will complete successfully** ✅
- Warnings about Unsafe APIs may still appear but are suppressed
- Your code compiles fine to Java 25 bytecode
- All dependencies are compatible

**Future improvements:**
- Guice will eventually update to support Java 25 natively
- Maven will upgrade its dependencies
- Warnings will disappear without needing `--add-opens` flag

---

### Troubleshooting

**If you still see warnings:**
- This is normal and safe to ignore for now
- The build still succeeds
- These are from temporary workarounds for Java 25 incompatibilities

**To suppress ALL warnings (not recommended):**
```
MAVEN_OPTS="-XX:+IgnoreUnrecognizedVMOptions --add-modules jdk.unsupported --add-opens java.base/sun.misc=ALL-UNNAMED -q"
```

**If build fails:**
- Ensure `JAVA_HOME` points to Zulu 25: `C:\Program Files\Zulu\zulu-25`
- Check `java -version` shows: `openjdk version "25.0.3"`
- Verify Maven is installed: `mvn -version`

---

### Version Information

**Your Setup:**
- Java: Zulu OpenJDK 25.0.3
- Spring Boot: 3.5.0
- Maven: 3.9.12+
- JJWT: 0.12.3

**Compatibility:**
- ✅ Java 25 supported by Spring Boot 3.5.0+
- ✅ JJWT 0.12.3 supports Java 25
- ✅ All security libraries are compatible

