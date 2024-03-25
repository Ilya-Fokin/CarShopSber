pipeline {
    //agent { docker { image 'maven:3.9.6-eclipse-temurin-17-alpine' } }
    stages {
        stage('build') {
            steps {
                def mvn = tool 'maven';
                sh "${mvn}/bin/mvn clean package"
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