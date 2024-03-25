pipeline {
    agent { docker { image 'maven:3.9.6-eclipse-temurin-17-alpine' } }
    stages {
        stage('build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('snyk test') {
            steps {
                   echo 'Testing...'
                   snykSecurity(
                        snykInstallation: 'snyk@latest',
                        snykTokenId: 'snyk-api-token',
                   )
            }
        }
        stage('Start Docker Compose') {
             steps {
                sh 'docker-compose up -d'
             }
        }
    }
}