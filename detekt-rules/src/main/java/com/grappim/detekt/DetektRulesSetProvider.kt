package com.grappim.detekt

import com.grappim.detekt.rules.WhenInsteadOfIfRule
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.RuleSet
import io.gitlab.arturbosch.detekt.api.RuleSetProvider

class DetektRulesSetProvider : RuleSetProvider {

    override val ruleSetId: String = "detekt-cashier-rules"

    override fun instance(config: Config): RuleSet =
        RuleSet(
            id = ruleSetId,
            rules = listOf(WhenInsteadOfIfRule())
        )
}