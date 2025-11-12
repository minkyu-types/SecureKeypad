package dev.loki.securekb.security

import android.content.Context
import android.content.pm.PackageManager
import java.io.File

/**
 * 루팅 탐지 클래스
 * 루팅된 기기인지 여러 방법으로 탐지
 *
 * 이 파일은 Claude code로 생성한 코드입니다
 */
class RootDetector(private val context: Context) {

    /**
     * 루팅 여부 종합 체크
     */
    fun isRooted(): Boolean {
        return checkRootBinaries() ||
                checkRootApps() ||
                checkSuCommand() ||
                checkBusyBox() ||
                checkRootFiles()
    }

    /**
     * 1. 루팅 관련 바이너리 파일 존재 확인
     */
    private fun checkRootBinaries(): Boolean {
        val rootBinaries = arrayOf(
            "/system/bin/su",
            "/system/xbin/su",
            "/system/app/Superuser.apk",
            "/data/local/xbin/su",
            "/data/local/bin/su",
            "/system/sd/xbin/su",
            "/system/bin/failsafe/su",
            "/data/local/su",
            "/su/bin/su"
        )

        return rootBinaries.any { path ->
            File(path).exists()
        }
    }

    /**
     * 2. 루팅 관련 앱 설치 여부 확인
     */
    private fun checkRootApps(): Boolean {
        val rootApps = arrayOf(
            "com.noshufou.android.su",
            "com.noshufou.android.su.elite",
            "eu.chainfire.supersu",
            "com.koushikdutta.superuser",
            "com.thirdparty.superuser",
            "com.yellowes.su",
            "com.topjohnwu.magisk"
        )

        return rootApps.any { packageName ->
            isPackageInstalled(packageName)
        }
    }

    /**
     * 3. su 명령어 실행 가능 여부 확인
     */
    private fun checkSuCommand(): Boolean {
        return try {
            val process = Runtime.getRuntime().exec(arrayOf("which", "su"))
            val reader = process.inputStream.bufferedReader()
            val result = reader.readText().trim()
            result.isNotEmpty()
        } catch (e: Exception) {
            false
        }
    }

    /**
     * 4. BusyBox 설치 여부 확인
     */
    private fun checkBusyBox(): Boolean {
        return try {
            val process = Runtime.getRuntime().exec(arrayOf("which", "busybox"))
            val reader = process.inputStream.bufferedReader()
            val result = reader.readText().trim()
            result.isNotEmpty()
        } catch (e: Exception) {
            false
        }
    }

    /**
     * 5. 루팅 관련 파일/디렉토리 존재 확인
     */
    private fun checkRootFiles(): Boolean {
        val rootPaths = arrayOf(
            "/system/app/Superuser.apk",
            "/sbin/su",
            "/system/bin/.ext/.su",
            "/system/xbin/daemonsu",
            "/system/etc/init.d/99SuperSUDaemon",
            "/dev/com.koushikdutta.superuser.daemon/",
            "/system/xbin/sugote"
        )

        return rootPaths.any { path ->
            File(path).exists()
        }
    }

    /**
     * 패키지 설치 여부 확인
     */
    private fun isPackageInstalled(packageName: String): Boolean {
        return try {
            context.packageManager.getPackageInfo(packageName, 0)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }

    /**
     * 루팅 탐지 결과 상세 정보
     */
    fun getRootDetectionDetails(): RootDetectionResult {
        return RootDetectionResult(
            isRooted = isRooted(),
            hasSuBinary = checkRootBinaries(),
            hasRootApps = checkRootApps(),
            canExecuteSu = checkSuCommand(),
            hasBusyBox = checkBusyBox(),
            hasRootFiles = checkRootFiles()
        )
    }
}

/**
 * 루팅 탐지 결과 데이터 클래스
 */
data class RootDetectionResult(
    val isRooted: Boolean,
    val hasSuBinary: Boolean,
    val hasRootApps: Boolean,
    val canExecuteSu: Boolean,
    val hasBusyBox: Boolean,
    val hasRootFiles: Boolean
)