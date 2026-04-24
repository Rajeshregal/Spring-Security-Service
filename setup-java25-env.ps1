# Setup Script for Zulu JDK 25 with Spring Boot 3.5.0
# This script configures the environment variables for building with Java 25

Write-Host "╔════════════════════════════════════════════════════════════╗" -ForegroundColor Cyan
Write-Host "║  Java 25 Environment Setup for Spring Boot 3.5.0            ║" -ForegroundColor Cyan
Write-Host "╚════════════════════════════════════════════════════════════╝" -ForegroundColor Cyan
Write-Host ""

# Set JAVA_HOME for Zulu 25
Write-Host "Setting JAVA_HOME..." -ForegroundColor Yellow
$env:JAVA_HOME = "C:\Program Files\Zulu\zulu-25"

# Add Zulu 25 to PATH
Write-Host "Adding Zulu 25 to PATH..." -ForegroundColor Yellow
$env:PATH = "C:\Program Files\Zulu\zulu-25\bin;$env:PATH"

# Set Maven options to handle Java 25 compatibility
# This suppresses warnings about deprecated sun.misc.Unsafe APIs
Write-Host "Configuring Maven options..." -ForegroundColor Yellow
$env:MAVEN_OPTS = "--add-modules jdk.unsupported --add-opens java.base/sun.misc=ALL-UNNAMED"

# Set file encoding to UTF-8 (best practice)
Write-Host "Setting file encoding to UTF-8..." -ForegroundColor Yellow
$env:JAVA_TOOL_OPTIONS = "-Dfile.encoding=UTF-8"

Write-Host ""
Write-Host "✅ Environment variables configured successfully!" -ForegroundColor Green
Write-Host ""

# Display configuration
Write-Host "═══════════════════════════════════════════════════════════" -ForegroundColor Cyan
Write-Host "Configuration Summary:" -ForegroundColor Cyan
Write-Host "═══════════════════════════════════════════════════════════" -ForegroundColor Cyan
Write-Host "JAVA_HOME:       $env:JAVA_HOME" -ForegroundColor Green
Write-Host "Java Version:    " -ForegroundColor Green -NoNewline
& "C:\Program Files\Zulu\zulu-25\bin\java" -version 2>&1 | Select-Object -First 1
Write-Host "MAVEN_OPTS:      $env:MAVEN_OPTS" -ForegroundColor Green
Write-Host "JAVA_TOOL_OPTIONS: $env:JAVA_TOOL_OPTIONS" -ForegroundColor Green
Write-Host ""
Write-Host "You can now run: mvn clean install" -ForegroundColor Yellow
Write-Host ""

