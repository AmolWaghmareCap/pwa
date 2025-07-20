pipeline {
    agent any

    environment {
        GRADLE_HOME = '/usr/share/gradle'
        JAVA_HOME = 'C:\\Program Files\\Java\\jdk-17'
        DEPLOY_PATH = '/opt/tomcat/webapps'
    }

    stages {
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

        stage('Deploy to Tomcat') {
            steps {
                echo 'Deploying to Tomcat...'
                bat '''
                    cp build/libs/*.jar $DEPLOY_PATH/app.jar
                    systemctl restart tomcat
                '''
            }
        }

        stage('Post-Deployment') {
            steps {
                echo 'Running post-deployment checks...'
                // Add health checks or smoke tests
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
