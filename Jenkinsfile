pipeline {
    agent any
    triggers {
      pollSCM('* * * * *')
    }
    stages {
        /*stage ("Checkout") {
            steps {
                //git branch: '1.0.0', url: 'https://github.com/caalpeva/poc-java.git'
                git url: 'https://github.com/caalpeva/esp.git'

            }
        }*/
        stage ("Compile") {
            steps {
                sh "./gradlew compileJava"
            }
        }
        stage ("Unit test") {
            steps {
                sh "./gradlew test"
            }
        }
        stage ("Code coverage") {
          steps {
            sh "./gradlew jacocoTestReport"
            publishHTML (target: [
              reportDir: 'build/reports/jacoco/test/html',
              reportFiles: 'index.html',
              reportName: "JaCoCo Report"
            ])
            sh "./gradlew jacocoTestCoverageVerification"
          }
        }
        stage ("Static code Analysis") {
          steps {
            sh "./gradlew checkstyleMain"
            publishHTML (target: [
              reportDir: 'build/reports/checkstyle/',
              reportFiles: 'main.html',
              reportName: "Checkstyle Report"
            ])
          }
        }
    }
}
