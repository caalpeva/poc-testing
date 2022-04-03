pipeline {
    agent any
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
    }
}
