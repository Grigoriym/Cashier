package com.grappim.lint.ruleset.detekt

import com.grappim.detekt.rules.WhenInsteadOfIfRule
import io.gitlab.arturbosch.detekt.test.lint
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.shouldBe
import org.intellij.lang.annotations.Language

class WhenInsteadOfIfSpec : FreeSpec({
    val rule = WhenInsteadOfIfRule()

    "should not lint when" {
        @Language("kt")
        val code = """
            fun main() {
                when (2+2) {
                    4-> println("ok")
                    else -> println("impossible")
                }
            }
        """.trimIndent()

        rule.lint(code).shouldBeEmpty()
    }

    "should not lint one-line if" {
        @Language("kt")
        val code = """
            fun main() {
                if (2 + 2 == 4) println("ok") else println("impossible")
            }
        """.trimIndent()

        rule.lint(code).shouldBeEmpty()
    }

    "should not lint one-line if with braces" {
        @Language("kt")
        val code = """
            fun main() {
                if (2 + 2 == 4) { println("ok") } else { println("impossible") }
            }
        """.trimIndent()

        rule.lint(code).shouldBeEmpty()
    }

    "should lint multiline if" {
        @Language("kt")
        val code = """
            fun main() {
                if (2 + 2 == 4) { 
                    println("ok") 
                } else { 
                    println("impossible") 
                }
            }
        """.trimIndent()

        rule.lint(code).size shouldBe 1
    }

    "should not lint multiline if without else clause" {
        @Language("kt")
        val code = """
            fun main() {
                if (2 + 2 == 4) { 
                    println("ok") 
                }
            }
        """.trimIndent()

        rule.lint(code).shouldBeEmpty()
    }
}

)