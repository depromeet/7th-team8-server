# NONO PLAN API

## 로컬 환경 설정
### Cloud SQL(GCP) 연동
- core/src/main/resources/application-local.yaml
```yaml
spring:
  cloud:
    gcp:
      sql:
        credentials:
          location: file:${프로젝트_경로}/credentials/dev-sql-credentials.json
```