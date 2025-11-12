# Contributing to SecureKeypad

이 마크다운 파일은 Claude Code로 생성되었습니다.

SecureKeypad에 기여해주셔서 감사합니다! 🎉

## 행동 강령

이 프로젝트는 모든 기여자가 서로를 존중하고 배려하는 환경을 만들기 위해 노력합니다.

## 기여 방법

### 버그 리포트

버그를 발견하셨나요? 다음 정보를 포함하여 Issue를 생성해주세요:

- 명확하고 설명적인 제목
- 버그 재현 단계
- 예상 동작 vs 실제 동작
- 스크린샷 (가능한 경우)
- 환경 정보 (Android 버전, 기기 모델)

### 기능 제안

새로운 기능을 제안하고 싶으신가요?

1. Issue를 생성하여 제안 내용을 설명해주세요
2. 왜 이 기능이 필요한지 설명해주세요
3. 가능하다면 구현 방법도 제안해주세요

### Pull Request 프로세스

1. **Fork** - 프로젝트를 Fork 합니다
2. **Branch** - 기능 브랜치를 생성합니다
   ```bash
   git checkout -b feature/amazing-feature
   ```
3. **Commit** - 변경사항을 커밋합니다
   ```bash
   git commit -m 'feat: Add amazing feature'
   ```
4. **Push** - 브랜치에 푸시합니다
   ```bash
   git push origin feature/amazing-feature
   ```
5. **Pull Request** - Pull Request를 생성합니다

### 커밋 메시지 규칙

[Conventional Commits](https://www.conventionalcommits.org/)를 따릅니다:

- `feat`: 새로운 기능 추가
- `fix`: 버그 수정
- `docs`: 문서 변경
- `style`: 코드 포맷팅, 세미콜론 누락 등
- `refactor`: 코드 리팩토링
- `test`: 테스트 코드
- `chore`: 빌드 작업, 패키지 매니저 설정 등

예시:
```
feat: Add auto-shuffle on input feature
fix: Fix root detection false positives
docs: Update README with new examples
```

### 코드 스타일

- **Kotlin** - [Kotlin Coding Conventions](https://kotlinlang.org/docs/coding-conventions.html) 준수
- **들여쓰기** - 4 spaces
- **네이밍**:
  - Class: PascalCase
  - Function/Variable: camelCase
  - Constant: UPPER_SNAKE_CASE

### 테스트

- 새로운 기능에는 테스트를 추가해주세요
- 모든 테스트가 통과하는지 확인해주세요
  ```bash
  ./gradlew test
  ```

## 개발 환경 설정

```bash
# 1. Repository 클론
git clone https://github.com/yourusername/SecureKeypad.git
cd SecureKeypad

# 2. Android Studio에서 프로젝트 열기

# 3. Gradle 동기화 실행

# 4. 앱 실행
```

## 질문이 있으신가요?

Issue를 통해 질문해주세요!

## 감사의 말

모든 기여자분들께 감사드립니다! 🙌