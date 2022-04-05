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

        stage("Update image version") {
          steps {
            //sh "cat docker-compose.yml"
            sh "sed  -i 's+{app-image}+${DOCKER_REGISTRY_URL}/${DOCKER_IMAGE}:${BUILD_NUMBER}+g' docker-compose.yml"
            //sh "cat docker-compose.yml"
          }
        }

        stage("Deploy to staging") {
          steps {
            //sh "docker run -d --rm -p 8082:8080 --name calculator ${DOCKER_REGISTRY_URL}/${DOCKER_IMAGE}:${BUILD_NUMBER}"
            sh "docker-compose up -d"
          }
        }

        stage("Acceptance test") {
          steps {
            sh '''
              sleep 10
              CALCULATOR_PORT=$(docker-compose port calculator 8080 | cut -d: -f2)
              echo "El numero de puerto es ${CALCULATOR_PORT}"
              sleep 60
              test $(curl localhost:${CALCULATOR_PORT}/sum?a=1&b=2) -eq 3
            '''
            //sh "chmod +x acceptance_test.sh && ./acceptance_test.sh"
          }
        }
    }

    post {
      always {
        //sh "docker stop calculator"
        sh "docker-compose down"
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
