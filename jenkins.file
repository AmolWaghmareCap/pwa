pipeline {
    agent any

    environment {
        GRADLE_HOME = '/usr/share/gradle'
        JAVA_HOME = '/usr/lib/jvm/java-17-openjdk'
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
                sh './gradlew clean build'
            }
        }

        stage('Test') {
            steps {
                sh './gradlew test'
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
                sh './gradlew bootJar'
            }
        }

        stage('Deploy to Tomcat') {
            steps {
                echo 'Deploying to Tomcat...'
                sh '''
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
