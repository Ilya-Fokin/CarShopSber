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
                snykSecurity(
                      snykInstallation: 'snyk@latest',
                      snykTokenId: 'snyk-api-token',
                      monitorProjectOnBuild: 'true'
                )
                sh 'snyk test'
            }
        }
    }
}