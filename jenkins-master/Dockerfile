FROM jenkins/jenkins:lts-slim

USER root

COPY jenkins-master/plugins.txt /usr/share/jenkins/ref/plugins.txt

RUN apt-get update && \
    apt-get install -y --no-install-recommends \
        apt-transport-https \
        ca-certificates \
        curl \
        gnupg2 \
        software-properties-common && \
    curl -fsSL https://download.docker.com/linux/debian/gpg | apt-key add - && \
    apt-key fingerprint 0EBFCD88 && \
    add-apt-repository \
       "deb [arch=amd64] https://download.docker.com/linux/debian \
       $(lsb_release -cs) \
       stable" && \
    apt-get update && \
    apt-get -y --no-install-recommends install docker-ce && \
    rm -rf /var/lib/apt/lists/* /var/cache/apt/*  && \
    /usr/local/bin/install-plugins.sh $(tr '\n' ' ' < /usr/share/jenkins/ref/plugins.txt) && \ 
    chown -R jenkins /usr/share/jenkins/ref && \
    echo 2.0 > /usr/share/jenkins/ref/jenkins.install.UpgradeWizard.state

USER jenkins

ENV JAVA_OPTS $JAVA_OPTS -Djenkins.install.runSetupWizard=false -Dhudson.model.DirectoryBrowserSupport.CSP=

EXPOSE 8080 8443
