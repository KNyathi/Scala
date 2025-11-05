#!/bin/bash

# Scala Project Creator Script - Variant 1
# Author: Khayelihle Nyathi
# Description: Automatically creates a Scala project with sbt structure

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Function to print colored output
print_message() {
    echo -e "${BLUE}[INFO]${NC} $1"
}

print_success() {
    echo -e "${GREEN}[SUCCESS]${NC} $1"
}

print_warning() {
    echo -e "${YELLOW}[WARNING]${NC} $1"
}

print_error() {
    echo -e "${RED}[ERROR]${NC} $1"
}

# Function to display usage
usage() {
    echo "Usage: $0 <project-name> [project-directory]"
    echo "Example: $0 my-scala-app"
    echo "Example: $0 my-scala-app /path/to/project"
    exit 1
}

# Function to check if command exists
command_exists() {
    command -v "$1" >/dev/null 2>&1
}

# Check dependencies
check_dependencies() {
    print_message "Checking dependencies..."
    
    if ! command_exists sbt; then
        print_error "sbt is not installed. Please install sbt first."
        exit 1
    fi
    
    if ! command_exists scala; then
        print_warning "Scala is not installed, but sbt will download it."
    fi
    
    print_success "Dependencies checked"
}

# Create project structure
create_project_structure() {
    local project_name=$1
    local project_dir=$2
    
    print_message "Creating project structure for '$project_name' in '$project_dir'..."
    
    # Create directory structure
    mkdir -p "$project_dir/src/main/scala"
    mkdir -p "$project_dir/src/test/scala"
    mkdir -p "$project_dir/project"
    
    # Create build.sbt
    cat > "$project_dir/build.sbt" << EOF
ThisBuild / version := "0.1.0"
ThisBuild / scalaVersion := "2.13.12"

lazy val root = (project in file("."))
  .settings(
    name := "$project_name",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.15" % Test
  )
EOF

    # Create main Scala file
    cat > "$project_dir/src/main/scala/Main.scala" << EOF
object Main {
  def main(args: Array[String]): Unit = {
    println("Hello, Scala!")
    println(s"Running ${project_name}")
    
    // Example functionality
    val numbers = List(1, 2, 3, 4, 5)
    val sum = numbers.sum
    val doubled = numbers.map(_ * 2)
    
    println(s"Sum of numbers: \$sum")
    println(s"Doubled numbers: \$doubled")
  }
  
  // Example method
  def greet(name: String): String = {
    s"Hello, \$name!"
  }
}
EOF

    # Create test file
    cat > "$project_dir/src/test/scala/MainTest.scala" << EOF
import org.scalatest.funsuite.AnyFunSuite

class MainTest extends AnyFunSuite {
  test("greet should return hello message") {
    assert(Main.greet("World") == "Hello, World!")
  }
  
  test("sum should calculate correctly") {
    val numbers = List(1, 2, 3)
    assert(numbers.sum == 6)
  }
}
EOF

    # Create .gitignore
    cat > "$project_dir/.gitignore" << EOF
# sbt specific
target/
project/target/
project/project/

# Scala-IDE specific
.scala_dependencies
.worksheet

# IDE specific
.idea/
.vscode/
*.iml

# OS specific
.DS_Store
Thumbs.db
EOF

    # Create README.md
    cat > "$project_dir/README.md" << EOF
# $project_name

A Scala project created automatically with the project creation script.

## Project Structure

\`\`\`
$project_name/
├── src/
│   ├── main/
│   │   └── scala/
│   │       └── Main.scala
│   └── test/
│       └── scala/
│           └── MainTest.scala
├── build.sbt
├── .gitignore
└── README.md
\`\`\`

## How to Run

1. Compile and run:
   \`\`\`bash
   sbt run
   \`\`\`

2. Run tests:
   \`\`\`bash
   sbt test
   \`\`\`

3. Start sbt shell:
   \`\`\`bash
   sbt
   \`\`\`

## Features

- Basic Scala application structure
- sbt build configuration
- Unit test setup with ScalaTest
- Git ignore file
EOF

    print_success "Project structure created"
}

# Build and test project
build_project() {
    local project_dir=$1
    
    print_message "Building project..."
    
    cd "$project_dir"
    
    # Test if sbt can load the project
    if sbt compile; then
        print_success "Project compiled successfully"
    else
        print_error "Project compilation failed"
        exit 1
    fi
    
    # Run tests
    print_message "Running tests..."
    if sbt test; then
        print_success "Tests passed"
    else
        print_warning "Some tests failed"
    fi
}

# Main script execution
main() {
    echo "=========================================="
    echo "   Scala Project Creator - Variant 1"
    echo "=========================================="
    
    # Check if project name is provided
    if [ $# -lt 1 ]; then
        usage
    fi
    
    local project_name=$1
    local project_dir=${2:-"./$project_name"}
    
    # Validate project name
    if [[ ! "$project_name" =~ ^[a-zA-Z][a-zA-Z0-9_-]*$ ]]; then
        print_error "Invalid project name. Use only letters, numbers, hyphens, and underscores."
        exit 1
    fi
    
    # Check if directory already exists
    if [ -d "$project_dir" ]; then
        print_error "Directory '$project_dir' already exists. Choose a different name or location."
        exit 1
    fi
    
    # Execute steps
    check_dependencies
    create_project_structure "$project_name" "$project_dir"
    build_project "$project_dir"
    
    # Final output
    echo ""
    print_success "Project '$project_name' created successfully!"
    echo ""
    echo "Next steps:"
    echo "  cd $project_dir"
    echo "  sbt run"
    echo "  sbt test"
    echo ""
    echo "Project location: $project_dir"
    echo "=========================================="
}

# Run main function with all arguments
main "$@"
