package jonnyandrew.markwonrepro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import io.noties.markwon.Markwon
import io.noties.markwon.html.HtmlPlugin

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setInlineCodeFailing()
        setInlineCodeWorking()
    }

    private fun setInlineCodeWorking() {
        val markwon = Markwon.builder(this)
            .usePlugin(HtmlPlugin.create())
            .build()

        val node = markwon.parse(
            "Here is some working <code>inline code</code>."
        )

        val spanned = markwon.render(node)

        val text1 = findViewById<TextView>(R.id.text1)
        markwon.setParsedMarkdown(text1, spanned)
    }

    private fun setInlineCodeFailing() {
        val markwon = Markwon.builder(this)
            .usePlugin(HtmlPlugin.create())
            .build()

        val node = markwon.parse(
                "A <div>block</div>Here is some failing <code>inline code</code>."
        )

        val spanned = markwon.render(node)

        val text2 = findViewById<TextView>(R.id.text2)
        markwon.setParsedMarkdown(text2, spanned)
    }
}