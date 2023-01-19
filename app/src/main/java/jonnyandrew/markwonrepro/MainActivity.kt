package jonnyandrew.markwonrepro

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.noties.markwon.Markwon
import io.noties.markwon.html.HtmlPlugin
import jonnyandrew.markwonrepro.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var views : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        views = ActivityMainBinding.inflate(layoutInflater)
        setContentView(views.root)

        workingExample()
        failingExample()
    }

    private fun workingExample() {
        val markwon = Markwon.builder(this)
            .usePlugin(HtmlPlugin.create())
            .build()

        val node = markwon.parse(
            "Here is some working <code>inline code</code>."
        )

        val spanned = markwon.render(node)

        markwon.setParsedMarkdown(views.workingExampleText, spanned)
    }

    private fun failingExample() {
        val markwon = Markwon.builder(this)
            .usePlugin(HtmlPlugin.create())
            .build()

        // Parse a block node
        val blocknode = markwon.parse("A <div>block</div>")
        markwon.render(blocknode)

        val node = markwon.parse(
                "Here is some failing <code>inline code</code>."
        )

        val spanned = markwon.render(node)

        markwon.setParsedMarkdown(views.failingExampleText, spanned)
    }
}