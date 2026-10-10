pipeline {
    agent any

    tools {
        jdk 'JDK21'
        maven 'Maven'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Compile and Test') {
            steps {
                sh 'java -version'
                sh 'mvn -version'
                sh 'mvn -B clean verify'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t student-grade-manager:${BUILD_NUMBER} .'
                sh 'docker tag student-grade-manager:${BUILD_NUMBER} student-grade-manager:latest'
            }
        }
    }

    post {
        success {
            echo 'Build, tests, and Docker image creation succeeded.'
        }
        failure {
            echo 'Pipeline failed. Check the console output and test reports.'
        }
    }
}
