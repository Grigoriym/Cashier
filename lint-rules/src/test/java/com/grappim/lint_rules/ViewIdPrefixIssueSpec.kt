package com.grappim.lint_rules

import com.android.tools.lint.checks.infrastructure.LintDetectorTest.xml
import com.android.tools.lint.checks.infrastructure.TestLintTask.lint
import com.android.tools.lint.detector.api.Issue
import io.kotest.core.spec.style.FreeSpec
import org.intellij.lang.annotations.Language

class ViewIdPrefixIssueSpec : FreeSpec({
    "should lint"{
        @Language("XML")
        val code = """
            <LinearLayout
            xmlns:android="http://schemas.android.com/apk/res/android"
            android:id="@+id/layout" 
            />
        """.trimIndent()

        ViewIdPrefixIssue.lintLayoutFile(code).expectWarningCount(1)
    }

    "should not lint"{
        @Language("XML")
        val code = """
            <LinearLayout
            xmlns:android="http://schemas.android.com/apk/res/android"
            android:id="@+id/vLayout" 
            />
        """.trimIndent()

        ViewIdPrefixIssue.lintLayoutFile(code).expectClean()
    }

    "should provide quick fix"{
        @Language("XML")
        val code = """
            <LinearLayout
            xmlns:android="http://schemas.android.com/apk/res/android"
            android:id="@+id/layout" 
            />
        """.trimIndent()

        ViewIdPrefixIssue.lintLayoutFile(code).expectFixDiffs("""
            Fix for res/layout/example.xml line 3: Add `v` prefix:
            @@ -3 +3
            - android:id="@+id/layout"
            + android:id="@+id/vLayout
        """.trimIndent())
    }
}) {
}

fun Issue.lintLayoutFile(file: String) = lint()
    .files(xml("res/layout/example.xml", file))
    .allowMissingSdk()
    .issues(this)
    .run()