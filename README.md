# 🔐 SecureKeypad

이 마크다운 파일은 Claude Code로 생성되었습니다.

안전한 입력을 위한 Android 보안 키패드 라이브러리

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Platform](https://img.shields.io/badge/platform-Android-green.svg)](https://www.android.com)
[![API](https://img.shields.io/badge/API-24%2B-brightgreen.svg)](https://android-arsenal.com/api?level=24)

## ✨ 주요 기능

### 🛡️ 보안 기능
- **입력값 마스킹**: 입력 내용을 `●●●`로 표시하여 어깨 너머 엿보기 방지
- **화면 캡처 방지**: 스크린샷 및 화면 녹화 차단 (`FLAG_SECURE`)
- **키패드 셔플**: 매번 다른 배열로 키패드 재배치하여 입력 패턴 노출 방지
- **루팅 탐지**: 루팅된 기기에서 앱 실행 차단
- **리플 효과 제거**: 버튼 클릭 시 시각적 피드백 제거로 입력 추론 방지

### 🎨 UI/UX 기능
- **Jetpack Compose 기반**: 현대적이고 선언적인 UI
- **커스터마이징 가능**: 색상, 크기, 레이아웃 커스터마이징 지원
- **랜덤/순차 셔플**: 2가지 셔플 알고리즘 제공
- **자동 셔플**: 입력마다 자동으로 키패드 재배치 옵션

### 🏗️ 아키텍처
- **단일 책임 원칙 (SRP)** 준수
- **의존성 주입** 지원
- **테스트 용이성**: 각 컴포넌트 독립적으로 테스트 가능

## 📦 설치

### Gradle (Kotlin DSL)

```kotlin
dependencies {
    implementation("dev.loki:securekeypad:1.0.0")
}
```

### Gradle (Groovy)

```groovy
dependencies {
    implementation 'dev.loki:securekeypad:1.0.0'
}
```

## 🚀 빠른 시작

### 1. 기본 사용법

```kotlin
@Composable
fun MyScreen() {
    var inputText by remember { mutableStateOf("") }

    SecureKeypad(
        onButtonClick = { button ->
            when (button) {
                is KeypadButton.KeypadContentButton.KeypadItemButton -> {
                    inputText += button.value
                }
                is KeypadButton.KeypadUtilButton.KeypadDeleteButton -> {
                    inputText = inputText.dropLast(1)
                }
                is KeypadButton.KeypadUtilButton.KeypadConfirmButton -> {
                    // 입력 완료 처리
                }
            }
        }
    )
}
```

### 2. 입력값 마스킹

```kotlin
TextField(
    value = inputText,
    onValueChange = { inputText = it },
    visualTransformation = PasswordVisualTransformation(),
    readOnly = true
)
```

### 3. 화면 캡처 방지

```kotlin
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 화면 캡처 방지
        window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE
        )
    }
}
```

### 4. 루팅 탐지

```kotlin
val rootDetector = RootDetector(context)

if (rootDetector.isRooted()) {
    // 루팅된 기기 - 앱 종료 또는 경고
    Toast.makeText(context, "루팅된 기기에서는 사용할 수 없습니다", Toast.LENGTH_LONG).show()
    finish()
}
```

## 📚 상세 문서

### 키패드 셔플 설정

```kotlin
val shuffleManager = KeypadShuffleManager()

// 랜덤 셔플
shuffleManager.setShuffleAlgorithm(ShuffleAlgorithm.RANDOM)

// 순차 정렬
shuffleManager.setShuffleAlgorithm(ShuffleAlgorithm.SEQUENTIAL)
```

### 자동 셔플 설정

```kotlin
val config = KeypadConfig(
    shuffleOnEachInput = true  // 입력마다 자동 셔플
)
keypadConfigManager.updateConfig(config)
```

### 커스텀 컨트롤러 사용

```kotlin
val customController = SecureKeypadControllerImpl(
    shuffleManager = KeypadShuffleManager(),
    initializationStrategy = KeypadInitializationStrategy(...),
    buttonPressHandler = ButtonPressHandler(...)
)

SecureKeypad(
    onButtonClick = { ... },
    controller = customController
)
```

## 🔒 보안 수준

| 기능 | 상태 | 설명 |
|------|------|------|
| 입력값 마스킹 | ✅ | 화면에 `●●●`로 표시 |
| 화면 캡처 방지 | ✅ | FLAG_SECURE 적용 |
| 키패드 셔플 | ✅ | UI 랜덤화 |
| 루팅 탐지 | ✅ | 5가지 방법으로 탐지 |
| 리플 효과 제거 | ✅ | 시각적 피드백 차단 |
| 메모리 암호화 | ⚠️ | 추후 추가 예정 |
| 안티 키로깅 | ⚠️ | 추후 추가 예정 |
| 입력 타이밍 랜덤화 | ⚠️ | 추후 추가 예정 |

## 🏛️ 아키텍처

```
SecureKeypad (UI Layer)
    ├── SecureKeypadController (Interface)
    │   └── SecureKeypadControllerImpl
    │       ├── KeypadInitializationStrategy
    │       ├── ButtonPressHandler
    │       └── KeypadShuffleManager
    └── Components
        ├── KeypadButtonItem
        ├── DeleteButton
        ├── ShuffleButton
        └── ConfirmButton

Security Layer
    └── RootDetector
```

## 🤝 기여하기

기여는 언제나 환영합니다! 다음 절차를 따라주세요:

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 라이선스

이 프로젝트는 Apache License 2.0 라이선스를 따릅니다. 자세한 내용은 [LICENSE](LICENSE) 파일을 참조하세요.

## 👨‍💻 제작자

**Loki** - [@github](https://github.com/yourusername)

## 🙏 감사의 말

- Jetpack Compose
- Material Design 3
- Kotlin Coroutines

## 📮 연락처

프로젝트 링크: [https://github.com/yourusername/SecureKeypad](https://github.com/yourusername/SecureKeypad)

---

⭐ 이 프로젝트가 도움이 되셨다면 Star를 눌러주세요!
