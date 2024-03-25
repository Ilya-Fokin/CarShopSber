pipeline {
    agent { docker { image 'maven:3.9.6-eclipse-temurin-17-alpine' } }
    stages {
        stage('build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('snyk-test') {
            echo 'Testing...'
            snykSecurity(
                  snykInstallation: 'snyk@latest',
                  snykTokenId: 'snyk-api-token'
            )
            steps {
            sh 'snyk test'
            }
        }
        stage('Start Docker Compose') {
             steps {
                sh 'docker-compose up -d'
             }
        }
    }
}