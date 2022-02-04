package com.grappim.detekt.rules

import io.gitlab.arturbosch.detekt.api.*
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtIfExpression

class WhenInsteadOfIfRule : Rule() {

    override val issue: Issue = Issue(
        id = "WhenInsteadOfIf",
        severity = Severity.Style,
        description = "Use when instead of it",
        debt = Debt.FIVE_MINS
    )

    override fun visitIfExpression(expression: KtIfExpression) {
        super.visitIfExpression(expression)
        if (expression.textContains('\n') && expression.`else` != null) {
            report(
                CodeSmell(
                    issue = issue,
                    entity = Entity.from(expression as PsiElement),
                    message = "Use when instead of if"
                )
            )
        }
    }

}