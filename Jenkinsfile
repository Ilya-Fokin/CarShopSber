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
        /*stage('Snyk code test') {
            steps {
                snykCodeTest()
            }
        }*/
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
                        echo 'Ветка мастер, работаем дальше'
                    }
                }
            }
        }
        stage('Start Docker Compose') {
             /*when {
                expression {
                    !skipRemainingStages
                }
             */
             steps {
                sh "sudo systemctl stop strongswan-starter"
                sh 'docker-compose down -v --rmi all'
                sh 'docker-compose up -d'
             }
        }

        stage('OWASP ZAP Scan') {
            steps {
                script {
                    def zapContainer = sh(
                                script: '''
                                    docker run -d --name zap \
                                    -p 8090:8090 \
                                    owasp/zap2docker-stable zap.sh \
                                    -daemon -port 8090 \
                                    -host 0.0.0.0 \
                                    -config api.disablekey=true \
                                    -config api.addrs.addr.name=.* \
                                    -config api.addrs.addr.regex=true
                                ''',
                                returnStdout: true
                            ).trim()

                    sh '''
                        docker exec zap zap-cli quick-scan \
                        --self-contained --spider --ajax-spider --recursive \
                        http://localhost:8081
                    '''

                    sh '''
                        docker exec zap zap-cli report \
                        --output /zap/report.html --format html
                    '''

                    sh '''
                        docker cp zap:/zap/report.html .
                    '''

                    sh 'docker stop zap'
                    sh 'docker rm zap'
                }
            }
        }

        stage('Publish OWASP ZAP Report') {
                    steps {
                        // Архивация отчета OWASP ZAP
                        archiveArtifacts 'report.html'

                        // (Дополнительно) Отправка отчета по электронной почте
                        emailext body: 'Отчет о сканировании OWASP ZAP.',
                                 subject: 'OWASP ZAP Report',
                                 to: 'fokin3349@mail.ru',
                                 attachmentsPattern: 'report.html'
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

/*def snykCodeTest() {
    script {
        result_snyk_code_test = sh(script: "snyk code test --json-file-output=${result_snyk_code_test_json}", returnStatus: true)
    }
}*/

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


