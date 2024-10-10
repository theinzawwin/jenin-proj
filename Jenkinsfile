pipeline {
    agent any
    tools{
        maven 'maven_3_8_3'
    }
    environment {
                    DOCKER_HUB_CREDENTIALS = credentialsId
                }
    stages{
        stage('Maven Clean Install'){
            steps{
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://gitlab.com/code-with-bisky/spring-boot-for-beginners.git']])
                sh 'mvn -version'
                sh 'mvn clean install'
            }
        }
        stage('Build Docker Image'){
            steps{
                script{
                    sh 'docker build -t trust-spring-jenkin .'
                }
            }
        }
        stage('Push Docker Image to Dockerhub'){
            steps{
                script{
                   withCredentials([string(credentialsId: 'DOCKER_PASSWORD', variable: 'DOCKER_PASSWORD')]) {
                sh 'docker login -u theinzawwin -p ${${credentials('theinzawwin-dockerhub').getPassword()}'
}
                   sh 'docker push theinzawwin/trust-spring-jenkin'
                }
            }
        }
    }
}