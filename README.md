# Docker/docker-compose

Useful commands for managing application with docker-compose

## Installation with docker-compose:

Build and run containers

```bash
docker-compose up --build
```

## Run

Simply run containers

```bash
docker-compose up
```

## Remove containers

Removes all containers

```bash
docker-compose down --all
```

## Delete volumes

Removes all volumes attached

```bash
docker-compose down -v
```

# Kubernetes


## Installation

Install application in Kubernetes environment

### Install database

Create PVC

```bash
kubectl apply -f k8s/postgres/postgres-pvc.yaml
```

Create postgres service pod

```bash
kubectl apply -f k8s/postgres/postgres-svc.yaml
```

Create postgres deployment pod

```bash
kubectl apply -f k8s/postgres/postgres-deployment.yaml
```

### Install spring

Create spring service pod

```bash
kubectl apply -f k8s/spring-vta/spring-svc.yaml
```

Create spring deployment pod 

```bash
kubectl apply -f k8s/spring-vta/spring-deployment.yaml
```

Create spring ingress

```bash
kubectl apply -f k8s/spring-vta/spring-ingress.yaml
```


