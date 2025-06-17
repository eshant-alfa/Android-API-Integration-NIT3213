package com.example.assignmentlast.ui.details

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.assignmentlast.R
import com.example.assignmentlast.databinding.ActivityDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener { finish() }


        loadEntityDetails()
    }

    private fun loadEntityDetails() {
        @Suppress("UNCHECKED_CAST")
        val entity = intent.getSerializableExtra("entity") as? HashMap<String, Any> ?: return

        val entityType = determineEntityType(entity)
        binding.toolbarTitle.text = "$entityType Details"
        binding.container.removeAllViews()

        val descriptionField = findDescriptionField(entity)
        val primaryFields = findPrimaryFields(entity)
        val statusFields = findStatusFields(entity)
        val remainingFields = entity.keys
            .filterNot { it in primaryFields || it in statusFields || it == descriptionField }
            .sorted()

        primaryFields.forEach { field ->
            if (entity.containsKey(field)) {
                val value = formatValue(entity[field])
                val isTitle = primaryFields.indexOf(field) == 0
                val isItalic = field.contains("scientific", ignoreCase = true)
                addLabelAndValue(
                    label = formatFieldName(field),
                    value = value,
                    isTitle = isTitle,
                    isItalic = isItalic,
                    topMargin = if (isTitle) 0 else dpToPx(16)
                )
            }
        }

        if (statusFields.isNotEmpty()) {
            val rowLayout = LinearLayout(this).apply {
                orientation = LinearLayout.HORIZONTAL
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply { topMargin = dpToPx(24) }
            }

            statusFields.forEach { field ->
                if (entity.containsKey(field)) {
                    val value = formatValue(entity[field])
                    val statusLayout = createStatusLayout(field, value)
                    val params = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
                    statusLayout.layoutParams = params
                    rowLayout.addView(statusLayout)
                }
            }

            if (rowLayout.childCount > 0) {
                binding.container.addView(rowLayout)
            }
        }

        remainingFields.forEach { field ->
            addLabelAndValue(
                label = formatFieldName(field),
                value = formatValue(entity[field]),
                topMargin = dpToPx(16)
            )
        }

        if (descriptionField != null && entity.containsKey(descriptionField)) {
            addLabelAndValue(
                label = formatFieldName(descriptionField),
                value = formatValue(entity[descriptionField]),
                topMargin = dpToPx(24)
            )
        }
    }

    private fun formatValue(value: Any?): String {
        return when (value) {
            is Double, is Float -> (value as Number).toLong().toString()
            is Int, is Long -> value.toString()
            is String -> value.trim()
            else -> value?.toString()?.trim() ?: ""
        }
    }

    private fun determineEntityType(entity: Map<String, Any>): String {
        return when {
            entity.containsKey("commonName") && entity.containsKey("scientificName") -> "Plant"
            entity.containsKey("species") && entity.containsKey("habitat") -> "Animal"
            entity.containsKey("dishName") || entity.containsKey("mealType") -> "Food"
            entity.containsKey("title") && entity.containsKey("author") -> "Book"
            else -> "Entity"
        }
    }

    private fun findPrimaryFields(entity: Map<String, Any>): List<String> {
        val result = mutableListOf<String>()
        listOf("dishName", "commonName", "species", "name", "title").firstOrNull { entity.containsKey(it) }?.let { result.add(it) }
        listOf("origin", "scientificName", "author", "subtitle").firstOrNull { entity.containsKey(it) }?.let { result.add(it) }
        return result
    }

    private fun findStatusFields(entity: Map<String, Any>): List<String> {
        val result = mutableListOf<String>()
        val statusKeywords = listOf(
            "mainIngredient", "mealType", "careLevel", "conservationStatus", "difficulty",
            "status", "lightRequirement", "habitat", "diet"
        )
        result.addAll(statusKeywords.filter { entity.containsKey(it) })
        result.addAll(entity.keys.filter {
            (it.contains("level", true) || it.contains("status", true) || it.contains("requirement", true)
                    || it.contains("type", true) || it.contains("ingredient", true)) && it !in result
        })
        return result.take(2)
    }

    private fun findDescriptionField(entity: Map<String, Any>): String? {
        return entity.keys.firstOrNull {
            it.contains("description", true) || it.contains("summary", true) || it.contains("about", true)
        }
    }

    private fun formatFieldName(field: String): String {
        return field.replaceFirstChar { it.uppercase() }
            .replace(Regex("([a-z])([A-Z])"), "$1 $2")
            .replace("_", " ")
    }

    private fun createStatusLayout(field: String, value: String): LinearLayout {
        val layout = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL }

        val label = TextView(this).apply {
            text = formatFieldName(field)
            textSize = 16f
            setTextColor(Color.parseColor("#2196F3"))
        }
        layout.addView(label)

        val shouldBeBadge = field.contains("level", true) || field.contains("status", true)
                || field.contains("difficulty", true)

        if (shouldBeBadge) {
            val (bgColor, textColor) = getStatusColors(field, value)
            val badge = TextView(this).apply {
                text = value.replaceFirstChar { it.uppercase() }
                textSize = 14f
                setTextColor(Color.parseColor(textColor))
                setPadding(dpToPx(16), dpToPx(8), dpToPx(16), dpToPx(8))
                val drawable = ContextCompat.getDrawable(this@DetailsActivity, R.drawable.badge_background)?.mutate()
                if (drawable is GradientDrawable) {
                    drawable.setColor(Color.parseColor(bgColor))
                    background = drawable
                }
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply { topMargin = dpToPx(8) }
            }
            layout.addView(badge)
        } else {
            val valueView = TextView(this).apply {
                text = value
                textSize = 14f
                setTextColor(Color.parseColor("#212121"))
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply { topMargin = dpToPx(8) }
            }
            layout.addView(valueView)
        }

        return layout
    }

    private fun getStatusColors(field: String, value: String): Pair<String, String> {
        return when (value.lowercase()) {
            "easy", "low", "good", "stable", "least concern" -> "#E8F5E9" to "#2E7D32"
            "moderate", "medium", "fair", "near threatened" -> "#FFF8E1" to "#F57F17"
            "hard", "difficult", "high", "poor", "endangered", "vulnerable", "critical" -> "#FFEBEE" to "#C62828"
            else -> "#E3F2FD" to "#1565C0"
        }
    }

    private fun addLabelAndValue(
        label: String,
        value: String,
        isTitle: Boolean = false,
        isItalic: Boolean = false,
        topMargin: Int = 0
    ) {
        val labelView = TextView(this).apply {
            text = label
            textSize = 16f
            setTextColor(Color.parseColor("#2196F3"))
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { this.topMargin = topMargin }
        }
        binding.container.addView(labelView)

        val valueView = TextView(this).apply {
            text = value
            textSize = if (isTitle) 24f else 16f
            setTextColor(Color.parseColor("#212121"))
            if (isTitle) setTypeface(null, Typeface.BOLD)
            if (isItalic) setTypeface(null, Typeface.ITALIC)
        }
        binding.container.addView(valueView)
    }

    private fun dpToPx(dp: Int): Int {
        return (dp * resources.displayMetrics.density).toInt()
    }
}
