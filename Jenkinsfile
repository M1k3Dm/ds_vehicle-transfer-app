pipeline {
    agent any

    environment {
        EMAIL_TO = "argirispaok@gmail.com"
    }

     stages {
        
        /*stage('Checkout') {
            steps {
                git branch: 'master', url: 'git@github.com:M1k3Dm/ds_vehicle-transfer-app.git'
            }
        } */

        /*stage('Test') {
            steps {
                sh './mvnw test'
            }
        }*/

        stage('run ansible pipeline') {
            steps {
                build job: 'ansible'
            }
        }

        stage('Install postgres') {
            steps {
                sh '''
                    export ANSIBLE_CONFIG=~/workspace/ansible/ansible.cfg
                    ansible-playbook -i ~/workspace/ansible/hosts.yaml -l gcloud-db-server ~/workspace/ansible/playbooks/postgres.yaml
                '''
            }
        }

        stage('Deploy spring boot app') {
            steps {
                sh '''
                    export ANSIBLE_CONFIG=~/workspace/ansible/ansible.cfg
                    ansible-playbook -i ~/workspace/ansible/hosts.yaml -l gcloud-app-server ~/workspace/ansible/playbooks/springboot.yaml
                '''
            }
        }
    }

    /*post {
        always {
            mail  to: "${EMAIL_TO}", body: "Project ${env.JOB_NAME} <br>, Build status ${currentBuild.currentResult} <br> Build Number: ${env.BUILD_NUMBER} <br> Build URL: ${env.BUILD_URL}", subject: "JENKINS: Project name -> ${env.JOB_NAME}, Build -> ${currentBuild.currentResult}"
        }
    }*/
}