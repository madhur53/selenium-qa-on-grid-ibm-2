pipeline {
agent any
tools { maven 'Maven-3.9' jdk 'JDK-17' }
environment { BASE_URL = 'https://the-internet.herokuapp.com' }
stages {
stage('Checkout') { steps { checkout scm } }
stage('Run Tests') {
steps { sh 'mvn clean test -Dbase.url=${BASE_URL} -q' }
post { always {
junit allowEmptyResults: true,
testResults: 'target/surefire-reports/*.xml'
} }
}
stage('Publish Report') {
steps { publishHTML([reportDir: 'target/surefire-reports',
reportFiles: 'index.html',
reportName: 'Test Report',
keepAll: true,
alwaysLinkToLastBuild: true,
allowMissing: false
]) }
}
}
post { success { echo 'All tests green' }
always { cleanWs() } }
}