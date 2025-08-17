# Microservices Demo with Distributed Tracing

This demo demonstrates a microservices architecture with 3 services that communicate through REST APIs and implement distributed tracing with Elasticsearch/Kibana and Datadog monitoring.

## Architecture

```
Service1 (port 8081) 
    ↓ REST call
Service2 (port 8082)
    ↓ REST call  
Service3 (port 8083)
```

## Features

- **Hexagonal Architecture**: Clean architecture pattern implemented in all services
- **Distributed Tracing**: Using Spring Cloud Sleuth with Zipkin and Elasticsearch
- **Monitoring**: Datadog agent integration for metrics and APM
- **Logging**: Structured logging with trace IDs
- **Containerization**: Docker Compose setup for easy deployment

## Services

### Service1
- **Port**: 8081
- **Function**: Entry point, calls Service2
- **Tech Stack**: Spring Boot 3.5.4, WebFlux, OpenFeign

### Service2  
- **Port**: 8082
- **Function**: Middle layer, processes requests and calls Service3
- **Tech Stack**: Spring Boot 3.5.4, WebFlux, OpenFeign

### Service3
- **Port**: 8083
- **Function**: Terminal service, returns final response
- **Tech Stack**: Spring Boot 3.5.4, WebFlux

## Infrastructure

### Monitoring Stack
- **Elasticsearch**: 9200 (Data storage for traces)
- **Kibana**: 5601 (Visualization dashboard) 
- **Zipkin**: 9411 (Trace collection)
- **Datadog Agent**: 8126 (APM and metrics)

## Quick Start

### 1. Build Services
```bash
mvn clean package -DskipTests
```

### 2. Start Infrastructure
```bash
docker-compose up -d
```

### 3. Test the Flow
```bash
curl http://localhost:8081
```

Expected response:
```
Service2 processed: Hello from Service3! This is the final response in the chain.
```

### 4. View Traces

**Zipkin UI**: http://localhost:9411
**Kibana**: http://localhost:5601

## Environment Variables

### Setup
1. Copy the environment template:
```bash
cp .env.example .env
```

2. Edit `.env` file with your actual values:
```bash
# Datadog Configuration
DD_API_KEY=your_datadog_api_key_here
DD_SITE=datadoghq.eu
DD_ENV=dev

# Google OAuth2 Configuration  
GOOGLE_CLIENT_ID=your_google_client_id_here.apps.googleusercontent.com
GOOGLE_CLIENT_SECRET=your_google_client_secret_here

# Environment Configuration
ENVIRONMENT=dev
```

**Note**: The `.env` file is git-ignored for security. See [SECURITY.md](SECURITY.md) for detailed security guidelines.

## Local Development

To run services locally (without Docker):

1. Start Elasticsearch and Zipkin:
```bash
docker-compose up elasticsearch kibana zipkin -d
```

2. Run services in separate terminals:
```bash
# Terminal 1
cd service3 && mvn spring-boot:run

# Terminal 2  
cd service2 && mvn spring-boot:run

# Terminal 3
cd service1 && mvn spring-boot:run
```

## Trace Analysis

Each request generates traces that can be viewed in:

1. **Zipkin**: Real-time trace visualization
2. **Kibana**: Advanced trace analytics with Elasticsearch
3. **Datadog**: APM metrics and distributed tracing (if API key provided)

The logging pattern includes trace and span IDs:
```
[service-name,trace-id,span-id]
```

## Stopping the Demo

```bash
docker-compose down
```

To remove volumes:
```bash
docker-compose down -v
```