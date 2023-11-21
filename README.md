# Anabada : 지역기반 물물교환 플랫폼 

E2E-Project By [Kernel360](https://github.com/Kernel360)

## 소개

_**동네 주민들과 물건을 바꾸고 나눌 수 있는 지역기반 물물교환 플랫폼.**_

> 현재 카카오톡 로그인 기능이 구현된 상태이지만 비즈앱으로 전환하지 않아 필요한 정보들을 모두 받아올 수 없어 테스트 하실 수 없습니다. 
> (비즈앱으로 전환시 사업자 등록 번호 필요) 수요일 서비스 테스트 시현시 해당 기능 테스트를 원하신다면 
> 아래 멤버에게 요청 해주시길 바랍니다.

 웹 사이트는 다음 링크에서 접속하실 수 있습니다. [AnabadA](https://anabada.shop/)

## 멤버

| Backend                               | Backend                             | Backend                           |
|---------------------------------------|-------------------------------------|-----------------------------------|
| [병룡](https://github.com/fingersdanny) | [윤선](https://github.com/yoonseon12) | [호윤](https://github.com/Uknow928) |

## 기술 스택

- Backend
    - Java 17
    - Spring Boot 2.7
    - Spring Data JPA / QueryDSL
    - Spring Security / JWT / OAuth 2.0 Client
    - Spring Cloud OpenFeign
- Frontend
    - HTML / CSS
    - Javascript / Jquery
- DB
    - MySQL
- Cache
    - Spring Data Redis

## 프로젝트 아키텍쳐
![anabada-system-architecture](src/main/resources/readme/Anabada%20System%20Architecture.png)

## ERD
![anabada-erd](src/main/resources/readme/Anabada%20ERD.png)

## 개발 컨벤션

### Branch Strategy

![anabada-github-flowchart](src/main/resources/readme/Anabada%20Github%20Flowchart.png)

1. local develop 브랜치에서 필요한 기능을 구현하기 위해 새로운 브랜치(feat, fix ..)를 생성한다.
2. 작업 완료 후 작업한 브랜치에서(feat, fix ..) commit을 진행한 후 local develop 브랜치로 merge한다.
3. upstream main에 변경사항이 있는지 확인 후 origin develop으로 push한다.
    1. 변경사항이 있을 경우 upstream main에서 local main으로 pull을 진행하고 develop으로 merge
        1. 충돌이 발생할 경우 해당 부분을 해결 후 develop 브랜치를 remote develop으로 push
4. 깃허브 웹에서 origin develop 브랜치의 커밋사항을 upstream develop에 반영하기 위해  PR을 요청한다.
5. 코드리뷰가 완료되면 리뷰 담당자가 PR내용을 upstream develop에 반영한다.
6. upstream develop의 반영사항을 upstream main에 반영하기 위해 merge PR을 요청한다.
7. merge 담당자가 upstream develop의 반영사항을 upstream main에 반영한다.
8. upstream main에 반영되면 upstream main → local main에 pull을 진행하여 현재 운영중인 서비스 상태를 local main에서 유지한다.
9. local main → local develop에 merge를 진행하여 개발 브랜치에 변경 사항을 반영한다.

### Format

  아래 링크에 있는 formatter를 다운로드하여 IntelliJ IDEA에 적용

[캠퍼스 핵데이 Java 코딩 컨벤션](https://naver.github.io/hackday-conventions-java/#_intellij)

---
## Pull Request

_**개발 작업 진행 간 아래와 같은 Pull Request Template을 사용하였습니다.**_

### 😄 PR을 보내기 전에 확인해주세요!!

  - 보내는`branch`와 받는`branch`를 확인해 주세요!`develop`-->`develop`이 되어야 합니다.
  - `Reviewers`를 지정해 주세요. 모든 팀원이 변경 사항을 확인할 수 있도록 지정해 주세요.
  - `Assignees`를 지정해 주세요. 본인이 작업한 PR이라면 본인만 / 페어가 있다면 페어까지 함께 지정해 주세요.
  - `Labels`를 지정해 주세요.
  - `Development`에서`issues`를 지정해 주세요.
  - `Project`에서`E2E1-Anabada`를 고르시고`In Progress`로 바꿔주세요.
  - 정리된 컨벤션은 [여기](https://www.notion.so/c128f93c878247698d9b4d2c5a65ffe2?pvs=21)에서 확인해 주시고 질문이 있다면 [팀장](https://github.com/fingersdanny)에게 문의해 주세요.

### PR 제목

> PR 성격에 따라서 작성해 주세요.
- `upstream/develop`에서`upstream/main`로 보내는 Pull Request일 경우
    - merge : develop #추가된 PR 번호 into main
- `origin/develop`에서 `upstream/develop`로 보내는 Pull Request일 경우
    - feat : 추가된 기능에 대한 내용
    - chore : 추가된 문서 / 설명에 대한 내용
    - fix : 수정한 내용
    - 이외의 내용은 PR 올릴 때 우측 중단의``labels``를 참고하여 작성해 주세요.

### 주요 변경 사항 🛠️

> 주요 변경 사항은 다음을 포함해 주세요.
- Controller / Service / Repository 등에 추가된 메서드 명 및 기능을 추가해 주세요.
- 새로 추가된 기능들에 대한 설명을 포함해 주세요.
- 수정 사항이 있다면 세부 내용을 적어주세요. 다음 예시를 참고해 주세요.
    - `TradeOfferController` API endpoint 수정

### 질문 ❗

- 개발에 궁금한 점이 있다면 팀원들과 같이 고민해볼 수 있도록 Comment를 남겨주세요.
- 프로젝트에 대한 어떤 질문도 괜찮습니다!

---

## Commit

### 🤖 Commit Message는 다음과 같이 작성해주세요!

- feat: 어떤 특정 어플리케이션에 더할 새로운 feature
- fix: 어떤 오류 해결(fix)
- style: 스타일과 연관된 feature나 업데이트들
- refactor: 코드 베이스의 특정 부분을 재정렬(refactoring)
- test: 테스트와 관련된 모든 것
- chore: 정규 코드 유지보수. 문서 수정
- bug : 뭐가 잘 안돼요..

```
feat : DB를 기반으로 Entity 재설계
```

---
## Issues

### 👻 PR 전 Issue 에 등록 후 개발!

> Assignees : 본인을 포함한 페어  
> Labels : 아래에서 해당하는 레이블들을 모두 골라주세요.  
> Board : E2E1-Anabada 선택
- feat: 어떤 특정 어플리케이션에 더할 새로운 feature
- fix: 어떤 오류 해결(fix)
- style: 스타일과 연관된 feature나 업데이트들
- refactor: 코드 베이스의 특정 부분을 재정렬(refactoring)
- test: 테스트와 관련된 모든 것
- chore: 정규 코드 유지보수. 문서 수정
- bug: 에러, 버그 등…
- merge: 브랜치 간 병합



---
## Package

다음과 같은 도메인형 구조를 사용합니다.

```
domain
 ⎿ member
   ⎿ api
   ⎿ dto
   ⎿ repository
   ⎿ entity
   ⎿ service
        
 ⎿ trade
   ⎿ api
   ⎿ dto
   ⎿ repository
   ⎿ entity
   ⎿ service

global
 ⎿ commons
   ⎿ domain
   ⎿ entity
 ⎿ config
 ⎿ error
 ⎿ interceptor
 ⎿ utils
 ⎿ jwt
```

[[아키텍쳐] 패키지 구조 : 계층형 VS 도메인형 어떤 것을 선택할까?](https://ksh-coding.tistory.com/96)

---
## DTO

> 기본적인 응답과 요청의 경우 : 하는 일 (동사) + 해당 Entity + Response / Request  
> 예시 : Find + Member + Response
> 
> 특수한 경우 
> > 여러 번 재사용 될 수 있는 경우 : 하는 일 (동사) + 해당 Entity + Dto  
> > 예시 : Find + TradeOffer + Dto
> 
> > 통계에 필요한 경우 : Entity에서 데이터를 불러오는 기준 + Dto  
> > 예시 : AgeGroup + Dto
---
## API
[Swagger UI](https://anabada.shop/swagger-ui/)에서 API 명세서를 확인하실 수 있습니다. 
