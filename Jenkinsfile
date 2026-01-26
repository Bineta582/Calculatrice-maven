pipeline {
    agent any

    tools {
        maven 'Maven-3.9'
        jdk 'JDK-17'
    }

    environment {
        SONARQUBE_SERVER = 'SonarQube'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Compile') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') { // nom du serveur défini dans Jenkins
                    withCredentials([string(credentialsId: 'sonarqube-token', variable: 'SONAR_AUTH_TOKEN')]) {
                        sh """
                        mvn sonar:sonar \
                            -Dsonar.projectKey=calculatrice-maven \
                            -Dsonar.projectName="Calculatrice Maven" \
                            -Dsonar.login=$SONAR_AUTH_TOKEN
                        """
                    }
                }
            }
        }

        stage('Quality Gate') {
            steps {
                timeout(time: 1, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
        stage('Deploy to Nexus') {
            steps {
                sh 'mvn clean deploy'
            }
        }
    }


    post {
        success {
            echo 'PIPELINE SUCCESS '
        }
        failure {
            echo 'PIPELINE FAILED '
        }
    }
}
