package com.grappim.lint_rules

import com.android.tools.lint.detector.api.*
import org.w3c.dom.Attr
import java.util.*

val ViewIdPrefixIssue = Issue.create(
    id = "ViewIdPrefix",
    briefDescription = "Use v prefix",
    explanation = "Kotlin Synthetics fix",
    category = Category.INTEROPERABILITY_KOTLIN,
    priority = 5,
    severity = Severity.WARNING,
    implementation = Implementation(
        ViewIdPrefixDetector::class.java,
        Scope.RESOURCE_FILE_SCOPE
    )
)

class ViewIdPrefixDetector : LayoutDetector() {

    private val validPrefixes = setOf("v")

    override fun getApplicableAttributes(): Collection<String> = listOf("id")

    override fun visitAttribute(context: XmlContext, attribute: Attr) {
        if (attribute.value.getPrefix() !in validPrefixes) {
            context.report(
                issue = ViewIdPrefixIssue,
                location = context.getLocation(attribute),
                message = "Incorrect view id prefix",
                quickfixData = buildAddLintFix(attribute)
            )
        }
    }

    private fun String.getPrefix() = getId().takeWhile {
        it.isLowerCase()
    }

    private fun String.getId() = takeLastWhile {
        it != '/'
    }

    private fun buildAddLintFix(attribute: Attr): LintFix {
        val correctId = "v${
            attribute.value.getId()
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
        }"
        return fix()
            .name("Add `v` prefix")
            .replace()
            .pattern("/(\\S+)")
            .with(correctId)
            .build()
    }
}