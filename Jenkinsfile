pipeline {
    agent any

    stages {
           stage('get code') {
            steps {
                // Get code from a GitHub repository
               git branch: 'main', url: 'https://github.com/Alaahamdii/DevOpsProject.git'
                }
            }

    stage('Backend Build') {
            steps {
                // Build the backend
                //sh script 'mvn clean install'
                echo "hello"
            }
        }
    }
}
