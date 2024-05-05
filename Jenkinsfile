pipeline {
    agent any

    environment {
    def api = '6c2b7175-768e-4293-a806-6848cc196ac0'
    def org = 'dd028e93-0359-4924-9511-3c4b525a82776'

    def result_snyk_test = 0
    def result_snyk_test_json = 'snyk_test.json'
    def result_snyk_test_html = 'snyk_test.html'

    def result_snyk_code_test = 0
    def result_snyk_code_test_json = 'snyk_code_test.json'
    def result_snyk_code_test_html = 'snyk_code_test.html'

    def skipRemainingStages = false

    zapPort = "8090"
    zapUrl = "http://localhost:${zapPort}"
    scanTarget = "http://localhost:8085"
    zapReport = "zap-report.html"

    def project_name = 'com.example:CarShopSber'
    }

    stages {
        stage('SCM') {
            steps {
                echo '' + env.BRANCH_NAME
            }
        }
        stage('Build') {
            steps {
                buildProject()
            }
        }
        stage('Snyk configure') {
            steps {
                snykConfigure()
            }
        }
        stage('Snyk test') {
            steps {
                snykTest()
            }
        }
        stage('Snyk code test') {
            steps {
                snykCodeTest()
            }
        }
        /*stage ("Check results") {
            steps {
                checkResultsSnykTest()
            }
        }*/
        stage ("Check branch") {
            steps {
                script {
                    if (env.BRANCH_NAME != 'master') {
                        echo '' + env.BRANCH_NAME
                        skipRemainingStages = true
                    } else {
                        echo 'Current branch is master'
                    }
                }
            }
        }
        stage('Start Docker Compose') {
             when {
                expression {
                    !skipRemainingStages
                }
             }

             steps {
                sh "sudo systemctl stop strongswan-starter"
                sh 'docker-compose down -v --rmi all'
                sh 'docker-compose up -d'
             }
        }

        stage('Start OWASP ZAP') {
                    steps {
                        script {
                            sh "zap.sh -daemon -port ${zapPort} -config api.key= &"
                        }
                    }
        }

        stage('Quick Scan with OWASP ZAP') {
                    steps {
                        script {
                            sh "zap-cli spider ${scanTarget}"
                            sh "zap-cli active-scan ${scanTarget}"
                        }
                    }
        }

        stage('Generate ZAP Report') {
                    steps {
                        script {
                            sh "zap-cli report --output zap-report.html"
                        }
                    }
        }

        stage('Check alerts') {
                    steps {
                        script {
                            def highSeverityAlerts = sh(
                                script: "zap-cli alerts -l Medium --exit-code False | wc -l",
                                returnStdout: true
                            ).trim().toInteger()

                            if (highSeverityAlerts > 0) {
                                emailext body: "Отчет OWASP ZAP о проведенной проверке.",
                                                                     subject: "OWASP ZAP Report",
                                                                     to: "fokin3349@mail.ru",
                                                                     attachmentsPattern: "${zapReport}",
                                                                     mimeType: 'text/html'

                                error "Найдено ${highSeverityAlerts} уязвимостей. Сборка остановлена."
                            }
                        }
                    }
                }


    }
     post {
            always {
                script {
                    sh "zap-cli -p ${zapPort} shutdown"
                }
            }
        }
}

def buildProject() {
    script {
        def mvn = tool 'maven'
        sh "${mvn}/bin/mvn clean package -DskipTests --no-transfer-progress"
    }
}

def snykConfigure() {
    script {
        sh "sudo systemctl start strongswan-starter"
        sh "snyk auth ${api}"
        sh "snyk config set org=${org}"
        sh "chmod +x mvnw"
    }
}

def snykTest() {
    script {
        result_snyk_test = sh(script: "snyk test --json-file-output=${result_snyk_test_json}", returnStatus: true)
        sh "snyk monitor"
    }
}

def snykCodeTest() {
    script {
        result_snyk_code_test = sh(script: "snyk code test --json-file-output=${result_snyk_code_test_json}", returnStatus: true)
    }
}

def checkResultsSnykTest() {
    script {
        //def recipients = emailextrecipients([ [$class: 'DevelopersRecipientProvider'],[$class: 'CulpritsRecipientProvider']])
        def recipients = 'fokin3349@mail.ru'
        echo "Developer Email : ${recipients}"

        if (result_snyk_test != 0 || result_snyk_code_test != 0) {
            if (result_snyk_test != 0) {
                sendResultHtml(result_snyk_test_json, result_snyk_test_html, recipients)
            }
            if (result_snyk_code_test != 0) {
                sendResultHtml(result_snyk_code_test_json,result_snyk_code_test_html, recipients)
            }
            error 'Snyk test обнаружил уязвимости в проекте. Pipline остановлен.'
        }
        else {
            echo 'Уязвимости не найдены, выполнение pipline продолжается'
        }
    }
}

def sendResultHtml(result_json_file, test_html_file, recipient) {
    script {
        sh "snyk-to-html -i ${result_json_file} -o ${test_html_file}"
        emailext body: 'Snyk обнаружил уязвимости в Вашем коде. Пожалуйста, ознакомьтесь с отчетом',
                 subject: 'Найдены уязвимости в Вашем коммите',
                 to: "${recipient}",
                 mimeType: 'text/html',
                 attachmentsPattern: "${test_html_file}"
    }
}


