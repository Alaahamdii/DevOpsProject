pipeline {
    agent any

    stages {
           stage('Build') {
            steps {
                // Get code from a GitHub repository
               git branch: 'main', url: 'https://github.com/Alaahamdii/stage-spring-boot'
                }
            }

        stage('Backend Build and Deploy') {
            steps {
                // Build the backend
                //sh script 'mvn clean install'
                echo "hello"
            }
        }
    }
}
