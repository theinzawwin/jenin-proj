pipeline {
    agent any
    tools {
        maven 'maven_3_8_3'
    }

    stages {
        stage('Maven Clean Install') {
            steps {
                script {
                    try {
                        checkout([$class: 'GitSCM',
                                  branches: [[name: '*/main']],
                                  userRemoteConfigs: [[url: 'https://github.com/theinzawwin/jenin-proj.git', credentialsId: 'theinzawwin-github']],
                                  extensions: []
                        ])
                    } catch (Exception e) {
                        echo "TZW Failed to checkout repository: ${e.message}"
                    }
                }
            }
        }

       stage('Maven Build and Install') {
           steps {
               bat 'mvn clean install package'
           }
       }

       stage('Build Docker Image') {
           steps {
               bat 'docker build -t trust-spring-jenkin .'
           }
       }

       stage('Push Docker Image to Dockerhub') {
           steps {
               script {
                                   // Use usernamePassword for DockerHub login
                                   withCredentials([usernamePassword(credentialsId: 'theinzawwin-dockerhub', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASSWORD')]) {
                                       bat 'docker login -u %DOCKER_USER% -p %DOCKER_PASSWORD%'
                                   }
                                   bat 'docker push theinzawwin/trust-spring-jenkin'
                               }
           }
       }
    }
}
