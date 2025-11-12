package dev.loki.securekb

import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import dev.loki.securekb.component.SecureKeyboardScreen
import dev.loki.securekb.security.RootDetector
import dev.loki.securekb.ui.theme.SecureKeyboardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 보안 체크: 루팅 탐지
        if (!performSecurityChecks()) {
            return
        }

        // 화면 캡처 방지 (스크린샷, 화면 녹화 차단)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE
        )

        enableEdgeToEdge()
        setContent {
            SecureKeyboardTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SecureKeyboardScreen()
                }
            }
        }
    }

    /**
     * 보안 체크 수행
     * @return true: 보안 체크 통과, false: 보안 위협 감지
     */
    private fun performSecurityChecks(): Boolean {
        val rootDetector = RootDetector(this)

        // 루팅 탐지
        if (rootDetector.isRooted()) {
            showSecurityWarning("보안 위협 감지", "루팅된 기기에서는 보안을 위해 이 앱을 사용할 수 없습니다.")
            finish() // 앱 종료
            return false
        }

        return true
    }

    /**
     * 보안 경고 표시 및 앱 종료
     */
    private fun showSecurityWarning(title: String, message: String) {
        Toast.makeText(this, "$title\n$message", Toast.LENGTH_LONG).show()
    }
}