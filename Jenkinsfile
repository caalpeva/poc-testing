pipeline {
    agent any
    triggers {
      pollSCM('* * * * *')
    }
    environment {
      DOCKER_REGISTRY_URL="localhost:5000"
      DOCKER_IMAGE="poc-calculator"
    }
    stages {
        /*
        stage("Env Variables") {
          steps {
            sh "printenv"
          }
        }
        */

        /*
        stage ("Checkout") {
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

        stage("Package") {
          steps {
            sh "./gradlew build"
          }
        }

        stage("Docker build") {
          steps {
            sh "docker build -t ${DOCKER_REGISTRY_URL}/${DOCKER_IMAGE}:${BUILD_NUMBER} ."
          }
        }

        /*
        stage("Docker login") {
          steps {
            withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'docker-hub-credentials',
              usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
                sh "docker login --username $USERNAME --password $PASSWORD"
              }
          }
        }
        */

        stage("Docker push") {
          steps {
            sh "docker push ${DOCKER_REGISTRY_URL}/${DOCKER_IMAGE}:${BUILD_NUMBER}"
          }
        }

        stage("Deploy to staging") {
          steps {
            sh "docker run -d --rm -p 8082:8081 --name calculator ${DOCKER_REGISTRY_URL}/${DOCKER_IMAGE}:${BUILD_NUMBER}"
          }
        }

        stage("Acceptance test") {
          steps {
            sleep 60
            dir("scritps") {
              sh "./acceptance_test.sh"
            }
          }
        }
    }

    post {
      always {
        sh "docker stop calculator"
        mail to: 'hyeepaa@gmail.com',
        subject: "Completed Pipeline: ${currentBuild.fullDisplayName}",
        body: "Your build completed, please check: ${env.BUILD_URL}"
      }

      /*failure {
        slackSend channel: '#dragons-team',
        color: 'danger',
        message: "The pipeline ${currentBuild.fullDisplayName} failed."
      }*/
    }
}
