pipeline {
    agent any

    environment {
        GRADLE_HOME = '/usr/share/gradle'
        JAVA_HOME = ''
        DEPLOY_PATH = '/opt/tomcat/webapps'
    }

    stages {


        stage('Detect Java') {
            steps {
                bat 'where java'
            }
        }

        stage('Set JAVA_HOME') {
            steps {
                script {
                def javaPath = bat(script: 'where java', returnStdout: true).trim()
                def javaHome = javaPath.replaceAll('\\\\bin\\\\java.exe', '')
                env.JAVA_HOME = javaHome
                echo "JAVA_HOME set to: ${env.JAVA_HOME}"
                }
            }
        }

        stage('Checkout') {
            steps {
                git url: 'https://github.com/AmolWaghmareCap/pwa.git', branch: 'master'
            }
        }

        stage('Build') {
            steps {
                bat './gradlew clean build'
            }
        }

        stage('Test') {
            steps {
                bat './gradlew test'
            }
        }

        stage('Code Quality') {
            steps {
                // Example: SonarQube or Checkstyle
                echo 'Running code quality checks...'
            }
        }

        stage('Package') {
            steps {
                bat './gradlew bootJar'
            }
        }

        stage('Run App') {
            steps {
                bat 'start "" java -jar build\\libs\\myapp.jar'
            }
        }

    }

    post {
        success {
            echo 'Deployment successful!'
        }
        failure {
            echo 'Deployment failed!'
        }
    }
}
