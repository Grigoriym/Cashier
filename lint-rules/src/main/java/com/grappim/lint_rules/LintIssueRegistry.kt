package com.grappim.lint_rules

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.Issue

class LintIssueRegistry : IssueRegistry() {

    override val issues: List<Issue> = listOf(ViewIdPrefixIssue)
}